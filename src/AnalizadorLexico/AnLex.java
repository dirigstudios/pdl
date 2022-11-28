package AnalizadorLexico;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;

public class AnLex 
{
    public static TablaSimbolos tablaSimbolos = new TablaSimbolos();

    public static boolean leerSigChar = true;
    public static char character;

    public static Token getNextToken(FileReader fuente, PrintWriter salidaTokens, PrintWriter salidaTS) throws IOException
    {
        Token tk;
        int c;
        
        if (!leerSigChar)
        {
            tk = AFD.automata(character, salidaTokens, tablaSimbolos);
            if (tk != null)
            {
                leerSigChar = true;
                return tk;
            }
        }
        while((c = fuente.read()) != -1)
        {
            character = (char) c;
            tk = AFD.automata(character, salidaTokens, tablaSimbolos);
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
        tk = AFD.automata(' ', salidaTokens, tablaSimbolos);
        if (tk != null)
            return tk;
        tablaSimbolos.printTS(salidaTS);
        return new Token("$","");
    }
}