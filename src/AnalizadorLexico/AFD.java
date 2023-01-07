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
    public static boolean leerSigCaracter = true;
    public static boolean isComment = false;
    //public static int lines = 1;


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

    public static Token automata(char c, PrintWriter fd, TablaSimbolos tablaSimbolos, AnSt.Lines lines)
    {
        Token tk;
        
        if (!isComment && estadoactual == Estados.Inicial)
        {
            lex = "";
            estadoactual = nextStage(c);
        }
        if (c == '\n')
            lines.addLine();
        switch (estadoactual)
        {
            case Inicial:
                break;

            case PalabraReservada:
                // Errores
                if (lex.length() > 0 && Character.isDigit(lex.charAt(0)))
                {
                    System.out.println("Error : inicio de nombre de variable invalido.");
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
                else if (!tablaSimbolos.containsKey(lex) && !Character.isDigit(c) && !Character.isAlphabetic(c) && c != '_')
                {
                    tablaSimbolos.put(lex);
                    Token.genToken("id", String.valueOf(tablaSimbolos.size() - 1), fd);
                    tk = new Token("id", String.valueOf(tablaSimbolos.size() - 1));
                    estadoactual = Estados.Inicial;
                    return tk;
                }
                // Recoger id de la tabla de simbolos
                else if (!Character.isDigit(c) && !Character.isAlphabetic(c) && c != '_')
                {
                    Token.genToken("id", String.valueOf(tablaSimbolos.tablaSimbolos.indexOf(tablaSimbolos.get(lex))), fd);
                    tk = new Token("id", String.valueOf(tablaSimbolos.tablaSimbolos.indexOf(tablaSimbolos.get(lex))));
                    estadoactual = Estados.Inicial;
                    return tk;
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
                    System.err.println("Error en linea: " + lines.toString() + " -> " + "Error lexico: Comentario mal formado, el fin debe" +
                            " tener la forma */");
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
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error lexico, el valor numerico introducido" +
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
