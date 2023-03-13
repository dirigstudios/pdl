package GCI;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import AnalizadorLexico.TablaSimbolos;
import AnalizadorSintactico.TablaM.estados;

public class GCI
{
    private int nTemps = 0;
    private int nEts = 0;
    public enum operador{suma, igual, negacion, asignacion, goTo, If, asigancionResto}

    /***
     * la funcion @emite envia tanto a un fichero como al generador de codigo objeto un cuarteto
     */
    public static void emite(String operador, String var1, String var2, String dest, PrintWriter ficheroCuartetos)
    {
        ficheroCuartetos.println(operador.toString()+ " " + var1 + " " + var2 + " " + dest);
        //GCI.send(operador.toString() + var1 + var2 + dest)
    }

    /***
     * La funcion inserta en la tabla de simbolos una nueva variable temporal
     * @param estado es un argumento que le da informacion a la funcion acerca de cuanto tamaño tiene que reservar para la temporal
     * @return devuelve el nombre de la nueva temporal generada
     */
    public static String nuevaTemp(TablaSimbolos tablaGlobal, TablaSimbolos tablaLocal, estados estado)
    {
        int id;
        if(tablaLocal == null)
        {
            id = tablaGlobal.putnew(estado);
            return tablaGlobal.getIdTabla() + "." + id;
        }
        else
        {
            id = tablaLocal.putnew(estado);
            return tablaLocal.getIdTabla() + "." + id;
        }
    }

    /***
     * genera una nueva etiqueta para el Código Intermedio
     * @return devuelve el nombre de la nueva etiqueta generada
     */
    public String nuevaEt()
    {
        nEts++;
        return "et0" + Integer.toString(nEts);
    }

}
