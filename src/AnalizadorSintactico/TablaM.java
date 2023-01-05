package AnalizadorSintactico;

import AnalizadorLexico.Token;

import java.util.LinkedList;

public class TablaM {
    //TODO: P->P`
    public static class Regla
    {
        simbolos antecedente;

        LinkedList<simbolos> consecuente;
        int numero;

        public Regla(simbolos antecedente, LinkedList<simbolos> consecuente, int numero)
        {
            this.antecedente = antecedente;
            this.consecuente = consecuente;
            this.numero = numero;
        }

        public simbolos getAntecedente()
        {
            return antecedente;
        }

        public LinkedList<simbolos> getConsecuente()
        {
            return consecuente;
        }

        public int getNumero()
        {
            return numero;
        }
    }

    /*22 no terminales, 28 terminales*/
    //Este enum sirve para sacar las reglas
    public enum simbolos{A,B,C,D,E,F,H,K,L,O,P,Q,R,RR,S,SS,T,U,UU,V,VV,X,negacion,asignacionResto,abrePar,cierraPar,suma,coma,dosPuntos,
                        puntoComa,igual,comparacion,booleanR,cadena,caseR,defaultR,constEnt,function,id,input,
                        intR,let,print,returnR,string,switchR,abreLlave,cierraLlave,breakR,ifR,$, unoUno, unoDos, dos, tres, cuatro, cincoUno, cincoDos,
                        cincoTres, seis, siete, ocho, nueve, diez, once, doce, trece, catorce, quinceUno, quinceDos, quinceTres, dieciseisUno, dieciseisDos, dieciSiete,
                        dieciOcho, dieciNueve, veinte, veintiUno, veintiDos, veintiTres, veintiCuatro, veintiCinco, veintiSeis, veintiSiete, veintiOcho, veintiNueve,
                        treinta, treintaiUno, treintaiDos, treintaiTres, treintaiCuatro, treintaiCinco, treintaiSeis, treintaiSiete, treintaiOcho, treintaiNueve, cuarenta,
                        cuarentaiUno, cuarentaiDos, cuarentaiTres, cuarentaiCuatro, cuarentaiCinco, cuarentaiSeis, cuarentaiSiete, cuarentaiOcho, cuarentaiNueve, cincuenta,
                        cincuentaiUno, cincuentaiDos, cincuentaiTres;

        public enum estados{ok, error, vacio, constEnt, cadena, booleanR};

        estados estadoaActual = estados.vacio;
        int nameId;

        public int getNameId() { return nameId; }

        public void setNameId(int nameId) { this.nameId = nameId; }

        public estados getEstadoaActual() { return estadoaActual; }

        public void setEstadoaActual(estados estadoaActual) { this.estadoaActual = estadoaActual; }

        public static boolean compare(String tipo, simbolos cima)
        {
            return cima.name().equals(tipo);
        }

        public static boolean isTerminal(simbolos cima)
        {
            return ((49>=cima.ordinal())&&(cima.ordinal()>21));
        }

        public static boolean isSem(simbolos cima)
        {
            return (cima.ordinal()>49);
        }
    }

    //Estos enums sirven para ubicarnos en la matriz de reglas
    private enum simbolosNoTerminales{A,B,C,D,E,F,H,K,L,O,P,Q,R,RR,S,SS,T,U,UU,V,VV,X}
    private enum simbolosTerminales{negacion,asignacionResto,abrePar,cierraPar,suma,coma,dosPuntos,puntoComa,igual,comparacion,booleanR,breakR,cadena,caseR,defaultR,constEnt,function,id,ifR,input,intR,let,print,returnR,string,switchR,abreLlave,cierraLlave,$}

    static Regla[][] tablaM = new Regla[22][29];

