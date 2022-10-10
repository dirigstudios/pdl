//---------------------------------------------------------------------------
#include <iostream.h>
#include <string.h>
#include "cNodosimbolo.h"
#include <stdlib.h>
#include <stdio.h>

/*##############################################################################
# NOMBRE: CNodoSimbolo														 #
# PROPOSITO:Constructor de CNodoSimbolo										 #
# PARAMETROS DE ENTRADA: Numero:1											 #
#			ent:entrada que contendra el nodo, Tipo:CEntrada   				 #
# SALIDA:-																	 #
# EXCEPCIONES:-																 #
##############################################################################*/


CNodoSimbolo::CNodoSimbolo(CEntrada *ent)
{
    entrada = ent;
    hi = NULL;
    hd = NULL;
};//CNodoSimbolo

/*##############################################################################
# NOMBRE: EliminarNodo															 #
# PROPOSITO:Elimina un nodo liberando los recursos asignados				 #
# PARAMETROS DE ENTRADA:-													 #
# SALIDA:-																	 #
# EXCEPCIONES:-																 #
##############################################################################*/

void CNodoSimbolo::EliminarNodo()
{
    entrada->EliminarEntrada();
    delete entrada;
};//Eliminar

/*##############################################################################
# NOMBRE: GetHi																 #
# PROPOSITO:Devuelve el hijo izquierdo de un nodo							 #
# PARAMETROS DE ENTRADA:-													 #
# SALIDA:Puntero al hijo izquierdo del nodo									 #
# EXCEPCIONES:-																 #
##############################################################################*/

/*CNodoSimbolo * CNodoSimbolo::GetHi()
{
    return hi;
};//GetHi

/*##############################################################################
# NOMBRE: SetHi																 #
# PROPOSITO:Inserta el hijo izquierdo de un nodo							 #
# PARAMETROS DE ENTRADA:Numero:1											 #
#                 nodo:Nodo que se insertara como hijo izq,Tipo:CNodoSimbolos#
# SALIDA:-																	 #
# EXCEPCIONES:-																 #
##############################################################################*/

/*void CNodoSimbolo::SetHi(CNodoSimbolo *nodo)
{
    hi=nodo;
};//SetHi
/*##############################################################################
# NOMBRE: SetHd																 #
# PROPOSITO:Inserta el hijo derecho   de un nodo							 #
# PARAMETROS DE ENTRADA:Numero:1											 #
#                nodo:Nodo que se insertara como hijo dcho,Tipo:CNodoSimbolos#
# SALIDA:-																	 #
# EXCEPCIONES:-																 #
##############################################################################*/

/*void CNodoSimbolo::SetHd(CNodoSimbolo *nodo)
{
    hd=nodo;
};//SetHd

/*##############################################################################
# NOMBRE: GetHd																 #
# PROPOSITO:Devuelve el hijo derecho   de un nodo							 #
# PARAMETROS DE ENTRADA:-													 #
# SALIDA:Puntero al hijo derecho del nodo									 #
# EXCEPCIONES:-																 #
##############################################################################*/

/*CNodoSimbolo * CNodoSimbolo::GetHd()
{
    return hd;
};//GetHd


/*##############################################################################
# NOMBRE:Compara															 #
# PROPOSITO:Compara un lexema con el contenido de un nodo				     #
# PARAMETROS DE ENTRADA:Numero:1											 #
#    lexema:lexema a comparar con el lexema almacenado en el nodo,Tipo:Cadena#
# SALIDA:resultado de la comparacion										 #
# EXCEPCIONES:-																 #
##############################################################################*/

/*int CNodoSimbolo::Compara(char *lexema)
{
return strcmp(lexema,entrada->GetLexema());
};//Compara

/*##############################################################################
# NOMBRE: EsVacioHi  													   	 #
# PROPOSITO:Comprueba si un nodo tiene un hijo izquierdo					 #
# PARAMETROS DE ENTRADA:-													 #
# SALIDA:resultado de la comprobacion										 #
# EXCEPCIONES:-																 #
##############################################################################*/
/*bool CNodoSimbolo::EsVacioHi()
{
return (hi==NULL);
};//ExisteHi

/*##############################################################################
# NOMBRE: Copiar													         #
# PROPOSITO: Asigna a una entrada una serie de atributos                     #
# PARAMETROS DE ENTRADA: Numero:1                                            #
#                        lista:Lista a copiar, Tipo:CAtributo      		     #
# SALIDA: -																     #
# EXCEPCIONES: -										                     #
##############################################################################*/

/*void CNodoSimbolo::Copiar(CAtributo *lista)
{
entrada->Copiar(lista);
};//Copiar

/*##############################################################################
# NOMBRE: DevolverTipo													     #
# PROPOSITO: Obtiene el tipo de una entrada									 #
# PARAMETROS DE ENTRADA: - 											         #
# SALIDA: Tipo del lexema almacenado                                         #
# EXCEPCIONES: -										                     #
##############################################################################*/

