#include "CArbolSimbolos.h"

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
  tabla.InsertarTipo(nodo1,funcion);
  tabla.InsertarTipo(nodo2,identificador);
  tabla.InsertarTipo(nodo3,identificador);
  tabla.InsertarTipo(nodo4,funcion);
  tabla.InsertarTipo(nodo5,identificador);
  tabla.InsertarTipo(nodo6,etiqueta);
  tabla.InsertarTipo(nodo7,vector);
  tabla.InsertarTipo(nodo8,procedimiento);
  tabla.InsertarTipo(nodo9,identificador);

  cout<<"Inicializamos la tabla de simbolos: while, for, if, then, break,case, define,int,char,void,throw,catch"<<endl;

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
  tabla.InsertarValor(nodo,"numpara",2);
  tabla.InsertarValor(nodo,"tiporetorn","entero");
  valor_d=(void *)nodo;
  tabla.InsertarValor(nodo,"direccio",valor_d);
  tabla.InsertarParametro(nodo,"parametros",valor,ptr);
  tabla.InsertarParametro(nodo,"parametros",referencia,ent);

  cout<<"damos valor a los atributos de resta(15.7,9.6)"<<endl;
  tabla.InsertarValor(nodo1,"numpara",2);
  tabla.InsertarValor(nodo1,"tiporetorn","real");
  valor_d=(void *)nodo1;
  tabla.InsertarValor(nodo1,"direccio",valor_d);
  tabla.InsertarParametro(nodo1,"parametros",valor,real);
  tabla.InsertarParametro(nodo1,"parametr",valor,real);

  cout<<"damos valor a los atributos de resultado"<<endl;
  valor_d=(void *)nodo2;
  tabla.InsertarValor(nodo2,"posicionmemori",valor_d);
  tabla.InsertarValor(nodo2,"inicializad",0);


  cout<<"damos valor a los atributos de vector[9]"<<endl;
  valor_d=(void *)nodo7;
  tabla.InsertarValor(nodo7,"tipoelement","enteros");
  tabla.InsertarValor(nodo7,"posicio",9);
  tabla.InsertarValor(nodo7,"numeroelemento",10);
  tabla.InsertarValor(nodo7,"indicein",0);
  tabla.InsertarValor(nodo7,"indicesu",9);

  cout<<"Dame valores del primer parametro de suma (era ptr->66,referencia->1)"<<endl;
  cout<<tabla.ObtenerTipoParametro(nodo,"parametros",1)<<endl;
  cout<<tabla.ObtenerPasoParametro(nodo,"parametros",1)<<endl;


 cout<<"Impresion en salida"<<endl;
 tabla.ImprimirTabla("salida.txt");

  return 0;

}
//---------------------------------------------------------------------------

