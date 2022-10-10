//---------------------------------------------------------------------------
#include <iostream.h>
#include <fstream.h>
#include <string.h>
#include "CARBOLSIMBOLOS.h"
#include <stdlib.h>
#include <stdio.h>
#include <typeinfo.h>
#include <time.h>

/****************************************************************************/
/********************  METODOS   PRIVADOS       *****************************/
/****************************************************************************/

/*##############################################################################
# NOMBRE: InsertarAtributoFuncion                                            #
# PROPOSITO: Insertar En la lista "funcion" un nuevo atributo                #
# PARAMETROS DE ENTRADA: Numero: 1                                           #
#                        * atr :Nuevo atributo a insertar, tipo :CAtributo   #
# SALIDA: -                                                                  #
# EXCEPCIONES: -                                                             #
##############################################################################*/
void CArbolSimbolos::InsertarAtributoFuncion(CAtributo *atr)
{
	if (estructurafuncion==NULL)
			estructurafuncion=atr;
	else
    {
		atr->SetSig(estructurafuncion);
        estructurafuncion= atr;
    }//else
};//InsertarAtributoFuncion

/*##############################################################################
# NOMBRE: InsertarAtributoIdentificador                                      #
# PROPOSITO: Insertar En la lista "identificador" un nuevo atributo          #
# PARAMETROS DE ENTRADA: Numero: 1                                           #
#                        * atr :Nuevo atributo a insertar, tipo :CAtributo   #
# EXCEPCIONES: -                                                             #
# SALIDA: -                                                                  #
##############################################################################*/
void CArbolSimbolos::InsertarAtributoIdentificador(CAtributo *atr)
{
	if (estructuraidentificador==NULL)
		estructuraidentificador=atr;
	else
    {
		atr->SetSig(estructuraidentificador);
        estructuraidentificador= atr;
	}//else
};//InsertarAtributoIdentificador

/*##############################################################################
# NOMBRE: InsertarAtributoVector                                             #
# PROPOSITO: Insertar En la lista "vector" un nuevo atributo                 #
# PARAMETROS DE ENTRADA: Numero: 1                                           #
#                        * atr :Nuevo atributo a insertar, tipo :CAtributo   #
# EXCEPCIONES: -                                                             #
# SALIDA: -                                                                  #
##############################################################################*/
void CArbolSimbolos::InsertarAtributoVector(CAtributo *atr)
{
	if (estructuravector==NULL)
		estructuravector=atr;
	else
    {
		atr->SetSig(estructuravector);
        estructuravector= atr;
	}//else
};//InsertarAtributoIdentificador

/*##############################################################################
# NOMBRE: InsertarAtributoClase                                              #
# PROPOSITO: Insertar En la lista "clase" un nuevo atributo                  #
# PARAMETROS DE ENTRADA: Numero: 1                                           #
#                        * atr :Nuevo atributo a insertar, tipo :CAtributo   #
# SALIDA: -                                                                  #
# EXCEPCIONES: -                                                             #
##############################################################################*/

void CArbolSimbolos::InsertarAtributoClase(CAtributo *atr)
{
	if (estructuraclase==NULL)
		estructuraclase=atr;
	else
    {
		atr->SetSig(estructuraclase);
        estructuraclase= atr;
	};//clase
}; //InsertarAtributoClase

/*##############################################################################
# NOMBRE: InsertarAtributoPalabraReserv                                      #
# PROPOSITO: Insertar En la lista "palabraReserv" un nuevo atributo          #
# PARAMETROS DE ENTRADA: Numero: 1                                           #
#                        * atr :Nuevo atributo a insertar, tipo :CAtributo   #
# SALIDA: -                                                                  #
# EXCEPCIONES: -                                                             #
##############################################################################*/
void CArbolSimbolos::InsertarAtributoPalabraReserv(CAtributo *atr)
{
	if (estructurapalabraReserv==NULL)
		estructurapalabraReserv=atr;
	else
    {
		atr->SetSig(estructurapalabraReserv);
        estructurapalabraReserv= atr;
	};//else
};//InsertarAtributoPalbraReserv


