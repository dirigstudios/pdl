//---------------------------------------------------------------------------
#include <iostream.h>
#include <stdlib.h>
#include <string.h>
#include "CENTRADA.h"
#include "typeinfo.h"
#include <stdio.h>

/****************************************************************************/
/********************  METODOS PUBLICOS         *****************************/
/****************************************************************************/
/*##############################################################################
# NOMBRE: Copia															     #
# PROPOSITO: Realizar una copia de una lista de atributos		 			 #
# PARAMETROS DE ENTRADA: Numero:1											 #
# 		  lista: Lista a copiar, Tipo:CAtributo                    		     #
# SALIDA: Copia de la lista pasada como parametro de entrada				 #
# EXCEPCIONES:No hay memoria sufiente					                     #
##############################################################################*/


CAtributo *CEntrada::Copia(CAtributo *lista)
{
CAtributo *nuevo;//Ptr al nuevo atributo
CAtributo *creado;//Ptr al nuevo atributo creado
CAtributo *act=lista;//Ptr a la lista de atributos
CAtributo *neu;//Ptr al nuevo atributo insertado

char *nombre;
try
{
if (lista==NULL)
	 nuevo=NULL;
else
	{
	nombre=act->GetNombre();
	try
	{
		if (strcmp(act->DarTipo(),"entero")==0)
			nuevo= new CEntero(nombre);
		else if (strcmp(act->DarTipo(),"parametro")==0)
			nuevo= new CListaParametros(nombre);
		else if (strcmp(act->DarTipo(),"cadena")==0)
			nuevo= new CCadena(nombre);
		else if (strcmp(act->DarTipo(),"puntero")==0)
			nuevo= new CPuntero(nombre);
	neu=nuevo;
	while (act!=NULL)
		{   
			act=act->GetSig();
			if (act==NULL)
    			neu->SetSig(NULL);
            else
			  {
		       nombre=act->GetNombre();
			   if (strcmp(act->DarTipo(),"entero")==0)
					creado= new CEntero(nombre);
				else if (strcmp(act->DarTipo(),"parametro")==0)
					creado= new CListaParametros(nombre);
				else if (strcmp(act->DarTipo(),"cadena")==0)
					creado= new CCadena(nombre);
				else if (strcmp(act->DarTipo(),"puntero")==0)
					creado = new CPuntero(nombre);
    			neu->SetSig(creado);
			    neu=neu->GetSig();
               }//else

 		}//while
		}// try
	#if __GNUG__
		catch(...)
	#else 
		catch(const EAccessViolation)
	#endif	
	{
		throw;
	};
	 }//else
return nuevo;

}//try
#if __GNUG__
    catch(...)
#else
	catch(const CMemoryException)
#endif
{
cout<<"EXCEPCION:No hay memoria suficiente"<<endl;
throw;
};//catch

};//Copia


/****************************************************************************/
/********************  METODOS PRIVADOS         *****************************/
/****************************************************************************/

/*##############################################################################
# NOMBRE: Eliminar  										  		         #
# PROPOSITO: Elimina una entrada liberando los recursos		                 #
# PARAMETROS DE ENTRADA: -          										 #
# SALIDA: -																	 #
# EXCEPCIONES: -										                     #
##############################################################################*/

CEntrada::EliminarEntrada()
{
CAtributo *atr=atributos;//Ptr a la lista de atributos

delete lexema;
if (atributos != NULL){

   while (atr)
   {
   atr->EliminarAtributo();
   atr = atr->GetSig();
   } //while
}//if
};//Eliminar


/*##############################################################################
# NOMBRE: Copiar													         #
# PROPOSITO: Asigna a una entrada una serie de atributos                     #
# PARAMETROS DE ENTRADA: Numero:1											 #
# 		  lista: Lista a copiar, Tipo:CAtributo                    		     #
# SALIDA: -																     #
# EXCEPCIONES: -										                     #
##############################################################################*/

void CEntrada::Copiar(CAtributo *lista)
{
this->atributos=Copia(lista);
};//Copiar

