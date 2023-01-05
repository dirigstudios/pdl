package AnalizadorSintactico;

import AnalizadorLexico.AnLex;
import AnalizadorLexico.TablaSimbolos;
import AnalizadorLexico.Token;
import AnalizadorSemantico.AnSm;
import AnalizadorSintactico.TablaM.simbolos;
import AnalizadorSintactico.TablaM.Regla;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Stack;

public class AnSt {
    public static TablaSimbolos tsG = new TablaSimbolos(0);
    public static TablaSimbolos tsA;
    public static simbolos simbolo_aux;
    private static Stack<simbolos> pila = new Stack<>();
    public static Stack<simbolos> pilaAux = new Stack<>();
    static TablaM tablaM = new TablaM();
    private static AnSm aux = new AnSm();

    private static void initializeStack() {
        pila.push(simbolos.$); //apilo EOF
        pila.push(simbolos.P); //apilo el AXIOMA de la gramatica tipo 2
    }

    public static void algorithmAnSt(FileReader fuente, PrintWriter salidaTokens, PrintWriter salidaTS, PrintWriter salidaParser) throws IOException {
        initializeStack();
        Token sigTok = AnLex.getNextToken(fuente, salidaTokens, salidaTS, tsG);
        simbolos cima;
        salidaParser.print("Descendente ");

        while (pila.peek() != simbolos.$)
        {
            System.out.println(pila.toString() + "\n" + pilaAux.toString() + "\n");
            cima = pila.peek();
            if (simbolos.isTerminal(cima)) {
                String aComparar = sigTok.getTipo();
                if (aComparar.equals("palabraReservada")) {
                    aComparar = sigTok.getAtributo();
                    switch (aComparar) {
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
                if (simbolos.compare(aComparar, cima)) {
                    simbolo_aux = pila.pop();
                    if (aComparar.equals("id"))
                        simbolo_aux.setNameId(Integer.parseInt(sigTok.getAtributo()));
                    aux.añadirAtributos(simbolo_aux, pilaAux);
                    sigTok = AnLex.getNextToken(fuente, salidaTokens, salidaTS, tsG);
                } else {
                    System.out.println("Error Sintáctico: El terminal de la cima de la pila \"" +
                            cima.name() + "\" no coincide con el token <" + sigTok.getTipo() + ", " + sigTok.getAtributo() + "> enviado por el AnLex.");
                    return;
                }
            } else if (simbolos.isSem(cima))
            {
                aux.ejecutarRegla(cima, tsG, tsA, pila, pilaAux);
                pila.pop();
            } else
            {
                Regla regla = tablaM.getRule(cima, sigTok);
                if (regla != null)
                {
                    String numeroRegla = String.valueOf(regla.getNumero());
                    salidaParser.print(numeroRegla + " ");
                    simbolo_aux = pila.pop();
                    aux.añadirAtributos(simbolo_aux, pilaAux);
                    LinkedList<simbolos> consecuente = regla.getConsecuente();
                    int i = consecuente.size() - 1;
                    while (i >= 0)
                    {
                        pila.push(consecuente.get(i));
                        i--;
                    }

                } else
                {
                    System.out.println("Error Sintáctico: No existe regla para M[" + cima + ", <" + sigTok.getTipo() + ", " + sigTok.getAtributo() + ">].");
                    return;
                }
            }
        }
        //TODO: comprobación final pilaAux sea igual que el axioma
        if (!(sigTok.getTipo().equals("$") && pilaAux.peek() == simbolos.P))
            System.out.println("Error Sintáctico: El texto no finaliza con \"$\", sino con <" + sigTok.getTipo() + ", " + sigTok.getAtributo() + ">.");
        tsG.printTS(salidaTS);
        System.out.println("P: " + pila.toString() + "\n" + "AUX: " + pilaAux.toString() + "\n");
        return;
    }
}