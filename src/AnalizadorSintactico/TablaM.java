package AnalizadorSintactico;

import AnalizadorLexico.Token;
import AnalizadorLexico.TablaSimbolos;

import java.util.LinkedList;
import java.util.List;

public class TablaM {
    public static class Regla
    {
        Simbolo antecedente;

        LinkedList<Simbolo> consecuente;
        int numero;

        public Regla(Simbolo antecedente, LinkedList<Simbolo> consecuente, int numero)
        {
            this.antecedente = antecedente;
            this.consecuente = consecuente;
            this.numero = numero;
        }

        public Simbolo getAntecedente() { return antecedente; }

        public LinkedList<Simbolo> getConsecuente() { return consecuente; }

        public int getNumero() { return numero; }
    }


    //Nueva clase para simbolos
    public static class Simbolo{

        simbolos valor;
        estados estadoaActual;
        int nameId; //id.pos -> desplazamiento en contexto de la tabla símbolos
        String lugar;
        String etbreak;
        boolean evaluado;
        String etcase;
        String siguiente;
        String etiq;

        List<String> params;

        public String getEtiq() { return etiq; }

        public List<String> getParams() {return params;}

        public void setParams(List<String> params) {this.params = params;}
        public void setEtiq(String etiq) { this.etiq = etiq; }

        public String getSiguiente() { return siguiente; }

        public void setSiguiente(String siguiente) { this.siguiente = siguiente; }

        public boolean isEvaluado() { return evaluado; }

        public void setEvaluado(boolean evaluado) { this.evaluado = evaluado; }

        public String getEtbreak() { return etbreak; }

        public void setEtbreak(String etbreak) { this.etbreak = etbreak; }

        public String getEtcase() { return etbreak; }

        public void setEtcase(String etcase) { this.etcase = etcase; }

        public String getLugar() { return lugar; }

        public void setLugar(String lugar) { this.lugar = lugar; }

        boolean tipoTabla = true; // true = tsG, false = tsL

        public Simbolo(simbolos valor) { this.valor = valor; }

        /**
         * devuelve su posición en la tabla de símbolos
         */
        public int getNameId() { return nameId; }

        public void setNameId(int nameId) {
            this.nameId = nameId; }

        public estados getEstadoActual() { return estadoaActual; }

        public void setEstadoActual(estados estadoaActual) { this.estadoaActual = estadoaActual; }

        public boolean isTerminal() { return ((52>=valor.ordinal())&&(valor.ordinal()>22)); }

        public boolean isSem() { return (valor.ordinal()>52); }

        public simbolos getValor() { return valor; }

        @Override
        public String toString() {
            return valor.toString();
        }

    }

    //Esta funcion podria ir fuera o dentro
    public static boolean compare(String tipo, Simbolo cima)
    {
            return cima.getValor().name().equals(tipo);
    }

    //Este enum tendria que estar declarado fuera
    public enum estados{ok, error, vacio, constEnt, cadena, booleanR}

    /*22 no terminales, 28 terminales*/
    //Este enum sirve para sacar las reglas
    //TODO eliminar
    public enum simbolos{A,B,C,D,E,F,H,K,L,O,P,PP,Q,R,RR,S,SS,T,U,UU,V,VV,X,negacion,asignacionResto, asignacion, abrePar,cierraPar,suma,coma,dosPuntos,
                        puntoComa,igual,comparacion,booleanR,cadena,caseR,defaultR,constEnt,function,id,input,
                        intR,let,print,returnR,string,switchR,abreLlave,cierraLlave,breakR,ifR,$, unoUno, unoDos, dos, tresUno, tresDos ,cuatro, cincoUno, cincoDos,
                        cincoTres, cincoCuatro , seis, siete, ocho, nueve, diez, once, doce, trece, catorce, catorceUno, quinceUno, quinceDos, quinceTres, dieciseisUno, dieciseisDos, dieciseisTres, dieciSiete, dieciSieteUno,
                        dieciOcho, dieciNueve, veinte, veintiUnoUno, veintiUnoDos, veintiUnoTres, veintiDos, veintiTres, veintiCuatro, veintiCinco, veintiSeis, veintiSiete, veintiOcho, veintiNueve,
                        treinta, treintaiUno, treintaiDos, treintaiTres, treintaiCuatro, treintaiCinco, treintaiSeis, treintaiSiete, treintaiOcho, treintaiNueve, cuarenta,
                        cuarentaiUno, cuarentaiDos, cuarentaiTres, cuarentaiCuatro, cuarentaiCinco, cuarentaiSeis, cuarentaiSiete, cuarentaiOcho, cuarentaiNueve, cincuenta,
                        cincuentaiUno, cincuentaiDos, cincuentaiTres;}