    public TablaM() {
        // Tabla para el consecuente
        LinkedList<simbolos> templist;
        // Reglas

        // E -> R RR
        templist = new LinkedList<>();
        templist.add(simbolos.R);
        templist.add(simbolos.RR);
        templist.add(simbolos.cuarenta);
        Regla regla1 = new Regla(simbolos.E, templist, 1);

        // RR -> == R RR
        templist = new LinkedList<>();
        templist.add(simbolos.comparacion);
        templist.add(simbolos.R);
        templist.add(simbolos.RR);
        templist.add(simbolos.cuarentaiUno);
        Regla regla2 = new Regla(simbolos.RR, templist, 2);

        // RR -> lambda
        templist = new LinkedList<>();
        templist.add(simbolos.cuarentaiDos);
        Regla regla3 = new Regla(simbolos.RR, templist, 3);

        // R -> U UU
        templist = new LinkedList<>();
        templist.add(simbolos.U);
        templist.add(simbolos.UU);
        templist.add(simbolos.cuarentaiTres);
        Regla regla4 = new Regla(simbolos.R, templist, 4);

        // UU -> + U UU
        templist = new LinkedList<>();
        templist.add(simbolos.suma);
        templist.add(simbolos.U);
        templist.add(simbolos.UU);
        templist.add(simbolos.cuarentaiCuatro);
        Regla regla5 = new Regla(simbolos.UU, templist, 5);

        // UU -> lambda
        templist = new LinkedList<>();
        templist.add(simbolos.cuarentaiCinco);
        Regla regla6 = new Regla(simbolos.UU, templist, 6);

        // U -> ! V
        templist = new LinkedList<>();
        templist.add(simbolos.negacion);
        templist.add(simbolos.V);
        templist.add(simbolos.cuarentaiSeis);
        Regla regla7 = new Regla(simbolos.U, templist, 7);

        // U -> V
        templist = new LinkedList<>();
        templist.add(simbolos.V);
        templist.add(simbolos.cuarentaiSiete);
        Regla regla8 = new Regla(simbolos.U, templist, 8);

        // V -> id VV
        templist = new LinkedList<>();
        templist.add(simbolos.id);
        templist.add(simbolos.VV);
        templist.add(simbolos.cuarentaiOcho);
        Regla regla9 = new Regla(simbolos.V, templist, 9);

        // V -> ( E )
        templist = new LinkedList<>();
        templist.add(simbolos.abrePar);
        templist.add(simbolos.E);
        templist.add(simbolos.cierraPar);
        templist.add(simbolos.cuarentaiNueve);
        Regla regla10 = new Regla(simbolos.V, templist, 10);

        // V -> constEnt
        templist = new LinkedList<>();
        templist.add(simbolos.constEnt);
        templist.add(simbolos.cincuenta);
        Regla regla11 = new Regla(simbolos.V, templist, 11);

        // V -> cadena
        templist = new LinkedList<>();
        templist.add(simbolos.cadena);
        templist.add(simbolos.cincuentaiUno);
        Regla regla12 = new Regla(simbolos.V, templist, 12);

        // VV -> ( L )
        templist = new LinkedList<>();
        templist.add(simbolos.abrePar);
        templist.add(simbolos.L);
        templist.add(simbolos.cierraPar);
        templist.add(simbolos.cincuentaiDos);
        Regla regla13 = new Regla(simbolos.VV, templist, 13);

        // VV -> lambda
        templist = new LinkedList<>();
        templist.add(simbolos.cincuentaiTres);
        Regla regla14 = new Regla(simbolos.VV, templist, 14);

        // S -> id SS
        templist = new LinkedList<>();
        templist.add(simbolos.id);
        templist.add(simbolos.SS);
        templist.add(simbolos.veintiSiete);
        Regla regla15 = new Regla(simbolos.S, templist, 15);

        // SS -> %= E ;
        templist = new LinkedList<>();
        templist.add(simbolos.asignacionResto);
        templist.add(simbolos.E);
        templist.add(simbolos.puntoComa);
        templist.add(simbolos.veintiOcho);
        Regla regla16 = new Regla(simbolos.SS, templist, 16);

        // SS -> = E ;
        templist = new LinkedList<>();
        templist.add(simbolos.igual);
        templist.add(simbolos.E);
        templist.add(simbolos.puntoComa);
        templist.add(simbolos.veintiNueve);
        Regla regla17 = new Regla(simbolos.SS, templist, 17);

        // SS -> ( L ) ;
        templist = new LinkedList<>();
        templist.add(simbolos.abrePar);
        templist.add(simbolos.L);
        templist.add(simbolos.cierraPar);
        templist.add(simbolos.puntoComa);
        templist.add(simbolos.treinta);
        Regla regla18 = new Regla(simbolos.SS, templist, 18);

        // S -> print R ;
        templist = new LinkedList<>();
        templist.add(simbolos.print);
        templist.add(simbolos.R);
        templist.add(simbolos.puntoComa);
        templist.add(simbolos.treintaiUno);
        Regla regla19 = new Regla(simbolos.S, templist, 19);

        // S -> input id ;
        templist = new LinkedList<>();
        templist.add(simbolos.input);
        templist.add(simbolos.id);
        templist.add(simbolos.puntoComa);
        templist.add(simbolos.treintaiDos);
        Regla regla20 = new Regla(simbolos.S, templist, 20);

        // S -> return X ;
        templist = new LinkedList<>();
        templist.add(simbolos.returnR);
        templist.add(simbolos.X);
        templist.add(simbolos.puntoComa);
        templist.add(simbolos.treintaiTres);
        Regla regla21 = new Regla(simbolos.S, templist, 21);

        // L -> E Q
        templist = new LinkedList<>();
        templist.add(simbolos.E);
        templist.add(simbolos.Q);
        templist.add(simbolos.treintaiCuatro);
        Regla regla22 = new Regla(simbolos.L, templist, 22);

        // L -> lambda
        templist = new LinkedList<>();
        templist.add(simbolos.treintaiCinco);
        Regla regla23 = new Regla(simbolos.L, templist, 23);

        // Q -> , E Q
        templist = new LinkedList<>();
        templist.add(simbolos.coma);
        templist.add(simbolos.E);
        templist.add(simbolos.Q);
        templist.add(simbolos.treintaiSeis);
        Regla regla24 = new Regla(simbolos.Q, templist, 24);

        // Q -> lambda
        templist = new LinkedList<>();
        templist.add(simbolos.treintaiSiete);
        Regla regla25 = new Regla(simbolos.Q, templist, 25);

        // X -> E
        templist = new LinkedList<>();
        templist.add(simbolos.E);
        templist.add(simbolos.treintaiOcho);
        Regla regla26 = new Regla(simbolos.X, templist, 26);

        // X -> lambda
        templist = new LinkedList<>();
        templist.add(simbolos.treintaiNueve);
        Regla regla27 = new Regla(simbolos.X, templist, 27);

        // B -> switch ( E ) { O }
        templist = new LinkedList<>();
        templist.add(simbolos.switchR);
        templist.add(simbolos.quinceUno);
        templist.add(simbolos.abrePar);
        templist.add(simbolos.E);
        templist.add(simbolos.cierraPar);
        templist.add(simbolos.quinceDos);
        templist.add(simbolos.abreLlave);
        templist.add(simbolos.O);
        templist.add(simbolos.cierraLlave);
        templist.add(simbolos.quinceTres);
        Regla regla28 = new Regla(simbolos.B, templist, 28);

        // B -> if ( E ) S
        templist = new LinkedList<>();
        templist.add(simbolos.ifR);
        templist.add(simbolos.abrePar);
        templist.add(simbolos.E);
        templist.add(simbolos.cierraPar);
        templist.add(simbolos.S);
        templist.add(simbolos.catorce);
        Regla regla29 = new Regla(simbolos.B, templist, 29);

        // O -> case constEnt : C D O
        templist = new LinkedList<>();
        templist.add(simbolos.caseR);
        templist.add(simbolos.constEnt);
        templist.add(simbolos.dieciseisUno);
        templist.add(simbolos.dosPuntos);
        templist.add(simbolos.C);
        templist.add(simbolos.D);
        templist.add(simbolos.O);
        templist.add(simbolos.dieciseisDos);
        Regla regla30 = new Regla(simbolos.O, templist, 30);

        // O -> default : C D O
        templist = new LinkedList<>();
        templist.add(simbolos.defaultR);
        templist.add(simbolos.dosPuntos);
        templist.add(simbolos.C);
        templist.add(simbolos.D);
        templist.add(simbolos.O);
        templist.add(simbolos.dieciSiete);
        Regla regla31 = new Regla(simbolos.O, templist, 31);

        // O -> lambda
        templist = new LinkedList<>();
        templist.add(simbolos.dieciOcho);
        Regla regla32 = new Regla(simbolos.O, templist, 32);

        // D -> break ;
        templist = new LinkedList<>();
        templist.add(simbolos.breakR);
        templist.add(simbolos.puntoComa);
        templist.add(simbolos.dieciNueve);
        Regla regla33 = new Regla(simbolos.D, templist, 33);

        // D -> lambda
        templist = new LinkedList<>();
        templist.add(simbolos.veinte);
        Regla regla34 = new Regla(simbolos.D, templist, 34);

        // B -> let id T ;
        templist = new LinkedList<>();
        templist.add(simbolos.let);
        templist.add(simbolos.id);
        templist.add(simbolos.T);
        templist.add(simbolos.puntoComa);
        templist.add(simbolos.veintiUno);
        Regla regla35 = new Regla(simbolos.B, templist, 35);

        // T -> int
        templist = new LinkedList<>();
        templist.add(simbolos.intR);
        templist.add(simbolos.veintiDos);
        Regla regla36 = new Regla(simbolos.T, templist, 36);

        // T -> boolean
        templist = new LinkedList<>();
        templist.add(simbolos.booleanR);
        templist.add(simbolos.veintiTres);
        Regla regla37 = new Regla(simbolos.T, templist, 37);

        // T -> string
        templist = new LinkedList<>();
        templist.add(simbolos.string);
        templist.add(simbolos.veintiCuatro);
        Regla regla38 = new Regla(simbolos.T, templist, 38);

        // B -> S
        templist = new LinkedList<>();
        templist.add(simbolos.S);
        templist.add(simbolos.veintiCinco);
        Regla regla39 = new Regla(simbolos.B, templist, 39);

        // F -> function id H ( A ) { C }
        templist = new LinkedList<>();
        templist.add(simbolos.function);
        templist.add(simbolos.id);
        templist.add(simbolos.cincoUno);
        templist.add(simbolos.H);
        templist.add(simbolos.abrePar);
        templist.add(simbolos.A);
        templist.add(simbolos.cierraPar);
        templist.add(simbolos.cincoDos);
        templist.add(simbolos.abreLlave);
        templist.add(simbolos.C);
        templist.add(simbolos.cierraLlave);
        templist.add(simbolos.cincoTres);
        Regla regla40 = new Regla(simbolos.F, templist, 40);

        // H -> T
        templist = new LinkedList<>();
        templist.add(simbolos.T);
        templist.add(simbolos.seis);
        Regla regla41 = new Regla(simbolos.H, templist, 41);

        // H -> lambda
        templist = new LinkedList<>();
        templist.add(simbolos.siete);
        Regla regla42 = new Regla(simbolos.H, templist, 42);

        // A -> T id K
        templist = new LinkedList<>();
        templist.add(simbolos.T);
        templist.add(simbolos.id);
        templist.add(simbolos.K);
        templist.add(simbolos.ocho);
        Regla regla43 = new Regla(simbolos.A, templist, 43);

        // A -> lambda
        templist = new LinkedList<>();
        templist.add(simbolos.nueve);
        Regla regla44 = new Regla(simbolos.A, templist, 44);

        // K -> , T id K
        templist = new LinkedList<>();
        templist.add(simbolos.coma);
        templist.add(simbolos.T);
        templist.add(simbolos.id);
        templist.add(simbolos.K);
        templist.add(simbolos.diez);
        Regla regla45 = new Regla(simbolos.K, templist, 45);

        // K -> lambda
        templist = new LinkedList<>();
        templist.add(simbolos.once);
        Regla regla46 = new Regla(simbolos.K, templist, 46);

        // C -> B C
        templist = new LinkedList<>();
        templist.add(simbolos.B);
        templist.add(simbolos.C);
        templist.add(simbolos.doce);
        Regla regla47 = new Regla(simbolos.C, templist, 47);

        // C -> lambda
        templist = new LinkedList<>();
        templist.add(simbolos.trece);
        Regla regla48 = new Regla(simbolos.C, templist, 48);

        // P -> B P
        templist = new LinkedList<>();
        templist.add(simbolos.B);
        templist.add(simbolos.P);
        templist.add(simbolos.dos);
        Regla regla49 = new Regla(simbolos.P, templist, 49);

        // P -> F P
        templist = new LinkedList<>();
        templist.add(simbolos.F);
        templist.add(simbolos.P);
        templist.add(simbolos.tres);
        Regla regla50 = new Regla(simbolos.P, templist, 50);

        // P -> lambda
        templist = new LinkedList<>();
        templist.add(simbolos.cuatro);
        Regla regla51 = new Regla(simbolos.R, templist, 51);

        // Añadimos las reglas a la tabla
        // A -> lambda
        tablaM[simbolosNoTerminales.A.ordinal()][simbolosTerminales.cierraPar.ordinal()] = regla44;

        // A -> T id K
        tablaM[simbolosNoTerminales.A.ordinal()][simbolosTerminales.booleanR.ordinal()] = regla43;
        tablaM[simbolosNoTerminales.A.ordinal()][simbolosTerminales.intR.ordinal()] = regla43;
        tablaM[simbolosNoTerminales.A.ordinal()][simbolosTerminales.string.ordinal()] = regla43;

        // B -> S
        tablaM[simbolosNoTerminales.B.ordinal()][simbolosTerminales.id.ordinal()] = regla39;
        tablaM[simbolosNoTerminales.B.ordinal()][simbolosTerminales.returnR.ordinal()] = regla39;
        tablaM[simbolosNoTerminales.B.ordinal()][simbolosTerminales.print.ordinal()] = regla39;
        tablaM[simbolosNoTerminales.B.ordinal()][simbolosTerminales.input.ordinal()] = regla39;

        // B -> if ( E ) S
        tablaM[simbolosNoTerminales.B.ordinal()][simbolosTerminales.ifR.ordinal()] = regla29;

        // B -> switch ( E ) { O }
        tablaM[simbolosNoTerminales.B.ordinal()][simbolosTerminales.switchR.ordinal()] = regla28;

        // B -> let id T ;
        tablaM[simbolosNoTerminales.B.ordinal()][simbolosTerminales.let.ordinal()] = regla35;

        // C -> B C
        tablaM[simbolosNoTerminales.C.ordinal()][simbolosTerminales.id.ordinal()] = regla47;
        tablaM[simbolosNoTerminales.C.ordinal()][simbolosTerminales.ifR.ordinal()] = regla47;
        tablaM[simbolosNoTerminales.C.ordinal()][simbolosTerminales.input.ordinal()] = regla47;
        tablaM[simbolosNoTerminales.C.ordinal()][simbolosTerminales.let.ordinal()] = regla47;
        tablaM[simbolosNoTerminales.C.ordinal()][simbolosTerminales.print.ordinal()] = regla47;
        tablaM[simbolosNoTerminales.C.ordinal()][simbolosTerminales.returnR.ordinal()] = regla47;
        tablaM[simbolosNoTerminales.C.ordinal()][simbolosTerminales.switchR.ordinal()] = regla47;

        // C -> lambda
        tablaM[simbolosNoTerminales.C.ordinal()][simbolosTerminales.cierraLlave.ordinal()] = regla48;
        tablaM[simbolosNoTerminales.C.ordinal()][simbolosTerminales.caseR.ordinal()] = regla48;
        tablaM[simbolosNoTerminales.C.ordinal()][simbolosTerminales.breakR.ordinal()] = regla48;
        tablaM[simbolosNoTerminales.C.ordinal()][simbolosTerminales.defaultR.ordinal()] = regla48;

        // D -> break ;
        tablaM[simbolosNoTerminales.D.ordinal()][simbolosTerminales.breakR.ordinal()] = regla33;

        // D -> lambda
        tablaM[simbolosNoTerminales.D.ordinal()][simbolosTerminales.caseR.ordinal()] = regla34;
        tablaM[simbolosNoTerminales.D.ordinal()][simbolosTerminales.defaultR.ordinal()] = regla34;
        tablaM[simbolosNoTerminales.D.ordinal()][simbolosTerminales.cierraLlave.ordinal()] = regla34;

        // E -> R RR
        tablaM[simbolosNoTerminales.E.ordinal()][simbolosTerminales.negacion.ordinal()] = regla1;
        tablaM[simbolosNoTerminales.E.ordinal()][simbolosTerminales.abrePar.ordinal()] = regla1;
        tablaM[simbolosNoTerminales.E.ordinal()][simbolosTerminales.cadena.ordinal()] = regla1;
        tablaM[simbolosNoTerminales.E.ordinal()][simbolosTerminales.constEnt.ordinal()] = regla1;
        tablaM[simbolosNoTerminales.E.ordinal()][simbolosTerminales.id.ordinal()] = regla1;

        // F -> function id H ( A ) { C }
        tablaM[simbolosNoTerminales.F.ordinal()][simbolosTerminales.function.ordinal()] = regla40;

        // H -> T
        tablaM[simbolosNoTerminales.H.ordinal()][simbolosTerminales.booleanR.ordinal()] = regla41;
        tablaM[simbolosNoTerminales.H.ordinal()][simbolosTerminales.intR.ordinal()] = regla41;
        tablaM[simbolosNoTerminales.H.ordinal()][simbolosTerminales.string.ordinal()] = regla41;

        // H -> lambda
        tablaM[simbolosNoTerminales.H.ordinal()][simbolosTerminales.abrePar.ordinal()] = regla42;

        // K -> , T id K
        tablaM[simbolosNoTerminales.K.ordinal()][simbolosTerminales.coma.ordinal()] = regla45;

        // K -> lambda
        tablaM[simbolosNoTerminales.K.ordinal()][simbolosTerminales.cierraPar.ordinal()] = regla46;

        // L -> lambda
        tablaM[simbolosNoTerminales.L.ordinal()][simbolosTerminales.cierraPar.ordinal()] = regla23;

        // L -> E Q
        tablaM[simbolosNoTerminales.L.ordinal()][simbolosTerminales.id.ordinal()] = regla22;
        tablaM[simbolosNoTerminales.L.ordinal()][simbolosTerminales.constEnt.ordinal()] = regla22;
        tablaM[simbolosNoTerminales.L.ordinal()][simbolosTerminales.cadena.ordinal()] = regla22;
        tablaM[simbolosNoTerminales.L.ordinal()][simbolosTerminales.negacion.ordinal()] = regla22;
        tablaM[simbolosNoTerminales.L.ordinal()][simbolosTerminales.abrePar.ordinal()] = regla22;

        // O -> lambda
        tablaM[simbolosNoTerminales.O.ordinal()][simbolosTerminales.cierraLlave.ordinal()] = regla32;

        // O -> case E : P D O
        tablaM[simbolosNoTerminales.O.ordinal()][simbolosTerminales.caseR.ordinal()] = regla30;

        // O -> default : P D O
        tablaM[simbolosNoTerminales.O.ordinal()][simbolosTerminales.defaultR.ordinal()] = regla31;

        // P -> B P
        tablaM[simbolosNoTerminales.P.ordinal()][simbolosTerminales.id.ordinal()] = regla49;
        tablaM[simbolosNoTerminales.P.ordinal()][simbolosTerminales.ifR.ordinal()] = regla49;
        tablaM[simbolosNoTerminales.P.ordinal()][simbolosTerminales.input.ordinal()] = regla49;
        tablaM[simbolosNoTerminales.P.ordinal()][simbolosTerminales.let.ordinal()] = regla49;
        tablaM[simbolosNoTerminales.P.ordinal()][simbolosTerminales.returnR.ordinal()] = regla49;
        tablaM[simbolosNoTerminales.P.ordinal()][simbolosTerminales.switchR.ordinal()] = regla49;
        tablaM[simbolosNoTerminales.P.ordinal()][simbolosTerminales.print.ordinal()] = regla49;

        // P -> F P
        tablaM[simbolosNoTerminales.P.ordinal()][simbolosTerminales.function.ordinal()] = regla50;

        // P -> lambda
        tablaM[simbolosNoTerminales.P.ordinal()][simbolosTerminales.$.ordinal()] = regla51;

        // Q -> , E Q
        tablaM[simbolosNoTerminales.Q.ordinal()][simbolosTerminales.coma.ordinal()] = regla24;

        // Q -> lambda
        tablaM[simbolosNoTerminales.Q.ordinal()][simbolosTerminales.cierraPar.ordinal()] = regla25;

        // R -> U UU
        tablaM[simbolosNoTerminales.R.ordinal()][simbolosTerminales.abrePar.ordinal()] = regla4;
        tablaM[simbolosNoTerminales.R.ordinal()][simbolosTerminales.cadena.ordinal()] = regla4;
        tablaM[simbolosNoTerminales.R.ordinal()][simbolosTerminales.constEnt.ordinal()] = regla4;
        tablaM[simbolosNoTerminales.R.ordinal()][simbolosTerminales.id.ordinal()] = regla4;
        tablaM[simbolosNoTerminales.R.ordinal()][simbolosTerminales.negacion.ordinal()] = regla4;

        // RR -> == R RR
        tablaM[simbolosNoTerminales.RR.ordinal()][simbolosTerminales.comparacion.ordinal()] = regla2;

        // RR -> lambda
        tablaM[simbolosNoTerminales.RR.ordinal()][simbolosTerminales.cierraPar.ordinal()] = regla3;
        tablaM[simbolosNoTerminales.RR.ordinal()][simbolosTerminales.coma.ordinal()] = regla3;
        tablaM[simbolosNoTerminales.RR.ordinal()][simbolosTerminales.puntoComa.ordinal()] = regla3;

        // S -> return X ;
        tablaM[simbolosNoTerminales.S.ordinal()][simbolosTerminales.returnR.ordinal()] = regla21;

        // S -> print R ;
        tablaM[simbolosNoTerminales.S.ordinal()][simbolosTerminales.print.ordinal()] = regla19;

        // S -> input id ;
        tablaM[simbolosNoTerminales.S.ordinal()][simbolosTerminales.input.ordinal()] = regla20;

        // S -> id SS
        tablaM[simbolosNoTerminales.S.ordinal()][simbolosTerminales.id.ordinal()] = regla15;

        // SS -> ( L ) ;
        tablaM[simbolosNoTerminales.SS.ordinal()][simbolosTerminales.abrePar.ordinal()] = regla18;

        // SS -> %= E
        tablaM[simbolosNoTerminales.SS.ordinal()][simbolosTerminales.asignacionResto.ordinal()] = regla16;

        // SS -> = E ;
        tablaM[simbolosNoTerminales.SS.ordinal()][simbolosTerminales.igual.ordinal()] = regla17;

        // T -> boolean
        tablaM[simbolosNoTerminales.T.ordinal()][simbolosTerminales.booleanR.ordinal()] = regla37;
        // T -> int
        tablaM[simbolosNoTerminales.T.ordinal()][simbolosTerminales.intR.ordinal()] = regla36;
        // T -> string
        tablaM[simbolosNoTerminales.T.ordinal()][simbolosTerminales.string.ordinal()] = regla38;

        // U -> ! V
        tablaM[simbolosNoTerminales.U.ordinal()][simbolosTerminales.negacion.ordinal()] = regla7;

        // U -> V
        tablaM[simbolosNoTerminales.U.ordinal()][simbolosTerminales.abrePar.ordinal()] = regla8;
        tablaM[simbolosNoTerminales.U.ordinal()][simbolosTerminales.cadena.ordinal()] = regla8;
        tablaM[simbolosNoTerminales.U.ordinal()][simbolosTerminales.constEnt.ordinal()] = regla8;
        tablaM[simbolosNoTerminales.U.ordinal()][simbolosTerminales.id.ordinal()] = regla8;

        // UU -> + U UU
        tablaM[simbolosNoTerminales.UU.ordinal()][simbolosTerminales.suma.ordinal()] = regla5;

        // UU -> lambda
        tablaM[simbolosNoTerminales.UU.ordinal()][simbolosTerminales.cierraPar.ordinal()] = regla6;
        tablaM[simbolosNoTerminales.UU.ordinal()][simbolosTerminales.coma.ordinal()] = regla6;
        tablaM[simbolosNoTerminales.UU.ordinal()][simbolosTerminales.puntoComa.ordinal()] = regla6;
        tablaM[simbolosNoTerminales.UU.ordinal()][simbolosTerminales.comparacion.ordinal()] = regla6;

        // V -> ( E )
        tablaM[simbolosNoTerminales.V.ordinal()][simbolosTerminales.abrePar.ordinal()] = regla10;

        // V -> cad
        tablaM[simbolosNoTerminales.V.ordinal()][simbolosTerminales.cadena.ordinal()] = regla12;

        // V -> ent
        tablaM[simbolosNoTerminales.V.ordinal()][simbolosTerminales.constEnt.ordinal()] = regla11;

        // V -> id VV
        tablaM[simbolosNoTerminales.V.ordinal()][simbolosTerminales.id.ordinal()] = regla9;

        // VV -> ( L )
        tablaM[simbolosNoTerminales.VV.ordinal()][simbolosTerminales.abrePar.ordinal()] = regla13;

        // VV -> lambda
        tablaM[simbolosNoTerminales.VV.ordinal()][simbolosTerminales.comparacion.ordinal()] = regla14;
        tablaM[simbolosNoTerminales.VV.ordinal()][simbolosTerminales.cierraPar.ordinal()] = regla14;
        tablaM[simbolosNoTerminales.VV.ordinal()][simbolosTerminales.suma.ordinal()] = regla14;
        tablaM[simbolosNoTerminales.VV.ordinal()][simbolosTerminales.coma.ordinal()] = regla14;
        tablaM[simbolosNoTerminales.VV.ordinal()][simbolosTerminales.puntoComa.ordinal()] = regla14;

        // X -> E
        tablaM[simbolosNoTerminales.X.ordinal()][simbolosTerminales.comparacion.ordinal()] = regla26;
        tablaM[simbolosNoTerminales.X.ordinal()][simbolosTerminales.abrePar.ordinal()] = regla26;
        tablaM[simbolosNoTerminales.X.ordinal()][simbolosTerminales.cadena.ordinal()] = regla26;
        tablaM[simbolosNoTerminales.X.ordinal()][simbolosTerminales.constEnt.ordinal()] = regla26;
        tablaM[simbolosNoTerminales.X.ordinal()][simbolosTerminales.id.ordinal()] = regla26;

        // X -> lambda
        tablaM[simbolosNoTerminales.X.ordinal()][simbolosTerminales.puntoComa.ordinal()] = regla27;
    }

