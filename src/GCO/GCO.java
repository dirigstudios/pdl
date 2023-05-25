package GCO;

import java.io.BufferedReader;
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
        int id;

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
                if (!esEntera(op1)) //el operador puede ser una constEnt
                {
                    id = getIdFromLugarInTS(op1);
                    e = tsL.get(id);
                    desp = e.getDesplazamiento();
                    op1 = "#" + (desp + id - 1) + "[.IX]";
                }
                else
                    op1 = "#" + op1;

                //obtengo la direccion de res
                id = getIdFromLugarInTS(res);
                e = tsL.get(id);
                desp = e.getDesplazamiento();
                res = "#" + (desp + id - 1) + "[.IX]"; //TODO: revisar si el desplazamiento es correcto -> lo cambia andrés
            }
            else
            {
                if (!esEntera(op1)) //el operador puede ser una constEnt
                {
                    id = getIdFromLugarInTS(op1);
                    e = tsG.get(id);
                    desp = e.getDesplazamiento();
                    op1 = "#" + (desp + id - 1) + "[.IY]";
                }
                else
                    op1 = "#" + op1;

                //obtengo la direccion de res
                id = getIdFromLugarInTS(res);
                e = tsG.get(id);
                desp = e.getDesplazamiento();
                res = "#" + (desp + id - 1) + "[.IY]"; //TODO: revisar si el desplazamiento es correcto -> lo cambia andrés
            }

            fichCO.println("MOVE " + op1 + ", " + res);
        }
        else if (op.equals("+")) //(+, op1, op2, res)
        {
            String op1 = tokenizer.nextToken();
            String op2 = tokenizer.nextToken();
            String res = tokenizer.nextToken();

            if (!local)
            {
                id = getIdFromLugarInTS(op1);
                e = tsL.get(id);
                desp = e.getDesplazamiento();
                op1 = "#" + (desp + id - 1) + "[.IX]";

                id = getIdFromLugarInTS(op2);
                e = tsL.get(id);
                desp = e.getDesplazamiento();
                op2 = "#" + (desp + id - 1) + "[.IX]";

                id = getIdFromLugarInTS(res);
                e = tsL.get(id);
                desp = e.getDesplazamiento();
                res = "#" + (desp + id - 1) + "[.IX]";
            }
            else
            {
                id = getIdFromLugarInTS(op1);
                e = tsG.get(id);
                desp = e.getDesplazamiento();
                op1 = "#" + (desp + id - 1) + "[.IY]";

                id = getIdFromLugarInTS(op2);
                e = tsG.get(id);
                desp = e.getDesplazamiento();
                op2 = "#" + (desp + id - 1) + "[.IY]";

                id = getIdFromLugarInTS(res);
                e = tsG.get(id);
                desp = e.getDesplazamiento();
                res = "#" + (desp + id - 1) + "[.IY]";
            }

            fichCO.println("ADD " + op1 + ", " + op2);
            fichCO.println("MOVE .A, " + res);
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
     * @throws IOException
     */
    public static void fichAppender(BufferedReader fichDE, BufferedReader fichCO, BufferedReader fichPila, PrintWriter fichObjeto) throws IOException
    {
        String linea;

        fichObjeto.println("MOVE #inicio_estaticas, .IY"); //
        fichObjeto.println("MOVE #inicio_pila, .IX"); //determino al inicio donde comienza la pila
        while ( (linea = fichDE.readLine()) != null)
            fichObjeto.println(linea);

        fichObjeto.println("");

        while ( (linea = fichCO.readLine()) != null)
            fichObjeto.println(linea);
        
        fichObjeto.println("");


        fichObjeto.println("inicio_estaticas: RES 200");

        fichObjeto.println("");

        fichObjeto.println("inicio_pila: NOP");
        while ( (linea = fichPila.readLine()) != null)
            fichObjeto.println(linea);
    }
}
