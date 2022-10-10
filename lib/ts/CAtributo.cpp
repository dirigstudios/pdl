//---------------------------------------------------------------------------
#include <iostream.h>
#include <stdlib.h>
#include <string.h>
#include "CATRIBUTO.h"
#include <typeinfo.h>
#include <stdio.h>


/****************************************************************************/
/********************  ATRIBUTO   *******************************************/
/****************************************************************************/
/*##############################################################################
#####################    METODOS PUBLICOS      #################################
##############################################################################*/
/*##############################################################################
# NOMBRE: CAtributo															 #
# PROPOSITO:Constructor de CAtributo										 #
# PARAMETROS DE ENTRADA: Numero:1											 #
# 			nombre:nombre del atributo a crear , Tipo:cadena  				 #
# SALIDA:-																	 #
# EXCEPCIONES:Memoria insuficiente  										 #
##############################################################################*/
CAtributo::CAtributo(char *nombre)
{
try
{
 	this->nombre= new char[strlen(nombre) + 1];
 	strcpy(this->nombre, nombre);
	this->siguiente = NULL;
}//try
#if __GNUG__
    catch(...)
#else
	catch(const CMemoryException)
#endif
{   throw;
	cout<<"EXCEPCION:No hay memoria suficiente"<<endl;
}//catch
};//CAtributo

/*##############################################################################
# NOMBRE: Eliminar															 #
# PROPOSITO:Eliminar un CAtributo liberando los recursos asignados	 		 #
# PARAMETROS DE ENTRADA:-					   								 #
# SALIDA:-																	 #
# EXCEPCIONES:-																 #
##############################################################################*/

/*##############################################################################
# NOMBRE: SetSig															 #
# PROPOSITO:Inserta un nuevo elemento										 #
# PARAMETROS DE ENTRADA: Numero:1											 #
# 				atr:atributo a insertar,Tipo:CAtributo						 #
# SALIDA:-																	 #
# EXCEPCIONES:-																 #
##############################################################################*/

/*##############################################################################
# NOMBRE: GetSig															 #
# PROPOSITO:Devuelve el siguiente elemento                                   #
# PARAMETROS DE ENTRADA:-  													 #
# SALIDA:Siguiente atributo													 #
# EXCEPCIONES:-																 #
##############################################################################*/

/*##############################################################################
# NOMBRE: GetNombre															 #
# PROPOSITO:Devuelve el valor del atributo nombre							 #
# PARAMETROS DE ENTRADA:-													 #
# SALIDA:cadena de caracteres almecenada en el atrubuto nombre				 #
# EXCEPCIONES:-																 #
##############################################################################*/

/****************************************************************************/
/********************  CENTERO    *******************************************/
/****************************************************************************/

/*##############################################################################
#####################    METODOS PUBLICOS      #################################
##############################################################################*/

/*##############################################################################
# NOMBRE: InsertarValor														 #
# PROPOSITO:DarValor al atributo valor de la clase CEntero                   #
# PARAMETROS DE ENTRADA: Numero:1											 #
# 						valor:Valor entero a almacenar	,Tipo:Entero		 #
# SALIDA:-																	 #
# EXCEPCIONES:-																 #
##############################################################################*/

void CEntero::InsertarValor(int valor)
{
 this->valor=valor;
 if (ocupado==0)
     this->ocupado=1;
};//InsertarValor


/*##############################################################################
# NOMBRE: GetValor															 #
# PROPOSITO:Dar el valor del atributo valor de la clase CEntero              #
# PARAMETROS DE ENTRADA:-													 #
# SALIDA:Valor que habia almacenado en el atributo valor					 #
# EXCEPCIONES:El atributo no tenia valor alguno								 #
##############################################################################*/

int CEntero::GetValor()
{
try
{
if (ocupado==0)
  throw "!EXCEPCION EN DevolverValor: Este atributo no tiene valor alguno";
else
	return valor;
}//try
catch(char *str)
{
	cout<<str<<endl;
    throw;
}//catch
};//GetValor

