package AnalizadorLexico;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;

public class AFD 
{
    public static HashMap tablaSimbolos = new HashMap<String, Integer>();

    public enum Estados{Inicial, AsignacionR, ConstanteNumerica, Cadena, PalabraReservada, Comparacion, Asignacion,
                        AbrePar, CierraPar, AbreLlave, CierraLlave, PuntoComa, DosPuntos, Coma, Negacion, Suma, Final}
    public static Estados estadoactual = Estados.Inicial;

    public static int numeroSimbolos = 0;

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
        boolean fin = false;
        while (i < palabra.length() && !fin)        //TODO pensar en como tratar el ultimo caracter de la linea
        {
            c = palabra.charAt(i);
            if (!(estadoactual == Estados.Inicial))
                i++;
            switch (estadoactual) {
                case Inicial: // Estado S
                    estadoactual = nextStage(c);
                    if (estadoactual == Estados.Inicial)
                        i++;
                    break;
                case PalabraReservada: // Estado T
                    lex += String.valueOf(c);
                    if (Token.TPR.contains(lex) && i == palabra.length()) //TODO pensar como implementar la condicion de que el sig caracter sea del, sin salir del index del la linea
                    {
                        Token.genToken("palabraReservada", lex, fd);
                        lex = "";
                        estadoactual = Estados.Final;
                    }
                    else if (Token.isDel(c) || i == palabra.length())
                    {
                        if (!tablaSimbolos.containsKey(lex))
                        {
                            tablaSimbolos.put(lex, numeroSimbolos);
                            Token.genToken("id", lex, fd);
                            numeroSimbolos++;
                            lex = "";
                        }
                    }
                    break;

                case Cadena:
                    if (((int) c == 0 || c == '"') && counter <= 64 && lex != "") {
                        Token.genToken("cadena", lex, fd);
                        lex = "";
                        counter = 0;
                        estadoactual = Estados.Final;
                    } else {
                        if (c != '"')
                            lex += (String.valueOf(c));
                        counter++;
                        estadoactual = Estados.Cadena;
                    }
                    break;

                case ConstanteNumerica:
                    if (Character.isDigit(c) && i < palabra.length() && Character.isDigit(palabra.charAt(i)))
                        counter = (Character.getNumericValue(c) + counter) * 10;
                    else if (counter > 32767) {
                        System.out.println("Error, valor numerico excede los limites");
                    } else
                        counter = (Character.getNumericValue(c) + counter);
                    if (!Character.isDigit(c) || i == palabra.length() && counter <= 32767) {
                        Token.genToken("constEnt", Integer.toString(counter), fd);
                        counter = 0;
                            estadoactual = Estados.Final;
                    }
                    break;

                case AsignacionR:
                    if (palabra.charAt(i) == '=') {
                        Token.genToken("asignacionResto", "-", fd);
                        estadoactual = Estados.Final;
                        i++;
                    } else
                        System.out.println("Error: simbolo no reconocido");
                    break;

                case Asignacion:
                    if (palabra.charAt(i) != '=') {
                        Token.genToken("igual", "-", fd);
                        estadoactual = Estados.Final;
                    } else
                        estadoactual = Estados.Comparacion;
                    break;

                case Comparacion:
                    Token.genToken("comparacion", "-", fd);
                    estadoactual = Estados.Final;
                    break;

                case AbreLlave:
                    Token.genToken("abreLlave", "-", fd);
                    estadoactual = Estados.Final;
                    break;

                case CierraLlave:
                    Token.genToken("cierraLlave", "-", fd);
                    estadoactual = Estados.Final;
                    break;

                case AbrePar:
                    Token.genToken("abrePar", "-", fd);
                    estadoactual = Estados.Final;
                    break;

                case CierraPar:
                    Token.genToken("cierraPar", "-", fd);
                    estadoactual = Estados.Final;
                    break;

                case DosPuntos:
                    Token.genToken("dosPuntos", "-", fd);
                    estadoactual = Estados.Final;
                    break;

                case PuntoComa:
                    Token.genToken("puntoComa", "-", fd);
                    estadoactual = Estados.Final;
                    break;

                case Coma:
                    Token.genToken("coma", "-", fd);
                    estadoactual = Estados.Final;
                    break;

                case Negacion:
                    Token.genToken("negacion", "-", fd);
                    estadoactual = Estados.Final;
                    break;

                case Suma:
                    Token.genToken("suma", "-", fd);
                    estadoactual = Estados.Final;
                    break;

                case Final:
                    fin = true;
                    break;
            }
        }
        return i;
    }
/*
    public static void main(String[] args) throws FileNotFoundException {
        Token.initializeTPR();
        fd = new PrintWriter("./tests/tokens.txt");
        System.out.println(automata("12333", fd));
        fd.close();
    }
*/
}
