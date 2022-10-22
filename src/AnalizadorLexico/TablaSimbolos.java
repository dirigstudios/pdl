package AnalizadorLexico;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;

public class TablaSimbolos 
{
    public static HashMap<String, Integer> tablaSimbolos;
    public static int idTabla = 0;

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

    public void printTS(PrintWriter ts)
    {
        int i = 0;
        String table;
        Set<String> ids;
        ts.println("#" + idTabla + ":");
        ids = tablaSimbolos.keySet();
        for(String s : ids)
        {
            ts.println("    * lexema: '" + s + "'");
        }
    }
}