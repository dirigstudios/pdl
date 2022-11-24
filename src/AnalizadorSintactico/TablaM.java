package AnalizadorSintactico;

import AnalizadorLexico.Token;

import java.util.LinkedList;

public class TablaM {
    static class Regla
    {
        simbolos antecedente;
        LinkedList<simbolos> consecuente;

        public Regla(simbolos antecedente, LinkedList<simbolos> consecuente) {
            this.antecedente = antecedente;
            this.consecuente = consecuente;
        }

        public simbolos getAntecedente() {
            return antecedente;
        }

        public LinkedList<simbolos> getConsecuente() {
            return consecuente;
        }
    }

    /*21 no terminales, 26 terminales*/
    //Este enum sirve para sacar las reglas
    public enum simbolos{A,B,C,E,F,H,K,L,O,P,Q,R,RR,S,SS,T,U,UU,V,VV,X,exclamacion,asignacionResto,abrePar,cierraPar,suma,coma,dosPuntos,puntoYComa,asignacion,comparacion,booleanR,cadena,caseR,defaultR,entero,function,id,input,intR,let,print,returnR,string,switchR,abreLLave,cierraLLave,finFichero;

        public static boolean compare(String tipo, simbolos cima) {
            return cima.name().equals(tipo);
        }

        public static boolean isTerminal(simbolos cima) {
            return (cima.ordinal()>20);
        }
    }

    //Estos enums sirven para ubicarnos en la matriz de reglas
    public enum simbolosNoTerminales{A,B,C,E,F,H,K,L,O,P,Q,R,RR,S,SS,T,U,UU,V,VV,X}
    public enum simbolosTerminales{exclamacion,asignacionResto,abrePar,cierraPar,suma,coma,dosPuntos,puntoYComa,asignacion,comparacion,booleanR,cadena,caseR,defaultR,entero,function,id,input,intR,let,print,returnR,string,switchR,abreLLave,cierraLLave,finFichero}

    static Regla[][] tablaM = new Regla[21][26];

    public TablaM() {
        //Ejemplo creacion de una regla (regla E -> !R)
        LinkedList<simbolos> tempList = new LinkedList<>();
        tempList.add(simbolos.exclamacion);
        tempList.add(simbolos.R);
        Regla temp = new Regla(simbolos.E, tempList);
        tablaM[simbolosNoTerminales.valueOf("E").ordinal()][simbolosTerminales.valueOf("exclamacion").ordinal()] = temp;
        //Resto de reglas
        // A -> lambda (cierraPar)
        tempList = new LinkedList<>();
        temp = new Regla(simbolos.A, tempList);
        tablaM[simbolosNoTerminales.valueOf("A").ordinal()][simbolosTerminales.valueOf("cierraPar").ordinal()] = temp;
        // A -> T id K (boolean)
        tempList = new LinkedList<>();
        tempList.add(simbolos.T);
        tempList.add(simbolos.id);
        tempList.add(simbolos.K);
        temp = new Regla(simbolos.A, tempList);
        tablaM[simbolosNoTerminales.valueOf("A").ordinal()][simbolosTerminales.valueOf("booleanR").ordinal()] = temp;
        // A -> T id K (int)
        tablaM[simbolosNoTerminales.valueOf("A").ordinal()][simbolosTerminales.valueOf("intR").ordinal()] = temp;
        // A -> T id K (string)
        tablaM[simbolosNoTerminales.valueOf("A").ordinal()][simbolosTerminales.valueOf("string").ordinal()] = temp;
        // B -> S (id)
    }

    public static Regla getRule(simbolos cima, Token sigTok) {// cima no terminal
        return tablaM[cima.ordinal()][simbolosTerminales.valueOf(sigTok.getTipo()).ordinal()];
    }
}