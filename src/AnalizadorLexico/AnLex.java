package AnalizadorLexico;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileReader;

public class AnLex 
{
    public static TablaSimbolos tablaSimbolos = new TablaSimbolos();

    public static boolean leerSigChar = true;
    public static char character;

    public static Token getNextToken(FileReader fuente, PrintWriter salidaTokens, PrintWriter salidaTS) throws IOException
    {
        Token tk = null;
        int c = 0;
        if (!leerSigChar)
        {
            tk = AFD.automata(character, salidaTokens, tablaSimbolos);
            if (tk != null)
            {
                leerSigChar = true;
                return tk;
            }
        }
            while((c = fuente.read()) != -1)
            {
                character = (char) c;
                tk = AFD.automata(character, salidaTokens, tablaSimbolos);
                if (tk != null)
                {
                    if (tk.getTipo() == "palabraReservada" || tk.getTipo() == "constEnt" ||
                            tk.getTipo() == "id")
                        leerSigChar = false;
                    else
                        leerSigChar = true;
                    return tk;
                }
            }
            AFD.automata(' ', salidaTokens, tablaSimbolos);
            if (tk!=null)
                return tk;
            tablaSimbolos.printTS(salidaTS);
            return new Token("$","");
    }

//    public static Token getNext(Scanner fuente, PrintWriter salidaTokens, PrintWriter salidaTS)
//    {
//        String line = "";
//        String newLine;
//        Token tk = new Token("","");
//
//        // cambiar el if por un while en la siguiente entrega
//        while (fuente.hasNextLine())
//        {
//            //System.out.println(line.length());
//            if (pos >= line.length())
//            {
//                pos = 0;
//                line = fuente.nextLine();
//            }
//            newLine = generateLine(line);
//            System.out.println("linea:\n" + newLine);
//            pos += AFD.automata(newLine, salidaTokens, tablaSimbolos, tk);
//            return tk;
//        }
//        tablaSimbolos.printTS(salidaTS);
//        return tk;
//    }
}