TipoEntrada CNodoSimbolo::DevolverTipo()
{
try
 {
	return entrada->GetTipo();
 }//try
catch(char *str)
	{
    throw;
	}//catch
}; //DevolverTipo


/*##############################################################################
# NOMBRE: InsertarValor													     #
# PROPOSITO: Inserta un valor de tipo entero a un atributo de un lexema		 #
# PARAMETROS DE ENTRADA:Numero:2                                             #
#                 nombre:nombre del atributo, Tipo:Cadena                    #
#                 valor:valor que se quiere almacenar,Tipo:Entero			 #
# SALIDA: -								                                     #
# EXCEPCIONES: El atributo no pertenezca a la entrada	                     #
##############################################################################*/
void CNodoSimbolo::InsertarValor(char *nombre,int valor)
{
try
	{
	entrada->InsertarValor(nombre,valor);
	}//try
catch(char *str)
	{
    throw;
	}//catch
};//InsertarValor

/*##############################################################################
# NOMBRE: DevolverValor														 #
# PROPOSITO: Da el valor de un atributo de tipo entero                       #
# PARAMETROS DE ENTRADA:Numero:2                                             #
#                 nombre:nombre del atributo, Tipo:Cadena                    #
#                 valor:valor que se devolvera,Tipo:Entero      			 #
# SALIDA:En el parametro de entrada valor se devolvera el valor pedido	     #
# EXCEPCIONES: El atributo no exista					                     #
#              El atributo no tenga valor									 #
##############################################################################*/

void CNodoSimbolo::DevolverValor(char *nombre,int &valor)
{
try
	{
	entrada->DevolverValor(nombre,valor);
	}//try
catch(char *)
	{
	throw;
	}//catch
};//DevolverValor

/*##############################################################################
# NOMBRE: DevolverValor														 #
# PROPOSITO: Da el valor de un atributo de tipo puntero                      #
# PARAMETROS DE ENTRADA:Numero:2                                             #
#                 nombre:nombre del atributo, Tipo:Cadena                    #
#                 valor:valor que se quiere almacenar,Tipo:puntero			 #
# SALIDA:En el parametro de entrada valor se devolvera el valor pedido	     #
# EXCEPCIONES: El atributo no exista					                     #
#              El atributo no tenga valor									 #
##############################################################################*/

void CNodoSimbolo::DevolverValor(char *nombre,punt &valor)
{
try
	{
	entrada->DevolverValor(nombre,valor);
	}//try
catch(char *)
	{
	throw;
	}//catch
};//DevolverValor

 /*##############################################################################
# NOMBRE: DevolverValor														 #
# PROPOSITO: Da el valor de un atributo de tipo palabra                      #
# PARAMETROS DE ENTRADA:Numero:2                                             #
#                 nombre:nombre del atributo, Tipo:Cadena                    #
#                 valor:valor que se quiere almacenar,Tipo:Palabra			 #
# SALIDA:En el parametro de entrada valor se devolvera el valor pedido	     #
# EXCEPCIONES: El atributo no exista					                     #
#              El atributo no tenga valor									 #
##############################################################################*/

void CNodoSimbolo::DevolverValor(char *nombre,palabra &valor)
{
try
	{
	entrada->DevolverValor(nombre,valor);
	}//try
catch(char *)
	{
	throw;
	}//catch
};//DevolverValor

/*##############################################################################
# NOMBRE: ObtenerPasoParam  										         #
# PROPOSITO: Devuelve el tipo del paso un parametro de una funcion           #
## PARAMETROS DE ENTRADA:Numero:2                                            #
#                 nombre:nombre del atributo, Tipo:Cadena                    #
#                 numeroparam:numero en la lista de parametros,Tipo:Entero	 #
# SALIDA: El tipo del paso del parametro pedido								 #
# EXCEPCIONES: El atributo no pertenezca a la entrada	                     #
##############################################################################*/

PasoParametro CNodoSimbolo::ObtenerPasoParam(char *nombre,int numeroparam)
{
try
	{
    return entrada->ObtenerPasoParam(nombre,numeroparam);
	}//try

catch(char *str)
	{
    throw;
	}//catch
catch(int nparam)
	{
    cout<<"EXCEPCION EN ObtenerPasoParametro: EL parametro "<<nparam<<" no existe"<<endl;
	throw;
	}//catch
};//ObtenerPasoarametro

/*##############################################################################
# NOMBRE: InsertarParam  										             #
# PROPOSITO: Insertar un atributo parametro a un determinado lexema funcion  #
#            o procedimiento                								 #
# PARAMETROS DE ENTRADA:Numero:3                                             #
#                 nombre:nombre del atributo, Tipo:Cadena                    #
#                 pparam:paso del parametro,Tipo:Pasoparametro				 #
#                 tipo:Tipo del parametro,Tipo:TipoParam					 #
# SALIDA: -																	 #
# EXCEPCIONES: El atributo no pertenezca a la entrada	                     #
#              memoria insufiente										     #
##############################################################################*/