/*##############################################################################
# NOMBRE: InsertarAtributoProcedimiento                                      #
# PROPOSITO: Insertar En la lista "procedimiento" un nuevo atributo          #
# PARAMETROS DE ENTRADA: Numero: 1                                           #
#                        * atr :Nuevo atributo a insertar, tipo :CAtributo   #
# SALIDA: -                                                                  #
# EXCEPCIONES: -                                                             #
##############################################################################*/
void CArbolSimbolos::InsertarAtributoProcedimiento(CAtributo *atr)
{
	if (estructuraprocedimiento==NULL)
		estructuraprocedimiento=atr;
	else
    {
		atr->SetSig(estructuraprocedimiento);
        estructuraprocedimiento= atr;
	};//else
};//InsertarAtributoProcedimiento

/*##############################################################################
# NOMBRE: InsertarAtributoEtiqueta                                           #
# PROPOSITO: Insertar En la lista "etiqueta" un nuevo atributo               #
# PARAMETROS DE ENTRADA: Numero: 1                                           #
#                        * atr :Nuevo atributo a insertar, tipo :CAtributo   #
# SALIDA: -                                                                  #
# EXCEPCIONES: -                                                             #
##############################################################################*/
void CArbolSimbolos::InsertarAtributoEtiqueta(CAtributo *atr)
{
	if (estructuraetiqueta==NULL)
		estructuraetiqueta=atr;
	else	
    {
		atr->SetSig(estructuraetiqueta);
        estructuraetiqueta= atr;
	}  //else
}; //InsertarAtributoEtiqueta


/*##############################################################################
# NOMBRE: InsertarAtributoRegistro                                           #
# PROPOSITO: Insertar En la lista "registro" un nuevo atributo               #
# PARAMETROS DE ENTRADA: Numero: 1                                           #
#                        * atr :Nuevo atributo a insertar, tipo :CAtributo   #
# SALIDA: -                                                                  #
# EXCEPCIONES: -                                                             #
##############################################################################*/
void CArbolSimbolos::InsertarAtributoRegistro(CAtributo *atr)
{
if (estructuraregistro==NULL)
		estructuraregistro=atr;
else
           {atr->SetSig(estructuraregistro);
         	estructuraregistro= atr;} //else
}; //InsertarAtributoRegistro

/*##############################################################################
# NOMBRE: Eliminar          				                                 #
# PROPOSITO: Elimina recursivamente el arbol  de simbolos		             #
# PARAMETROS DE ENTRADA: Numero: 1                                           #
#                    arbol :arbol de simbolos a eliminar, tipo:CNodoSimbolo  #
# SALIDA: -                                                                  #
# EXCEPCIONES: -                                                             #
##############################################################################*/
void CArbolSimbolos::Eliminar(CNodoSimbolo *arbol)
{

CNodoSimbolo *act=arbol;//ptr que recorrera el arbol de simbolos
CNodoSimbolo *hi, *hd;//ptr a los hijos izq y derecho respectivamente

if (arbol!=NULL)
   {
	act->EliminarNodo();
    hi=act->GetHi();
	arbol=hi;
    Eliminar(arbol);
    hd=act->GetHd();
	arbol=hd;
	Eliminar(arbol);
    }//if
};//Eliminar

/*##############################################################################
# NOMBRE: Imprimir				                                             #
# PROPOSITO: Imprime un arbol de simbolos recursivamente en el fichero de    #
#             salida indicado                                                #
# PARAMETROS DE ENTRADA: Numero: 2                                           #
#           arbol :Arbol de simbolos a imprimir, tipo :CNodoSimbolosAtributo #
#			sal :Fichero de salida, tipo ostream                             #
# SALIDA: -                                                                  #
# EXCEPCIONES: -                                                             #
##############################################################################*/
void CArbolSimbolos::Imprimir(CNodoSimbolo *arbol,ostream &sal)
{

CNodoSimbolo *act=arbol;//ptr que recorrera el arbol de simbolos
CNodoSimbolo *hi, *hd;//ptr a los hijos izq y derecho respectivamente


if (arbol!=NULL)
   {
	act->ImprimirNodo(sal);
    hi=act->GetHi();
    Imprimir(hi,sal);
    hd=act->GetHd();
	Imprimir(hd,sal);
    }//if

}; //Iprimir