/*##############################################################################
# NOMBRE: ImprimeContenido											 		 #
# PROPOSITO:Imprime el contenido de una instancia del objecto CEntero        #
# PARAMETROS DE ENTRADA: Numero:1											 #
#                     sal:Fichero en el que se imprimira ,Tipo:ostream		 #
# SALIDA:-																	 #
# EXCEPCIONES:-																 #
##############################################################################*/
void CEntero::ImprimeContenido(ostream &sal)
{
if (ocupado==0)
     sal<<"( )";
else
	sal<<"("<<valor<<")";
};//ImprimeContenido

/*inline char *CEntero::DarTipo()
{
	return "entero";
}

/****************************************************************************/
/********************  CCADENA    *******************************************/
/****************************************************************************/

/*##############################################################################
#####################    METODOS PUBLICOS      #################################
##############################################################################*/
/*##############################################################################
# NOMBRE: InsertarValor														 #
# PROPOSITO:DarValor al atributo valor de la clase CCadena                   #
# PARAMETROS DE ENTRADA: Numero:1											 #
#             valor:Valor cadena de caracteres a almacenar,Tipo:Cadena       #
# SALIDA:-																	 #
# EXCEPCIONES:No hay memoria suficiente 									 #
##############################################################################*/
void CCadena::InsertarValor(char *valor)
{
try
  {
	this->valor= new char[strlen(valor) + 1];
      strcpy(this->valor, valor);
  }//try
#if __GNUG__
    catch(...)
#else
	catch(const CMemoryException)
#endif
{
   cout<<"EXCEPCION:No hay memoria sufiente"<<endl;
   throw;
}//catch

}; //InsertarValor
/*##############################################################################
# NOMBRE: GetValor															 #
# PROPOSITO:Dar el valor del atributo valor de la clase CCadena              #
# PARAMETROS DE ENTRADA:-													 #
# SALIDA:Valor que habia almacenado en el atributo valor					 #
# EXCEPCIONES:El atributo no tenia valor alguno								 #
##############################################################################*/
char *CCadena::GetValor()
{
try
{
	if (valor==NULL)
		  throw "!EXCEPCION EN DevolverValor: Este atributo no tiene valor alguno";
    else
		return valor;
}//try
catch(char *str)
{
	cout<<str<<endl;
    throw;
}//catch
};//GetValor

/*##############################################################################
# NOMBRE: ImprimeContenido											 		 #
# PROPOSITO:Imprime el contenido de una instancia del objecto CCadena        #
# PARAMETROS DE ENTRADA: Numero:1											 #
# 						-sal:Fichero en el que se imprimira, Tipo:ostream	 #
# SALIDA:-																	 #
# EXCEPCIONES:-																 #
##############################################################################*/

/****************************************************************************/
/********************  CPARAMETRO  ******************************************/
/****************************************************************************/

/*##############################################################################
#####################    METODOS PUBLICOS      #################################
##############################################################################*/
/*##############################################################################
# NOMBRE: CParametro				 										 #
# PROPOSITO:Constructor de CParametro										 #
# PARAMETROS DE ENTRADA: Numero:2											 #
#         pparam:Paso de parametro , Tipo:PasoParametro					     #
#         tipo:Tipo	del parametro,Tipo:TipoParam  						     #
# SALIDA:-																	 #
# EXCEPCIONES:-																 #
##############################################################################*/
CParametro::CParametro(PasoParametro pparam, TipoParam tipo)
{
this->pparam= pparam;
this->tipo= tipo;
this->siguiente = NULL;
};//CParametro

/*##############################################################################
# NOMBRE: SetSig															 #
# PROPOSITO:Inserta un nuevo elemento										 #
# PARAMETROS DE ENTRADA: Numero:1											 #
# 	            param:parametro a insertar, Tipo:CParametro  				 #
# SALIDA:-																	 #
# EXCEPCIONES:-																 #
##############################################################################*/