/*##############################################################################
# NOMBRE: InsertarTipo														 #
# PROPOSITO: Inserta el tipo a una entrada									 #
# PARAMETROS DE ENTRADA: Numero:1											 #
# 		tent:Tipo a insertar ,Tipo:TipoEntrada								 #
# SALIDA: -																     #
# EXCEPCIONES: -										                     #
##############################################################################*/

void CEntrada::InsertarTipo(TipoEntrada tent)
{
tipoEntrada=tent;
 if (tipointroducido==0)
     this->tipointroducido=1;
};//InsertarTipo

/*##############################################################################
# NOMBRE: CompruebaTipoEntrada												 #
# PROPOSITO: Comprueba si un determinado tipo es el de la entrada			 #
# PARAMETROS DE ENTRADA: Numero:1											 #
# 		tent:Tipo a comprobar ,Tipo:TipoEntrada								 #
# SALIDA: resultado de la comprobacion									     #
# EXCEPCIONES: -										                     #
##############################################################################*/

int CEntrada::CompruebaTipoEntrada(TipoEntrada tent)
{
return tipoEntrada==tent;

};//InsertarTipo

/*##############################################################################
# NOMBRE: ImprimirEntrada													 #
# PROPOSITO: Imprimir el contenido de una entrada							 #
# PARAMETROS DE ENTRADA: Numero:1											 #
# 						-sal:Fichero en el que se imprimira, Tipo:ostream	 #
# SALIDA: -																     #
# EXCEPCIONES: -										                     #
##############################################################################*/


void CEntrada::ImprimirEntrada(ostream &sal)
{
CAtributo *act=atributos;//Ptr a la lista de atributos
CEntero *ent;//Ptr a un atributo de tipo entero
CCadena *cad;//Ptr a un atributo de tipo cadena
CPuntero *ptr;//Ptr a un atributo de tipo puntero
CListaParametros *lpar;//Puntero a un atributo de tipo listad de parametros

if (atributos==NULL)
    sal<<"NULL"<<endl;
else
	{
    while (act!=NULL)
		{
	    sal<<act->GetNombre();

		 if (strcmp(act->DarTipo(),"entero")==0)
		{
			 ent = (CEntero *) act;
             ent->ImprimeContenido(sal);
	    }//if
		else if (strcmp(act->DarTipo(),"parametro")==0)
		{
			lpar = (CListaParametros *) act;
		    lpar->ImprimeContenido(sal);
	    }//else if
		else if (strcmp(act->DarTipo(),"cadena")==0)
		{
			cad = (CCadena *) act;
			cad->ImprimeContenido(sal);
	     }//else if
		else if (strcmp(act->DarTipo(),"puntero")==0)
		{
			ptr = (CPuntero *) act;
	        ptr->ImprimeContenido(sal);
     	 }//else if
	    sal<<"->";
    	act=act->GetSig();
        }//while
sal<<"NULL"<<endl;
	}//else
};//Imprimir


/*##############################################################################
# NOMBRE: GetLexema														     #
# PROPOSITO: Devuelve el lexema de una determinada entrada					 #
# PARAMETROS DE ENTRADA:-													 #
# SALIDA:lexema											                     #
# EXCEPCIONES: -										                     #
##############################################################################*/
char * CEntrada::GetLexema() const
{
return lexema;
};

/*##############################################################################
# NOMBRE: GetTipo														     #
# PROPOSITO: Devuelve el tipo de una determinada entrada					 #
# PARAMETROS DE ENTRADA:-													 #
# SALIDA: Tipo de la entrada											     #
# EXCEPCIONES: -										                     #
##############################################################################*/

TipoEntrada CEntrada::GetTipo()
{
try
{
	if (tipointroducido==0)
  		throw "EXCEPCION EN ObtenerTipo:Dicha entrada no tiene introducido ningun tipo";
	return tipoEntrada;
}//try
catch(char *str)
{
	cout<<str<<endl;
    throw;
}//catch
};//Get Tipo

/*##############################################################################
# NOMBRE: CEntrada															 #
# PROPOSITO: Constructor de CEntrada										 #
# PARAMETROS DE ENTRADA:Numero:1                                             #
#                  lexema:Lexema que se introducira en la entrada,Tipo:Cadena#
# SALIDA: -																     #
# EXCEPCIONES: No hay memoria sufiente					                     #
##############################################################################*/

