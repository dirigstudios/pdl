package AnalizadorSintactico;

import AnalizadorLexico.AnLex;
import AnalizadorLexico.Token;
import AnalizadorSintactico.TablaM.simbolos;
import AnalizadorSintactico.TablaM.Regla;
import AnalizadorSintactico.TablaM;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Stack;

public class AnSt 
{
    private static Stack<simbolos> pila = new Stack<>();
    static TablaM tablaM = new TablaM();

    private static void initializeStack()
    {
        pila.push(simbolos.$); //apilo EOF
        pila.push(simbolos.P); //apilo el AXIOMA de la gramatica tipo 2
    }

    public static void algorithmAnSt (FileReader fuente, PrintWriter salidaTokens, PrintWriter salidaTS, PrintWriter salidaParser) throws IOException
    {
        initializeStack();
        Token sigTok = AnLex.getNextToken(fuente, salidaTokens, salidaTS);
        simbolos cima = pila.peek();
        salidaParser.print("Descendente ");
        int j = 0;

        while (!(pila.peek() == simbolos.$))
        {
            cima = pila.peek();
            if (simbolos.isTerminal(cima))
            {
                String aComparar = sigTok.getTipo();
                if (aComparar.equals("palabraReservada"))
                {
                    aComparar = sigTok.getAtributo();
                    switch (aComparar)
                    {
                        case "int":
                            aComparar = "intR";
                            break;
                        case "default":
                            aComparar = "defaultR";
                            break;
                        case "break":
                            aComparar = "breakR";
                            break;
                        case "case":
                            aComparar = "caseR";
                            break;
                        case "return":
                            aComparar = "returnR";
                            break;
                        case "boolean":
                            aComparar = "booleanR";
                            break;
                        case "switch":
                            aComparar = "switchR";
                            break;
                        case "if":
                            aComparar = "ifR";
                            break;
                    }
                }
                if (simbolos.compare(aComparar, cima))
                {
                    pila.pop();
                    sigTok = AnLex.getNextToken(fuente, salidaTokens, salidaTS);
                }
                else
                {
                    System.out.println("Error Sintáctico: El terminal de la cima de la pila \"" +
                        cima.name() + "\" no coincide con el token <" + sigTok.getTipo() + ", " + sigTok.getAtributo() + "> enviado por el AnLex.");
                    return ;
                }
            }
            else
            {
                Regla regla = tablaM.getRule(cima, sigTok);
                if (regla != null)
                {
                    String numeroRegla = String.valueOf(regla.getNumero());
                    salidaParser.print(numeroRegla + " ");
                    pila.pop();
                    LinkedList<simbolos> consecuente = regla.getConsecuente();
                    int i = consecuente.size() - 1;
                    while (i >= 0)
                    {
                        pila.push(consecuente.get(i));
                        i--;
                    }
                }
                else
                {
                    System.out.println("Error Sintáctico: No existe regla para M[" + cima + ", <" + sigTok.getTipo() + ", " + sigTok.getAtributo() + ">].");
                    return ;
                }
            }
            j++;
        }

        if (!sigTok.getTipo().equals("$"))
            System.out.println("Error Sintáctico: El texto no finaliza con \"$\", sino con <" + sigTok.getTipo() + ", " + sigTok.getAtributo() + ">.");
        return ;
    }
}