/*##############################################################################
# NOMBRE: ImprimeContenido											 		 #
# PROPOSITO:Imprime el contenido de una instancia del objecto CParametro     #
# PARAMETROS DE ENTRADA: Numero:1											 #
# 						-sal:Fichero en el que se imprimira, Tipo:ostream	 #
# SALIDA:-																	 #
# EXCEPCIONES:-																 #
##############################################################################*/
void CParametro::ImprimeContenido(ostream &sal)
{

switch(tipo)
	{
	case 0: sal<<"Entero";
    		break;
    case 1: sal<<"Cadena";
    		break;
    case 2: sal<<"Real";
    		break;
    case 3: sal<<"Vector";
    		break;
    case 4: sal<<"Clase";
    		break;
    case 5: sal<<"Registro";
    		break;
    case 6: sal<<"Puntero";
    		break;
    }//switch

sal<<"|";
if (pparam==0)
    sal<<"valor";
else
    sal<<"Referencia";

};//ImprimeContenido

/*##############################################################################
# NOMBRE: GetSig															 #
# PROPOSITO:Devuelve el siguiente elemento                                   #
# PARAMETROS DE ENTRADA:-  													 #
# SALIDA:Siguiente parametro												 #
# EXCEPCIONES:-																 #
##############################################################################*/

/*##############################################################################
# NOMBRE: GetPasoParametro													 #
# PROPOSITO:Devuelve el valor del tipo de paso del parametro                 #
# PARAMETROS DE ENTRADA:-  													 #
# SALIDA:Paso del parametro													 #
# EXCEPCIONES:-																 #
##############################################################################*/

/*inline PasoParametro CParametro::GetPasoParametro()
{
	return pparam;
};//GetPasoParametro

/*##############################################################################
# NOMBRE: GetTipo       													 #
# PROPOSITO:Devuelve el valor del tipo del parametro                         #
# PARAMETROS DE ENTRADA:-  													 #
# SALIDA:Tipo del parametro													 #
# EXCEPCIONES:-																 #
##############################################################################*/

/****************************************************************************/
/********************  CLISTAPARAMETROS**************************************/
/****************************************************************************/
/*##############################################################################
#####################    METODOS PRIVADOS      #################################
##############################################################################*/
/*##############################################################################
# NOMBRE: BuscarParametro 													 #
# PROPOSITO:Busca un parametro dada su posicion el la lista                  #
# PARAMETROS DE ENTRADA: Numero:1											 #
# 		  posicionParam:Entero que indica la posicion en la lista,Tipo:Entero#
# SALIDA:Parametro buscado													 #
# EXCEPCIONES:La posicion indicada sobrepasa el numero de parametros         #
#			  almacenados												     #
##############################################################################*/

CParametro *CListaParametros::BuscarParametro(int posicionParam)
{
CParametro *act=listaParam;//Ptr que recorrera la lista de parametros
int numeroparametro=posicionParam;

try
{
while ((posicionParam!=1)&&(act!=NULL))
		{act = act->GetSig();
         posicionParam=posicionParam-1;
         }//while
if (act==NULL)
  throw numeroparametro;
else
  return act;
}//try
catch(int num)
{
throw;
}//catch


};//BuscarParametro

/*##############################################################################
#####################    METODOS PUBLICOS      #################################
##############################################################################*/

/*##############################################################################
# NOMBRE: InsertarParametro													 #
# PROPOSITO:Insertar un nuevo parametro a la lista de parametros             #
# PARAMETROS DE ENTRADA: Numero:1											 #
# 						parametro: parametro a almacenar ,Tipo:CParametro	 #
# SALIDA:-																	 #
# EXCEPCIONES:-																 #
##############################################################################*/
void CListaParametros::InsertarParametro(CParametro *parametro)
 {
 CParametro *act=listaParam; //Ptr que recorrera la lista de parametros
 CParametro *ant; //ptr a un parametro
 ant=act;

 if (listaParam==NULL)
   {
     listaParam=parametro;
     listaParam->SetSig(NULL);

   }
 else
   {
	while (act!=NULL)
			{ant=act;
             act=act->GetSig();
            }//while


    ant->SetSig(parametro);
    ant=ant->GetSig();
    ant->SetSig(NULL);
    }//else
};//InsertarParametro

