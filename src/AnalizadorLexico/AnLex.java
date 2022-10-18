package AnalizadorLexico;

import java.io.PrintWriter;
import java.util.Scanner;

public class AnLex 
{
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

    public static void getNext(Scanner fuente, PrintWriter salidaTokens)
    {
        String line;
        String newLine;

        if (fuente.hasNextLine())
        {
            line = fuente.nextLine();
            System.out.println(line.length());
            //quitar para la siguiente entrega
            while (pos + 1 < line.length())
            {
                newLine = generateLine(line);
                System.out.println(newLine);
                pos += AFD.automata(newLine, salidaTokens);
            }
        }
    }
}