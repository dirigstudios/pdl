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

    class EntradaFuncion extends Entrada
    {
        int etiqueta;
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
        return tablaSimbolos.get(id);
    }

    public void insertaTipoTS(int id, estados estado)
    {
        tablaSimbolos.get(id).setTipo(estado);
    }

    public int size()
    {
        return tablaSimbolos.size();
    }

    public void printTS(PrintWriter ts)
    {
        ts.println("#" + idTabla + ":");
        for(Entrada entrada : tablaSimbolos)
            ts.println("    * lexema: '" + entrada.getLexema() + "'" + "     tipo: " + ((entrada.getTipo()==null)?"no declarado":entrada.getTipo()));
    }
}