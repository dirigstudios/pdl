package GCO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import AnalizadorLexico.TablaSimbolos;

public class GCO 
{
    /**
     * famoso "switch" que imprime para cada cuarteto su correspondiente instruccion objeto
     * @param fichGCI
     * @param fichObjeto
     * @throws IOException
     */
    //asumo archivo gci bien formado: cada linea contiene exactamente 4 palabras
    public static void switchGCO(BufferedReader fichTS, BufferedReader fichGCI, PrintWriter fichObjeto) throws IOException
    {
        String instruction;
        StringTokenizer tokenizer;
        String op;
        boolean local = false;

        initializeGCO(fichTS, fichGCI, fichObjeto);

        while ( (instruction = fichGCI.readLine() ) != null)
        {
            tokenizer = new StringTokenizer(instruction, " ");

            op = tokenizer.nextToken();
            if (op.equals(":=")) //(:=, op1, NULL, res)
            {
                //TODO: transformar referencias intermedias a zonas de memoria en el código objeto
                String op1 = tokenizer.nextToken();
                tokenizer.nextToken(); //me deshago del null
                String res = tokenizer.nextToken();

                //if (local)
                    //hacer direccionamiento relativo a IX
                //else
                    //hacer direccionamiento relativo a datos estaticos IY

                fichObjeto.println("MOVE " + op1 + ", " + res);
            }
        }
    }

    /**
     * inicializa la zona de datos estáticos del código objeto
     * @param fichGCI
     * @param fichObjeto
     */
    public static void initializeGCO(BufferedReader fichTS, BufferedReader fichGCI, PrintWriter fichObjeto)
    {
        //TODO: ver como cargar la tsG a la zona de Datos Estáticos
    }

    public static void main(String[] args) throws IOException 
    {
        BufferedReader fichGCI = new BufferedReader(new FileReader("./tests/gci.txt"));
        PrintWriter fichObjeto = new PrintWriter("./tests/objeto.txt");
        BufferedReader fichTS = new BufferedReader(new FileReader("./tests/ts.txt")); //TODO: leer de este fichero infumable?
        switchGCO(fichTS, fichGCI, fichObjeto);
        fichTS.close();
        fichGCI.close();
        fichObjeto.close();
    }
}