/****************************************************************************/
/********************  METODOS   PUBLICOS       *****************************/
/****************************************************************************/

/*##############################################################################
# NOMBRE: CrearTabla         				                                 #
# PROPOSITO: Crea una nueva tabla de simbolos					             #
# PARAMETROS DE ENTRADA: -									                 #
# SALIDA: -                                                                  #
# EXCEPCIONES: -                                                             #
##############################################################################*/
void CArbolSimbolos::CrearTabla()
{
    arbol = NULL;
    estructurafuncion = NULL;
    estructuraidentificador = NULL;
    estructuraclase = NULL;
	estructuraprocedimiento= NULL;
	estructuraetiqueta=NULL;
	estructuraregistro=NULL;
	estructurapalabraReserv=NULL;
    estructuravector=NULL;
};//CrearTabla

/*##############################################################################
# NOMBRE: ElimiarTabla         				                                 #
# PROPOSITO: Elimina una tabla de simbolos	    				             #
# PARAMETROS DE ENTRADA: -									                 #
# SALIDA: -                                                                  #
# EXCEPCIONES: -                                                             #
##############################################################################*/

void CArbolSimbolos::EliminarTabla()
{
CNodoSimbolo *act=arbol;//ptr que recorrera el arbol de simbolos
CNodoSimbolo *hi, *hd;//ptr a los hijos izq y derecho respectivamente


if (arbol!=NULL)
	{
    act->EliminarNodo();
	hi=act->GetHi();
	arbol=hi;
    Eliminar(arbol);
	hd=act->GetHd();
	arbol=hd;
	Eliminar(arbol);
    }//if
};//EliminarTabla

/*##############################################################################
# NOMBRE: ImprimirTabla				                                           #
# PROPOSITO: Imprime una tabla de simbolos en el fichero de salida             #
# PARAMETROS DE ENTRADA:Numero: 1                                              #
#                       salida: Tipo:cadena,nombre del fichero de salida       #
# SALIDA: -                                                                    #
# EXCEPCIONES: No se puede generar el fichero de salida                        #
##############################################################################*/
void CArbolSimbolos::ImprimirTabla(char *salida)
{

CNodoSimbolo *act=arbol;//ptr que recorrera el arbol de simbolos
CNodoSimbolo *hi, *hd;//ptr a los hijos izq y derecho respectivamente


try
{
ofstream sal (salida,ios::out);

if (!sal)
  throw "EXCEPCION En ImprimirTabla: Error abriendo fichero de salida";

if (arbol==NULL)
   sal<<"NULL";
else
	{
    sal<<"IMPRESION DE LA TABLA DE SIMBOLOS"<<endl;
    act->ImprimirNodo(sal);
    hi=act->GetHi();
    Imprimir(hi,sal);
    hd=act->GetHd();
	Imprimir(hd,sal);
    } //else

sal.close();
}//try
catch(char *str)
{
	cout<<str<<endl;
}//catch

};//Imprimir

/*##############################################################################
# NOMBRE: InsertarSimbolo		                                             #
# PROPOSITO: Inserta un nuevo simbolo en la tabla de simbolos				 #
# PARAMETROS DE ENTRADA:                                                     #
#                    lexema: Tipo:cadena de caracteres, lexema a insertar    #
# SALIDA: puntero a la posicion del arbol en la que se ha insertado el lexema#                                                                  #
# EXCEPCIONES:El lexema ya esta insertado   			                     #
#             Memoria Insufiente											 #
##############################################################################*/


