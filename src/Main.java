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
    public static void main(String[] args) throws IOException 
    {
        // Token tk = new Token("","");
        if (args.length != 1)
        {
            System.out.println("Uso incorrecto, numero de argumentos erroneo.\n uso: Main: direccion archivo fuente");
            return;
        }
        File fdFuente = new File(args[0]);
//        File fdFuente = new File("./tests/ejemplo");
        System.out.println("Attempting to read from file in: " + fdFuente.getCanonicalPath());
        FileReader fuente = new FileReader(fdFuente);
        PrintWriter fichToken = new PrintWriter("tokens.txt");
        PrintWriter fichTS = new PrintWriter("ts.txt");
        PrintWriter fichParser = new PrintWriter("parser.txt");
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