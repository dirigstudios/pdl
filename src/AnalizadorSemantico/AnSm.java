package AnalizadorSemantico;

import AnalizadorLexico.TablaSimbolos;
import AnalizadorLexico.Token;
import AnalizadorSintactico.TablaM;
import AnalizadorSintactico.TablaM.simbolos;
import java.util.Stack;
import AnalizadorSintactico.TablaM.Regla;
import AnalizadorSintactico.TablaM.simbolos.estados;

public class AnSm
{
    //TODO: hacer el EdT de la gramática (ON TESTING)
    //TODO: añadir en cada regla una acción semántica que saque de la pilaAux
    // tantos simbolos como haya en el consecuente de la regla (sin contar las acciones semánticas) (DOING)
    //TODO: añadir al enum simbolos las acciones semánticas (DONE)
    //TODO: añadir a las reglas de la TablaM las acciones semánticas (DONE)
    int tablasCreadas = 1;

    public void añadirAtributos(simbolos simb, Stack<simbolos> pilaAux)
    {
        pilaAux.push(simb);
    }

    //TODO: método AnSm
    public void ejecutarRegla(simbolos simb, TablaSimbolos tablaGlobal, TablaSimbolos tablaLocal, Stack<simbolos> pila, Stack<simbolos> pilaAux)
    {
        simbolos simbolos_aux = pila.peek();
        switch (simbolos_aux)
        {
            case unoUno:
                tablaGlobal = new TablaSimbolos(0);
                break;
            case unoDos:
                tablaGlobal = null;
                break;
            case dos: case tres:
                simbolos s1 = pilaAux.pop();
                simbolos s2 = pilaAux.pop();
                if (s1.getEstadoaActual() == estados.vacio && s2.getEstadoaActual() == estados.ok)
                    pila.peek().setEstadoaActual(estados.ok);
                else if (s1.getEstadoaActual() == estados.ok && s2.getEstadoaActual() == estados.ok)
                    pila.peek().setEstadoaActual(estados.ok);
                else
                    pila.peek().setEstadoaActual(estados.error);
                break;
            case cuatro: case siete: case nueve: case once: case trece: case dieciOcho: case veinte: case treintaiCinco: case treintaiSiete: case treintaiNueve: case cuarentaiDos: case cuarentaiCinco: case cincuentaiTres:
                pilaAux.peek().setEstadoaActual(estados.vacio);
                break;
            case cincoUno:
                tablaLocal = new TablaSimbolos(tablasCreadas++);
                break;
            case cincoDos:

                break;
            case cincoTres:
                break;
            case seis: case veintiCinco: case treintaiOcho: case cuarentaiSiete:
                pilaAux.peek().setEstadoaActual(pilaAux.pop().getEstadoaActual());
                break;
            case ocho:
                break;
            case diez:
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
                simbolos.estados aux = pilaAux.pop().getEstadoaActual();
                simbolos id = pilaAux.pop();
                if (tablaLocal == null)
                {
                    tablaGlobal.insertaTipoTS(id.getNameId(), aux);
                    System.out.println(id.getNameId());
                    pilaAux.pop();
                }
                else
                {
                    tablaLocal.insertaTipoTS(id.getNameId(), aux);
                    System.out.println(id.getNameId());
                    pilaAux.pop();
                }
                pilaAux.peek().setEstadoaActual(estados.ok);
                break;
            case veintiDos: case cincuenta:
                pilaAux.pop();
                pilaAux.peek().setEstadoaActual(estados.constEnt);
                break;
            case veintiTres:
                pilaAux.pop();
                pilaAux.peek().setEstadoaActual(estados.booleanR);
                break;
            case veintiCuatro: case cincuentaiUno:
                pilaAux.pop();
                pilaAux.peek().setEstadoaActual(estados.cadena);
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
                break;
            case treintaiTres:
                break;
            case treintaiCuatro:
                break;
            case treintaiSeis:
                break;
            case cuarenta:
                break;
            case cuarentaiUno:
                break;
            case cuarentaiTres:
                break;
            case cuarentaiCuatro:
                break;
            case cuarentaiSeis:
                break;
            case cuarentaiOcho:
                break;
            case cuarentaiNueve:
                break;
            case cincuentaiDos:
                pilaAux.pop();
                simbolos L = pilaAux.pop();
                pilaAux.pop();
                if (L.getEstadoaActual() != estados.error)
                    pilaAux.peek().setEstadoaActual(estados.ok);
                else
                    pilaAux.peek().setEstadoaActual(estados.error);
                break;
        }
    }
    //y extraer sus atributos de la pila
    //meterlo todo en pilaAux 


    //TODO: métodoAnSm2
    //ejecutar la accion semántica y sacarla de la pila
}