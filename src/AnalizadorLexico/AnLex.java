package AnalizadorLexico;

import AnalizadorSintactico.AnSt;

import javax.sound.sampled.Line;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;

public class AnLex 
{

    public static boolean leerSigChar = true;
    public static char character;

    public static Token getNextToken(FileReader fuente, PrintWriter salidaTokens, PrintWriter salidaTS, TablaSimbolos tablaSimbolos, AnSt.Lines lines) throws IOException
    {
        Token tk;
        int c;
        
        if (!leerSigChar)
        {
            tk = AFD.automata(character, salidaTokens, tablaSimbolos, lines);
            if (tk != null)
            {
                leerSigChar = true;
                return tk;
            }
        }
        while((c = fuente.read()) != -1)
        {
            character = (char) c;
            tk = AFD.automata(character, salidaTokens, tablaSimbolos, lines);
            if (tk != null)
            {
                if (tk.getTipo().equals("palabraReservada") || tk.getTipo().equals("constEnt") ||
                        tk.getTipo().equals("id") || tk.getTipo().equals("igual"))
                    leerSigChar = false;
                else
                    leerSigChar = true;
                return tk;
            }
        }
        tk = AFD.automata(' ', salidaTokens, tablaSimbolos, lines);
        if (tk != null)
            return tk;
        return new Token("$","");
    }
}