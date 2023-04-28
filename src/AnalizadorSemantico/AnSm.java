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
import AnalizadorSintactico.TablaM.simbolos;
import GCI.GCI;

public class AnSm
{

    int tablasCreadas = 1;

    public int casActual = -1;
    public List<List<Integer>> caseEnt = new ArrayList<>();

    public void anadirAtributos(Simbolo simb, Stack<Simbolo> pilaAux) { pilaAux.push(simb); }

    public void ejecutarRegla(TablaSimbolos tablaGlobal, TablaSimbolos tablaLocal, Simbolo simbolo_cima, Stack<Simbolo> pilaAux,
                              PrintWriter ts, Lines lines, Zona_decl zona_decl, PrintWriter fichGCI)
    {
        Simbolo id;
        Simbolo aux;
        Simbolo aux2;
        Simbolo s1;
        Simbolo s2;
        //aux para el switch -> acc sem 15
        Simbolo a1, a2, a3, a4, a5, a6, a7;
        String idLugar="";
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
                GCI.emite(":", "Et" + tablaGlobal.get(a5.getNameId()).getLexema() + "01", null, null, fichGCI);
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
                GCI.emite("return", null, null, null, fichGCI);
                break;
            case seis:
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
                tablaGlobal.tablaSimbolos.get(tablaGlobal.tablaSimbolos.size() - 1).anadirParametro(aux2.getEstadoActual());
                break;
            case diez:
                pilaAux.pop();  // K
                aux = pilaAux.pop(); //id
                aux2 = pilaAux.pop(); //T
                tablaLocal.insertaTipoTS(aux.getNameId(), aux2.getEstadoActual());
                tablaGlobal.tablaSimbolos.get(tablaGlobal.tablaSimbolos.size() - 1).anadirParametro(aux2.getEstadoActual());
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
                //GCI
                GCI.emite(":", simbolo_cima.getSiguiente(), null, null, fichGCI);
                break;
            case catorceUno:
                s2 = pilaAux.pop(); // S
                a3 = pilaAux.pop(); // )
                s1 = pilaAux.pop(); // E
                a2 = pilaAux.pop(); // (
                a1 = pilaAux.pop(); // if
                simbolo_cima.setSiguiente(GCI.nuevaEt());
                GCI.emite("if", s1.getLugar(), "", simbolo_cima.getEtiq(), fichGCI);
                pilaAux.push(a1);
                pilaAux.push(a2);
                pilaAux.push(s1);
                pilaAux.push(a3);
                pilaAux.push(s2);
                break;
            case quinceUno:
                caseEnt.add(new ArrayList<>());
                casActual++;
                //GCI
                pilaAux.peek().setEtbreak(GCI.nuevaEt()); //B.break := nuevaEt() //TODO implementar esta etiqueta
                //pilaAux.peek().setEvaluado(GCI.nuevaTemp(tablaGlobal, tablaLocal, estados.booleanR)); //B.evaluado := nuevaTemp() // se hace solo
                GCI.emite(":=", String.valueOf(pilaAux.peek().isEvaluado()), null, "0", fichGCI);
                break;
            case quinceDos:
                a4 = pilaAux.pop(); // )
                a5 = pilaAux.pop(); // E
                a6 = pilaAux.pop(); // (
                a7 = pilaAux.pop(); // switch
                if (a5.getEstadoActual() == estados.ok || a5.getEstadoActual() == estados.constEnt)
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
                GCI.emite(":", simbolo_cima.getEtbreak(), null, null, fichGCI);
                break;
            case dieciseisUno:
                s1 = pilaAux.pop();    // ConstEnt
                s2 = pilaAux.pop();    // case
                int ent = s1.getNameId();   //constEnt
                //TODO implementar D.break := O.break, como sacar D de la pila?¿?¿?
                if(caseEnt.get(casActual).contains(ent))
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error semantico: existen uno o multiples cases con la misma constante entera\n");
                else
                    caseEnt.get(casActual).add(ent);
                simbolo_cima.setEtbreak(GCI.nuevaEt());
                GCI.emite("if==", simbolo_cima.isEvaluado()?"1":"0", "1", "2", fichGCI);
                GCI.emite("if!=", simbolo_cima.getEtcase(), String.valueOf(ent), simbolo_cima.getSiguiente(), fichGCI);

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
            case dieciseisTres:
                GCI.emite(":", simbolo_cima.getSiguiente(), null, null, fichGCI);
                break;
            case dieciSieteUno:
                GCI.emite("if==", simbolo_cima.isEvaluado() ? "true" : "false", "1", simbolo_cima.getEtbreak(), fichGCI);
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
                //GCI
                GCI.emite("goto", pilaAux.peek().getEtbreak(), null, null, fichGCI); //goto D.break //TODO implementar esta etiqueta
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
            case veintiDos:
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
            case veintiSieteUno:
                break;
            case veintiSieteDos:
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
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error semantico: la asignación con resto debe realizarse con un valor entero\n");
                    pilaAux.peek().setEstadoActual(estados.error);
                }
                //GCI
                s2 = pilaAux.pop(); // SS, por lo que en la cima de la pila esta id!!
                idLugar = tablaGlobal.buscaLugarTS(pilaAux.peek().getNameId());  // Revisar que hacer si es la local la que hay que ver
                GCI.emite("%", s1.getLugar(), idLugar, idLugar, fichGCI);
                pilaAux.push(s2); // devuelvo SS a donde debe estar, habiendo accedido a id
                break;
            case veintiNueve:
                pilaAux.pop(); // ;
                s1 = pilaAux.pop(); // E
                pilaAux.pop(); // =
                if (s1.getEstadoActual() != estados.error && s1.getEstadoActual() != estados.vacio)
                    pilaAux.peek().setEstadoActual(s1.getEstadoActual());
                else
                    pilaAux.peek().setEstadoActual(estados.error);
                //GCI
                //diferenciar entre logico y entero VS cadena (asiganr una cadena conlleva mas instruccion objeto que asignar un logico u entero)
                s2 = pilaAux.pop(); // SS, por lo que en la cima de la pila esta id!!
                idLugar = tablaGlobal.buscaLugarTS(pilaAux.peek().getNameId());  // Revisar que hacer si es la local la que hay que ver
                GCI.emite(":=", s1.getLugar(), "null", idLugar, fichGCI);
                pilaAux.push(s2); // devuelvo SS a donde debe estar, habiendo accedido a id
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
                //GCI
                List<String> paramss = s1.getParams();
                for (int i = 0; i < paramss.size(); i++)
                    GCI.emite("param", paramss.get(i), "", "", fichGCI);
                GCI.emite("call", pilaAux.peek().getEtiq(), "", "", fichGCI);
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
                //GCI
                GCI.emite("param", s1.getLugar(), null, null, fichGCI);
                GCI.emite("call", "print", null, null, fichGCI);
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
                //GCI
                GCI.emite("param", tablaGlobal.buscaLugarTS(s1.getNameId()), null, null, fichGCI);
                GCI.emite("call", "input", null, null, fichGCI);
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
                //GCI
                GCI.emite("return", s1.getLugar(), null, null, fichGCI);
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
                //GCI
                pilaAux.peek().setParams(s1.getParams());
                pilaAux.peek().getParams().add(s2.getLugar());
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
                //GCI
                pilaAux.peek().setParams(s1.getParams());
                pilaAux.peek().getParams().add(s2.getLugar());
                break;
            case treintaiOcho:
                aux = pilaAux.pop(); // E
                aux2 = pilaAux.pop(); // X
                aux2.setEstadoActual(aux.getEstadoActual());
                pilaAux.push(aux2);
                //GCI
                pilaAux.peek().setLugar(aux.getLugar());
                break;
            case cuarenta:
                s1 = pilaAux.pop(); // RR
                s2 = pilaAux.pop(); // R
                // SEM
                if (s1.getEstadoActual() != estados.vacio && s2.getEstadoActual() == estados.constEnt)
                    pilaAux.peek().setEstadoActual(estados.booleanR);
                else if (s1.getEstadoActual()!=estados.error && s2.getEstadoActual()!=estados.error)
                    pilaAux.peek().setEstadoActual(s2.getEstadoActual());
                else
                {
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error semantico: La expresion no esta bien formada\n");
                    pilaAux.peek().setEstadoActual(estados.error);
                }
                //GCI
                if (s1.getEstadoActual() != estados.vacio) {
                    pilaAux.peek().setLugar(GCI.nuevaTemp(tablaGlobal, tablaLocal, estados.booleanR));
                    GCI.emite("goto==", s2.getLugar(), s1.getLugar(), "sig_instr + 2", fichGCI);
                    GCI.emite(":=", "0", null, s1.getLugar(), fichGCI);
                    GCI.emite("goto", null, null, "sig_instr + 2", fichGCI);
                    GCI.emite(":=", "1", null, s1.getLugar(), fichGCI);
                }
                else
                    pilaAux.peek().setLugar(s2.getLugar());
                break;
            case cuarentaiUno:
                s1 = pilaAux.pop(); // RR1
                s2 = pilaAux.pop(); // R
                pilaAux.pop();      // ==
                if (s2.getEstadoActual() == estados.constEnt && s1.getEstadoActual() != estados.error)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                {
                    pilaAux.peek().setEstadoActual(estados.error);
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error semantico: La comparacion solo admite valores enteros\n");
                }
                //GCI
                pilaAux.peek().setLugar(s2.getLugar()); //RR.lugar := R.lugar
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
                //GCI
                //pilaAux.peek().setLugar(GCI.nuevaTemp(tablaGlobal, tablaLocal, s2.getEstadoActual()));
                if (s1.getEstadoActual() == estados.vacio)
                    pilaAux.peek().setLugar(s2.getLugar());
                else
                {
                    pilaAux.peek().setLugar(GCI.nuevaTemp(tablaGlobal, tablaLocal, s2.getEstadoActual()));
                    GCI.emite("+", s2.getLugar(), s1.getLugar(), pilaAux.peek().getLugar(), fichGCI);
                }
                break;
            case cuarentaiCuatro:
                s1 = pilaAux.pop(); // UU1
                s2 = pilaAux.pop(); // U
                pilaAux.pop(); // +
                if (s2.getEstadoActual() == estados.constEnt && s1.getEstadoActual() != estados.error)
                    pilaAux.peek().setEstadoActual(estados.ok);
                else
                {
                    pilaAux.peek().setEstadoActual(estados.error);
                    System.out.println("Error en linea: " + lines.toString() + " -> " + "Error semantico: el operando + solo admite valores enteros\n");
                }
                //GCI
                pilaAux.peek().setLugar(GCI.nuevaTemp(tablaGlobal, tablaLocal, s2.getEstadoActual()));
                if (s1.getEstadoActual() == estados.vacio)
                    GCI.emite(":=", s2.getLugar(), null, pilaAux.peek().getLugar(), fichGCI);
                else
                    GCI.emite("+", s2.getLugar(), s1.getLugar(), pilaAux.peek().getLugar(), fichGCI);
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
                //GCI
                pilaAux.peek().setLugar(GCI.nuevaTemp(tablaGlobal, tablaGlobal, estados.booleanR));
                GCI.emite("not", s1.getLugar(), null, pilaAux.peek().getLugar(), fichGCI);
                break;
            case cuarentaiSiete:
                aux = pilaAux.pop(); // V
                aux2 = pilaAux.pop(); // U
                aux2.setEstadoActual(aux.getEstadoActual());
                //GCI
                aux2.setLugar(aux.getLugar());
                pilaAux.push(aux2);
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
                //GCI
                Simbolo V = pilaAux.peek();
                if (tablaLocal != null && (tablaLocal.get(id.getNameId()) != null && s1.getEstadoActual() != estados.error))
                    V.setLugar(GCI.nuevaTemp(tablaGlobal, tablaLocal, tablaLocal.get(id.getNameId()).getTipo())); //V.lugar := nuevaTemp(BuscaTipoTS(id.pos))
                else if (tablaGlobal.get(id.getNameId()) != null && s1.getEstadoActual() != estados.error)
                    V.setLugar(GCI.nuevaTemp(tablaGlobal, tablaLocal, tablaGlobal.get(id.getNameId()).getTipo())); //V.lugar := nuevaTemp(BuscaTipoTS(id.pos))

