package AnalizadorLexico;

import AnalizadorSintactico.AnSt;

import java.io.PrintWriter;

public class AFD 
{
    public enum Estados{Inicial, AsignacionR, ConstanteNumerica, Cadena, PalabraReservada, Asignacion,
                        AbrePar, CierraPar, AbreLlave, CierraLlave, PuntoComa, DosPuntos, Coma, Negacion, Suma, Comentario}
    public static Estados estadoactual = Estados.Inicial;
    public static String lex;
    public static int counter;
    private static final int MAX_NUMBER_OF_TS = 100;
    public static int[] numberId = new int[MAX_NUMBER_OF_TS];
    public static boolean isComment = false;


    public static Estados nextStage(char c)
    {
        if (Character.isAlphabetic(c) || c == '_')
            return Estados.PalabraReservada;
        if (c == '\"')
            return Estados.Cadena;
        if (Character.isDigit(c))
            return Estados.ConstanteNumerica;
        if (c == '%')
            return Estados.AsignacionR;
        if (c == '=')
            return Estados.Asignacion;
        if (c == '{')
            return Estados.AbreLlave;
        if (c == '}')
            return Estados.CierraLlave;
        if (c == '(')
            return Estados.AbrePar;
        if (c == ')')
            return Estados.CierraPar;
        if (c == ':')
            return Estados.DosPuntos;
        if (c == ';')
            return Estados.PuntoComa;
        if (c == ',')
            return Estados.Coma;
        if (c == '!')
            return Estados.Negacion;
        if (c == '+')
            return Estados.Suma;
        if (c == '/')
            return Estados.Comentario;
        return Estados.Inicial;
    }