CEntrada::CEntrada(char *lexema)
{
try
{
	this->lexema= new char[strlen(lexema) + 1];
	strcpy(this->lexema, lexema);
	this->atributos=NULL;
    this->tipointroducido=0;
}//try
#if __GNUG__
    catch(...)
#else
	catch(const CMemoryException)
#endif
{
	cout<<"EXCEPCION:No hay memoria suficiente"<<endl;
    throw;
}//catch
};//CEntrada

/*##############################################################################
# NOMBRE: BuscarAtributo													 #
# PROPOSITO: Busca un atributo almacenado en una entrada					 #
# PARAMETROS DE ENTRADA:Numero:1                                             #
# 			nombre:Nombre del atrinuto a buscar,Tipo:Cadena					 #
# SALIDA:Puntero al atributo encontrado									     #
# EXCEPCIONES:No exista dicho atributo										 #
##############################################################################*/

CAtributo *CEntrada::BuscarAtributo(char *nombre)
{
CAtributo *atr=atributos;//Ptr que recorrera la lista de atributos
int encontrado = 0;//Entero que representa si hemos encontrado el atributo

try
{
if (atributos != NULL){

   while (atr && !encontrado)
   {   
       if (strcmpi (atr->GetNombre(), nombre)==0)
          encontrado = 1;
       else
           atr = atr->GetSig();
   }//while
   if (encontrado==0)
   		throw nombre;
}//if
else
  throw nombre;

return atr;
}//try
catch(char *str)
{

throw;
}//catch
};//BuscarAtributo

/*##############################################################################
# NOMBRE: InsertarValor														   #
# PROPOSITO: Da valor a un atributo de tipo entero de una entrada		       #
# PARAMETROS DE ENTRADA:Numero:2                                               #
# 			nombre:Nombre del atributo,Tipo:Cadena	      				       #
#           valor:Valor a almacenar, Tipo;Entero                               #
# EXCEPCIONES: El atributo no exista					                       #
##############################################################################*/

void CEntrada::InsertarValor(char *nombre,int valor)
{
CEntero *ent;//Ptr al atributo de tipo entero
CAtributo *atr;//Ptr al atributo

try
{
	atr=BuscarAtributo(nombre);
	ent = (CEntero *) atr;
	ent->InsertarValor(valor);
}//try
catch(char *str)
{
    cout<<"EXCEPCION EN InsertarValor: "<<"El atributo "<< str<<" no pertenece a esa entrada"<<endl;
	throw;
}//catch
};//InsertarValor

/*##############################################################################
# NOMBRE: InsertarValor														   #
# PROPOSITO: Da valor a un atributo de tipo palabrade una  entrada		       #
# PARAMETROS DE ENTRADA:Numero:2                                               #
# 			nombre:Nombre del atributo,Tipo:Cadena	      				       #
#           valor:Valor a almacenar, Tipo:Cadena                               #
# SALIDA: -											    					   #
# EXCEPCIONES: El atributo no exista					                       #
##############################################################################*/

void CEntrada::InsertarValor(char *nombre,palabra valor)
{

CCadena *cad;//Ptr al atributo de tipo cadena
CAtributo *atr;//Ptr al atributo

try
{
	atr=BuscarAtributo(nombre);
	cad = (CCadena *) atr;  
	cad->InsertarValor(valor);
}//try
catch(char *str)
{
    cout<<"EXCEPCION EN InsertarValor: "<<"El atributo "<< str<<" no pertenece a esa entrada"<<endl;
	throw;
}//catch
};//InsertarValor

/*##############################################################################
# NOMBRE: InsertarValor														   #
# PROPOSITO: Da valor a un atributo de tipo puntero de una entrada		       #
# PARAMETROS DE ENTRADA:Numero:2                                               #
# 			nombre:Nombre del atributo,Tipo:Cadena	      				       #
#           valor:Valor a almacenar, Tipo:Puntero                              #
# SALIDA: -																       #
# EXCEPCIONES: El atributo no exista					                       #
##############################################################################*/

