package AnalizadorLexico;

import java.io.PrintWriter;

public class AFD 
{
    public static TablaSimbolos tablaSimbolos = new TablaSimbolos();

    public enum Estados{Inicial, AsignacionR, ConstanteNumerica, Cadena, PalabraReservada, Asignacion,
                        AbrePar, CierraPar, AbreLlave, CierraLlave, PuntoComa, DosPuntos, Coma, Negacion, Suma}
    public static Estados estadoactual = Estados.Inicial;

    public static boolean leerSigCaracter = true;

    public static PrintWriter fd;


    public static Estados nextStage(char c) //TODO ADD IMPLEMENTATION FOR UPPERCASE CHARACTERS and variables that start with _
    {
        if (Character.isLowerCase(c) || c == '_')
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
        return Estados.Inicial;
    }

    public static int automata(String palabra, PrintWriter fd) {
        int i = 0;
        char c = 0;
        String lex = "";
        int counter = 0;

        c = palabra.charAt(i);
        estadoactual = nextStage(c);

        switch (estadoactual)
        {
            case Inicial: // Estado S
                i++;
                break;

            case PalabraReservada:
                while (i != palabra.length()
                        && (Character.isDigit(palabra.charAt(i))
                        || Character.isAlphabetic(palabra.charAt(i))
                        || palabra.charAt(i) == '_'))
                {
                    lex += String.valueOf(palabra.charAt(i));
                    i++;
                }
                if (Token.TPR.contains(lex))
                    Token.genToken("palabraReservada", lex, fd);
                else if (!tablaSimbolos.containsKey(lex))
                {
                    tablaSimbolos.put(lex, tablaSimbolos.size());
                    Token.genToken("id", String.valueOf(tablaSimbolos.size()), fd);
                }
                else
                    Token.genToken("id", String.valueOf(tablaSimbolos.get(lex)), fd);
                break;

            case Cadena:
                i++;
                while (i != palabra.length() && palabra.charAt(i) != '"' && i < 66)
                {
                    lex += String.valueOf(palabra.charAt(i));
                    i++;
                }
                if (palabra.charAt(i) == '"')
                    Token.genToken("cadena", lex, fd);
                else
                    System.out.println("Error : La string esta mal formada");
                i++;
                break;

            case ConstanteNumerica:
                while (i < palabra.length() && Character.isDigit(palabra.charAt(i)))
                {
                    counter *= 10;
                    counter += Character.getNumericValue(palabra.charAt(i));
                    i++;
                }
                if (counter > 32767)
                    System.out.println("Error, el valor numerico introducido excede el limite de 32767");
                Token.genToken("constEnt", Integer.toString(counter), fd);
                break;

            case AsignacionR:
                if (i != (palabra.length() - 1) && palabra.charAt(i + 1) == '=')
                {
                    Token.genToken("asignacionResto", "", fd);
                    i++;
                }
                else
                    System.out.println("Error: Expected '%='");
                i++;
                break;

            case Asignacion:
                if (i == (palabra.length() - 1) || palabra.charAt(i + 1) != '=')
                    Token.genToken("igual", "", fd);
                else
                {
                    Token.genToken("comparacion", "", fd);
                    i++;
                }
                i++;
                break;

            case AbreLlave:
                Token.genToken("abreLlave", "", fd);
                i++;
                break;

            case CierraLlave:
                Token.genToken("cierraLlave", "", fd);
                i++;
                break;

            case AbrePar:
                Token.genToken("abrePar", "", fd);
                i++;
                break;

            case CierraPar:
                Token.genToken("cierraPar", "", fd);
                i++;
                break;

            case DosPuntos:
                Token.genToken("dosPuntos", "", fd);
                i++;
                break;

            case PuntoComa:
                Token.genToken("puntoComa", "", fd);
                i++;
                break;

            case Coma:
                Token.genToken("coma", "", fd);
                i++;
                break;

            case Negacion:
                Token.genToken("negacion", "", fd);
                i++;
                break;

            case Suma:
                Token.genToken("suma", "", fd);
                i++;
                break;
        }
        return i;
    }
}
