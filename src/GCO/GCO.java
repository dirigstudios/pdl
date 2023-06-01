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
    private static int paramDespl = 1;  //para saber siempre cuantos desplazamientos he hecho a la hora de realizar "param", se reestablece a 1 cuando se hace call
    private static int numRet = 1;      //me permite generar etiquetas de retorno diferentes para cada llamada a funcion

    private static int string_number = 1; //me permite generar etiquetas de string diferentes para cada string

    /**
     * famoso "switch" que imprime para cada cuarteto su correspondiente instruccion objeto
     * @param instruccion
     * @param fichDE
     * @param fichCO
     * @param fichStrings
     * @throws IOException
     */
    //asumo que cada instrucción de ci está bien formado: contiene exactamente 4 palabras
    public static void switchGCO(String instruccion, PrintWriter fichDE, PrintWriter fichCO, PrintWriter fichStrings, TablaSimbolos tsG, TablaSimbolos tsL) {
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
            if (op1.charAt(0) == '\"' && !op1.endsWith("\""))
                op1 = getIncompleteString(tokenizer, op1);
            tokenizer.nextToken(); //me deshago del null
            String res = tokenizer.nextToken();
            if (op1.endsWith("\"") ) {
                if (esLocal(res))
                {
                    ts = tsL;
                    id = getIdFromLugarInTS(res);
                    desp = ts.getDesplazamiento(id);
                    res = "#" + desp + "[.IX]";
                }
                else
                {
                    ts = tsG;
                    id = getIdFromLugarInTS(res);
                    desp = ts.getDesplazamiento(id);
                    res = "#" + desp + "[.IY]";
                }
                int index = op1.length() - 1;
                op1 = op1.substring(0, index) + "\0" + op1.substring(index);
                fichStrings.println("data0" + string_number + ": DATA " + op1);
                fichCO.println("\t\tMOVE #data0" + string_number++ + ", " + res);
            }
            else {
                if (hayLocal) {
                    if (!esEntera(op1)) //el operador puede ser una constEnt
                    {
                        id = getIdFromLugarInTS(op1);

                        if (esLocal(op1)) {
                            desp = tsL.getDesplazamiento(id); //sumo el espacio del E.M. y el nº de parámetros de la func
                            op1 = "#" + desp + "[.IX]"; //IX = RA
                        } else {
                            desp = tsG.getDesplazamiento(id);
                            op1 = "#" + desp + "[.IY]"; //IY = zona de Datos Estáticos
                        }
                    } else
                        op1 = "#" + op1;

                    //obtengo la direccion de res
                    if (esLocal(res))
                        ts = tsL;
                    else
                        ts = tsG;
                    id = getIdFromLugarInTS(res);
                    desp = ts.getDesplazamiento(id);
                    res = "#" + desp + "[.IX]";
                } else {
                    if (!esEntera(op1)) //el operador puede ser una constEnt
                    {
                        id = getIdFromLugarInTS(op1);
                        desp = tsG.getDesplazamiento(id);
                        op1 = "#" + desp + "[.IY]";
                    } else
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
                    desp = tsL.getDesplazamiento(id); // + 1 + tsL.getParametros(); //sumo el espacio del E.M. y el nº de parámetros de la func
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
                    desp = tsL.getDesplazamiento(id);// + 1 + tsL.getParametros(); //sumo el espacio del E.M. + nº de parámetros de la func
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
                    desp = tsL.getDesplazamiento(id);// + 1 + tsL.getParametros(); //sumo el espacio del E.M. + nº de parámetros de la func
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
        else if (op.equals(":")) //(:, et01, null, null)
        {
            fichCO.println(tokenizer.nextToken() + ":\tNOP");
        }
        else if (op.equals("%")) //(%, op1, op2, res) -> todos los ops son direcciones (no hay constEnt)
        {
            String op1 = tokenizer.nextToken();
            String op2 = tokenizer.nextToken();
            String res = tokenizer.nextToken();

            if (hayLocal) {
                id = getIdFromLugarInTS(op1);
                if (esLocal(op1)) {
                    desp = tsL.getDesplazamiento(id);// + 1 + tsL.getParametros(); //sumo el espacio del E.M. + nº de parámetros de la func
                    op1 = "#" + desp + "[.IX]"; //IX = RA
                } else {
                    desp = tsG.getDesplazamiento(id);
                    op1 = "#" + desp + "[.IY]"; //IY = zona de Datos Estáticos
                }


                id = getIdFromLugarInTS(op2);
                if (esLocal(op2)) {
                    desp = tsL.getDesplazamiento(id) ;//+ 1 + tsL.getParametros(); //sumo el espacio del E.M. + nº de parámetros de la func
                    op2 = "#" + desp + "[.IX]"; //IX = RA
                } else {
                    desp = tsG.getDesplazamiento(id);
                    op2 = "#" + desp + "[.IY]"; //IY = zona de Datos Estáticos
                }


                id = getIdFromLugarInTS(res);
                if (esLocal(res)) {
                    desp = tsL.getDesplazamiento(id); //+ 1 + tsL.getParametros(); //sumo el espacio del E.M. + nº de parámetros de la func
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

            fichCO.println("\t\tMOD " + op2 + ", " + op1);
            fichCO.println("\t\tMOVE .A, " + res);
        }
        else if (op.equals("input")) //(input, op1, null, null)
        {
            String op1 = tokenizer.nextToken();
            TablaM.estados estadosop1;

            if (hayLocal) {
                id = getIdFromLugarInTS(op1);
                if (esLocal(op1)) {
                    desp = tsL.getDesplazamiento(id); //sumo el espacio del E.M. + nº de parámetros de la func
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
            {
                fichStrings.println("data0" + string_number + ": RES 64");
                fichCO.println("\t\tINSTR /data0" + string_number);
                fichCO.println("\t\tMOVE #data0" + string_number++ + ", " + op1);
            }
            else if (estadosop1 == TablaM.estados.constEnt)
                fichCO.println("\t\tININT " + op1);
        }
        else if (op.equals("print")) //(print, op1, null, null)
        {
            String op1 = tokenizer.nextToken();
            if (op1.charAt(0) == '\"' && !op1.endsWith("\""))
                op1 = getIncompleteString(tokenizer, op1);
            TablaM.estados estadosop1;
            if (hayLocal) {
                id = getIdFromLugarInTS(op1);
                if (esLocal(op1)) {
                    desp = tsL.getDesplazamiento(id); //sumo el espacio del E.M. + nº de parámetros de la func
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
            {
                fichCO.println("\t\tADD #0, " + op1);
                fichCO.println("\t\tWRSTR [.A]");
            }
            else if (estadosop1 == TablaM.estados.constEnt)
                fichCO.println("\t\tWRINT " + op1);
        }
        else if(op.equals("return")) //(return, op1, null, null)
        {
            String op1 = tokenizer.nextToken(); // op1 Siempre será null
            tokenizer.nextToken(); // op2 Siempre será null
            tokenizer.nextToken();
            int despl = 0;
            if (hayLocal)
            {
                if (!op1.equals("null"))
                {
                    String nameFunc = tsG.get(tsL.getIdTabla()).getLexema();
                    fichCO.println("\t\tSUB " + "#Tam_RA_Et" + nameFunc + ", #1");
                    fichCO.println("\t\tADD .A, .IX;");
                    id = getIdFromLugarInTS(op1);
                    despl = tsL.getDesplazamiento(id); //sumo el espacio del E.M. + nº de parámetros de la func
                    fichCO.println("\t\tMOVE #" + despl + "[.IX], [.A]");
                }
                fichCO.println("\t\tBR [.IX]"); //(return, null, null, null)
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
                    desp = tsL.getDesplazamiento(id); // + 1 + tsL.getParametros(); //sumo el espacio del E.M. y el nº de parámetros de la func
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
            String valor = tokenizer.nextToken(); // valor será un numero entero N que representa sig_inst + N (res)

            if(!valor.equals("null"))
                fichCO.println("\t\tBR $3"); // sig_inst + 1
            else
                fichCO.println("\t\tBR /" + et1);
        }
        else if(op.equals("if")) // (if, op1, null, et01)
        {
            String op1 = tokenizer.nextToken();
            tokenizer.nextToken(); // op2 Siempre será null
            String et01 = tokenizer.nextToken();
            fichCO.println("\t\tCMP " + getVarDesp(op1, tsL, tsG) + ", #1");
            fichCO.println("\t\tBZ /" + et01);
        }
        else if(op.equals("if==")) //(if==, op1, op2, 2)
        {
            String op1 = tokenizer.nextToken();
            String op2 = tokenizer.nextToken(); // op2 Siempre será null
            tokenizer.nextToken();          // Se que siempre va a ser 2
            fichCO.println("\t\tCMP " + getVarDesp(op1, tsL, tsG) + "," + getVarDesp(op2, tsL, tsG));
            fichCO.println("\t\tBZ $5");
        }
        else if(op.equals("if!=")) //(if!=, op1, constEnt, et01)
        {
            String op1 = tokenizer.nextToken();
            String entero = tokenizer.nextToken(); // Es una constante Entera
            String et01 = tokenizer.nextToken();
            fichCO.println("\t\tCMP " + getVarDesp(op1, tsL, tsG) + ", #" + entero);
            fichCO.println("\t\tBNZ /" + et01);
        }
        else if(op.equals("param")) //(param, op1, null, null)
        {
            String op1 = tokenizer.nextToken();
            tokenizer.nextToken(); //  Siempre es null
            tokenizer.nextToken(); // Siempre es null

            if (tsL != null)
            {
                id = getIdFromLugarInTS(op1);
                if (esLocal(op1)) {
                    desp = tsL.getDesplazamiento(id); // + 1 + tsL.getParametros(); //sumo el espacio del E.M. y el nº de parámetros de la func
                    op1 = "#" + desp + "[.IX]"; //IX = RA
                } else {
                    desp = tsG.getDesplazamiento(id);
                    op1 = "#" + desp + "[.IY]"; //IY = zona de Datos Estáticos
                }
            }
            else
            {
                id = getIdFromLugarInTS(op1);
                desp = tsG.getDesplazamiento(id);
                op1 = "#" + desp + "[.IY]"; //IY = zona de Datos Estáticos
            }
            if (hayLocal)
                fichCO.println("\t\tADD #Tam_RA_Et" + tsG.get(tsL.getIdTabla()).getLexema() + ", .IX");
            else
                fichCO.println("\t\tADD #0, .IX");
            fichCO.println("\t\tADD #" + paramDespl + ", .A");
            fichCO.println("\t\tMOVE " + op1 + ", [.A]");
            paramDespl += 1;
        }
        else if(op.equals("call")) //(call, et1, null, null) ,(call, et1, null, res) -> implica return en res
        {
            paramDespl = 1;
            String et1 = tokenizer.nextToken();
            tokenizer.nextToken(); //  Siempre es null
            String res = tokenizer.nextToken();
            String sub;
            if (hayLocal)
            {
                fichCO.println("\t\tADD #Tam_RA_Et" + tsG.get(tsL.getIdTabla()).getLexema() + ", .IX"); //para desplazar el .IX con una etiqueta con el TamRA
                fichCO.println("\t\tMOVE #dir_ret_" + numRet + ", [.A]");
                fichCO.println("\t\tMOVE .A, .IX");
                sub = "\t\tSUB .IX, #Tam_RA_Et" + tsG.get(tsL.getIdTabla()).getLexema();
            }
            else
            {
                fichCO.println("\t\tMOVE #dir_ret_" + numRet + ", [.IX]");
                sub = "\t\tSUB .IX, #Tam_RA_main";
            }
            //fichCO.println("\t\tMOVE .IX, .SP"); //actualizo el puntero de pila al inicio
            fichCO.println("\t\tBR /" + et1);
            fichCO.println("dir_ret_" + numRet + ":\tNOP");
            if (!res.equals("null") && hayLocal)
            {
                fichCO.println("\t\tSUB #Tam_RA_" + et1 + ", #1");
                fichCO.println("\t\tADD .A, .IX");
                fichCO.println("\t\tMOVE [.A], .R9"); //movemos el VD a un registro random
            }
            if (hayLocal)
            {
                fichCO.println(sub); //SUB .IX, #Tam_RA_llamador -> actualizar el IX para que apunte al RA de la funcion 
                fichCO.println("\t\tMOVE .A, .IX");
                if (!res.equals("null"))
                    fichCO.println("\t\tMOVE .R9, " + getVarDesp(res, tsL, tsG));
            }
            else
            {
                if (!res.equals("null"))
                    fichCO.println("\t\tMOVE [.A], " + getVarDesp(res, tsL, tsG));
                fichCO.println("\t\tMOVE #inicio_pila, .IX");
            }
            numRet++;
        }
        fichCO = oldPrint; // me siento un poco #oldPrint
    }

    /**
     * returns the full string if there are whitespaces in it.
     * @param operacion
     * @param op1
     * @return
     */
    public static String getIncompleteString(StringTokenizer operacion, String op1)
    {
        while (!op1.endsWith("\""))
            op1 = op1 + " " + operacion.nextToken();
        return op1;
    }

    public static String getVarDesp(String var, TablaSimbolos tsL, TablaSimbolos tsG)
    {
        String resultado;
        int id;
        int desp;
        if (tsL != null)
        {
            id = getIdFromLugarInTS(var);
            if (esLocal(var)) {
                desp = tsL.getDesplazamiento(id); // + 1 + tsL.getParametros(); //sumo el espacio del E.M. y el nº de parámetros de la func
                resultado = "#" + desp + "[.IX]"; //IX = RA
            } else {
                desp = tsG.getDesplazamiento(id);
                resultado = "#" + desp + "[.IY]"; //IY = zona de Datos Estáticos
            }
        }
        else
        {
            id = getIdFromLugarInTS(var);
            desp = tsG.getDesplazamiento(id);
            resultado = "#" + desp + "[.IY]"; //IY = zona de Datos Estáticos
        }
        return resultado;
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
        StringTokenizer tokenizer = new StringTokenizer(lugar, ".");
        Integer.parseInt(tokenizer.nextToken()); //me salto la parte entera
        int parteDecimal = Integer.parseInt(tokenizer.nextToken());
        return parteDecimal;
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
     * @param fichStrings
     * @param fichObjeto
     * @throws IOException
     */
    public static void fichAppender(BufferedReader fichDE, BufferedReader fichCO, BufferedReader fichStrings, PrintWriter fichObjeto) throws IOException
    {
        String linea;

        fichObjeto.println("MOVE #inicio_estaticas, .IY");
        fichObjeto.println("MOVE #inicio_pila, .IX");
        //fichObjeto.println("MOVE .IX, .SP");
        fichObjeto.println("BR /main");
        fichObjeto.println("");
        fichObjeto.print("main:");
        fichObjeto.println("");
        while ( (linea = fichCO.readLine()) != null)
            fichObjeto.println(linea);
        fichObjeto.println("\t\tHALT");
        functionAppend(fichObjeto);
        fichObjeto.println("");
        //zona de etiquetas con constantes y strings
        while ( (linea = fichDE.readLine()) != null)
            fichObjeto.println(linea);
        fichObjeto.println("");
        while ( (linea = fichStrings.readLine()) != null)
            fichObjeto.println(linea);
        fichObjeto.println("");
        //solo modificar la posicion de SP cuando se cree o destruya un RA
        // while ( (linea = fichPila.readLine()) != null)
        //     fichObjeto.println(linea);
        fichObjeto.println("inicio_pila: NOP");
        fichObjeto.println("");
        fichObjeto.println("END");
        fichObjeto.println("; una obra de diriG studios(c) 2023.\n");
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
                    fichObjeto.println("\t\tBR [.IX]");
                    bufferedReader.close();
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al unir los archivos: " + e.getMessage());
        }
    }

    /**
     * escribe al inicio del fichero objeto final (zona DE) etiquetas con el tamaño del RA de la tsL enviada
     */
    public static void tamRA_calculator(TablaSimbolos tsG, TablaSimbolos tsL, PrintWriter fichDE)
    {
        if (tsG == tsL) //calcular tamaño del supuesto RA main
        {
            int tamRA = tsG.size();
            fichDE.println("inicio_estaticas: RES " + tamRA);
        }
        else
        {
            int tamRA = tsL.size();
            String lexema = tsG.get(tsL.getIdTabla()).getLexema();
            tamRA += 2; //sumo el EM (Dir Retorno) y VD

            fichDE.println("Tam_RA_Et" + lexema + ": EQU " + tamRA);
        }
    }
}
