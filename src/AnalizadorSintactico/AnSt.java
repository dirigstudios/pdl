package AnalizadorSintactico;

import AnalizadorLexico.AnLex;
import AnalizadorLexico.TablaSimbolos;
import AnalizadorLexico.Token;
import AnalizadorSemantico.AnSm;
import AnalizadorSintactico.TablaM.simbolos;
import AnalizadorSintactico.TablaM.Simbolo;
import AnalizadorSintactico.TablaM.Regla;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Stack;

public class AnSt {
    public static TablaSimbolos tsG = new TablaSimbolos(0);
    public static TablaSimbolos tsL = null;
    public static Simbolo simbolo_aux;
    private static Stack<Simbolo> pila = new Stack<>();
    public static Stack<Simbolo> pilaAux = new Stack<>();
    private static AnSm aux = new AnSm();
    public static class Zona_decl
    {
        public Boolean zona_decl;
        Zona_decl() { this.zona_decl = false; }
        public void changeTo(boolean value) { zona_decl = value; }
        public String toString() { return Boolean.toString(zona_decl); }
    }
    public static class Lines
    {
        public Integer lines;
        Lines(Integer initial)
        {
            this.lines = initial;
        }
        public void addLine() { lines++; }
        public String toString() {
            return Integer.toString(lines);
        }
    }

    public static void crearTablaAux(int ntablas)
    {
        tsL = new TablaSimbolos(ntablas);
    }

    public static void destruirTablaAux()
    {
        tsL = null;
    }

    private static void initializeStack() {
        pila.push(new Simbolo(simbolos.$)); //apilo EOF
        pila.push(new Simbolo(simbolos.PP)); //apilo el AXIOMA de la gramatica tipo 2
    }

    //TODO: implementar que escriba el Código Intermedio en el fichero salidaGCI
    public static void algorithmAnSt(FileReader fuente, PrintWriter salidaTokens, PrintWriter salidaTS, PrintWriter salidaParser, PrintWriter salidaGCI) throws IOException {
        initializeStack();
        Lines lines = new Lines(1);
        TablaM tablaM = new TablaM();
        Zona_decl zona_decl = new Zona_decl();
        Token sigTok = AnLex.getNextToken(fuente, salidaTokens, salidaTS, tsG, tsL, lines, zona_decl);
        Simbolo cima;
        salidaParser.print("Descendente ");

        while (pila.peek().getValor() != simbolos.$)
        {
//            System.out.println(pila.toString() + "\n" + pilaAux.toString() + "\n");
            cima = pila.peek();
            if (cima.isTerminal())
            {
                String aComparar = sigTok.getTipo();
                if (aComparar.equals("palabraReservada"))
                {
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
                if (TablaM.compare(aComparar, cima))
                {
                    simbolo_aux = pila.pop();
                    if (aComparar.equals("id"))
                    {
                        simbolo_aux.setNameId(Integer.parseInt(sigTok.getAtributo()));
                    }
                    else if (aComparar.equals("constEnt"))
                        simbolo_aux.setNameId(Integer.parseInt(sigTok.getAtributo()));
                    else if (aComparar.equals("cadena"))
                        simbolo_aux.setEtiq(sigTok.getAtributo());
                    aux.anadirAtributos(simbolo_aux, pilaAux);
                    sigTok = AnLex.getNextToken(fuente, salidaTokens, salidaTS, tsG, tsL, lines, zona_decl);
                }
                else
                {
                    System.out.println("Error Sintáctico: El terminal de la cima de la pila \"" +
                            cima.getValor().name() + "\" no coincide con el token <" + sigTok.getTipo() + ", " + sigTok.getAtributo() + "> enviado por el AnLex.");
                    return;
                }
            }
            else if (cima.isSem())
            {
                aux.ejecutarRegla(tsG, tsL, pila.peek(), pilaAux, salidaTS, lines, zona_decl, salidaGCI);
                pila.pop();
            }
            else
            {
                Regla regla = tablaM.getRule(cima.getValor(), sigTok);
                tablaM = new TablaM();
                if (regla != null)
                {
                    String numeroRegla = String.valueOf(regla.getNumero());
                    salidaParser.print(numeroRegla + " ");
                    simbolo_aux = pila.pop();
                    aux.anadirAtributos(simbolo_aux, pilaAux);
                    LinkedList<Simbolo> consecuente = regla.getConsecuente();
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
                    return;
                }
            }
        }
        if (!(sigTok.getTipo().equals("$") && pilaAux.peek().getValor() == simbolos.PP))
            System.out.println("Error Sintáctico: El texto no finaliza con \"$\", sino con <" + sigTok.getTipo() + ", " + sigTok.getAtributo() + ">.");
        tsG.printTS(salidaTS);
        //System.out.println("P: " + pila.toString() + "\n" + "AUX: " + pilaAux.toString() + "\n");
    }
}