CNodoSimbolo *CArbolSimbolos::InsertarSimbolo(char *lexema)
{

    CNodoSimbolo *act=arbol; //ptr que recorrera el arbol de simbolos
	int encontrado=0;//variable que controlara si hemos encontrado la posicion de inserccion
    int res;//variable que almancena el resultado de la comparcion del contenido de un nodo con
            // el parametro de entrada

    try
    {

    		CEntrada *entrada = new CEntrada(lexema);
    		CNodoSimbolo *nodo =new CNodoSimbolo(entrada);

			if (arbol==NULL)
		 		  {	arbol=nodo;
            	    return arbol;
                  }//if
			else
       			 {while (encontrado!=1)
					{res=act->Compara(lexema);

            		 if (res<0)
              			{
                           if (act->EsVacioHi())
                    		{
                            	act->SetHi(nodo);
                     			encontrado=1;
                     			return act->GetHi();
							}//if
                           else
								act=act->GetHi();
                		}//if
            		  else if (res>0)
               			{
						if (act->EsVacioHd())
                    		{	act->SetHd(nodo);
                     			encontrado=1;
                                return act->GetHd();
                    		}//if
                        else
                        	act=act->GetHd();
              			}//else if
                      else
 						 throw "EXCEPCION En InsertarSimbolo: Este lexema ya esta insertado";

           }//while
		}//else
}//try
catch(char *str)
{cout<<str<<endl;
throw;
}//catch

#if __GNUG__
    catch(...)
#else 
	catch(const CMemoryException)
#endif
{
	cout<<"EXCEPCION En InsertarSimbolo:No hay memoria suficiente"<<endl;
    throw;
}//catch*/
};//InsertarSimbolo
//*/
/*##############################################################################
# NOMBRE: IntroducirpalabraReservada                                         #
# PROPOSITO: Inserta una palabra Reservada								     #
# PARAMETROS DE ENTRADA:Numero:1											 #
#                  palabraReservada:palabra reservada a insertar,Tipo:cadena #
# SALIDA: -                                                                  #
# EXCEPCIONES: El lexema ya esta insertado   			                     #
#             Memoria Insufiente											 #										                     #
##############################################################################*/
 void CArbolSimbolos::IntroducirPalabraReservada(char *palabraReservada)
 {
 CNodoSimbolo *nodo;//ptr al nodo en el que se inserta la palabra reservada
try
 {
 nodo=InsertarSimbolo(palabraReservada);
 nodo->InsertarTipo((TipoEntrada)6);
 }//try
 catch(char *str)
 {
 }//catch
 };//IntroducirpalabraReservada

/*##############################################################################
# NOMBRE: EsPalabraReservada                                                 #
# PROPOSITO: Comprueba si un lexema esta almacenado como palabra reservada   #
# PARAMETROS DE ENTRADA:Numero:1											 #
#                       lexema: Lexema a consultar,Tipo:cadena               #
# SALIDA: Entero (0->falso, 1->Cierto)                                                                #
# EXCEPCIONES: -                                                             #
##############################################################################*/

int CArbolSimbolos::EsPalabraReservada(char *lexema)
{
CNodoSimbolo *nodo;//ptr al nodo que contiene dicho lexema

nodo=BuscarSimbolo(lexema);
if (nodo!=NULL)
  return nodo->CompruebaTipo((TipoEntrada)6);
else
  return 0;
};//EsPalabraReservada


