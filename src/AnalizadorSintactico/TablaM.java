package AnalizadorSintactico;

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
    //TODO base de datos de reglas
    /*21 no terminales, 26 terminales*/
    //Este enum sirve para sacar las reglas
    public enum simbolos{A,B,C,E,F,H,K,L,O,P,Q,R,RR,S,SS,T,U,UU,V,VV,X,exclamacion,asignacionResto,abrePar,cierraPar,suma,coma,dosPuntos,puntoYComa,asignacion,comparacion,booleanR,cadena,caseR,defaultR,entero,function,id,input,intR,let,print,returnR,string,switchR,abreLLave,cierraLLave}
    //Estos enums sirven para ubicarnos en la matriz de reglas
    public enum simbolosNoTerminales{A,B,C,E,F,H,K,L,O,P,Q,R,RR,S,SS,T,U,UU,V,VV,X}
    public enum simbolosTerminales{exclamacion,asignacionResto,abrePar,cierraPar,suma,coma,dosPuntos,puntoYComa,asignacion,comparacion,booleanR,cadena,caseR,defaultR,entero,function,id,input,intR,let,print,returnR,string,switchR,abreLLave,cierraLLave}
    Regla[][] tablaM = new Regla[21][26];
    public TablaM() {
        Enum<simbolos> temp = simbolos.valueOf("P");
    }
}