package AnalizadorSemantico;

import AnalizadorLexico.TablaSimbolos;
import AnalizadorLexico.Token;
import AnalizadorSintactico.AnSt;
import AnalizadorSintactico.TablaM;
import AnalizadorSintactico.TablaM.simbolos;

import java.io.PrintWriter;
import java.util.Stack;
import AnalizadorSintactico.TablaM.Regla;
import AnalizadorSintactico.TablaM.Simbolo;
import AnalizadorSintactico.TablaM.estados;

public class AnSm
{
    //TODO: hacer el EdT de la gramática (ON TESTING)
    //TODO: añadir en cada regla una acción semántica que saque de la pilaAux
    // tantos simbolos como haya en el consecuente de la regla (sin contar las acciones semánticas) (DOING)
    //TODO: añadir al enum simbolos las acciones semánticas (DONE)
    //TODO: añadir a las reglas de la TablaM las acciones semánticas (DONE)
    int tablasCreadas = 1;

    public void añadirAtributos(Simbolo simb, Stack<Simbolo> pilaAux)
    {
        pilaAux.push(simb);
    }

    //TODO: método AnSm
    public void ejecutarRegla(TablaSimbolos tablaGlobal, TablaSimbolos tablaLocal, Simbolo simbolo_cima, Stack<Simbolo> pilaAux, PrintWriter ts)
    {
        Simbolo id;
        Simbolo aux;
        Simbolo aux2;
        Simbolo s1;
        Simbolo s2;
        switch (simbolo_cima.getValor())
        {
            case unoUno:
                tablaGlobal = new TablaSimbolos(0);
                break;
            case unoDos:
                tablaGlobal = null;
                break;
            case dos: case tres:
                s1 = pilaAux.pop(); // P2
                s2 = pilaAux.pop(); // B/F
                if (s1.getEstadoActual() == estados.vacio && s2.getEstadoActual() == estados.ok)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else if (s1.getEstadoActual() == estados.ok && s2.getEstadoActual() == estados.ok)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case cuatro: case siete: case nueve: case once: case trece: case dieciOcho: case veinte: case treintaiCinco: case treintaiSiete: case treintaiNueve: case cuarentaiDos: case cuarentaiCinco: case cincuentaiTres:
                pilaAux.peek().setEstadoActual(estados.vacio);
                break;
            case cincoUno:
                AnSt.crearTablaAux(tablasCreadas++);
                break;
            case cincoDos:
                pilaAux.pop();
                pilaAux.pop();
                pilaAux.pop();
                aux = pilaAux.pop(); //H
                aux2 = pilaAux.pop(); //id
                tablaGlobal.insertaTipoTS(aux2.getNameId(), aux.getEstadoActual());
                //TODO etiqueta
                pilaAux.pop();
                break;
            case cincoTres:
                tablaLocal.printTS(ts);
                AnSt.destruirTablaAux();
                break;
            case seis: case veintiCinco: case treintaiOcho: case cuarentaiSiete:
                aux = pilaAux.pop();
                aux2 = pilaAux.pop();
                aux2.setEstadoActual(aux.getEstadoActual());
                pilaAux.push(aux2);
                break;
            case ocho:
                pilaAux.pop();
                aux = pilaAux.pop(); //id
                aux2 = pilaAux.pop(); //T
                if (tablaLocal == null)
                {
                    tablaGlobal.insertaTipoTS(aux.getNameId(), aux2.getEstadoActual());
                    System.out.println(tablaGlobal.get(aux.getNameId() - 1).getLexema());
                    System.out.println(aux.getNameId());
                }//TODO arreglar tabla de simbolos, por que no se declaran bien las cosas XD
                else
                {
                    tablaLocal.insertaTipoTS(aux.getNameId(), aux2.getEstadoActual());
                    System.out.println(tablaLocal.get(aux.getNameId() - 1).getLexema());
                    System.out.println(aux.getNameId());
                }
                break;
            case diez:
                pilaAux.pop();
                aux = pilaAux.pop(); //id
                aux2 = pilaAux.pop(); //T
                if (tablaLocal == null)
                {
                    tablaGlobal.insertaTipoTS(aux.getNameId(), aux2.getEstadoActual());
                    System.out.println(tablaGlobal.get(aux.getNameId() - 1).getLexema());
                    System.out.println(aux.getNameId());
                    pilaAux.pop();
                }
                else
                {
                    tablaLocal.insertaTipoTS(aux.getNameId(), aux2.getEstadoActual());
                    System.out.println(tablaLocal.get(aux.getNameId()).getLexema());
                    System.out.println(aux.getNameId());
                    pilaAux.pop();
                }
                break;
            case doce:
                break;
            case catorce:
                break;
            case quinceUno:
                break;
            case quinceDos:
                break;
            case quinceTres:
                break;
            case dieciseisUno:
                break;
            case dieciseisDos:
                break;
            case dieciSiete:
                break;
            case dieciNueve:
                break;
            case veintiUno:
                pilaAux.pop();
                estados T = pilaAux.pop().getEstadoActual();
                id = pilaAux.pop();
                if (tablaLocal == null)
                {
                    tablaGlobal.insertaTipoTS(id.getNameId(), T);
                    System.out.println(id.getNameId());
                    pilaAux.pop();
                }
                else
                {
                    tablaLocal.insertaTipoTS(id.getNameId(), T);
                    System.out.println(id.getNameId());
                    pilaAux.pop();
                }
                pilaAux.peek().setEstadoActual(estados.ok);
                break;
            case veintiDos: case cincuenta:
                pilaAux.pop();
                pilaAux.peek().setEstadoActual(estados.constEnt);
                break;
            case veintiTres:
                pilaAux.pop();
                pilaAux.peek().setEstadoActual(estados.booleanR);
                break;
            case veintiCuatro: case cincuentaiUno:
                pilaAux.pop();
                pilaAux.peek().setEstadoActual(estados.cadena);
                break;
            case veintiSeis:
                break;
            case veintiSiete:
                break;
            case veintiOcho:
                break;
            case veintiNueve:
                break;
            case treinta:
                break;
            case treintaiUno:
                break;
            case treintaiDos:
                pilaAux.pop(); // ;
                s1 = pilaAux.pop(); // id
                pilaAux.pop(); // input
                if (tablaLocal!=null && tablaLocal.get(s1.getNameId()).getTipo() != estados.booleanR)
                break;
            case treintaiTres:
                pilaAux.pop(); // ;
                s1 = pilaAux.pop(); // X
                pilaAux.pop(); // return
                if (tablaLocal.tipoDeFuncion() == s1.getEstadoActual())
                    pilaAux.peek().setEstadoActual(estados.ok);
                else if (tablaLocal.tipoDeFuncion() == null)
                    //TODO PARCHE SINTACTICO RETURN EN MAIN
                    System.out.println("Error sintactico, return en main");
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case treintaiCuatro:
                s1 = pilaAux.pop(); // Q
                s2 = pilaAux.pop(); // E
                if (s2.getEstadoActual()!=estados.error && s2.getEstadoActual()!=estados.vacio && s1.getEstadoActual() != estados.error)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case treintaiSeis:
                s1 = pilaAux.pop(); // Q2
                s2 = pilaAux.pop(); // E
                pilaAux.pop(); // ,
                if (s2.getEstadoActual()!=estados.error && s2.getEstadoActual()!=estados.vacio && s1.getEstadoActual() != estados.error)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case cuarenta:
                s1 = pilaAux.pop(); // RR
                s2 = pilaAux.pop(); // R
                if (s2.getEstadoActual()!=estados.vacio)
                    pilaAux.peek().setEstadoActual(estados.booleanR);
                else if (s1.getEstadoActual()!=estados.error && s2.getEstadoActual()!=estados.error)
                    pilaAux.peek().setEstadoActual(s2.getEstadoActual());
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case cuarentaiUno:
                s1 = pilaAux.pop(); // RR
                s2 = pilaAux.pop(); // R
                if (s1.getEstadoActual() != estados.error && s2.getEstadoActual() != estados.error)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case cuarentaiTres:
                s1 = pilaAux.pop(); // UU
                s2 = pilaAux.pop(); // U
                if (s1.getEstadoActual() == estados.vacio)
                    pilaAux.peek().setEstadoActual(s2.getEstadoActual());
                else if (s1.getEstadoActual() == estados.constEnt && s2.getEstadoActual() == estados.ok)
                    pilaAux.peek().setEstadoActual(estados.constEnt);
                else
                    pilaAux.peek().setEstadoActual(estados.vacio);
                    break;
            case cuarentaiCuatro:
                s1 = pilaAux.pop(); // UU2
                s2 = pilaAux.pop(); // U
                if (s2.getEstadoActual() == estados.constEnt && s1.getEstadoActual() != estados.error)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                pilaAux.pop();
                break;
            case cuarentaiSeis:
                Simbolo V = pilaAux.pop();
                pilaAux.pop();
                if (V.getEstadoActual() == estados.booleanR)
                    pilaAux.peek().setEstadoActual(estados.booleanR);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case cuarentaiOcho:
                Simbolo VV = pilaAux.pop();
                id = pilaAux.pop();
                if (((tablaLocal != null && tablaLocal.get(id.getNameId()) != null) || tablaGlobal.get(id.getNameId()) != null)
                                                        && VV.getEstadoActual() != estados.error)
                    pilaAux.peek().setEstadoActual(id.getEstadoActual());
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case cuarentaiNueve:
                pilaAux.pop();
                Simbolo E = pilaAux.pop();
                pilaAux.pop();
                pilaAux.peek().setEstadoActual(E.getEstadoActual());
                break;
            case cincuentaiDos:
                pilaAux.pop();
                Simbolo L = pilaAux.pop();
                pilaAux.pop();
                if (L.getEstadoActual() != estados.error)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
        }
    }
    //y extraer sus atributos de la pila
    //meterlo todo en pilaAux 


    //TODO: métodoAnSm2
    //ejecutar la accion semántica y sacarla de la pila
}