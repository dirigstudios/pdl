//---------------------------------------------------------------------------
#include "CArbolSimbolos.h"

int main(int argc, char **argv)
{
   CArbolSimbolos tabla;
   int resultado;
   void *resultadoptr;
   char *resultadocad;
   void *valor_d;
   CNodoSimbolo *nodo, *nodo1, *nodo2, *nodo3, *nodo4, *nodo5, *nodo6, *nodo7, *nodo8, *nodo9;

  /* Pruebas de sistema */

  /* 1º: creamos una tabla de símbolos, para ello utilizamos la función CrearTabla,
  que no tiene parámetros de entrada */
  tabla.CrearTabla();

  /* 2º: creamos las estructuras de entrada internas para cada tipo de entrada
  requeridas por el lenguaje de programación. Para un uso correcto de esta función
  hay que tener en cuenta los tipos de entrada y los tipos de atributos que están
  definidos */

  //tabla.CrearEntrada(clave,"reservada","string");


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

  /* 3º: inicialiazamos la tabla de símbolos con las palabras clave
  del lenguaje que se trate, previamente hay que crear el tipo de entrada en la
  tabla de símbolos para el tipo de entrada "clave", ver explicación de los
  parámetros en el manual de usuario*/

  tabla.IntroducirPalabraReservada("while");
  tabla.IntroducirPalabraReservada("for");
  tabla.IntroducirPalabraReservada("if");
  tabla.IntroducirPalabraReservada("then");
  tabla.IntroducirPalabraReservada("break");
  tabla.IntroducirPalabraReservada("case");
  tabla.IntroducirPalabraReservada("define");
  tabla.IntroducirPalabraReservada("int");
  tabla.IntroducirPalabraReservada("char");
  tabla.IntroducirPalabraReservada("void");
  tabla.IntroducirPalabraReservada("throw");
  tabla.IntroducirPalabraReservada("catch");



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



  cout<<"Insertamos el tipo de cada entrada"<<endl;

  tabla.InsertarTipo(nodo,funcion);
  tabla.InsertarTipo(nodo1,funcion);
  tabla.InsertarTipo(nodo2,identificador);
  tabla.InsertarTipo(nodo3,identificador);
  tabla.InsertarTipo(nodo4,funcion);
  tabla.InsertarTipo(nodo5,identificador);
  tabla.InsertarTipo(nodo6,etiqueta);
  tabla.InsertarTipo(nodo7,vector);
  tabla.InsertarTipo(nodo8,procedimiento);
  tabla.InsertarTipo(nodo9,identificador);
  tabla.ImprimirTabla("sal.txt");
  cout<<"Inicializamos la tabla de simbolos: while, for, if, then, break,case, define,int,char,void,throw,catch"<<endl;


  cout<<"es palabra reservada if"<<endl;
  if (tabla.EsPalabraReservada("if"))
     cout<<"Es palabra reservada"<<endl;
  else
     cout<<"No es palabra reservada"<<endl;

  cout<<"es palabra reservada void"<<endl;
  if (tabla.EsPalabraReservada("void"))
     cout<<"Es palabra reservada"<<endl;
  else
     cout<<"No es palabra reservada"<<endl;

  cout<<"es palabra reservada end"<<endl;
  if (tabla.EsPalabraReservada("end"))
     cout<<"Es palabra reservada"<<endl;
  else
     cout<<"No es palabra reservada"<<endl;

  cout<<"es palabra reservada while"<<endl;
  if (tabla.EsPalabraReservada("while"))
     cout<<"Es palabra reservada"<<endl;
  else
     cout<<"No es palabra reservada"<<endl;

  cout<<"es palabra reservada read"<<endl;
  if (tabla.EsPalabraReservada("read"))
     cout<<"Es palabra reservada"<<endl;
  else
     cout<<"No es palabra reservada"<<endl;

  cout<<"damos valor a los atributos de suma(8,9)"<<endl;
  tabla.InsertarValor(nodo,"numparam",2);
  tabla.InsertarValor(nodo,"tiporetorno","entero");
  valor_d=(void *)nodo;
  tabla.InsertarValor(nodo,"direccion",valor_d);
  tabla.InsertarParametro(nodo,"parametros",valor,ent);
  tabla.InsertarParametro(nodo,"parametros",referencia,ent);


  cout<<"damos valor a los atributos de resta(15.7,9.6)"<<endl;
  tabla.InsertarValor(nodo1,"numparam",2);
  tabla.InsertarValor(nodo1,"tiporetorno","real");
  valor_d=(void *)nodo1;
  tabla.InsertarValor(nodo1,"direccion",valor_d);
  tabla.InsertarParametro(nodo1,"parametros",valor,real);
  tabla.InsertarParametro(nodo1,"parametros",valor,real);

  cout<<"damos valor a los atributos de resultado"<<endl;
  valor_d=(void *)nodo2;
  tabla.InsertarValor(nodo2,"posicionmemoria",valor_d);
  tabla.InsertarValor(nodo2,"inicializada",0);


  cout<<"damos valor a los atributos de vector[9]"<<endl;
  valor_d=(void *)nodo7;
  tabla.InsertarValor(nodo7,"tipoelemento","enteros");
  tabla.InsertarValor(nodo7,"posicion",9);
  tabla.InsertarValor(nodo7,"numeroelementos",10);
  tabla.InsertarValor(nodo7,"indiceinf",0);
  tabla.InsertarValor(nodo7,"indicesup",9);

  cout<<"damos valor a los atributos salto a encontrado"<<endl;
  valor_d=(void *)nodo3;
  tabla.InsertarValor(nodo6,"posicionsalto",nodo3);

  cout<<"Dame valor de numeroelementos de vector[9]"<<endl;
  tabla.DevolverValor(nodo7,"numeroelementos",resultado);
  cout<<"OBTENGO:"<<resultado<<endl;

  cout<<"Dame valor de inicializada de resultado=0"<<endl;
  tabla.DevolverValor(nodo2,"inicializada",resultado);
  cout<<"OBTENGO:"<<resultado<<endl;

  cout<<"Dame valor de direccion de resta"<<endl;
  tabla.DevolverValor(nodo1,"direccion",resultadoptr);
  cout<<"OBTENGO:"<<resultadoptr<<endl;
  cout<<"HABIA INTRODUCIDO"<<(void *)nodo1<<endl;

  cout<<"Dame valor de tiporetorno de suma"<<endl;
  tabla.DevolverValor(nodo,"tiporetorno",resultadocad);
  cout<<"OBTENGO:"<<resultadocad<<endl;
  cout<<"HABIA INTRODUCIDO"<<"entero"<<endl;
  cout<<"Dame valores del segundo parametro de suma (era entero->0,valor->0)"<<endl;
  cout<<tabla.ObtenerTipoParametro(nodo,"parametros",2)<<endl;
  cout<<tabla.ObtenerPasoParametro(nodo,"parametros",2)<<endl;
  cout<<"Dame valores del primer parametro de suma (era entero->0,referencia->1)"<<endl;
  cout<<tabla.ObtenerTipoParametro(nodo,"parametros",1)<<endl;
  cout<<tabla.ObtenerPasoParametro(nodo,"parametros",1)<<endl;

  cout<<"Dime la posiscion en el arbol de escribir era"<<nodo8<<endl;
  cout<<"OBTENGO"<<tabla.BuscarSimbolo("escribir")<<endl;
  cout<<"Dime el tipo de escribir (procemiento->3)"<<endl;
  cout<<tabla.ObtenerTipo(nodo8)<<endl;


  cout<<"Impresion en salida"<<endl;
  tabla.ImprimirTabla("salida.txt");

  return 0;

}

//---------------------------------------------------------------------------

