package GCO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import AnalizadorLexico.TablaSimbolos;
import AnalizadorLexico.TablaSimbolos.Entrada;

public class GCO 
{
    /**
     * famoso "switch" que imprime para cada cuarteto su correspondiente instruccion objeto
     * @param instruccion
     * @param fichDE
     * @param fichCO
     * @param fichPila
     * @throws IOException
     */
    //asumo que cada instrucción de ci está bien formado: contiene exactamente 4 palabras
    public static void switchGCO(String instruccion, PrintWriter fichDE, PrintWriter fichCO, PrintWriter fichPila, TablaSimbolos tsG, TablaSimbolos tsL)
    {
        Entrada e;
        int desp;

        StringTokenizer tokenizer;
        String op;
        boolean local = (tsL == null); //para saber de donde extraer el lexema
            
        tokenizer = new StringTokenizer(instruccion, " ");

        op = tokenizer.nextToken();
        if (op.equals(":=")) //(:=, op1, NULL, res)
        {
            String op1 = tokenizer.nextToken();
            tokenizer.nextToken(); //me deshago del null
            String res = tokenizer.nextToken();

            if (!local)
            {
                //TODO: hacer direccionamiento relativo a pila IY
            }
            else
            {
                if (!esEntera(op1))
                {
                    e = tsG.get(getIdFromLugarInTS(op1));
                    desp = e.getDesplazamiento();
                    op1 = "#" + desp + "[.IX]";
                }

                if (!esEntera(res))
                {
                    e = tsG.get(getIdFromLugarInTS(res)); //TODO: VER PORQUE NO DEVUELVE LA ENTRADA DESEADA
                    desp = e.getDesplazamiento();
                    res = "#" + desp + "[.IX]";
                }
            }

            fichCO.println("MOVE " + op1 + ", " + res);
        }
    }

    /**
     * devuelve si el string que me han pasado es una constante entera
     * @param op
     * @return
     */
    private static boolean esEntera(String op)
    {
        try
        {
            Integer.parseInt(op);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }

    /**
     * @param lugar HA DE SER LO QUE DEVUELVE TS.GETLUGAR()
     * @return devuelve el "int id" de un id de la ts dado su getLugar
     * i.e. ts.getLugar("a") = 1.3 => int id = 3 (el id de la variable "a" en la ts 1)
     */
    private static int getIdFromLugarInTS(String lugar)
    {
        float tsDotId = Float.parseFloat(lugar);
        StringTokenizer tokenizer = new StringTokenizer(lugar, ".");
        int parteDecimal = Integer.parseInt(tokenizer.nextToken());
        boolean local = (parteDecimal != 0); //será local si el id de la ts no es 0 (0 == tsG)

        if (local)
            return (int)(tsDotId * 10) - (10 * parteDecimal); 
        return (int)(tsDotId * 10);
    }

    /**
     * junta los 3 ficheros generados por el GCO para obtener un fichero objeto único
     * @param fichDE
     * @param fichCO
     * @param fichPila
     */
    public static void fichAppender(PrintWriter fichDE, PrintWriter fichCO, PrintWriter fichPila)
    {
        //TODO: ver como juntar 3 ficheros en uno solo
    }
}
