package AnalizadorSintactico;

import AnalizadorLexico.Token;

import java.util.LinkedList;

public class TablaM {
    static class Regla
    {
        simbolos antecedente;
        LinkedList<simbolos> consecuente;
        int numero;

        public Regla(simbolos antecedente, LinkedList<simbolos> consecuente, int numero) {
            this.antecedente = antecedente;
            this.consecuente = consecuente;
            this.numero = numero;
        }

        public simbolos getAntecedente() {
            return antecedente;
        }

        public LinkedList<simbolos> getConsecuente() {
            return consecuente;
        }

        public int getNumero() {
            return numero;
        }
    }

    /*22 no terminales, 28 terminales*/
    //Este enum sirve para sacar las reglas
    public enum simbolos{A,B,C,D,E,F,H,K,L,O,P,Q,R,RR,S,SS,T,U,UU,V,VV,X,exclamacion,asignacionResto,abrePar,cierraPar,suma,coma,dosPuntos,puntoYComa,asignacion,comparacion,booleanR,cadena,caseR,defaultR,entero,function,id,input,intR,let,print,returnR,string,switchR,abreLLave,cierraLLave,breakR,ifR,finFichero;

        public static boolean compare(String tipo, simbolos cima) {
            return cima.name().equals(tipo);
        }

        public static boolean isTerminal(simbolos cima) {
            return (cima.ordinal()>20);
        }
    }

    //Estos enums sirven para ubicarnos en la matriz de reglas
    public enum simbolosNoTerminales{A,B,C,D,E,F,H,K,L,O,P,Q,R,RR,S,SS,T,U,UU,V,VV,X}
    public enum simbolosTerminales{exclamacion,asignacionResto,abrePar,cierraPar,suma,coma,dosPuntos,puntoYComa,asignacion,comparacion,booleanR,cadena,caseR,defaultR,entero,function,id,input,intR,let,print,returnR,string,switchR,abreLLave,cierraLLave,breakR,ifR,finFichero}

    static Regla[][] tablaM = new Regla[22][26];

    public TablaM() {
        
    }

    public static Regla getRule(simbolos cima, Token sigTok) {// cima no terminal
        return tablaM[cima.ordinal()][simbolosTerminales.valueOf(sigTok.getTipo()).ordinal()];

    }
}