/*##############################################################################
# NOMBRE: ImprimeContenido											 		 #
# PROPOSITO:Imprime el contenido de  CListaParametros					     #
# PARAMETROS DE ENTRADA: Numero:1											 #
# 						-sal:Fichero en el que se imprimira, Tipo:ostream	 #
# SALIDA:-																	 #
# EXCEPCIONES:-																 #
##############################################################################*/
void CListaParametros::ImprimeContenido(ostream &sal)
{
CParametro *act=listaParam; //Ptr que recorrera la lista de parametros

if (listaParam==NULL)
     sal<<"( NULL )";
 else
   {
    sal<<"(";
	while (act!=NULL)
	{act->ImprimeContenido(sal);

	 act=act->GetSig();
     sal<<"->";
	}//while
  sal<<"NULL)";
}//else

};//ImprimeContenido

/*##############################################################################
# NOMBRE: ObtenerPasoParametro										 		 #
# PROPOSITO:Obtiene el paso del parametro          						     #
# PARAMETROS DE ENTRADA: Numero:1											 #
# 		  posicionParam:Entero que indica la posicion en la lista,Tipo:Entero#
# SALIDA:Paso del parametro													 #
# EXCEPCIONES:La posicion indicada sobrepasa el nª de parametros alamcenados #
##############################################################################*/
PasoParametro CListaParametros::ObtenerPasoParametro(int posicionParam)
{
CParametro *param;//Ptr que recorrera la lista de parametros

try
{
param=BuscarParametro(posicionParam);
return param->GetPasoParametro();
}//try
catch(int nparam)
{
throw;
}//catch
};//Obtenerpasoparametro

/*##############################################################################
# NOMBRE: ObtenerTipoParametro										 		 #
# PROPOSITO:Obtiene el tipo del parametro          						     #
# PARAMETROS DE ENTRADA: Numero:1											 #
# 		  posicionParam:Entero que indica la posicion en la lista,Tipo:Entero#
# SALIDA:Tipo del parametro													 #
# EXCEPCIONES:La posicion indicada sobrepasa el nª de parametros alamcenados #
##############################################################################*/
TipoParam CListaParametros::ObtenerTipoParametro(int posicionParam)
{
CParametro *param;//Ptr que recorrera la lista de parametros

try
{
param=BuscarParametro(posicionParam);
return param->GetTipo();
}//try
catch(int nparam)
{
throw;
}//catch
};//ObtenerTipoParametro


/****************************************************************************/
/********************  CPuntero    *******************************************/
/****************************************************************************/

/*##############################################################################
#####################    METODOS PUBLICOS      #################################
##############################################################################*/

/*##############################################################################
# NOMBRE: InsertarValor														 #
# PROPOSITO:DarValor al atributo valor de la clase CPuntero                  #
# PARAMETROS DE ENTRADA: Numero:1											 #
# 	                val: Valor puntero a almacenar ,Tipo:Puntero			 #
# SALIDA:-																	 #
# EXCEPCIONES:-																 #
##############################################################################*/

/*##############################################################################
# NOMBRE: GetValor															 #
# PROPOSITO:Dar el valor del atributo valor de la clase CCadena              #
# PARAMETROS DE ENTRADA:-													 #
# SALIDA:Valor que habia almacenado en el atributo valor					 #
# EXCEPCIONES:El atributo no tenia valor alguno								 #
##############################################################################*/
void *CPuntero::GetValor()
{
try
{
	if (valor==NULL)
		  throw "EXCEPCION EN DevolverValor: Este atributo no tiene valor alguno";
    else
		return valor;
}//try
catch(char *str)
{
	cout<<str<<endl;
    throw;
}//catch
};//GetValor

/*##############################################################################
# NOMBRE: ImprimeContenido											 		 #
# PROPOSITO:Imprime el contenido de una instancia del objecto CPuntero       #
# PARAMETROS DE ENTRADA: Numero:1											 #
# 						-sal:Fichero en el que se imprimira, Tipo:ostream	 #
# SALIDA:-																	 #
# EXCEPCIONES:-																 #
##############################################################################*/

//---------------------------------------------------------------------------
//#pragma package(smart_init)

