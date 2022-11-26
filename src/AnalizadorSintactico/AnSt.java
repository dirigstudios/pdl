package AnalizadorSintactico;

import AnalizadorLexico.AnLex;
import AnalizadorLexico.Token;
import AnalizadorSintactico.TablaM.Regla;
import AnalizadorSintactico.TablaM.simbolos;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Stack;

public class AnSt 
{
    private static Stack<simbolos> pila = new Stack<>();
    TablaM tablaM = new TablaM();

    private static void initializeStack()
    {
        pila.push(simbolos.finFichero); //apilo EOF
        pila.push(simbolos.P); //apilo el AXIOMA de la gramatica tipo 2
    }

    public static void algorithmAnSt (FileReader fuente, PrintWriter salidaTokens, PrintWriter salidaTS, PrintWriter salidaParser) throws IOException
    {
        initializeStack();
        Token sigTok = AnLex.getNextToken(null, null, null);
        simbolos cima = pila.peek();
        salidaParser.print("Descendente ");

        while (!(pila.peek() == simbolos.finFichero))
        {
            if (simbolos.isTerminal(cima))
            {
                // adaptacion para las comparaciones sin con problemas 
                String aComparar = sigTok.getTipo();
                if (aComparar.equals("palabraReservada"))
                {
                    if (aComparar.endsWith("R"))
                        aComparar = aComparar.substring(0, aComparar.length() - 2);
                }
                if (simbolos.compare(aComparar, cima))
                {
                    pila.pop();
                    sigTok = AnLex.getNextToken(fuente, salidaTokens, salidaTS);
                }
                else
                {
                    System.out.println("Error Sintáctico: El terminal de la cima de la pila " + 
                        cima.name() + " no coincide con el token <" + sigTok.getTipo() + ", " + sigTok.getAtributo() + "> enviado por el AnLex.");
                    return ;
                }
            }
            else
            {
                Regla regla = TablaM.getRule(cima, sigTok);
                if (regla != null)
                {
                    String numeroRegla = String.valueOf(regla.getNumero());
                    salidaParser.print(numeroRegla + " ");
                    pila.pop();
                    LinkedList<simbolos> consecuente = regla.getConsecuente();
                    simbolos elemento;
                    int i = 0;
                    while ((elemento = consecuente.get(i))!= null)
                    {
                        pila.push(elemento);
                        i++;
                    }
                }
                else
                {
                    System.out.println("Error Sintáctico: No existe regla para M[" + cima + ", " + sigTok + "].");
                    return ;
                }
            }
        }

        if (!sigTok.getTipo().equals("$"))
            System.out.println("Error Sintáctico: El texto no finaliza con \"$\", sino con <" + sigTok.getTipo() + ", " + sigTok.getAtributo() + ">.");
        return ;
    }
}