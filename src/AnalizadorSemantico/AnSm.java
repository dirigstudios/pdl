package AnalizadorSemantico;

import AnalizadorLexico.TablaSimbolos;
import AnalizadorSintactico.AnSt;
import AnalizadorSintactico.AnSt.Lines;
import AnalizadorSintactico.AnSt.Zona_decl;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import AnalizadorSintactico.TablaM.Simbolo;
import AnalizadorSintactico.TablaM.estados;

public class AnSm
{
    //TODO: hacer el EdT de la gramática (ON TESTING)
    int tablasCreadas = 1;

    public int casActual = -1;
    public List<List<Integer>> caseEnt = new ArrayList<>();

    public void añadirAtributos(Simbolo simb, Stack<Simbolo> pilaAux) { pilaAux.push(simb); }

    public void ejecutarRegla(TablaSimbolos tablaGlobal, TablaSimbolos tablaLocal, Simbolo simbolo_cima, Stack<Simbolo> pilaAux,
                              PrintWriter ts, Lines lines, Zona_decl zona_decl)
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
                pilaAux.pop();
                tablaGlobal = null;
                break;
            case dos: case tresDos:
                s1 = pilaAux.pop(); // P2
                s2 = pilaAux.pop(); // B/F
                if (s1.getEstadoActual() == estados.vacio && s2.getEstadoActual() == estados.ok)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else if (s1.getEstadoActual() == estados.ok && s2.getEstadoActual() == estados.ok)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case tresUno:
                zona_decl.zona_decl = true;
                break;
            case cuatro: case siete: case nueve: case once: case trece: case veinte: case treintaiCinco: case treintaiSiete: case treintaiNueve: case cuarentaiDos: case cuarentaiCinco: case cincuentaiTres:
                pilaAux.peek().setEstadoActual(estados.vacio);
                break;
            case dieciOcho:
                caseEnt.remove(casActual);
                casActual--;
                pilaAux.peek().setEstadoActual(estados.vacio);
                break;
            case cincoUno:
                AnSt.crearTablaAux(tablasCreadas++);
                break;
            case cincoDos:
                a1 = pilaAux.pop(); // )
                a2 = pilaAux.pop(); // A
                a3 = pilaAux.pop(); // (
                a4 = pilaAux.pop(); // H
                a5 = pilaAux.pop(); // id
                tablaGlobal.insertaTipoTS(a5.getNameId(), a4.getEstadoActual());
                tablaGlobal.get(a5.getNameId()).setFunction();
                tablaLocal.setIdLocal(a5.getNameId());
                pilaAux.push(a5);
                pilaAux.push(a4);
                pilaAux.push(a3);
                pilaAux.push(a2);
                pilaAux.push(a1);
                zona_decl.zona_decl = false;
                break;
            case cincoTres:
                tablaLocal.printTS(ts);
                AnSt.destruirTablaAux();
                break;
            case cincoCuatro:
                pilaAux.pop();  // }
                s1 = pilaAux.pop(); // C
                pilaAux.pop();  // {
                pilaAux.pop();  // )
                pilaAux.pop();  // A
                pilaAux.pop();  // (
                pilaAux.pop();  // H
                pilaAux.pop();  // id
                pilaAux.pop();  // function
                if (s1.getEstadoActual() == estados.error)
                {
//                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error semantico: cuerpo de la funcion mal formada\n");
                    pilaAux.peek().setEstadoActual(estados.error);
                }
                else
                    pilaAux.peek().setEstadoActual(estados.ok);
                break;
            case seis: case treintaiOcho: case cuarentaiSiete:
                aux = pilaAux.pop();
                aux2 = pilaAux.pop();
                aux2.setEstadoActual(aux.getEstadoActual());
                pilaAux.push(aux2);
                break;
            case ocho:
                pilaAux.pop();
                aux = pilaAux.pop(); //id
                aux2 = pilaAux.pop(); //T
                tablaLocal.insertaTipoTS(aux.getNameId(), aux2.getEstadoActual());
                tablaGlobal.tablaSimbolos.get(tablaGlobal.tablaSimbolos.size() - 1).añadirParametro(aux2.getEstadoActual());
                break;
            case diez:
                pilaAux.pop();  // K
                aux = pilaAux.pop(); //id
                aux2 = pilaAux.pop(); //T
                tablaLocal.insertaTipoTS(aux.getNameId(), aux2.getEstadoActual());
                tablaGlobal.tablaSimbolos.get(tablaGlobal.tablaSimbolos.size() - 1).añadirParametro(aux2.getEstadoActual());
                pilaAux.pop();  // ,
                break;
            case doce:
                s2 = pilaAux.pop(); // C
                s1 = pilaAux.pop(); // B
                if (s2.getEstadoActual() == estados.vacio)
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
                if (s1.getEstadoActual() == estados.booleanR && s2.getEstadoActual() != estados.vacio)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case quinceUno:
                caseEnt.add(new ArrayList<>());
                casActual++;
                break;
            case quinceDos:
                a4 = pilaAux.pop(); // )
                a5 = pilaAux.pop(); // E
                a6 = pilaAux.pop(); // (
                a7 = pilaAux.pop(); // switch
                if (a5.getEstadoActual() == estados.ok)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                {
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error semantico: Expresion incorrecta para la evaluación del switch, debe ser de tipo entero\n");
                    pilaAux.peek().setEstadoActual(estados.error);
                }
                pilaAux.push(a7);
                pilaAux.push(a6);
                pilaAux.push(a5);
                pilaAux.push(a4);
                break;
            case quinceTres:
                pilaAux.pop(); // }
                a2 = pilaAux.pop(); // O
                pilaAux.pop(); // {
                pilaAux.pop(); // )
                a5 = pilaAux.pop(); // E
                pilaAux.pop(); // (
                pilaAux.pop(); // switch
                if (a5.getEstadoActual() == estados.constEnt && a2.getEstadoActual() == estados.ok)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case dieciseisUno:
                s1 = pilaAux.pop();    // ConstEnt
                s2 = pilaAux.pop();    // case
                int ent = s1.getNameId();   //constEnt
                if(caseEnt.get(casActual).contains(ent))
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error semantico: existen uno o multiples cases con la misma constante entera\n");
                else
                    caseEnt.get(casActual).add(ent);
                pilaAux.push(s2);
                pilaAux.push(s1);
                break;
            case dieciseisDos:
                s1 = pilaAux.pop(); // O
                pilaAux.pop();      // D
                s2 = pilaAux.pop(); // C
                pilaAux.pop();      // :
                pilaAux.pop(); // constEnt
                pilaAux.pop(); // case
                if ((s2.getEstadoActual() == estados.ok || s2.getEstadoActual() == estados.vacio) && (s1.getEstadoActual() == estados.ok || s1.getEstadoActual() == estados.vacio))
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                    pilaAux.peek().setEstadoActual(estados.error);
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
            case veintiUnoTres:
                pilaAux.pop();
                estados T = pilaAux.pop().getEstadoActual();
                id = pilaAux.pop();
                if (tablaLocal == null)
                    tablaGlobal.insertaTipoTS(id.getNameId(), T);
                else
                    tablaLocal.insertaTipoTS(id.getNameId(), T);
                pilaAux.pop();
                pilaAux.peek().setEstadoActual(estados.ok);
                break;
            case veintiUnoDos:
                zona_decl.zona_decl = false;
                break;
            case veintiUnoUno:
                zona_decl.zona_decl = true;
                break;
            case veintiDos: case cincuenta:
                pilaAux.pop();
                pilaAux.peek().setEstadoActual(estados.constEnt);
                break;
            case veintiTres:
                pilaAux.pop();
                pilaAux.peek().setEstadoActual(estados.booleanR);
                break;
            case veintiCuatro:
                pilaAux.pop();
                pilaAux.peek().setEstadoActual(estados.cadena);
                break;
            case veintiCinco:
                s1 = pilaAux.pop(); // S
                if (s1.getEstadoActual() != estados.error && s1.getEstadoActual() != estados.vacio)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                {
                    pilaAux.peek().setEstadoActual(estados.error);
                }
                break;
            case veintiSiete:
                s1 = pilaAux.pop(); // SS
                s2 = pilaAux.pop(); // id
                if (s1.getEstadoActual() == estados.ok || (tablaLocal != null && tablaLocal.get(s2.getNameId()) != null &&
                        tablaLocal.get(s2.getNameId()).getTipo() == s1.getEstadoActual()))
                    pilaAux.peek().setEstadoActual(estados.ok);
                else if (s1.getEstadoActual() == estados.ok || (tablaGlobal.get(s2.getNameId()) != null &&
                        tablaGlobal.get(s2.getNameId()).getTipo() == s1.getEstadoActual()))
                    pilaAux.peek().setEstadoActual(estados.ok);
                else if (s1.getEstadoActual() != estados.error)
                {
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error semantico: el tipo de variable no coincide con el tipo de valor que se intenta asignar\n");
                    pilaAux.peek().setEstadoActual(estados.error);
                }
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                break;
            case veintiOcho:
                pilaAux.pop(); // ;
                s1 = pilaAux.pop(); // E
                pilaAux.pop(); // %=
                if (s1.getEstadoActual() == estados.constEnt)
                    pilaAux.peek().setEstadoActual(s1.getEstadoActual());
                else
                {
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error semantico: la asignación con resto debe realizarse con un calor entero\n");
                    pilaAux.peek().setEstadoActual(estados.error);
                }
                break;
            case veintiNueve:
                pilaAux.pop(); // ;
                s1 = pilaAux.pop(); // E
                pilaAux.pop(); // =
                if (s1.getEstadoActual() != estados.error && s1.getEstadoActual() != estados.vacio)
                    pilaAux.peek().setEstadoActual(s1.getEstadoActual());
                else
                {
                    pilaAux.peek().setEstadoActual(estados.error);
                }
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
                {
                    System.out.println("Error en linea: " + lines.toString()+ " -> " + "Error semantico: Expresion mal formada o variable boolean no admitida\n");
                    pilaAux.peek().setEstadoActual(estados.error);
                }
                else
                    pilaAux.peek().setEstadoActual(estados.ok);
                break;
            case treintaiDos:
                pilaAux.pop(); // ;
                s1 = pilaAux.pop(); // id
                pilaAux.pop(); // input
                if (tablaLocal!=null && tablaLocal.get(s1.getNameId()) != null &&
                        tablaLocal.get(s1.getNameId()).getTipo() != estados.booleanR)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else if (tablaGlobal.get(s1.getNameId()) != null && tablaGlobal.get(s1.getNameId()).getTipo() != estados.booleanR )
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                {
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error semantico: variable booleana no admitida\n");
                    pilaAux.peek().setEstadoActual(estados.error);
                }
                break;
            case treintaiTres:
                pilaAux.pop(); // ;
                s1 = pilaAux.pop(); // X
                pilaAux.pop(); // return
                if (tablaGlobal.get(tablaLocal.getIdLocal()).getTipo() == null)
                    System.out.println("Error sintactico, return en main");
                else if (tablaGlobal.get(tablaLocal.getIdLocal()).getTipo() == s1.getEstadoActual())
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                {
                    System.out.println("Error semantico, tipo de funcion no coincide con el valor de return\n");
                    pilaAux.peek().setEstadoActual(estados.error);
                }
                break;
            case treintaiCuatro:
                s1 = pilaAux.pop(); // Q
                s2 = pilaAux.pop(); // E
                if (s2.getEstadoActual()!=estados.error && s2.getEstadoActual()!=estados.vacio && s1.getEstadoActual() != estados.error)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                {
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error semantico: Expresion mal formada o vacia\n");
                    pilaAux.peek().setEstadoActual(estados.error);
                }
                break;
            case treintaiSeis:
                s1 = pilaAux.pop(); // Q2
                s2 = pilaAux.pop(); // E
                pilaAux.pop(); // ,
                if (s2.getEstadoActual()!=estados.error && s2.getEstadoActual()!=estados.vacio && s1.getEstadoActual() != estados.error)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                {
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error semantico: Expresion mal formada o vacia\n");
                    pilaAux.peek().setEstadoActual(estados.error);
                }
                break;
            case cuarenta:
                s1 = pilaAux.pop(); // RR
                s2 = pilaAux.pop(); // R
                if (s1.getEstadoActual() != estados.vacio && s2.getEstadoActual() == estados.constEnt)
                    pilaAux.peek().setEstadoActual(estados.booleanR);
                else if (s1.getEstadoActual()!=estados.error && s2.getEstadoActual()!=estados.error && s1.getEstadoActual()!=estados.vacio)
                    pilaAux.peek().setEstadoActual(s2.getEstadoActual());
                else
                {
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error semantico: La expresion no esta bien formada\n");
                    pilaAux.peek().setEstadoActual(estados.error);
                }
                break;
            case cuarentaiUno:
                s1 = pilaAux.pop(); // RR
                s2 = pilaAux.pop(); // R
                pilaAux.pop();      // ==
                if (s2.getEstadoActual() == estados.constEnt && s1.getEstadoActual() != estados.error)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                {
                    pilaAux.peek().setEstadoActual(estados.error);
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error semantico: La comparacion solo admite valores enteros\n");
                }
                break;
            case cuarentaiTres:
                s1 = pilaAux.pop(); // UU
                s2 = pilaAux.pop(); // U
                if (s1.getEstadoActual() == estados.vacio)
                    pilaAux.peek().setEstadoActual(s2.getEstadoActual());
                else if (s2.getEstadoActual() == estados.constEnt && s1.getEstadoActual() == estados.ok)
                    pilaAux.peek().setEstadoActual(estados.constEnt);
                else
                    pilaAux.peek().setEstadoActual(estados.vacio);
                break;
            case cuarentaiCuatro:
                s1 = pilaAux.pop(); // UU2
                s2 = pilaAux.pop(); // U
                pilaAux.pop(); // +
                if (s2.getEstadoActual() == estados.constEnt && s1.getEstadoActual() != estados.error)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                {
                    pilaAux.peek().setEstadoActual(estados.error);
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error semantico: el operando + solo admite valores enteros\n");
                }
                break;
            case cuarentaiSeis:
                s1 = pilaAux.pop(); // V
                pilaAux.pop();      // !
                if (s1.getEstadoActual() == estados.booleanR)
                    pilaAux.peek().setEstadoActual(estados.booleanR);
                else
                {
                    pilaAux.peek().setEstadoActual(estados.error);
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error semantico: operando ! solo admite variables de tipo boolean\n");
                }
                break;
            case cuarentaiOcho:
                s1 = pilaAux.pop(); // VV
                id = pilaAux.pop(); // id
                if (tablaLocal != null && (tablaLocal.get(id.getNameId()) != null && s1.getEstadoActual() != estados.error))
                    pilaAux.peek().setEstadoActual(tablaLocal.get(id.getNameId()).getTipo());
                else if (tablaGlobal.get(id.getNameId()) != null && s1.getEstadoActual() != estados.error)
                    pilaAux.peek().setEstadoActual(tablaGlobal.get(id.getNameId()).getTipo());
                else
                {
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error semantico: id no declarado anteriormente\n");
                    pilaAux.peek().setEstadoActual(estados.error);
                }
                break;
            case cuarentaiNueve:
                pilaAux.pop();       // )
                s1 = pilaAux.pop();  // E
                pilaAux.pop();       // (
                pilaAux.peek().setEstadoActual(s1.getEstadoActual());
                break;
            case cincuentaiUno:
                pilaAux.pop();
                pilaAux.peek().setEstadoActual(estados.cadena);
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
            default:
                break;
        }
    }
}