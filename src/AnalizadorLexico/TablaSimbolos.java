package AnalizadorLexico;

import java.io.PrintWriter;
import java.util.HashMap;

public class TablaSimbolos 
{
    public static HashMap<String, Integer> tablaSimbolos;
    public static int numeroSimbolos = 0;
    PrintWriter ficheroTS;

    public TablaSimbolos()
    {
        tablaSimbolos = new HashMap<String, Integer>();
    }

    public boolean containsKey(String str)
    {
        return tablaSimbolos.containsKey(str);
    }

    public Integer put(String k, Integer v)
    {
        return tablaSimbolos.put(k, v);
    }

    public Integer get(String str)
    {
        return tablaSimbolos.get(str);
    }

    public int size()
    {
        return tablaSimbolos.size();
    }

    public void printTS(PrintWriter fd)
    {
        
    }
}