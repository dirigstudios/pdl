package AnalizadorLexico;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AnLex 
{
    public static void bucleGrande(Scanner fuente, PrintWriter salidaTokens)
    {
        pdl.initializeTPR();
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

    public static void main(String[] args) throws FileNotFoundException {
        Scanner fuente = new Scanner("../tests/ejemplo");
        PrintWriter fichToken = new PrintWriter("./tests/tokens.txt");
        bucleGrande(fuente, fichToken);
        fuente.close();
        fichToken.close();
    }
}
