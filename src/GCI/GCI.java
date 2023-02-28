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
    public void emite(operador op, int dir1, int dir2, int dir3, PrintWriter salidaGCI) throws FileNotFoundException
    {
        salidaGCI.write(op + Integer.toString(dir1) + Integer.toString(dir2) + Integer.toString(dir3));
        //GCO.gencod(op, dir1, dir2, dir3);
    }

    /***
     * La funcion inserta en la tabla de simbolos una nueva variable temporal
     * @param estado es un argumento que le da informacion a la funcion acerca de cuanto tamaño tiene que reservar para la temporal
     * @return devuelve el nombre de la nueva temporal generada
     */
    public String nuevaTemp (estados estado, TablaSimbolos ts)
    {
        ts.put("temp" + Integer.toString(nTemps),ts.size()-1);
        ts.insertaTipoTS(ts.get("temp" + Integer.toString(nTemps)).getId(),estado);
        nTemps++;
        return "temp" + Integer.toString(nTemps);
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