    public static Token automata(char c, PrintWriter fd, TablaSimbolos tablaSimbolos, TablaSimbolos tsLocal ,AnSt.Lines lines, AnSt.Zona_decl zona_decl)
    {
        Token tk;
        
        if (!isComment && estadoactual == Estados.Inicial)
        {
            lex = "";
            estadoactual = nextStage(c);
        }
        if (c == '\n')
        {
            lines.addLine();
            //System.out.println(lines.toString());
        }
        switch (estadoactual)
        {
            case Inicial:
                break;

            case PalabraReservada:
                // Errores
                if (lex.length() > 0 && Character.isDigit(lex.charAt(0)))
                {
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error léxico: inicio de nombre de variable invalido.");
                    return new Token("$", "");
                }
                // Generar Token Palabra reservada
                if (Token.TPR.contains(lex) && !Character.isAlphabetic(c) && !Character.isDigit(c) && c != '_')
                {
                    Token.genToken("palabraReservada", lex, fd);
                    tk = new Token("palabraReservada", lex);
                    estadoactual = Estados.Inicial;
                    return tk;
                }
                // Generar id en tabla de simbolos
                else if (!Character.isDigit(c) && !Character.isAlphabetic(c) && c != '_')
                {
                    if (tsLocal != null)
                    {
                        // id no declarado y zona_decl valida -> se genera el id en la ts local
                        if (!tsLocal.containsKey(lex) && zona_decl.zona_decl)
                        {
                            numberId[tsLocal.idTabla] = tsLocal.size() + 1;
                            tsLocal.put(lex, numberId[tsLocal.idTabla]);
                            Token.genToken("id", tsLocal.idTabla + "." + numberId[tsLocal.idTabla], fd);
                            tk = new Token("id", tsLocal.idTabla + "." + numberId[tsLocal.idTabla]);
                            estadoactual = Estados.Inicial;
                            return tk;
                        }
                        // id ya generado en tabla global o local, y zona_decl habilitada, generamos id, pero con errror al ya estar declarado
                        else if ((tablaSimbolos.containsKey(lex) || tsLocal.containsKey(lex)) && zona_decl.zona_decl)
                        {
                            if (tsLocal.containsKey(lex))
                            {
                                Token.genToken("id", tsLocal.buscaLugarTS(lex), fd);
                                tk = new Token("id", tsLocal.buscaLugarTS(lex));
                            }
                            else
                            {
                                Token.genToken("id", tablaSimbolos.buscaLugarTS(lex), fd);
                                tk = new Token("id", tablaSimbolos.buscaLugarTS(lex));
                            }
                            System.out.println("Error en linea: " + lines.toString() + " -> " + "Error léxico: Variable: "+ lex +" ya declarada\n");
                            estadoactual = Estados.Inicial;
                            return tk;
                        }
                        //  zona_decl falsa, por lo que generamos el id
                        else
                        {
                            if (tsLocal.containsKey(lex))
                            {
                                Token.genToken("id", tsLocal.buscaLugarTS(lex), fd);
                                tk = new Token("id", tsLocal.buscaLugarTS(lex));
                            }
                            else if (tablaSimbolos.containsKey(lex))
                            {
                                Token.genToken("id", tablaSimbolos.buscaLugarTS(lex), fd);
                                tk = new Token("id", tablaSimbolos.buscaLugarTS(lex));
                            }
                            else
                            {
                                numberId[tablaSimbolos.idLocal] = tablaSimbolos.size() + 1;
                                tablaSimbolos.put(lex, numberId[tablaSimbolos.idTabla]);
                                Token.genToken("id", tablaSimbolos.idTabla + "." + numberId[tablaSimbolos.idTabla], fd);
                                tk = new Token("id", tablaSimbolos.idTabla + "." + numberId[tablaSimbolos.idTabla]);
                            }
                            estadoactual = Estados.Inicial;
                            return tk;
                        }
                    }
                    else
                    {
                        // no contenido en la tabla de simbolos global, y zona decl == true, generamos el simbolo
                        if (!tablaSimbolos.containsKey(lex) && zona_decl.zona_decl)
                        {
                            numberId[tablaSimbolos.idLocal] = tablaSimbolos.size() + 1;
                            tablaSimbolos.put(lex, numberId[tablaSimbolos.idLocal]);
                            Token.genToken("id", "0." + numberId[tablaSimbolos.idLocal], fd);
                            tk = new Token("id", "0." + numberId[tablaSimbolos.idLocal]);
                            estadoactual = Estados.Inicial;
                            return tk;
                        }
                        // id ya declarado, y zona_decl == true, error de variable ya declarada
                        else if (tablaSimbolos.containsKey(lex) && zona_decl.zona_decl)
                        {
                            Token.genToken("id", tablaSimbolos.buscaLugarTS(lex), fd);
                            tk = new Token("id", tablaSimbolos.buscaLugarTS(lex));
                            System.out.println("Error en linea: " + lines.toString() + " -> " + "Error léxico: Variable: "+ lex +" ya declarada\n");
                            estadoactual = Estados.Inicial;
                            return tk;
                        }
                        // variable no declarada, y zona_Decl == false, agregamos la variable a la tablaGlobal
                        else if (!tablaSimbolos.containsKey(lex) && !zona_decl.zona_decl)
                        {
                            numberId[tablaSimbolos.idLocal] = tablaSimbolos.size() + 1;
                            tablaSimbolos.put(lex, numberId[tablaSimbolos.idLocal]);
                            Token.genToken("id", "0." + numberId[tablaSimbolos.idLocal], fd);
                            tk = new Token("id", "0." + numberId[tablaSimbolos.idLocal]);
                            estadoactual = Estados.Inicial;
                            return tk;
                        }
                        // variable ya declarada y zona_decl == false, generamos token id
                        else
                        {
                            Token.genToken("id", tablaSimbolos.buscaLugarTS(lex), fd);
                            tk = new Token("id", tablaSimbolos.buscaLugarTS(lex));
                            estadoactual = Estados.Inicial;
                            return tk;
                        }
                    }
                }
                break;

            case Cadena:
                if (lex.length() >= 66)
                {
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error léxico: Cadena sobrepasa los 64 caracteres.");
                    return new Token("$", "");
                }
                if (c == '"' && lex.length() >= 1)
                {
                    lex += String.valueOf(c);
                    Token.genToken("cadena", lex, fd);
                    tk = new Token("cadena", lex);
                    estadoactual = Estados.Inicial;
                    return tk;
                }
                //else if
                //System.out.println("Error : La string esta mal formada");
                break;

            case Comentario:
                if (!isComment && lex.length() >= 1 && c != '*')
                {
                    System.err.println("Error en linea: " + lines.toString() + " -> " + "Error lexico: Fin de comentario no válido.");
                    return new Token("$", "");
                }
                else if (!isComment && lex.length() >= 1)
                {
                    isComment = true;
                    lex = "";
                }
                else if (c == '*')
                    lex = "*";
                if (lex.equals("*") && c == '/')
                {
                    estadoactual = Estados.Inicial;
                    isComment = false;
                }
                else if (isComment && lex.length() > 1)
                    lex = "";
                break;

            case ConstanteNumerica:
                if (Character.isDigit(c))
                {
                    counter *= 10;
                    counter += Character.getNumericValue(c);
                }
                if (counter > 32767)
                {
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error lexico, el valor numerico" +
                            " excede el limite de 32767");
                    return new Token("$", "");
                }
                else if (!Character.isDigit(c))
                {
                    Token.genToken("constEnt", Integer.toString(counter), fd);
                    tk = new Token("constEnt", Integer.toString(counter));
                    counter = 0;
                    estadoactual = nextStage(c);
                    lex = String.valueOf(c);
                    return tk;
                }
                break;

            case AsignacionR:
                if (lex.equals("%") && c == '=')
                {
                    Token.genToken("asignacionResto","" , fd);
                    tk = new Token("asignacionResto", "");
                    estadoactual = Estados.Inicial;
                    return tk;
                }
                else if (lex.length() > 0)
                {
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error lexico: caracter % no reconocido, se espera: %=");
                    return new Token("$", "");
                }

            case Asignacion:
                if (lex.equals("=") && c != '=')
                {
                    Token.genToken("igual", "", fd);
                    estadoactual = nextStage(c);
                    lex = "";
                    return new Token("igual", "");
                }
                else if (lex.equals("="))
                {
                    Token.genToken("comparacion", "", fd);
                    estadoactual = Estados.Inicial;
                    return new Token("comparacion", "");
                }
                break;

            case AbreLlave:
                Token.genToken("abreLlave", "", fd);
                estadoactual = Estados.Inicial;
                return new Token("abreLlave", "");

            case CierraLlave:
                Token.genToken("cierraLlave", "", fd);
                estadoactual = Estados.Inicial;
                return new Token("cierraLlave", "");

            case AbrePar:
                Token.genToken("abrePar", "", fd);
                estadoactual = Estados.Inicial;
                return new Token("abrePar", "");

            case CierraPar:
                Token.genToken("cierraPar", "", fd);
                estadoactual = Estados.Inicial;
                return new Token("cierraPar", "");

            case DosPuntos:
                Token.genToken("dosPuntos", "", fd);
                estadoactual = Estados.Inicial;
                return new Token("dosPuntos", "");

            case PuntoComa:
                Token.genToken("puntoComa", "", fd);
                estadoactual = Estados.Inicial;
                return new Token("puntoComa", "");

            case Coma:
                Token.genToken("coma", "", fd);
                estadoactual = Estados.Inicial;
                return new Token("coma", "");

            case Negacion:
                Token.genToken("negacion", "", fd);
                estadoactual = Estados.Inicial;
                return new Token("negacion", "");

            case Suma:
                Token.genToken("suma", "", fd);
                estadoactual = Estados.Inicial;
                return new Token("suma", "");
        }

        if ((!Token.isDel(c) || estadoactual == Estados.Cadena) && !isComment)
            lex += String.valueOf(c);
        return null;
    }
}