    public Regla getRule(simbolos cima, Token sigTok) {// cima no terminal
        if (sigTok.getTipo().equals("palabraReservada"))
        {
            switch (sigTok.getAtributo())
            {
                case "if":
                    return tablaM[cima.ordinal()][simbolosTerminales.ifR.ordinal()];
                case "return":
                    return tablaM[cima.ordinal()][simbolosTerminales.returnR.ordinal()];
                case "switch":
                    return tablaM[cima.ordinal()][simbolosTerminales.switchR.ordinal()];
                case "case":
                    return tablaM[cima.ordinal()][simbolosTerminales.caseR.ordinal()];
                case "default":
                    return tablaM[cima.ordinal()][simbolosTerminales.defaultR.ordinal()];
                case "int":
                    return tablaM[cima.ordinal()][simbolosTerminales.intR.ordinal()];
                case "boolean":
                    return tablaM[cima.ordinal()][simbolosTerminales.booleanR.ordinal()];
                case "break":
                    return tablaM[cima.ordinal()][simbolosTerminales.breakR.ordinal()];
                default:
                    return tablaM[cima.ordinal()][simbolosTerminales.valueOf(sigTok.getAtributo()).ordinal()];
            }
        }
        else if (sigTok.getTipo().equals("igual")){
            return tablaM[cima.ordinal()][simbolosTerminales.igual.ordinal()];
        }
        else
            return tablaM[cima.ordinal()][simbolosTerminales.valueOf(sigTok.getTipo()).ordinal()];
    }
}