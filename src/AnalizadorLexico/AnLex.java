package AnalizadorLexico;

import java.io.PrintWriter;
import java.util.Scanner;

public class AnLex 
{
    public static void bucleGrande(Scanner fuente, PrintWriter salidaTokens)
    {
        Token.initializeTPR();
        String line;
        while(fuente.hasNextLine())
        {
            line = fuente.nextLine();
            AFD.automata(line, salidaTokens);
        }
    }
}