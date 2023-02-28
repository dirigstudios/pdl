import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import AnalizadorLexico.*;
import AnalizadorSintactico.*;
import AnalizadorSemantico.*;

public class Main
{
    final static boolean DEBUG = true;

    public static void main(String[] args) throws IOException 
    {
        // Token tk = new Token("","");
        if (args.length != 1)
        {
            System.out.println("Uso incorrecto, numero de argumentos erroneo.\n uso: Main: direccion archivo fuente");
            return;
        }
        File fdFuente;
        if (!DEBUG)
            fdFuente = new File(args[0]);
        else
            fdFuente = new File("./tests/ejemplo");
        System.out.println("Attempting to read from file in: " + fdFuente.getCanonicalPath());
        FileReader fuente = new FileReader(fdFuente);
        PrintWriter fichToken = new PrintWriter("tokens.txt");
        PrintWriter fichTS = new PrintWriter("ts.txt");
        PrintWriter fichParser = new PrintWriter("parser.txt");
        PrintWriter fichGCI = new PrintWriter("gci.txt");
        Token.initializeTPR();
        // while (!tk.getTipo().equals("$"))
        //    tk = AnLex.getNextToken(fuente, fichToken, fichTS);
        AnSt.algorithmAnSt(fuente, fichToken, fichTS, fichParser, fichGCI);
        fichTS.close();
        fuente.close();
        fichToken.close();
        fichParser.close();
    }
}