package AnalizadorLexico;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Character;
import java.util.Scanner;
import AnalizadorLexico.Token;

public class pdl {

    public static enum Estados{Inicial, AsignacionR, ConstanteNumerica, Cadena, PalabraReservada, Comparacion, Asignacion,
        AbrePar, CierraPar, AbreLlave, CierraLlave, Igual, PuntoComa, DosPuntos, Coma, Negacion};
    public static Estados estadoactual = Estados.Inicial;
    public static  ArrayList<String> TPR = new ArrayList<>();
    public static  boolean leerSigCaracter = true;
    public static PrintWriter fdSalida;

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
            return Estados.AbreLlave;
        if (c == '}')
            return Estados.CierraLlave;
        if (c == ':')
            return Estados.DosPuntos;
        if (c == ';')
            return Estados.PuntoComa;
        if (c == ',')
            return Estados.Coma;
        if (c == '!')
            return Estados.Negacion;
        return Estados.Inicial;
    }

    public static void automata(String line, PrintWriter fd)
    {
        int i = 0;
        char c = 0;
        String lex = "";
        int counter = 0;
        while(i < line.length())
        {
            if (leerSigCaracter)
            {
                c = line.charAt(i);
                i++;
            }
            else
                leerSigCaracter = true;
            switch (estadoactual) {
                case Inicial:                       // Estado S
                {
                    lex.concat(String.valueOf(c));
                    estadoactual = nextStage(c);
                    leerSigCaracter = false;
                    break;
                }
                case PalabraReservada:             // Estado T
                {
                    if (TPR.contains(lex))          //TODO pensar como implementar la condicion de que el sig caracter sea del, sin salir del index del la linea
                    {
                        Token.genToken("palabraReservada", lex, fd);
                        lex = "";
                        estadoactual = Estados.Inicial;
                        leerSigCaracter = false;
                    }
                    else
                        lex += String.valueOf(c);
                    break;
                }
                case Cadena:             // Estado A
                {
                    if (((int) c == 0 || c == '"') && counter <= 64)
                    {
                        Token.genToken("cadena", lex, fd);
                        lex = "";
                        counter = 0;
                        estadoactual = Estados.Inicial;
                    }
                    else
                    {
                        lex.concat(String.valueOf(c));
                        estadoactual = Estados.Cadena;
                    }
                    break;
                }
                case ConstanteNumerica:             // Estado B
                {
                    if (Character.isDigit(c))
                        counter *= 10 + Character.getNumericValue(c);
                    else if (counter > 32767)
                        System.out.println("Error, valor numerico excede los limites");
                    else if (!Character.isDigit(c))
                    {
                        Token.genToken("constEnt", Integer.toString(counter), fd);
                        counter = 0;
                        estadoactual = Estados.Inicial;
                        leerSigCaracter = false;
                    }

                }
                case AsignacionR: {

                }

            }
        }
    }
}