package AnalizadorLexico;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class AnLex 
{
    public void bucleGrande(Scanner fuente, PrintWriter salidaTokens)
    {
        while(fuente.hasNextLine())
        {
            String line;
            while(fuente.hasNextLine())
            {
                line = fuente.nextLine();
                pdl.automata(line, salidaTokens);
            }
        }
    }

    public static void main(String[] args) 
    {
        Scanner fuente = new Scanner("../tests/ejemplo");
        PrintWriter fichToken = new PrintWriter("./tests/tokens.txt");
        bucleGrande(fuente, fichToken);
    }
}