/*##############################################################################
# NOMBRE: CrearEntrada				                                         #
# PROPOSITO: Crea la estructura de una entrada de la tabla de simbolos       #
# PARAMETROS DE ENTRADA:Numero:3											 #
#                   TipoEntrada:Tipo de la entrada a crear ,Tipo: TipoEntrada#
#                   nombre:nombre del atributo que se almacenará,Tipo:Cadena #
#                   Tipo:Tipo del atributo a almacenar,Tipo: Tipo            #
# SALIDA: -                                                                  #
# EXCEPCIONES: Memoria Insufiente						                     #
#              TipoEntrada no definido                                       #
#              Tipo del atributo no definido
##############################################################################*/
void CArbolSimbolos::CrearEntrada(TipoEntrada tent,char *nombre, Tipo tipo)
{

CCadena *atrcad; //ptr a un atributo de tipo cadena
CPuntero *atrptr; //ptr a un atributo de tipo puntero
CListaParametros *lpar;//ptr a un atributo de tipo lista parametros
CEntero *atrent; //ptr a un atributo de tipo entero
CArbolSimbolos *arbol; // ptr a un atributo de tipo arbol de simbolos

try
{

switch (tipo)
  {case 0:atrent=new CEntero(nombre);
          switch (tent)
                {
               case 0: InsertarAtributoFuncion(atrent);
                        break;
               case 1: InsertarAtributoIdentificador(atrent);
               			break;
               case 2: InsertarAtributoClase(atrent);
               			break;
               case 3: InsertarAtributoProcedimiento(atrent);
               			break;
               case 4: InsertarAtributoEtiqueta(atrent);
                        break;
               case 5: InsertarAtributoRegistro(atrent);
                        break;
               case 6: InsertarAtributoPalabraReserv(atrent);
               			break;
               case 7: InsertarAtributoVector(atrent);
               			break;
               default: throw "EXCEPCION EN CrearEntrada:Tipo de entrada no definido";
               } break;
    case 1: atrcad=new CCadena(nombre);
            switch (tent)
                {
               case 0: InsertarAtributoFuncion(atrcad);
                       break;
               case 1: InsertarAtributoIdentificador(atrcad);
                       break;
               case 2: InsertarAtributoClase(atrcad);
                       break;
               case 3: InsertarAtributoProcedimiento(atrcad);
                       break;
               case 4: InsertarAtributoEtiqueta(atrcad);
                       break;
               case 5: InsertarAtributoRegistro(atrcad);
                       break;
               case 6: InsertarAtributoPalabraReserv(atrcad);
                       break;
               case 7: InsertarAtributoVector(atrcad);
               			break;

               default: throw "EXCEPCION EN CrearEntrada:Tipo de entrada no definido";
               } break;
    case 2: atrptr=new CPuntero(nombre);
            switch (tent)
                {
               case 0: InsertarAtributoFuncion(atrptr);
                        break;
               case 1: InsertarAtributoIdentificador(atrptr);
                        break;
               case 2: InsertarAtributoClase(atrptr);
                        break;
               case 3: InsertarAtributoProcedimiento(atrptr);
                        break;
               case 4: InsertarAtributoEtiqueta(atrptr);
                        break;
               case 5: InsertarAtributoRegistro(atrptr);
                        break;
               case 6: InsertarAtributoPalabraReserv(atrptr);
                        break;
               case 7: InsertarAtributoVector(atrent);
               			break;

               default: throw "EXCEPCION EN CrearEntrada:Tipo de entrada no definido";
               } break;
    case 3: lpar=new CListaParametros(nombre);
            switch (tent)
                {
               case 0: InsertarAtributoFuncion(lpar);
                       break;
               case 1: InsertarAtributoIdentificador(lpar);
                       break;
               case 2: InsertarAtributoClase(lpar);
                       break;
               case 3: InsertarAtributoProcedimiento(lpar);
                       break;
               case 4: InsertarAtributoEtiqueta(lpar);
                       break;
               case 5: InsertarAtributoRegistro(lpar);
                       break;
               case 6: InsertarAtributoPalabraReserv(lpar);
                       break;
               case 7: InsertarAtributoVector(atrent);
               			break;
               default: throw "EXCEPCION EN CrearEntrada:Tipo de entrada no definido";

               } break;
			/*lydia*/
	/*case 4: arbol.CrearTabla();
	*/		

   default: throw "EXCEPCION EN CrearEntrada:Tipo del atributo no definido";
   }//swich
}//try
#if __GNUG__
    catch(...)
#else if 
	catch(const CMemoryException)
#endif
{
	cout<<"EXCEPCION EN CrearEntrada:No hay memoria suficiente"<<endl;
}//catch
};//CrearEntrada