void CNodoSimbolo::InsertarParam(char *nombre, PasoParametro pparam, TipoParam tipo)
{
try
	{
	entrada->InsertarParametro(nombre,pparam,tipo);
	}//try
catch(char *srt)
	{
    throw;
	}//catch
};
/*##############################################################################
# NOMBRE: ObtenerTipoParam  										         #
# PROPOSITO: Devuelve el tipo de un parametro de una funcion                 #
## PARAMETROS DE ENTRADA:Numero:2                                            #
#                 nombre:nombre del atributo, Tipo:Cadena                    #
#                 numeroparam:numero en la lista de parametros,Tipo:Entero	 #
# SALIDA: El tipo del parametro pedido										 #
# EXCEPCIONES: El atributo no pertenezca a la entrada	                     #
##############################################################################*/


TipoParam CNodoSimbolo::ObtenerTipoParam(char *nombre,int numeroparam)
{
try
	{
	return entrada->ObtenerTipoParam(nombre,numeroparam);
	}//try
catch(char *str)
	{
    throw;
	}//catch

catch(int nparam)
	{
    cout<<"EXCEPCION EN ObtenerTipoParametro: EL parametro "<<nparam<<" no existe"<<endl;
	throw;
	}//catch
};//ObtenerTipoParametro

/*##############################################################################
# NOMBRE: InsertarValor													     #
# PROPOSITO: Inserta un valor de tipo puntero a un atributo de un lexema	 #
## PARAMETROS DE ENTRADA:Numero:2                                            #
#                 nombre:nombre del atributo, Tipo:Cadena                    #
#                 numeroparam:numero en la lista de parametros,Tipo:Entero	 #
# SALIDA: -								                                     #
# EXCEPCIONES: El atributo no pertenezca a la entrada	                     #
##############################################################################*/
void CNodoSimbolo::InsertarValor(char *nombre,void *valor)
{
try
	{
	entrada->InsertarValor(nombre,valor);
	}//try
catch(char *str)
	{
    throw;
	}//catch
};//InsertarValor

/*##############################################################################
# NOMBRE: InsertarValor													     #
# PROPOSITO: Inserta un valor de tipo cadena a un atributo de un lexema		 #
# PARAMETROS DE ENTRADA:Numero:2                                             #
#                 nombre:nombre del atributo, Tipo:Cadena                    #
#                 valor:valor que se quiere almacenar,Tipo:Palabra			 #
# SALIDA: -								                                     #
# EXCEPCIONES: El atributo no pertenezca a la entrada	                     #
##############################################################################*/
void CNodoSimbolo::InsertarValor(char *nombre,char *valor)
{
try
	{
	entrada->InsertarValor(nombre,valor);
	}//try
catch(char *str)
	{
    throw;
	}//catch
};//InsertarValor

/*##############################################################################
# NOMBRE: InsertarTipo 													   	 #
# PROPOSITO:Introduce el tipo de la entrada									 #
# PARAMETROS DE ENTRADA:Numero:1											 #
#                       tent:Tipo de la entrada,Tipo:TipoEntrda				 #
# SALIDA:-																	 #
# EXCEPCIONES:-																 #
##############################################################################*/
/*void CNodoSimbolo::InsertarTipo(TipoEntrada tent)
{
entrada->InsertarTipo(tent);
};//InsertarTipo

/*##############################################################################
# NOMBRE: ComprobarTipo													   	 #
# PROPOSITO:Comprueba si un nodo tiene ese determinado tipo					 #
# PARAMETROS DE ENTRADA:Tipo a comprobar									 #
# SALIDA:resultado de la comprobacion										 #
# EXCEPCIONES:-																 #
##############################################################################*/
/*int CNodoSimbolo::CompruebaTipo(TipoEntrada tent)
{
return entrada->CompruebaTipoEntrada(tent);
};//CompruebaTipo

/*##############################################################################
# NOMBRE: EsVacioHd  													   	 #
# PROPOSITO:Comprueba si un nodo tiene un hijo derecho						 #
# PARAMETROS DE ENTRADA:-													 #
# SALIDA:resultado de la comprobacion										 #
# EXCEPCIONES:-																 #
##############################################################################*/
/*bool CNodoSimbolo::EsVacioHd()
{
return (hd==NULL);
};//ExisteHd

/*##############################################################################
# NOMBRE: ImprimirNodo													   	 #
# PROPOSITO:Imprime el contenido de un nodo								     #
# PARAMETROS DE ENTRADA:Numero:1                                             #
#                sal: Fichero de salida, Tipo:ostream						 #
# SALIDA:-																	 #
# EXCEPCIONES:-																 #
##############################################################################*/
void CNodoSimbolo::ImprimirNodo(ostream &sal)
{
sal<<entrada->GetLexema()<<":";
entrada->ImprimirEntrada(sal);
};//Imprimir

//---------------------------------------------------------------------------
//#pragma package(smart_init)
