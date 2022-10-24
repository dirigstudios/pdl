package AnalizadorLexico;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException 
    {
        File fdFuente = new File("tests/ejemplo");
        System.out.println("Attempting to read from file in: " + fdFuente.getCanonicalPath());
        Scanner fuente = new Scanner(fdFuente);
        PrintWriter fichToken = new PrintWriter("./tests/tokens.txt");
        PrintWriter fichTS = new PrintWriter("./tests/ts.txt");
        Token.initializeTPR();
        AnLex.getNext(fuente, fichToken, fichTS);
        fichTS.close();
        fuente.close();
        fichToken.close();
    }
}