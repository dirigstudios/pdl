package AnalizadorLexico;

import java.io.PrintWriter;
import java.util.Scanner;

public class AnLex 
{
    public static TablaSimbolos tablaSimbolos = new TablaSimbolos();

    public static int pos = 0;

    public static String generateLine(String str)
    {
        StringBuilder newLine = new StringBuilder();
        int i;

        i = pos;
        while (i < str.length()) 
        {
            newLine.append(str.charAt(i));
            i++;
        }
        return newLine.toString();
    }

    public static void getNext(Scanner fuente, PrintWriter salidaTokens, PrintWriter salidaTS)
    {
        String line;
        String newLine;

        // cambiar el if por un while en la siguiente entrega
        while (fuente.hasNextLine())
        {
            line = fuente.nextLine();
            System.out.println(line.length());
            while (pos < line.length())
            {
                newLine = generateLine(line);
                System.out.println(newLine);
                pos += AFD.automata(newLine, salidaTokens, tablaSimbolos);
            }
            pos = 0;
        }
        tablaSimbolos.printTS(salidaTS);
    }
}