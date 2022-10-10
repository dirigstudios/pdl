//---------------------------------------------------------------------------
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "CArbolSimbolos.h"
#include <iostream.h>

#pragma hdrstop
//---------------------------------------------------------------------------
USERES("prueba8.res");
USEUNIT("CATRIBUTO.cpp");
USEUNIT("CNODOSIMBOLO.cpp");
USEUNIT("CARBOLSIMBOLOS.cpp");
USEUNIT("CENTRADA.cpp");
//---------------------------------------------------------------------------
int main(int argc, char **argv)
{
   CArbolSimbolos tabla;
   int resultado;
   void *resultadoptr;
   char *resultadocad;
   TipoParam tipo;
   PasoParametro paso;
   void *valor_d;
   CNodoSimbolo *nodo, *nodo1, *nodo2, *nodo3, *nodo4, *nodo5, *nodo6, *nodo7, *nodo8, *nodo9;

  cout<<"Creamos la tabla de simbolos"<<endl;
  tabla.CrearTabla();


  cout<<"INSERTAMOS suma, resta, resultado,encontrado, producto, ptr,salto, vector, escribir,palabra"<<endl;

  nodo=tabla.InsertarSimbolo("suma");
  nodo1=tabla.InsertarSimbolo("resta");
  nodo2=tabla.InsertarSimbolo("resultado");
  nodo3=tabla.InsertarSimbolo("encontrado");
  nodo4=tabla.InsertarSimbolo("producto");
  nodo5=tabla.InsertarSimbolo("ptr");
  nodo6=tabla.InsertarSimbolo("salto");
  nodo7=tabla.InsertarSimbolo("vector");
  nodo8=tabla.InsertarSimbolo("escribir");
  nodo9=tabla.InsertarSimbolo("palabra");

  cout<<"Damos formato a las entradas"<<endl;

  tabla.CrearEntrada(identificador,"posicionmemoria",puntero);
  tabla.CrearEntrada(identificador,"inicializada",entero);


  tabla.CrearEntrada(vector,"tipoelemento",cadena);
  tabla.CrearEntrada(vector,"posicion",entero);
  tabla.CrearEntrada(vector,"numeroelementos",entero);
  tabla.CrearEntrada(vector,"indiceinf",entero);
  tabla.CrearEntrada(vector,"indicesup",entero);


  tabla.CrearEntrada(funcion,"numparam",entero);
  tabla.CrearEntrada(funcion,"tiporetorno",cadena);
  tabla.CrearEntrada(funcion,"parametros",listaParametros);
  tabla.CrearEntrada(funcion,"direccion",puntero);

  tabla.CrearEntrada(procedimiento,"numparam",entero);
  tabla.CrearEntrada(procedimiento,"parametros",listaParametros);
  tabla.CrearEntrada(procedimiento,"direccion",puntero);

  tabla.CrearEntrada(etiqueta,"posicionsalto",puntero);

  tabla.CrearEntrada(registro,"posicion",puntero);


  cout<<"Insertamos el tipo de cada entrada"<<endl;

  tabla.InsertarTipo(nodo,funcion);
  tabla.InsertarTipo(nodo1,procedimiento);
  tabla.InsertarTipo(nodo2,etiqueta);

  tabla.ObtenerTipo(nodo3);
  tabla.ObtenerTipo(nodo4);



  tabla.ImprimirTabla("salida.txt");

  return 0;

}
//---------------------------------------------------------------------------