                //comprobamos si el id es function entrando en la TS correspondiente
                boolean isFunction = false;
                if (tablaLocal != null && (tablaLocal.get(id.getNameId()) != null && s1.getEstadoActual() != estados.error))
                    isFunction = tablaLocal.get(id.getNameId()).isFunction();
                else if (tablaGlobal.get(id.getNameId()) != null && s1.getEstadoActual() != estados.error)
                    isFunction = tablaGlobal.get(id.getNameId()).isFunction();

                if (isFunction) //if BuscaTipoTS(id.pos) != funcion
                    GCI.emite("", id.getEstadoActual().toString(), "", V.getLugar(), fichGCI); //then emite(NULL, buscaLugarTS(id.pos), NULL, V.lugar)
                else
                {
                    //extraemos el id.pos para buscar la etiqueta en la TS correspondiente
                    if (tablaLocal != null && (tablaLocal.get(id.getNameId()) != null && s1.getEstadoActual() != estados.error))
                        s1.setEtiq(tablaLocal.get(id.getNameId()).getEtiqueta()); //VV.etiq := BuscaEtiqTS(id.pos)
                    else if (tablaGlobal.get(id.getNameId()) != null && s1.getEstadoActual() != estados.error)
                        s1.setEtiq(tablaGlobal.get(id.getNameId()).getEtiqueta()); //VV.etiq := BuscaEtiqTS(id.pos)
                    V.setLugar(s1.getLugar()); //V.lugar := VV.lugar;
                }
                break;
            case cuarentaiNueve:
                pilaAux.pop();       // )
                s1 = pilaAux.pop();  // E
                pilaAux.pop();       // (
                pilaAux.peek().setEstadoActual(s1.getEstadoActual());
                //GCI
                pilaAux.peek().setLugar(s1.getLugar());
                break;
            case cincuenta:
                s1 = pilaAux.pop(); // consEnt
                //GCI
                pilaAux.peek().setLugar(GCI.nuevaTemp(tablaGlobal, tablaLocal, estados.constEnt));
                GCI.emite(":=", Integer.toString(s1.getNameId()), null, pilaAux.peek().getLugar(), fichGCI);
                pilaAux.peek().setEstadoActual(estados.constEnt);
                break;
            case cincuentaiUno:
                s1 = pilaAux.pop(); // cadena
                pilaAux.peek().setLugar(GCI.nuevaTemp(tablaGlobal, tablaLocal, estados.cadena));
                //GCI
                GCI.emite(":=", Integer.toString(s1.getNameId()), null, pilaAux.peek().getLugar(), fichGCI);
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
                //GCI
                List<String> params = s1.getParams();
                for (int i = 0; i < params.size(); i++)
                    GCI.emite("param", params.get(i), "", "", fichGCI);
                GCI.emite("call", pilaAux.peek().getEtiq(), "", "", fichGCI);
                break;
            default:
                break;
        }
    }
}