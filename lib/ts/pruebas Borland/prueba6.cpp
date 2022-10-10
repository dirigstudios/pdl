//---------------------------------------------------------------------------
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "CArbolSimbolos.h"
#include <iostream.h>

#pragma hdrstop
//---------------------------------------------------------------------------
USERES("PRUEBA6.res");
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

  tabla.CrearEntrada(8,"posicionmemoria",puntero);
  tabla.CrearEntrada(identificador,"inicializada",ent);


  tabla.CrearEntrada(vector,"tipoelemento",cad);
  tabla.CrearEntrada(vec,"posicion",entero);
  tabla.CrearEntrada(vector,"numeroelementos",ent);
  tabla.CrearEntrada(vector,"indiceinf",entero);
  tabla.CrearEntrada(vector,"indicesup",entero);


  tabla.CrearEntrada(funcion,"numparam",ent);
  tabla.CrearEntrada(funcion,"tiporetorno",cad);
  tabla.CrearEntrada(funcion,"parametros",11);
  tabla.CrearEntrada(87,"direccion",puntero);

  tabla.CrearEntrada(88,"numparam",entero);
  tabla.CrearEntrada(procedimiento,"parametros",50);
  tabla.CrearEntrada(procedimiento,"direccion",ptr);

  tabla.CrearEntrada(12,"posicionsalto",puntero);

  tabla.CrearEntrada(44,"posicion",puntero);



  tabla.ImprimirTabla("salida.txt");

  return 0;

}
//---------------------------------------------------------------------------

