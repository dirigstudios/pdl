import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import AnalizadorLexico.*;
import AnalizadorSintactico.*;
import GCO.GCO;

public class Main
{
    final static boolean DEBUG = false;

    public static void main(String[] args) throws IOException
    {
        // Token tk = new Token("","");
        File fdFuente;
        if (!DEBUG)
        {
            if (args.length != 1)
            {
                System.out.println("Uso incorrecto, numero de argumentos erroneo.\n uso: Main: direccion archivo fuente");
                return;
            }
            fdFuente = new File(args[0]);
        }
        else
            fdFuente = new File("./tests/ejemplo");
        System.out.println("Attempting to read from file in: " + fdFuente.getCanonicalPath());

        FileReader fuente = new FileReader(fdFuente);
        PrintWriter fichToken = new PrintWriter("./tests/tokens.txt");
        PrintWriter fichTS = new PrintWriter("./tests/ts.txt");
        PrintWriter fichParser = new PrintWriter("./tests/parser.txt");
        PrintWriter fichGCI = new PrintWriter("./tests/gci.txt");
        PrintWriter fichDE = new PrintWriter("./tests/de.txt");
        PrintWriter fichCO = new PrintWriter("./tests/co.txt");
        PrintWriter fichStrings = new PrintWriter("./tests/strings.txt");
        Token.initializeTPR();
        // while (!tk.getTipo().equals("$"))
        //    tk = AnLex.getNextToken(fuente, fichToken, fichTS);
        AnSt.algorithmAnSt(fuente, fichToken, fichTS, fichParser, fichGCI, fichDE, fichCO, fichStrings);
        fichDE.close();
        fichCO.close();
        fichStrings.close();
        fichTS.close();
        fuente.close();
        fichToken.close();
        fichParser.close();
        fichGCI.close();

        BufferedReader fDE = new BufferedReader(new FileReader("./tests/de.txt"));
        BufferedReader fCO = new BufferedReader(new FileReader("./tests/co.txt"));
        BufferedReader fStrings = new BufferedReader(new FileReader("./tests/strings.txt"));
        PrintWriter fObjeto = new PrintWriter("./tests/objeto.ens");
        GCO.fichAppender(fDE, fCO, fStrings, fObjeto);
        fDE.close();
        fCO.close();
        fStrings.close();
        fObjeto.close();
    }
}