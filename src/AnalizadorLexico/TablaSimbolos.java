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
        private int desplazamiento;
        ArrayList<estados> parametros  = new ArrayList<>();;
        boolean function = false;

        public void setFunction()
        {
            function = true;
        }

        public boolean isFunction()
        {
            return function;
        }

        public void añadirParametro(estados estado) { parametros.add(estado); }

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

    public void put(String k)
    {
        Entrada aux = new Entrada();
        aux.setLexema(k);
        tablaSimbolos.add(aux);
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
        if (id >= size() || id < 0 || size() == 0)
            return null;
        return tablaSimbolos.get(id);
    }

    public void insertaTipoTS(int id, estados estado)
    {
        switch (estado)
        {
            case booleanR: case constEnt:
                tablaSimbolos.get(id).setDesplazamiento(1);
                break;
            case cadena:
                tablaSimbolos.get(id).setDesplazamiento(64);
                break;
            default:
                break;
        }
        tablaSimbolos.get(id).setTipo(estado);
    }

    public int size()
    {
        return tablaSimbolos.size();
    }

    public void printTS(PrintWriter ts) {
        ts.println("#" + idTabla + ":");
        int desplAC = 0;
        for (Entrada entrada : tablaSimbolos) {
            if (entrada.isFunction())
            {
                ts.println("* LEXEMA : '" + entrada.getLexema() + "'" + "\n\tATRIBUTOS :\n\t\t+tipo : '" + "funcion'" + "\n\t\t +numParam : " + entrada.parametros.size());
                for ( int c = 0 ; c < entrada.parametros.size() ; c++ )
                {
                    ts.println("\t\t  +TipoParam" + c + " : '" + entrada.parametros.get(c) + "'");
                    ts.println("\t\t   +ModoParam" + c + " : " + "1");
                }
                ts.println("\t\t+TipoRetorno : '" + entrada.getTipo() + "'");
                ts.println("\t\t+EtiqFuncion : 'Et" + entrada.getLexema() + "01'");
            }
            else
            {
                desplAC = desplAC + entrada.desplazamiento;
                ts.println("* LEXEMA : '" + entrada.getLexema() + "'" + "\n\tATRIBUTOS :\n\t\t+tipo : '" + ((entrada.getTipo() == null) ? "no declarado" : entrada.getTipo()) + "'\n\t\t+despl : " + desplAC);
            }
        }
    }
}