void CEntrada::InsertarValor(char *nombre,void *valor)
{
CPuntero *ptr;//Ptr al atributo de tipo entero
CAtributo *atr;//Ptr al atributo

try
{
	atr=BuscarAtributo(nombre);
	ptr = (CPuntero *) atr;
	ptr->InsertarValor(valor);
}//try
catch(char *str)
{
cout<<"EXCEPCION EN InsertarValor: "<<"El atributo "<< str<<" no pertenece a esa entrada"<<endl;
throw;
}//catch
};//InsertarValor

/*##############################################################################
# NOMBRE: DevolverValor														   #
# PROPOSITO: Da el valor de un atributo de tipo entero                         #
# PARAMETROS DE ENTRADA:Numero:2                                               #
# 			nombre:Nombre del atributo,Tipo:Cadena	      				       #
#           valor:Valor a obtener, Tipo:Entero                                 #
# SALIDA:En el parametro de entrada valor se devolvera el valor pedido	       #
# EXCEPCIONES: El atributo no exista					                       #
#              El atributo no tenga valor								       #
##############################################################################*/

void CEntrada::DevolverValor(char *nombre,int &valor)
{
CAtributo *atr=atributos;//Ptr a la lista de atributos
CEntero *ent;//Ptr al atributo

try
{
	atr=BuscarAtributo(nombre);
	ent = (CEntero *) atr;
	valor=ent->GetValor();
}//try
catch(char *str)
{
    cout<<"EXCEPCION EN DevolverValor: "<<"El atributo "<< str<<" no pertenece a esa entrada"<<endl;
    throw;
}//catch
};//DevolverValor

/*##############################################################################
# NOMBRE: DevolverValor														   #
# PROPOSITO: Da el valor de un atributo de tipo palabra                        #
# PARAMETROS DE ENTRADA:Numero:2                                               #
# 			nombre:Nombre del atributo,Tipo:Cadena	      				       #
#           valor:Valor a obtener, Tipo:Cadena                                 #
# SALIDA:En el parametro de entrada valor se devolvera el valor pedido	       #
# EXCEPCIONES: El atributo no exista					                       #
#              El atributo no tenga valor									   #
##############################################################################*/

void CEntrada::DevolverValor(char *nombre,palabra &valor)
{
CAtributo *atr=atributos;//Ptr a la lista de atributos
CCadena *cadena;//Ptr al atributo

try
	{
	atr=BuscarAtributo(nombre);
	cadena = (CCadena *) atr;
	valor=cadena->GetValor();
	}//try
catch(char *str)
	{
    cout<<"EXCEPCION EN DevolverValor: "<<"El atributo "<< str<<" no pertenece a esa entrada"<<endl;
    throw;
	}//catch
};//DevolverValor


/*##############################################################################
# NOMBRE: DevolverValor														   #
# PROPOSITO: Da el valor de un atributo de tipo puntero                        #
# PARAMETROS DE ENTRADA:Numero:2                                               #
# 			nombre:Nombre del atributo,Tipo:Cadena	      				       #
#           valor:Valor a obtener, Tipo:Puntero                                #
# SALIDA:En el parametro de entrada valor se devolvera el valor pedido	       #
# EXCEPCIONES: El atributo no exista					                       #
#              El atributo no tenga valor									   #
##############################################################################*/


void CEntrada::DevolverValor(char *nombre,punt &valor)
{
CAtributo *atr=atributos;//Ptr a la lista de atributos
CPuntero *ptr;//Ptr al atributo

try
{
	atr=BuscarAtributo(nombre);
	ptr = (CPuntero *) atr;
	valor=ptr->GetValor();
}//try
catch(char *str)
{
   cout<<"EXCEPCION EN DevolverValor: "<<"El atributo "<< str<<" no pertenece a esa entrada"<<endl;
   throw;
}//catch
};//DevolverValor