/*##############################################################################
# NOMBRE: BuscarSimbolo				                                         #
# PROPOSITO: Busca la posicion de un lexema en el arbol de simbolos, si no   #
#            está se devolvera un puntero a NULL		                     #
# PARAMETROS DE ENTRADA:Numero:1											 #
#                       lex: lexema a buscar,Tipo:Cadena                     #
# SALIDA: punero a la posicion en la que se encuentra el lexema indicado     #
#		   o NULL                                                            #
# EXCEPCIONES: -										                     #
##############################################################################*/
CNodoSimbolo *CArbolSimbolos::BuscarSimbolo(char *lex)
{
   bool encontrado = false;//variable booleana que controla si hemos encontrado el simbolo
   CNodoSimbolo *act=arbol;//ptr que recorrera el arbol de simbolos
   int res;//variable que almancena el resultado de la comparcion del contenido de un nodo con
           // el parametro de entrada


   if (arbol != NULL)
	 {while (act && !encontrado)
		   {res=act->Compara(lex);
	       if (res==0)
    	      encontrado = true;
           else if (res<0)
			 act = act->GetHi();
     	   else
			 act = act->GetHd();
   	 }//while
     }//if
   if (!encontrado)
     	act=NULL;
 return act;

};//BuscarSimbolo


/*##############################################################################
# NOMBRE: InsertarTipo													     #
# PROPOSITO: Inserta el tipo a un lexema almacenado							 #
# PARAMETROS DE ENTRADA:Numero:2											 #
#           simb:ptr a la posicion del lexema en el arbol, Tipo: CNodoSimbolo#
#           tent:Tipo del lexema,Tipo TipoEntrada							 #
# SALIDA: -                                                                  #
# EXCEPCIONES: Tipo Entrada no definido					                     #
##############################################################################*/

void CArbolSimbolos::InsertarTipo(CNodoSimbolo *simb,TipoEntrada tent)
{
//funcion=0, identificador, clase, procedimiento,
//                  etiqueta, registro, palabraReserv, vector};
try
{
if (tent>7)
	throw "EXCEPCION EN InsertarTipo: TipoEntrada no definido";
else
  {
        simb->InsertarTipo(tent);
	switch(tent)
        {
	    case 0:simb->Copiar(estructurafuncion);
    	       break;
	    case 1:simb->Copiar(estructuraidentificador);
    	       break;
	    case 2:simb->Copiar(estructuraclase);
    	       break;
	    case 3:simb->Copiar(estructuraprocedimiento);
    	       break;
	    case 4:simb->Copiar(estructuraetiqueta);
	           break;
    	    case 5:simb->Copiar(estructuraregistro);
	           break;
            case 6:simb->Copiar(estructurapalabraReserv);
	           break;
	    default:simb->Copiar(estructuravector);
    	          break;
        }//swich
  }//else
}//try
catch(char *str)
{
cout<<str<<endl;
}//catch
};//InsertarTipo

/*##############################################################################
# NOMBRE: ObtenerTipo													       #
# PROPOSITO: Obtiene el tipo a un lexema almacenado							   #
# PARAMETROS DE ENTRADA: Numero:1											   #
#   simb:ptr a la posicion del lexema en el arbol de simbolos,Tipo:CNodoSimbolo#
# SALIDA: Tipo del lexema almacenado                                           #
# EXCEPCIONES: El lexema no tiene un tipo asignado		                       #
##############################################################################*/

TipoEntrada CArbolSimbolos::ObtenerTipo(CNodoSimbolo *simb)
{
try
	{
    return simb->DevolverTipo();
	}//try

catch(char *str)
	{
	}//catch
}; //ObtenerTipo

/*##############################################################################
# NOMBRE: InsertarValor													       #
# PROPOSITO: Inserta un valor de tipo entero a un atributo de un lexema		   #
# PARAMETROS DE ENTRADA: Numero:3											   #
#   simb:ptr a la posicion del lexema en el arbol de simbolos,Tipo:CNodoSimbolo#
#   nombre:nombre del atributo, Tipo:Cadena									   #
#   valor:valor que se quiere almacenar,Tipo:Entero							   #
# SALIDA: -								                                       #
# EXCEPCIONES: El atributo no pertenezca a la entrada	                       #
##############################################################################*/
void CArbolSimbolos::InsertarValor(CNodoSimbolo *simb,char *nombre,int valor)
{
try
	{
	simb->InsertarValor(nombre,valor);
	}//try
catch(char *str)
	{
	}//catch
};//InsertarValor

