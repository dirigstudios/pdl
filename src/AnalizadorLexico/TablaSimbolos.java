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
        for (Entrada entrada : tablaSimbolos) {
            if (entrada.isFunction())
            {
                ts.print("    * lexema: '" + entrada.getLexema() + "'" + "     tipo de return : " + ((entrada.getTipo() == null) ? "no declarado" : entrada.getTipo()) + "    Argumentos : ");

                int c;
                for ( c = 0 ; c < entrada.parametros.size() ; c++ )
                {
                    ts.print(entrada.parametros.get(c) + " ");
                }
                ts.println("    Numero de Argumentos : " + c);
            }
            else
            {
                ts.println("    * lexema: '" + entrada.getLexema() + "'" + "     tipo: " + ((entrada.getTipo() == null) ? "no declarado" : entrada.getTipo()) + "  desplazamiento : " + entrada.desplazamiento);
            }
        }
    }
}