    //Estos enums sirven para ubicarnos en la matriz de reglas
    private enum simbolosNoTerminales{A,B,C,D,E,F,H,K,L,O,P,PP,Q,R,RR,S,SS,T,U,UU,V,VV,X}
    private enum simbolosTerminales{negacion,asignacionResto,abrePar,cierraPar,suma,coma,dosPuntos,puntoComa,igual,comparacion,booleanR,breakR,cadena,caseR,defaultR,constEnt,function,id,ifR,input,intR,let,print,returnR,string,switchR,abreLlave,cierraLlave,$}

    static Regla[][] tablaM = new Regla[23][29];

    public TablaM() {
        // Tabla para el consecuente
        LinkedList<Simbolo> templist;
        // Reglas

        // E -> R RR
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.R));
        templist.add(new Simbolo(simbolos.RR));
        templist.add(new Simbolo(simbolos.cuarenta));
        Regla regla1 = new Regla(new Simbolo(simbolos.E), templist, 1);

        // RR -> == R RR
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.comparacion));
        templist.add(new Simbolo(simbolos.R));
        templist.add(new Simbolo(simbolos.RR));
        templist.add(new Simbolo(simbolos.cuarentaiUno));
        Regla regla2 = new Regla(new Simbolo(simbolos.RR), templist, 2);

        // RR -> lambda
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.cuarentaiDos));
        Regla regla3 = new Regla(new Simbolo(simbolos.RR), templist, 3);

        // R -> U UU
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.U));
        templist.add(new Simbolo(simbolos.UU));
        templist.add(new Simbolo(simbolos.cuarentaiTres));
        Regla regla4 = new Regla(new Simbolo(simbolos.R), templist, 4);

        // UU -> + U UU
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.suma));
        templist.add(new Simbolo(simbolos.U));
        templist.add(new Simbolo(simbolos.UU));
        templist.add(new Simbolo(simbolos.cuarentaiCuatro));
        Regla regla5 = new Regla(new Simbolo(simbolos.UU), templist, 5);

        // UU -> lambda
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.cuarentaiCinco));
        Regla regla6 = new Regla(new Simbolo(simbolos.UU), templist, 6);

        // U -> ! V
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.negacion));
        templist.add(new Simbolo(simbolos.V));
        templist.add(new Simbolo(simbolos.cuarentaiSeis));
        Regla regla7 = new Regla(new Simbolo(simbolos.U), templist, 7);

        // U -> V
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.V));
        templist.add(new Simbolo(simbolos.cuarentaiSiete));
        Regla regla8 = new Regla(new Simbolo(simbolos.U), templist, 8);

        // V -> id VV
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.id));
        templist.add(new Simbolo(simbolos.VV));
        templist.add(new Simbolo(simbolos.cuarentaiOcho));
        Regla regla9 = new Regla(new Simbolo(simbolos.V), templist, 9);

        // V -> ( E )
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.abrePar));
        templist.add(new Simbolo(simbolos.E));
        templist.add(new Simbolo(simbolos.cierraPar));
        templist.add(new Simbolo(simbolos.cuarentaiNueve));
        Regla regla10 = new Regla(new Simbolo(simbolos.V), templist, 10);

        // V -> constEnt
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.constEnt));
        templist.add(new Simbolo(simbolos.cincuenta));
        Regla regla11 = new Regla(new Simbolo(simbolos.V), templist, 11);

        // V -> cadena
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.cadena));
        templist.add(new Simbolo(simbolos.cincuentaiUno));
        Regla regla12 = new Regla(new Simbolo(simbolos.V), templist, 12);

        // VV -> ( L )
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.abrePar));
        templist.add(new Simbolo(simbolos.L));
        templist.add(new Simbolo(simbolos.cierraPar));
        templist.add(new Simbolo(simbolos.cincuentaiDos));
        Regla regla13 = new Regla(new Simbolo(simbolos.VV), templist, 13);

        // VV -> lambda
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.cincuentaiTres));
        Regla regla14 = new Regla(new Simbolo(simbolos.VV), templist, 14);

        // S -> id SS
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.id));
        templist.add(new Simbolo(simbolos.SS));
        templist.add(new Simbolo(simbolos.veintiSiete));
        Regla regla15 = new Regla(new Simbolo(simbolos.S), templist, 15);

        // SS -> %= E ;
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.asignacionResto));
        templist.add(new Simbolo(simbolos.E));
        templist.add(new Simbolo(simbolos.puntoComa));
        templist.add(new Simbolo(simbolos.veintiOcho));
        Regla regla16 = new Regla(new Simbolo(simbolos.SS), templist, 16);


        // SS -> = E ;
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.igual));
        templist.add(new Simbolo(simbolos.E));
        templist.add(new Simbolo(simbolos.puntoComa));
        templist.add(new Simbolo(simbolos.veintiNueve));
        Regla regla17 = new Regla(new Simbolo(simbolos.SS), templist, 17);

        // SS -> ( L ) ;
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.abrePar));
        templist.add(new Simbolo(simbolos.L));
        templist.add(new Simbolo(simbolos.cierraPar));
        templist.add(new Simbolo(simbolos.puntoComa));
        templist.add(new Simbolo(simbolos.treinta));
        Regla regla18 = new Regla(new Simbolo(simbolos.SS), templist, 18);

        // S -> print R ;
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.print));
        templist.add(new Simbolo(simbolos.R));
        templist.add(new Simbolo(simbolos.puntoComa));
        templist.add(new Simbolo(simbolos.treintaiUno));
        Regla regla19 = new Regla(new Simbolo(simbolos.S), templist, 19);

        // S -> input id ;
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.input));
        templist.add(new Simbolo(simbolos.id));
        templist.add(new Simbolo(simbolos.puntoComa));
        templist.add(new Simbolo(simbolos.treintaiDos));
        Regla regla20 = new Regla(new Simbolo(simbolos.S), templist, 20);

        // S -> return X ;
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.returnR));
        templist.add(new Simbolo(simbolos.X));
        templist.add(new Simbolo(simbolos.puntoComa));
        templist.add(new Simbolo(simbolos.treintaiTres));
        Regla regla21 = new Regla(new Simbolo(simbolos.S), templist, 21);

        // L -> E Q
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.E));
        templist.add(new Simbolo(simbolos.Q));
        templist.add(new Simbolo(simbolos.treintaiCuatro));
        Regla regla22 = new Regla(new Simbolo(simbolos.L), templist, 22);

        // L -> lambda
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.treintaiCinco));
        Regla regla23 = new Regla(new Simbolo(simbolos.L), templist, 23);

        // Q -> , E Q
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.coma));
        templist.add(new Simbolo(simbolos.E));
        templist.add(new Simbolo(simbolos.Q));
        templist.add(new Simbolo(simbolos.treintaiSeis));
        Regla regla24 = new Regla(new Simbolo(simbolos.Q), templist, 24);

        // Q -> lambda
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.treintaiSiete));
        Regla regla25 = new Regla(new Simbolo(simbolos.Q), templist, 25);

        // X -> E
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.E));
        templist.add(new Simbolo(simbolos.treintaiOcho));
        Regla regla26 = new Regla(new Simbolo(simbolos.X), templist, 26);

        // X -> lambda
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.treintaiNueve));
        Regla regla27 = new Regla(new Simbolo(simbolos.X), templist, 27);

        // B -> switch ( E ) { O }
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.switchR));
        templist.add(new Simbolo(simbolos.quinceUno));
        templist.add(new Simbolo(simbolos.abrePar));
        templist.add(new Simbolo(simbolos.E));
        templist.add(new Simbolo(simbolos.cierraPar));
        templist.add(new Simbolo(simbolos.quinceDos));
        templist.add(new Simbolo(simbolos.abreLlave));
        templist.add(new Simbolo(simbolos.O));
        templist.add(new Simbolo(simbolos.cierraLlave));
        templist.add(new Simbolo(simbolos.quinceTres));
        Regla regla28 = new Regla(new Simbolo(simbolos.B), templist, 28);

        // B -> if ( E ) S
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.ifR));
        templist.add(new Simbolo(simbolos.abrePar));
        templist.add(new Simbolo(simbolos.E));
        templist.add(new Simbolo(simbolos.cierraPar));
        templist.add(new Simbolo(simbolos.catorceUno));
        templist.add(new Simbolo(simbolos.S));
        templist.add(new Simbolo(simbolos.catorce));
        Regla regla29 = new Regla(new Simbolo(simbolos.B), templist, 29);

        // O -> case constEnt : C D O
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.caseR));
        templist.add(new Simbolo(simbolos.constEnt));
        templist.add(new Simbolo(simbolos.dieciseisUno));
        templist.add(new Simbolo(simbolos.dosPuntos));
        templist.add(new Simbolo(simbolos.C));
        templist.add(new Simbolo(simbolos.D));
        templist.add(new Simbolo(simbolos.dieciseisTres));
        templist.add(new Simbolo(simbolos.O));
        templist.add(new Simbolo(simbolos.dieciseisDos));
        Regla regla30 = new Regla(new Simbolo(simbolos.O), templist, 30);

        // O -> default : C D O
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.defaultR));
        templist.add(new Simbolo(simbolos.dosPuntos));
        templist.add(new Simbolo(simbolos.dieciSieteUno));
        templist.add(new Simbolo(simbolos.C));
        templist.add(new Simbolo(simbolos.D));
        templist.add(new Simbolo(simbolos.O));
        templist.add(new Simbolo(simbolos.dieciSiete));
        Regla regla31 = new Regla(new Simbolo(simbolos.O), templist, 31);

        // O -> lambda
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.dieciOcho));
        Regla regla32 = new Regla(new Simbolo(simbolos.O), templist, 32);

        // D -> break ;
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.breakR));
        templist.add(new Simbolo(simbolos.puntoComa));
        templist.add(new Simbolo(simbolos.dieciNueve));
        Regla regla33 = new Regla(new Simbolo(simbolos.D), templist, 33);

        // D -> lambda
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.veinte));
        Regla regla34 = new Regla(new Simbolo(simbolos.D), templist, 34);

        // B -> let id T ;
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.veintiUnoUno));
        templist.add(new Simbolo(simbolos.let));
        templist.add(new Simbolo(simbolos.id));
        templist.add(new Simbolo(simbolos.T));
        templist.add(new Simbolo(simbolos.veintiUnoDos));
        templist.add(new Simbolo(simbolos.puntoComa));
        templist.add(new Simbolo(simbolos.veintiUnoTres));
        Regla regla35 = new Regla(new Simbolo(simbolos.B), templist, 35);

        // T -> int
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.intR));
        templist.add(new Simbolo(simbolos.veintiDos));
        Regla regla36 = new Regla(new Simbolo(simbolos.T), templist, 36);

        // T -> boolean
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.booleanR));
        templist.add(new Simbolo(simbolos.veintiTres));
        Regla regla37 = new Regla(new Simbolo(simbolos.T), templist, 37);

        // T -> string
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.string));
        templist.add(new Simbolo(simbolos.veintiCuatro));
        Regla regla38 = new Regla(new Simbolo(simbolos.T), templist, 38);

        // B -> S
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.S));
        templist.add(new Simbolo(simbolos.veintiCinco));
        Regla regla39 = new Regla(new Simbolo(simbolos.B), templist, 39);

        // F -> function id H ( A ) { C }
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.function));
        templist.add(new Simbolo(simbolos.id));
        templist.add(new Simbolo(simbolos.cincoUno));
        templist.add(new Simbolo(simbolos.H));
        templist.add(new Simbolo(simbolos.abrePar));
        templist.add(new Simbolo(simbolos.A));
        templist.add(new Simbolo(simbolos.cierraPar));
        templist.add(new Simbolo(simbolos.cincoDos));
        templist.add(new Simbolo(simbolos.abreLlave));
        templist.add(new Simbolo(simbolos.C));
        templist.add(new Simbolo(simbolos.cincoTres));
        templist.add(new Simbolo(simbolos.cierraLlave));
        templist.add(new Simbolo(simbolos.cincoCuatro));
        Regla regla40 = new Regla(new Simbolo(simbolos.F), templist, 40);

        // H -> T
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.T));
        templist.add(new Simbolo(simbolos.seis));
        Regla regla41 = new Regla(new Simbolo(simbolos.H), templist, 41);

        // H -> lambda
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.siete));
        Regla regla42 = new Regla(new Simbolo(simbolos.H), templist, 42);

        // A -> T id K
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.T));
        templist.add(new Simbolo(simbolos.id));
        templist.add(new Simbolo(simbolos.K));
        templist.add(new Simbolo(simbolos.ocho));
        Regla regla43 = new Regla(new Simbolo(simbolos.A), templist, 43);

        // A -> lambda
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.nueve));
        Regla regla44 = new Regla(new Simbolo(simbolos.A), templist, 44);

        // K -> , T id K
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.coma));
        templist.add(new Simbolo(simbolos.T));
        templist.add(new Simbolo(simbolos.id));
        templist.add(new Simbolo(simbolos.K));
        templist.add(new Simbolo(simbolos.diez));
        Regla regla45 = new Regla(new Simbolo(simbolos.K), templist, 45);

        // K -> lambda
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.once));
        Regla regla46 = new Regla(new Simbolo(simbolos.K), templist, 46);

        // C -> B C
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.B));
        templist.add(new Simbolo(simbolos.C));
        templist.add(new Simbolo(simbolos.doce));
        Regla regla47 = new Regla(new Simbolo(simbolos.C), templist, 47);

        // C -> lambda
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.trece));
        Regla regla48 = new Regla(new Simbolo(simbolos.C), templist, 48);

        // P -> B P
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.B));
        templist.add(new Simbolo(simbolos.P));
        templist.add(new Simbolo(simbolos.dos));
        Regla regla49 = new Regla(new Simbolo(simbolos.P), templist, 49);

        // P -> F P
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.tresUno));
        templist.add(new Simbolo(simbolos.F));
        templist.add(new Simbolo(simbolos.P));
        templist.add(new Simbolo(simbolos.tresDos));
        Regla regla50 = new Regla(new Simbolo(simbolos.P), templist, 50);

        // P -> lambda
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.cuatro));
        Regla regla51 = new Regla(new Simbolo(simbolos.R), templist, 51);

        //PP -> P
        templist = new LinkedList<>();
        templist.add(new Simbolo(simbolos.unoUno));
        templist.add(new Simbolo(simbolos.P));
        templist.add(new Simbolo(simbolos.unoDos));
        Regla regla52 = new Regla(new Simbolo(simbolos.PP), templist, 52);

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

        // PP -> P
        tablaM[simbolosNoTerminales.PP.ordinal()][simbolosTerminales.id.ordinal()] = regla52;
        tablaM[simbolosNoTerminales.PP.ordinal()][simbolosTerminales.ifR.ordinal()] = regla52;
        tablaM[simbolosNoTerminales.PP.ordinal()][simbolosTerminales.input.ordinal()] = regla52;
        tablaM[simbolosNoTerminales.PP.ordinal()][simbolosTerminales.function.ordinal()] = regla52;
        tablaM[simbolosNoTerminales.PP.ordinal()][simbolosTerminales.let.ordinal()] = regla52;
        tablaM[simbolosNoTerminales.PP.ordinal()][simbolosTerminales.print.ordinal()] = regla52;
        tablaM[simbolosNoTerminales.PP.ordinal()][simbolosTerminales.returnR.ordinal()] = regla52;
        tablaM[simbolosNoTerminales.PP.ordinal()][simbolosTerminales.switchR.ordinal()] = regla52;
        tablaM[simbolosNoTerminales.PP.ordinal()][simbolosTerminales.$.ordinal()] = regla52;

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
        tablaM[simbolosNoTerminales.X.ordinal()][simbolosTerminales.negacion.ordinal()] = regla26;
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