/*##############################################################################
# NOMBRE: InsertarValor													       #
# PROPOSITO: Inserta un valor de cadena entero a un atributo de un lexema      #
# PARAMETROS DE ENTRADA: Numero:3											   #
#   simb:ptr a la posicion del lexema en el arbol de simbolos,Tipo:CNodoSimbolo#
#   nombre:nombre del atributo, Tipo:Cadena									   #
#   valor:valor que se quiere almacenar,Tipo:cadena  						   #
# SALIDA: -								                                       #
# EXCEPCIONES: El atributo no pertenezca a la entrada	                       #
##############################################################################*/

void CArbolSimbolos::InsertarValor(CNodoSimbolo *simb,char *nombre,char *valor)
{


try
	{
    simb->InsertarValor(nombre,valor);
	}//try
catch(char *str)
	{
	}//catch
};//InsertarValor

/*##############################################################################
# NOMBRE: InsertarValor													       #
# PROPOSITO: Inserta un valor de tipo puntero a un atributo de un lexema       #
# PARAMETROS DE ENTRADA: Numero:3											   #
#   simb:ptr a la posicion del lexema en el arbol de simbolos,Tipo:CNodoSimbolo#
#   nombre:nombre del atributo, Tipo:Cadena									   #
#   valor:valor que se quiere almacenar,Tipo:puntero						   #
# SALIDA: -								                                       #
# EXCEPCIONES: El atributo no pertenezca a la entrada	                       #
##############################################################################*/

void CArbolSimbolos::InsertarValor(CNodoSimbolo *simb,char *nombre,void *valor)
{

try
	{
	simb->InsertarValor(nombre,valor);
	}//try
catch(char *str)
	{
	}//catch
};//InsertarValor

/*##############################################################################
# NOMBRE: DevolverValor													       #
# PROPOSITO: Devuelve el valor de un atributo de tipo entero de un lexema      #
# PARAMETROS DE ENTRADA: Numero:3											   #
#   simb:ptr a la posicion del lexema en el arbol de simbolos,Tipo:CNodoSimbolo#
#   nombre:nombre del atributo, Tipo:Cadena									   #
#   valor:valor que se quiere obtener,Tipo:Entero							   #
# SALIDA: en el parametro valor se almacenara el valor pedido			       #					                                     #
# EXCEPCIONES: El atributo no pertenezca a la entrada	                       #
#              El atributo no tiene valor alguno							   #
##############################################################################*/

void CArbolSimbolos::DevolverValor(CNodoSimbolo *simb,char *nombre,int &valor)
{
try
	{
	simb->DevolverValor(nombre,valor);
	}//try
catch(char *str)
	{
	}//catch
};//DevolverValor

/*##############################################################################
# NOMBRE: DevolverValor													       #
# PROPOSITO: Devuelve el valor de un atributo de tipo puntero de un lexema	   #
# PARAMETROS DE ENTRADA: Numero:3											   #
#   simb:ptr a la posicion del lexema en el arbol de simbolos,Tipo:CNodoSimbolo#
#   nombre:nombre del atributo, Tipo:Cadena									   #
#   valor:valor que se quiere obtener,Tipo:Punt 							   #
# SALIDA: en el parametro valor se almacenara el valor pedido			       #					                                     #
# EXCEPCIONES: El atributo no pertenezca a la entrada	                       #
#              El atributo no tiene valor alguno							   #
##############################################################################*/
void CArbolSimbolos::DevolverValor(CNodoSimbolo *simb,char *nombre,punt &valor)
{
try
	{
    simb->DevolverValor(nombre,valor);
	}//try
catch(char *str)
	{
	}//catch
};//DevolverValor

