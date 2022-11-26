import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import AnalizadorLexico.*;
import AnalizadorSintactico.*;


public class Main
{
    public static void main(String[] args) throws IOException 
    {
        // Token tk = new Token("","");
        File fdFuente = new File("./tests/ejemplo");
        System.out.println("Attempting to read from file in: " + fdFuente.getCanonicalPath());
        FileReader fuente = new FileReader(fdFuente);
        PrintWriter fichToken = new PrintWriter("./tests/tokens.txt");
        PrintWriter fichTS = new PrintWriter("./tests/ts.txt");
        PrintWriter fichParser = new PrintWriter("./tests/parser.txt");
        Token.initializeTPR();
        // while (!tk.getTipo().equals("$"))
        //    tk = AnLex.getNextToken(fuente, fichToken, fichTS);
        AnSt.algorithmAnSt(fuente, fichToken, fichTS, fichParser);
        fichTS.close();
        fuente.close();
        fichToken.close();
        fichParser.close();
    }
}