import jdk.nashorn.internal.parser.Token;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Character;
import java.util.Scanner;

public class pdl {

    public static enum Estados{Inicial, AsignacionR, ConstanteNumerica, Cadena, PalabraReservada, Comparacion};
    public static Estados estadoactual = Estados.Inicial;
    public static final ArrayList<String> TPR = new ArrayList<>();
    public static  boolean leerSigCaracter = true;

    public static boolean isDel(char c)
    {
        if (c == '\n' || c == '\t' || c == ' ' || c == ';')
            return true;
        return false;
    }

    public static void initializeTPR()
    {
        TPR.add("boolean");
        TPR.add("break");
        TPR.add("case");
        TPR.add("function");
        TPR.add("if");
        TPR.add("input");
        TPR.add("int");
        TPR.add("let");
        TPR.add("print");
        TPR.add("return");
        TPR.add("string");
        TPR.add("switch");
    }

    public static Estados nextStage(char c)
    {
        if (Character.isLowerCase(c))
            return Estados.PalabraReservada;
        if (c == '\"')
            return Estados.Cadena;
        if (Character.isDigit(c))
            return Estados.ConstanteNumerica;
        if (c == '%')
            return Estados.AsignacionR;
        if (c == '=')
            return Estados.Comparacion;
        if (c == '{')
            return Estados.Cadena;
        if (c == '}')
            return Estados.Cadena;
        if (c == ':')
            return Estados.Cadena;
        if (c == ';')
            return Estados.Cadena;
        if (c == '\'')
            return Estados.Cadena;
        if (c == '!')
            return Estados.Cadena;
        return Estados.Inicial;
    }

    public static void automata(int stage, String line, int pos)
    {
        int i = 0;
        char c;
        String tok = "";
        int counter = 0;
        while(line.charAt(i) != '\n' && i < line.length(){
            if (leerSigCaracter)
            {
                c = line.charAt(i);
                leerSigCaracter = false;
            }
            switch (estadoactual) {
                case Inicial:             // Estado S
                {
                    //caracterActual = line.charAt(i);
                    //tok.concat(String.valueOf(caracterActual));
                    estadoactual = nextStage(c);
                    break;
                }
                case PalabraReservada:             // Estado T
                {
                    if (!Character.isLowerCase(c))
                    {
                        //genToken(palabraReservada, tok);
                        tok = "";
                        estadoactual = Estados.Inicial;
                        leerSigCaracter = false;
                    }
                    else
                    {
                        tok.concat(String.valueOf(c));
                    }
                }
                case Cadena:             // Estado A
                {

                }
                case ConstanteNumerica:             // Estado B
                {

                }
                case AsignacionR:
                {

                }

            }
            i++;
        }
    }
    
    public static void main(String[] args)
    {
        Scanner fichero = new Scanner("../tests/ejemplo");
        automata(1, fichero);
    }
}