/*##############################################################################
# NOMBRE: DevolverValor													       #
# PROPOSITO: Devuelve el valor de un atributo de tipo palabra de un lexema	   #
# PARAMETROS DE ENTRADA: Numero:3											   #
#   simb:ptr a la posicion del lexema en el arbol de simbolos,Tipo:CNodoSimbolo#
#   nombre:nombre del atributo, Tipo:Cadena									   #
#   valor:valor que se quiere obtener,Tipo:palabra  						   #
# SALIDA: en el parametro valor se almacenara el valor pedido			       #					                                     #
# EXCEPCIONES: El atributo no pertenezca a la entrada	                       #
#              El atributo no tiene valor alguno							   #
##############################################################################*/
void CArbolSimbolos::DevolverValor(CNodoSimbolo *simb,char *nombre,palabra &valor)
{
try
	{
    simb->DevolverValor(nombre,valor);
	}//try
catch(char *str)
	{
	}//catch
};//DevolverValor

/*##############################################################################
# NOMBRE: ObtenerTipoparametro  										       #
# PROPOSITO: Devuelve el tipo de un parametro de una funcion                   #
# PARAMETROS DE ENTRADA: Numero:3											   #
#   simb:ptr a la posicion del lexema en el arbol de simbolos,Tipo:CNodoSimbolo#
#   nombre:nombre del atributo, Tipo:Cadena									   #
#   numeroparam:Posicion del parametro en la lista de parametros, Tipo:Entero  #
# SALIDA: El tipo del parametro pedido										   #
# EXCEPCIONES: El atributo no pertenezca a la entrada	                       #
#              numeroparam erroneo											   #
##############################################################################*/


TipoParam CArbolSimbolos::ObtenerTipoParametro(CNodoSimbolo *simb,char *nombre,int numeroparam)
{
try
	{
    return simb->ObtenerTipoParam(nombre,numeroparam);
	}//try
catch(char *str)
	{
	}//catch*/
catch(int nparam)
	{

	}//catch

};//ObtenerTipoParametro

/*##############################################################################
# NOMBRE: ObtenerPasoParametro  										       #
# PROPOSITO: Devuelve el tipo del paso un parametro de una funcion             #
# PARAMETROS DE ENTRADA: Numero:3											   #
#   simb:ptr a la posicion del lexema en el arbol de simbolos,Tipo:CNodoSimbolo#
#   nombre:nombre del atributo, Tipo:Cadena									   #
#   valor:valor que se quiere obtener,Tipo:Entero							   #
# SALIDA: El tipo del paso del parametro pedido								   #
# EXCEPCIONES: El atributo no pertenezca a la entrada	                       #
#              numeroparam erroneo											   #
##############################################################################*/

PasoParametro CArbolSimbolos::ObtenerPasoParametro(CNodoSimbolo *simb,char *nombre,int numeroparam)
{
try
	{
	return simb->ObtenerPasoParam(nombre,numeroparam);
	}//try
catch(char *str)
	{
	}//catch
catch(int nparam)
	{

	}//catch

};//ObtenerPasoarametro

/*##############################################################################
# NOMBRE: InsertarParametro  										           #
# PROPOSITO: Insertar un atributo parametro a un determinado lexema funcion    #
#            o procedimiento                								   #
# PARAMETROS DE ENTRADA: Numero:4											   #
#   simb:ptr a la posicion del lexema en el arbol de simbolos,Tipo:CNodoSimbolo#
#   nombre:nombre del atributo, Tipo:Cadena									   #
#   pparam:Paso del parametro,Tipo:Pasoparametro    						   #
#   tipo:Tipo del parametro,Tipo:TipoParam                                     #
# SALIDA: -																	   #
# EXCEPCIONES: El atributo no pertenezca a la entrada	                       #
#              memoria insufiente										       #
##############################################################################*/

void CArbolSimbolos::InsertarParametro(CNodoSimbolo *simb, char *nombre, PasoParametro pparam, TipoParam tipo)
{
try
	{
	simb->InsertarParam(nombre,pparam,tipo);
	}//try
catch(char *str)
	{
	}//catch
};
//---------------------------------------------------------------------------
//#pragma package(smart_init)

