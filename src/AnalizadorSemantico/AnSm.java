package AnalizadorSemantico;

import AnalizadorLexico.TablaSimbolos;
import AnalizadorLexico.Token;
import AnalizadorSintactico.AnSt;
import AnalizadorSintactico.TablaM;
import AnalizadorSintactico.TablaM.simbolos;

import java.io.PrintWriter;
import java.util.List;
import java.util.Stack;
import AnalizadorSintactico.TablaM.Regla;
import AnalizadorSintactico.TablaM.Simbolo;
import AnalizadorSintactico.TablaM.estados;

public class AnSm
{
    //TODO: hacer el EdT de la gramática (ON TESTING)
    int tablasCreadas = 1;

    List<List<Integer>> cases_etiq;

    public void añadirAtributos(Simbolo simb, Stack<Simbolo> pilaAux)
    {
        pilaAux.push(simb);
    }

    public void ejecutarRegla(TablaSimbolos tablaGlobal, TablaSimbolos tablaLocal, Simbolo simbolo_cima, Stack<Simbolo> pilaAux, PrintWriter ts)
    {
        Simbolo id;
        Simbolo aux;
        Simbolo aux2;
        Simbolo s1;
        Simbolo s2;
        //aux para el switch -> acc sem 15
        Simbolo a1, a2, a3, a4, a5, a6, a7;
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
                {
                    System.out.println("ERROR\n");
                    pilaAux.peek().setEstadoActual(estados.error);
                }
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
                    System.out.println(tablaGlobal.get(aux.getNameId()).getLexema());
                    System.out.println(aux.getNameId());
                }
                else
                {
                    tablaLocal.insertaTipoTS(aux.getNameId(), aux2.getEstadoActual());
                    System.out.println(tablaLocal.get(aux.getNameId()).getLexema());
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
                    System.out.println(tablaGlobal.get(aux.getNameId()).getLexema());
                    System.out.println(aux.getNameId());
                    pilaAux.pop();
                }
                else
                {
                    tablaLocal.insertaTipoTS(aux.getNameId(), aux2.getEstadoActual());
                    System.out.println(tablaLocal.get(aux.getNameId() - 1).getLexema());
                    System.out.println(aux.getNameId());
                    pilaAux.pop();
                }
                break;
            case doce:
                s2 = pilaAux.pop(); // C
                s1 = pilaAux.pop(); // B
                if (s2.getEstadoActual() != estados.vacio)
                    pilaAux.peek().setEstadoActual(s1.getEstadoActual());
                else
                    pilaAux.peek().setEstadoActual(s2.getEstadoActual());
                break;
            case catorce:
                s2 = pilaAux.pop(); // S
                pilaAux.pop(); // )
                s1 = pilaAux.pop(); // E
                pilaAux.pop(); // (
                pilaAux.pop(); // if
                if (s2.getEstadoActual() != estados.vacio)
                    pilaAux.peek().setEstadoActual(s1.getEstadoActual());
                break;
            case quinceUno:
                //TODO llamada funcion initCases()
                break;
            case quinceDos:
                a4 = pilaAux.pop(); // )
                a5 = pilaAux.pop(); // E
                a6 = pilaAux.pop(); // (
                a7 = pilaAux.pop(); // switch
                if (a5.getEstadoActual() == estados.ok)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case quinceTres:
                a1 = pilaAux.pop(); // }
                a2 = pilaAux.pop(); // O
                a3 = pilaAux.pop(); // {
                a4 = pilaAux.pop(); // )
                a5 = pilaAux.pop(); // E
                a6 = pilaAux.pop(); // (
                a7 = pilaAux.pop(); // switch
                if (a5.getEstadoActual() == estados.constEnt && a2.getEstadoActual() == estados.ok)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                pilaAux.push(a7);
                pilaAux.push(a6);
                pilaAux.push(a5);
                pilaAux.push(a4);
                //no se introducen el resto porque no haran falta para la 15.2 ni 15.1
                break;
            case dieciseisUno:
                //TODO COMPROBACION ETIQUETA
                pilaAux.pop();
                pilaAux.pop();
                break;
            case dieciseisDos:
                s1 = pilaAux.pop(); // O
                pilaAux.pop();      // D
                s2 = pilaAux.pop(); // C
                pilaAux.pop();      // :
                aux = pilaAux.pop(); // constEnt
                aux2 = pilaAux.pop(); // case
                if (s2.getEstadoActual() == estados.ok && (s1.getEstadoActual() == estados.ok || s1.getEstadoActual() == estados.vacio))
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                pilaAux.push(aux2);
                pilaAux.push(aux);
                break;
            case dieciSiete:
                s1 = pilaAux.pop(); // O
                pilaAux.pop();      // D
                s2 = pilaAux.pop(); // C
                pilaAux.pop();      // :
                pilaAux.pop();      // default
                if (s2.getEstadoActual() == estados.ok && (s1.getEstadoActual() == estados.ok || s1.getEstadoActual() == estados.vacio))
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case dieciNueve:
                pilaAux.pop(); // ;
                pilaAux.pop(); // break
                pilaAux.peek().setEstadoActual(estados.ok);
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
            case veintiSiete:
                s1 = pilaAux.pop(); // SS
                s2 = pilaAux.pop(); // id
                System.out.println(s1.getEstadoActual().name() + "\n\n\n");
                if (s1.getEstadoActual() == estados.ok || (tablaLocal != null && tablaLocal.get(s2.getNameId() - 1).getTipo() == s1.getEstadoActual()))
                    pilaAux.peek().setEstadoActual(estados.ok);
                else if (s1.getEstadoActual() == estados.ok || tablaGlobal.get(s2.getNameId()).getTipo() == s1.getEstadoActual())
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                {
                    System.out.println("FALLO SEMANTICO \n\n\n");
                    pilaAux.peek().setEstadoActual(estados.error);
                }
                break;
            case veintiOcho:
                pilaAux.pop(); // ;
                s1 = pilaAux.pop(); // E
                pilaAux.pop(); // =
                pilaAux.pop(); // %
                if (s1.getEstadoActual() == estados.constEnt)
                    pilaAux.peek().setEstadoActual(s1.getEstadoActual());
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case veintiNueve:
                pilaAux.pop(); // ;
                s1 = pilaAux.pop(); // E
                pilaAux.pop(); // =
                System.out.println("FALLO SEMANTICO: " + s1.getEstadoActual() + "\n\n\n");
                if (s1.getEstadoActual() != estados.error && s1.getEstadoActual() != estados.vacio)
                    pilaAux.peek().setEstadoActual(s1.getEstadoActual());
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case treinta:
                pilaAux.pop(); // ;
                pilaAux.pop(); // )
                s1 = pilaAux.pop(); // L
                pilaAux.pop(); // (
                if (s1.getEstadoActual() != estados.error)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case treintaiUno:
                pilaAux.pop(); // ;
                s1 = pilaAux.pop(); // R
                pilaAux.pop(); // print
                if (s1.getEstadoActual() == estados.booleanR || s1.getEstadoActual() == estados.error)
                    pilaAux.peek().setEstadoActual(estados.error);
                else
                    pilaAux.peek().setEstadoActual(estados.ok);
                break;
            case treintaiDos:
                pilaAux.pop(); // ;
                s1 = pilaAux.pop(); // id
                pilaAux.pop(); // input
                if (tablaLocal!=null && (tablaLocal.get(s1.getNameId()).getTipo() != estados.booleanR ||
                        (tablaLocal.get(s1.getNameId()) != null)))
                    pilaAux.peek().setEstadoActual(estados.ok);
                else if (tablaGlobal.get(s1.getNameId()).getTipo() != estados.booleanR ||
                        (tablaGlobal.get(s1.getNameId()) != null))
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case treintaiTres:
                pilaAux.pop(); // ;
                s1 = pilaAux.pop(); // X
                pilaAux.pop(); // return
                if (tablaLocal.tipoDeFuncion() == null)
                    //TODO PARCHE SINTACTICO RETURN EN MAIN
                    System.out.println("Error sintactico, return en main");
                else if (tablaLocal.tipoDeFuncion() == s1.getEstadoActual())
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                {
                    System.out.println("Error semantico, tipo de funcion no coincide con el valor de return");
                    pilaAux.peek().setEstadoActual(estados.error);
                }
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
                System.out.println(s2.getEstadoActual().name() + "\n\n\n");
                if (s1.getEstadoActual() != estados.vacio)
                    pilaAux.peek().setEstadoActual(estados.booleanR);
                else if (s1.getEstadoActual()!=estados.error && s2.getEstadoActual()!=estados.error)
                    pilaAux.peek().setEstadoActual(s2.getEstadoActual());
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case cuarentaiUno:
                s1 = pilaAux.pop(); // RR
                s2 = pilaAux.pop(); // R
                pilaAux.pop();      // ==
                System.out.println(s2.getEstadoActual().name() + "\n\n\n");
                if (s1.getEstadoActual() != estados.error && s2.getEstadoActual() != estados.error)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case cuarentaiTres:
                s1 = pilaAux.pop(); // UU
                s2 = pilaAux.pop(); // U
                System.out.println(s2.getEstadoActual().name() + "\n\n\n");
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
                pilaAux.pop(); // +
                System.out.println(s2.getEstadoActual().name() + "\n\n\n");
                if (s2.getEstadoActual() == estados.constEnt && s1.getEstadoActual() != estados.error)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case cuarentaiSeis:
                s1 = pilaAux.pop(); // V
                pilaAux.pop();      // !
                if (s1.getEstadoActual() == estados.booleanR)
                    pilaAux.peek().setEstadoActual(estados.booleanR);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case cuarentaiOcho:
                s1 = pilaAux.pop(); // VV
                id = pilaAux.pop(); // id
                System.out.println(s1.getEstadoActual().name() + "\n\n\n");
                System.out.println(tablaGlobal.get(id.getNameId()).getTipo()+ "\n\n\n");
                if (tablaGlobal.get(id.getNameId()) != null && s1.getEstadoActual() != estados.error)
                    pilaAux.peek().setEstadoActual(tablaGlobal.get(id.getNameId()).getTipo());
                else if (((tablaLocal != null && tablaLocal.get(id.getNameId()) != null) || tablaGlobal.get(id.getNameId()) != null)
                                                        && s1.getEstadoActual() != estados.error)
                    pilaAux.peek().setEstadoActual(tablaLocal.get(id.getNameId()).getTipo());
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case cuarentaiNueve:
                pilaAux.pop();       // )
                s1 = pilaAux.pop();  // E
                pilaAux.pop();       // (
                pilaAux.peek().setEstadoActual(s1.getEstadoActual());
                break;
            case cincuentaiDos:
                pilaAux.pop();       // )
                s1 = pilaAux.pop();  // L
                pilaAux.pop();       // (
                if (s1.getEstadoActual() != estados.error)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
        }
    }
}