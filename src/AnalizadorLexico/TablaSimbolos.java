package AnalizadorLexico;

import java.io.PrintWriter;
import java.util.*;
import AnalizadorSintactico.TablaM.estados;

public class TablaSimbolos 
{
    public class Entrada
    {
        private String lexema;
        private estados tipo;
        private int desplazamiento; //cuanto ocupa el tipo de variable
        ArrayList<estados> parametros  = new ArrayList<>();;
        boolean function = false;
        int id;

        public String getEtiqueta()
        {
            if (function)
                return "Et" + lexema;
            else
                return null;
        }

        public int getId() { return id; }

        public void setId(int id) { this.id = id; }

        public void setFunction()
        {
            function = true;
        }

        public boolean isFunction()
        {
            return function;
        }

        public void anadirParametro(estados estado) { parametros.add(estado); }

        public String getLexema()
        {
            return lexema;
        }

        public void setLexema(String lexema) { this.lexema = lexema; }

        public estados getTipo() {
            return tipo;
        }

        public void setTipo(estados tipo) {
            this.tipo = tipo;
        }

        public int getDesplazamiento() {
            return desplazamiento;
        }

        public void setDesplazamiento(int desplazamiento) {
            this.desplazamiento = desplazamiento;
        }
    }

    public int idTabla;
    public List<Entrada> tablaSimbolos;
    public int idLocal;
    public int parametros;
    public int getParametros() {
        return parametros;
    }

    public void setParametros(int parametros) {
        this.parametros = parametros;
    }

    public int getIdLocal() { return idLocal; }

    public void setIdLocal(int idLocal) { this.idLocal = idLocal; }

    public TablaSimbolos(int idTabla)
    {
        this.idTabla = idTabla;
        tablaSimbolos = new ArrayList<>();
    }

    public int getIdTabla() {
        return idTabla;
    }

    public boolean containsKey(String str)
    {
        if (tablaSimbolos.size() == 0)
            return false;
        for (Entrada e : tablaSimbolos)
        {
            if (e.getLexema().equals(str))
                return true;
        }
        return false;
    }

    public void put(String k, int id)
    {
        Entrada aux = new Entrada();
        aux.setLexema(k);
        aux.setId(id);
        aux.setTipo(estados.constEnt);
        aux.setDesplazamiento(1);
        tablaSimbolos.add(aux);
    }

    public int putnew(estados estado){
        Entrada aux = new Entrada();
        aux.setLexema("temp" + tablaSimbolos.size() + 1);
        aux.setId(tablaSimbolos.size() + 1);
        tablaSimbolos.add(aux);
        insertaTipoTS(aux.id, estado);
        return aux.id;
    }

    public Entrada get(String str)
    {
        for (Entrada e : tablaSimbolos)
        {
            if (e.getLexema().equals(str))
                return e;
        }
        return null;
    }

    public Entrada get(int id)
    {
        Entrada ret = null;
//        if (id >= size() || id < 0 || size() == 0)
//            return null;
        for(Entrada e : tablaSimbolos)
        {
            if (e.getId() == id)
                ret = e;
        }
        return ret;
    }

    public void insertaTipoTS(int id, estados estado)
    {
        Entrada aux = null;
        for(Entrada e : tablaSimbolos)
        {
            if (e.getId() == id)
                aux = e;
        }
        if (aux == null)
            return;
        switch (estado)
        {
            case booleanR: case constEnt:
                aux.setDesplazamiento(1);
                break;
            case cadena:
                aux.setDesplazamiento(1); //TODO: esto no es cierto
                break;
            default:
                break;
        }
        aux.setTipo(estado);
    }

    public String buscaLugarTS(int idpos) {
        return this.getIdTabla() + "." + this.get(idpos).getId();
    }
    public String buscaLugarTS(String lex){
        return this.getIdTabla() + "." + this.get(lex).getId();
    }

    public int size()
    {
        return tablaSimbolos.size();
    }

    /**
     * Esta funcion devuelve el desplazamiento total dentro de la tabla de simbolos de un id
     * @param pos la posicion del elemento de la tabla del que se quiere sacar el desplazamiento
     * @return el desplazamiento total
     */
    public int getDesplazamiento(int pos) {
        int total_despl = 0;
        int count = 1;
        //va iterando sobre la tabla hasta que llega al id
        while(count <= pos)
        {
            total_despl = total_despl + get(count).getDesplazamiento();
            count++;
        }
        return total_despl;
    }

    public void printTS(PrintWriter ts) {
        ts.println("#" + idTabla + ":");
        int desplAC = 0;
        for (Entrada entrada : tablaSimbolos) {
            if (entrada.isFunction())
            {
                ts.println("* LEXEMA : '" + entrada.getLexema() + "'" + "\n\tATRIBUTOS :\n\t\t+tipo : '" + "funcion'" + "\n\t\t +numParam : " + entrada.parametros.size());
                for ( int c = 0 ; c < entrada.parametros.size(); c++ )
                {
                    ts.println("\t\t  +TipoParam" + (c + 1) + " : '" + entrada.parametros.get(c) + "'");
                    ts.println("\t\t   +ModoParam" + (c + 1) + " : " + "1");
                }
                ts.println("\t\t+TipoRetorno : '" + entrada.getTipo() + "'");
                ts.println("\t\t+EtiqFuncion : 'Et" + entrada.getLexema() + "01'");
            }
            else
            {
                ts.println("* LEXEMA : '" + entrada.getLexema() + "'" + "\n\tATRIBUTOS :\n\t\t+tipo : '" + ((entrada.getTipo() == null) ? "no declarado" : entrada.getTipo()) + "'\n\t\t+despl : " + desplAC);
                desplAC = desplAC + entrada.desplazamiento;
            }
        }
    }
}