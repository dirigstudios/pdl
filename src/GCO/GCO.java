package GCO;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import AnalizadorLexico.TablaSimbolos;
import AnalizadorSintactico.TablaM;

public class GCO 
{
    private static HashMap<String, PrintWriter> functionFiles = new HashMap<>();

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
        int desp;
        int id;
        PrintWriter oldPrint = fichCO;

        StringTokenizer tokenizer;
        String op;
        boolean hayLocal = (tsL != null); //para saber de donde extraer el lexema
        TablaSimbolos ts;
        if (hayLocal) {
            fichCO = functionFiles.get(tsG.get(tsL.getIdTabla()).getLexema());
            if (fichCO == null) {
                try {
                    PrintWriter newFile = new PrintWriter("./tests/functions/" + tsG.get(tsL.getIdTabla()).getLexema());
                    functionFiles.put(tsG.get(tsL.getIdTabla()).getLexema(), newFile);
                    fichCO = newFile;
                    fichCO.println();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        tokenizer = new StringTokenizer(instruccion, " ");

        op = tokenizer.nextToken();
        if (op.equals(":=")) //(:=, op1, NULL, res)
        {
            String op1 = tokenizer.nextToken();
            tokenizer.nextToken(); //me deshago del null
            String res = tokenizer.nextToken();

            if (hayLocal)
            {
                if (!esEntera(op1)) //el operador puede ser una constEnt
                {
                    id = getIdFromLugarInTS(op1);

                    if (esLocal(op1))
                    {
                        desp = tsL.getDesplazamiento(id) + 1; //sumo el espacio del E.M. //TODO: sumar el nº de parámetros de la func
                        op1 = "#" + desp + "[.IX]"; //IX = RA
                    }
                    else
                    {
                        desp = tsG.getDesplazamiento(id);
                        op1 = "#" + desp + "[.IY]"; //IY = zona de Datos Estáticos
                    }
                }
                else
                    op1 = "#" + op1;

                //obtengo la direccion de res
                if (esLocal(res))
                    ts = tsL;
                else
                    ts = tsG;
                id = getIdFromLugarInTS(res);
                desp = ts.getDesplazamiento(id);
                res = "#" + desp + "[.IX]";
            }
            else
            {
                if (!esEntera(op1)) //el operador puede ser una constEnt
                {
                    id = getIdFromLugarInTS(op1);
                    desp = tsG.getDesplazamiento(id);
                    op1 = "#" + desp + "[.IY]";
                }
                else
                    op1 = "#" + op1;

                //obtengo la direccion de res
                if (esLocal(res))
                    ts = tsL;
                else
                    ts = tsG;
                id = getIdFromLugarInTS(res);
                desp = ts.getDesplazamiento(id);
                res = "#" + desp + "[.IY]";
            }

            fichCO.println("\t\tMOVE " + op1 + ", " + res);
        }
        else if (op.equals("+")) //(+, op1, op2, res) -> todos los ops son direcciones (no hay constEnt)
        {
            String op1 = tokenizer.nextToken();
            String op2 = tokenizer.nextToken();
            String res = tokenizer.nextToken();

            if (hayLocal)
            {
                id = getIdFromLugarInTS(op1);
                if (esLocal(op1))
                {
                    desp = tsL.getDesplazamiento(id) + 1; //sumo el espacio del E.M. //TODO: sumar el nº de parámetros de la func
                    op1 = "#" + desp + "[.IX]"; //IX = RA
                }
                else
                {
                    desp = tsG.getDesplazamiento(id);
                    op1 = "#" + desp + "[.IY]"; //IY = zona de Datos Estáticos
                }


                id = getIdFromLugarInTS(op2);
                if (esLocal(op2))
                {
                    desp = tsL.getDesplazamiento(id) + 1; //sumo el espacio del E.M. //TODO: sumar el nº de parámetros de la func
                    op2 = "#" + desp + "[.IX]"; //IX = RA
                }
                else
                {
                    desp = tsG.getDesplazamiento(id);
                    op2 = "#" + desp + "[.IY]"; //IY = zona de Datos Estáticos
                }


                id = getIdFromLugarInTS(res);
                if (esLocal(res))
                {
                    desp = tsL.getDesplazamiento(id) + 1; //sumo el espacio del E.M. //TODO: sumar el nº de parámetros de la func
                    res = "#" + desp + "[.IX]"; //IX = RA
                }
                else
                {
                    desp = tsG.getDesplazamiento(id);
                    res = "#" + desp + "[.IY]"; //IY = zona de Datos Estáticos
                }
            }
            else
            {
                id = getIdFromLugarInTS(op1);
                desp = tsG.getDesplazamiento(id);
                op1 = "#" + desp + "[.IY]";

                id = getIdFromLugarInTS(op2);
                desp = tsG.getDesplazamiento(id);
                op2 = "#" + desp + "[.IY]";

                id = getIdFromLugarInTS(res);
                desp = tsG.getDesplazamiento(id);
                res = "#" + desp + "[.IY]";
            }

            fichCO.println("\t\tADD " + op1 + ", " + op2);
            fichCO.println("\t\tMOVE .A, " + res);
        }
        else if (op.equals(":"))
        {
            fichCO.println(tokenizer.nextToken() + ":");
        }
        else if (op.equals("%")) //(+, op1, op2, res) -> todos los ops son direcciones (no hay constEnt)
        {
            String op1 = tokenizer.nextToken();
            String op2 = tokenizer.nextToken();
            String res = tokenizer.nextToken();

            if (hayLocal) {
                id = getIdFromLugarInTS(op1);
                if (esLocal(op1)) {
                    desp = tsL.getDesplazamiento(id) + 1; //sumo el espacio del E.M. //TODO: sumar el nº de parámetros de la func
                    op1 = "#" + desp + "[.IX]"; //IX = RA
                } else {
                    desp = tsG.getDesplazamiento(id);
                    op1 = "#" + desp + "[.IY]"; //IY = zona de Datos Estáticos
                }


                id = getIdFromLugarInTS(op2);
                if (esLocal(op2)) {
                    desp = tsL.getDesplazamiento(id) + 1; //sumo el espacio del E.M. //TODO: sumar el nº de parámetros de la func
                    op2 = "#" + desp + "[.IX]"; //IX = RA
                } else {
                    desp = tsG.getDesplazamiento(id);
                    op2 = "#" + desp + "[.IY]"; //IY = zona de Datos Estáticos
                }


                id = getIdFromLugarInTS(res);
                if (esLocal(res)) {
                    desp = tsL.getDesplazamiento(id) + 1; //sumo el espacio del E.M. //TODO: sumar el nº de parámetros de la func
                    res = "#" + desp + "[.IX]"; //IX = RA
                } else {
                    desp = tsG.getDesplazamiento(id);
                    res = "#" + desp + "[.IY]"; //IY = zona de Datos Estáticos
                }
            } else {
                id = getIdFromLugarInTS(op1);
                desp = tsG.getDesplazamiento(id);
                op1 = "#" + desp + "[.IY]";

                id = getIdFromLugarInTS(op2);
                desp = tsG.getDesplazamiento(id);
                op2 = "#" + desp + "[.IY]";

                id = getIdFromLugarInTS(res);
                desp = tsG.getDesplazamiento(id);
                res = "#" + desp + "[.IY]";
            }

            fichCO.println("\t\tMOD " + op1 + ", " + op2);
            fichCO.println("\t\tMOVE .A, " + res);
        }
        else if (op.equals("==")) //(+, op1, op2, res) -> todos los ops son direcciones (no hay constEnt)
        {
            String op1 = tokenizer.nextToken();
            String op2 = tokenizer.nextToken();
            String res = tokenizer.nextToken();

            if (hayLocal) {
                id = getIdFromLugarInTS(op1);
                if (esLocal(op1)) {
                    desp = tsL.getDesplazamiento(id) + 1; //sumo el espacio del E.M. //TODO: sumar el nº de parámetros de la func
                    op1 = "#" + desp + "[.IX]"; //IX = RA
                } else {
                    desp = tsG.getDesplazamiento(id);
                    op1 = "#" + desp + "[.IY]"; //IY = zona de Datos Estáticos
                }
                id = getIdFromLugarInTS(op2);
                if (esLocal(op2)) {
                    desp = tsL.getDesplazamiento(id) + 1; //sumo el espacio del E.M. //TODO: sumar el nº de parámetros de la func
                    op2 = "#" + desp + "[.IX]"; //IX = RA
                } else {
                    desp = tsG.getDesplazamiento(id);
                    op2 = "#" + desp + "[.IY]"; //IY = zona de Datos Estáticos
                }


                id = getIdFromLugarInTS(res);
                if (esLocal(res)) {
                    desp = tsL.getDesplazamiento(id) + 1; //sumo el espacio del E.M. //TODO: sumar el nº de parámetros de la func
                    res = "#" + desp + "[.IX]"; //IX = RA
                } else {
                    desp = tsG.getDesplazamiento(id);
                    res = "#" + desp + "[.IY]"; //IY = zona de Datos Estáticos
                }
            } else {
                id = getIdFromLugarInTS(op1);
                desp = tsG.getDesplazamiento(id);
                op1 = "#" + desp + "[.IY]";

                id = getIdFromLugarInTS(op2);
                desp = tsG.getDesplazamiento(id);
                op2 = "#" + desp + "[.IY]";

                id = getIdFromLugarInTS(res);
                desp = tsG.getDesplazamiento(id);
                res = "#" + desp + "[.IY]";
            }
            fichCO.println("\t\tCMP " + op1 + ", " + op2);
            fichCO.println("\t\tBNZ $3");
            fichCO.println("\t\tMOVE #1, " + res);
            fichCO.println("\t\tBR $2");
            fichCO.println("\t\tMOVE #0, " + res);
        }
        else if (op.equals("input"))
        {
            String op1 = tokenizer.nextToken();
            TablaM.estados estadosop1;

            if (hayLocal) {
                id = getIdFromLugarInTS(op1);
                if (esLocal(op1)) {
                    desp = tsL.getDesplazamiento(id) + 1; //sumo el espacio del E.M. //TODO: sumar el nº de parámetros de la func
                    op1 = "#" + desp + "[.IX]"; //IX = RA
                    estadosop1 = tsL.get(id).getTipo();
                } else {
                    desp = tsG.getDesplazamiento(id);
                    op1 = "#" + desp + "[.IY]"; //IY = zona de Datos Estáticos
                    estadosop1 = tsG.get(id).getTipo();
                }
            } else {
                id = getIdFromLugarInTS(op1);
                desp = tsG.getDesplazamiento(id);
                op1 = "#" + desp + "[.IY]";
                estadosop1 = tsG.get(id).getTipo();
            }
            if (estadosop1 == TablaM.estados.cadena)
                fichCO.println("\t\tINSTR " + op1);
            else if (estadosop1 == TablaM.estados.constEnt)
                fichCO.println("\t\tININT " + op1);
        }
        else if (op.equals("print"))
        {
            String op1 = tokenizer.nextToken();
            TablaM.estados estadosop1;

            if (hayLocal) {
                id = getIdFromLugarInTS(op1);
                if (esLocal(op1)) {
                    desp = tsL.getDesplazamiento(id) + 1; //sumo el espacio del E.M. //TODO: sumar el nº de parámetros de la func
                    op1 = "#" + desp + "[.IX]"; //IX = RA
                    estadosop1 = tsL.get(id).getTipo();
                } else {
                    desp = tsG.getDesplazamiento(id);
                    op1 = "#" + desp + "[.IY]"; //IY = zona de Datos Estáticos
                    estadosop1 = tsG.get(id).getTipo();
                }
            } else {
                id = getIdFromLugarInTS(op1);
                desp = tsG.getDesplazamiento(id);
                op1 = "#" + desp + "[.IY]";
                estadosop1 = tsG.get(id).getTipo();
            }
            if (estadosop1 == TablaM.estados.cadena)
                fichCO.println("\t\tWRSTR " + op1);
            else if (estadosop1 == TablaM.estados.constEnt)
                fichCO.println("\t\tWRINT " + op1);
        }
        else if(op.equals("return"))
        {
            tokenizer.nextToken(); // op1 Siempre será null
            tokenizer.nextToken(); // op1 Siempre será null
            String res = tokenizer.nextToken();
            int despl = 0;
            if (hayLocal)
            {
                if (!res.equals("null"))
                {
                    String nameFunc = tsG.get(tsL.getIdTabla()).getLexema();
                    if (tsG.get(tsL.getIdTabla()).getTipo() == TablaM.estados.constEnt || tsG.get(tsL.getIdTabla()).getTipo() == TablaM.estados.booleanR)
                        fichCO.println("\t\tSUB" + "#Tam_RA_" + nameFunc + ", #1");
                    else
                        fichCO.println("\t\tSUB" + "#Tam_RA_" + nameFunc + ", #64");
                    fichCO.println("\t\tADD .A, .IX;");
                    id = getIdFromLugarInTS(res);
                    if (esLocal(res))
                    {
                        despl = tsL.getDesplazamiento(id) + 1; //Se suma el tamaño de EM //TODO: sumar el nº de parámetros de la func
                        fichCO.println("\t\tMOVE #" + despl + "[.IX], [.A]");
                    }
                    else
                    {
                        despl = tsG.getDesplazamiento(id);
                        fichCO.println("\t\tMOVE #" + despl + "[.IY], [.A]");
                    }
                }
                fichCO.println("\t\tBR [.IX]");
            }
            /*
            else
                fichCO.println("\t\tHALT");*/
        }
        else if(op.equals("not")) //(not, op1, null, res)
        {
            String op1 = tokenizer.nextToken();
            tokenizer.nextToken(); // op2 Siempre será null
            String res = tokenizer.nextToken();

            if (hayLocal)
            {
                id = getIdFromLugarInTS(op1);
                if (esLocal(op1))
                {
                    desp = tsL.getDesplazamiento(id) + 1; //sumo el espacio del E.M. //TODO: sumar el nº de parámetros de la func
                    op1 = "#" + desp + "[.IX]"; //IX = RA
                }
                else
                {
                    desp = tsG.getDesplazamiento(id);
                    op1 = "#" + desp + "[.IY]"; //IY = zona de Datos Estáticos
                }

                //obtengo la direccion de res
                String punteroPila;
                if (esLocal(res))
                {
                    ts = tsL;
                    punteroPila = "[.IX]";
                }
                else
                {
                    ts = tsG;
                    punteroPila = "[.IY]";
                }
                id = getIdFromLugarInTS(res);
                desp = ts.getDesplazamiento(id);
                res = "#" + desp + punteroPila;
            }
            else
            {
                id = getIdFromLugarInTS(op1);
                desp = tsG.getDesplazamiento(id);
                op1 = "#" + desp + "[.IY]";
                id = getIdFromLugarInTS(res);
                desp = tsG.getDesplazamiento(id);
                res = "#" + desp + "[.IY]";
            }
            fichCO.println("\t\tNOT " + op1 + ", " + res);
        }
        else if(op.equals("goto")) //(goto, null, null, sig_int + 1), (goto, et1, null, null)
        {
            String et1 = tokenizer.nextToken();
            tokenizer.nextToken(); // op2 Siempre será null
            String valor = tokenizer.nextToken(); // valor será un numero entero N que representa sig_inst + N
            String nameFunc = tsG.get(et1).getLexema();
            if(valor.equals("null"))
                fichCO.println("BR $2"); // sig_inst + 1
            else
                fichCO.println("BR \\" + nameFunc);
        }
        fichCO = oldPrint;
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
        int parteEntera = Integer.parseInt(tokenizer.nextToken());
        boolean local = (parteEntera != 0); //será local si el id de la ts no es 0 (0 == tsG)

        if (local)
            return (int)(tsDotId * 10) - (10 * parteEntera); 
        return (int)(tsDotId * 10);
    }

    private static boolean esLocal(String lugar)
    {
        StringTokenizer tokenizer = new StringTokenizer(lugar, ".");
        int parteEntera = Integer.parseInt(tokenizer.nextToken());
        return (parteEntera != 0); //será local si el id de la ts no es 0 (0 == tsG)
    }

    /**
     * junta los 3 ficheros generados por el GCO para obtener un fichero objeto único
     * @param fichDE
     * @param fichCO
     * @param fichPila
     * @param fichObjeto
     * @throws IOException
     */
    public static void fichAppender(BufferedReader fichDE, BufferedReader fichCO, BufferedReader fichPila, PrintWriter fichObjeto) throws IOException
    {
        String linea;

        fichObjeto.println("MOVE #inicio_estaticas, .IY");
        fichObjeto.println("BR /main");

        fichObjeto.println("");

        fichObjeto.print("main:");
        while ( (linea = fichDE.readLine()) != null)
            fichObjeto.println(linea);

        fichObjeto.println("");

        while ( (linea = fichCO.readLine()) != null)
            fichObjeto.println(linea);
        fichObjeto.println("HALT");

        fichObjeto.println("");

        functionAppend(fichObjeto);

        fichObjeto.println("inicio_estaticas: RES 200");

        fichObjeto.println("");

        //TODO: solo modificar la posicion de SP cuando se cree o destruya un RA
        while ( (linea = fichPila.readLine()) != null)
            fichObjeto.println(linea);

        fichObjeto.println("");

        fichObjeto.println("END");
    }

    public static void functionAppend(PrintWriter fichObjeto)
    {
        try {
            for (Map.Entry<String, PrintWriter> entry : functionFiles.entrySet()) {
                String nombreArchivo = entry.getKey();
                PrintWriter printWriter = entry.getValue();
                printWriter.println();
                printWriter.close();
                File archivo = new File("./tests/functions/" + nombreArchivo);

                if (archivo.exists()) {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(archivo));
                    String linea;
                    while ((linea = bufferedReader.readLine()) != null) {
                        fichObjeto.println(linea);
                    }
                    bufferedReader.close();
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al unir los archivos: " + e.getMessage());
        }
    }
}