/*##############################################################################
# NOMBRE: InsertarParametro													   #
# PROPOSITO: Inserta un atributo de tipo parametro		                       #
# PARAMETROS DE ENTRADA:Numero:2                                               #
# 			nombre:Nombre del atributo,Tipo:Cadena	      				       #
#           pparam:Valor del paso del parametro a insertar, Tipo:PasoParametro #
#           tipo:Tipo del parametro,Tipo:TipoParam							   #
# SALIDA:-																	   #
# EXCEPCIONES: El atributo lista de parametros no exista					   #
#              memoria insufiente										       #
##############################################################################*/


void CEntrada::InsertarParametro(char *nombre, PasoParametro pparam, TipoParam tipo)
{
CAtributo *atr;//Ptr al atributo en el que se insertara
CListaParametros *lpar; //Ptr a la lista de parametros donde se insertara
CParametro *parametro;//Nuevo Parametro creado

try
	{
	parametro=new CParametro(pparam,tipo);
    atr=BuscarAtributo(nombre);
    lpar = (CListaParametros *) atr;
	lpar->InsertarParametro(parametro);

	}//try


catch(char *str)
	{

    cout<<"EXCEPCION EN InsertarParametro: "<<"El atributo "<< str<<" no pertenece a esa entrada"<<endl;
	throw;
	}//catch
#if __GNUG__
    catch(...)
#else 
	catch(const CMemoryException)
#endif
{
	cout<<"EXCEPCION EN InsertatParametro:No hay memoria suficiente"<<endl;
    throw;
}//catch
};//InsertarParametro

/*##############################################################################
# NOMBRE: ObtenerPasoParam													 #
# PROPOSITO: Obtiene el tipo del paso del parametro		                     #
# PARAMETROS DE ENTRADA: Numero:2											 #
# 		  numeroparam:Entero que indica la posicion en la lista,Tipo:Entero  #
#          nombre:Nombre del atributo, Tipo:cadena                           #
# SALIDA:Tipo del paso del parametro pedido		  							 #
# EXCEPCIONES: El atributo lista de parametros no exista					 #
#              El numero del parametro sobrepase el numero de parametros int #
##############################################################################*/

PasoParametro CEntrada::ObtenerPasoParam(char *nombre, int numeroparam)
{
CAtributo *atr;//Ptr al Atributo buscado
CListaParametros *lpar;//Ptr a la lista de parametros de dicho atributo

try
	{
	atr=BuscarAtributo(nombre);
	lpar = (CListaParametros *) atr; 
	return lpar->ObtenerPasoParametro(numeroparam);
	}//try

catch(char *str)
	{
    cout<<"EXCEPCION EN ObtenerPasoParam: "<<"El atributo "<< str<<" no pertenece a esa entrada"<<endl;
	throw;
	}//catch
catch(int nparam)
	{
	throw;
	}//catch
};//ObtenerPasoParam

/*##############################################################################
# NOMBRE: ObtenerTipoParam													 #
# PROPOSITO: Obtiene el tipo del parametro		                             #
# PARAMETROS DE ENTRADA: Numero:2											 #
# 		  numeroparam:Entero que indica la posicion en la lista,Tipo:Entero  #
#          nombre:Nombre del atributo, Tipo:cadena                           #
# SALIDA:Tipo del parametro pedido		  							         #
# EXCEPCIONES: El atributo lista de parametros no exista					 #
#              El numero del parametro sobrepase el numero de parametros int #
##############################################################################*/

TipoParam CEntrada::ObtenerTipoParam(char *nombre, int numeroparam)
{
CAtributo *atr;//Ptr al Atributo buscado
CListaParametros *lpar;//Ptr a la lista de parametros de dicho atributo

try
	{
	atr=BuscarAtributo(nombre);
	lpar = (CListaParametros *) atr;
	return lpar->ObtenerTipoParametro(numeroparam);
	}//try

catch (char *str)
	{
    cout<<"EXCEPCION EN ObtenerTipoParam: "<<"El atributo "<< str<<" no pertenece a esa entrada"<<endl;
	throw;
	}//catch
catch(int nparam)
	{
	throw;
	}//catch
};//ObtenerTipoParam


//---------------------------------------------------------------------------
//#pragma package(smart_init)

