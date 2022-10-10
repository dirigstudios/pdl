//---------------------------------------------------------------------------
/*#if !defined( _CARBOLSIMBOLOS_H )
#define _CARBOLSIMBOLOS_H*/
#if !defined (_CATRIBUTO_H)
#define _CATRIBUTO_H
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <iostream.h>
#if __GNUG__
#include <typeinfo.h>
#else
    ;
#endif
#if __GNUG__
	;
#elif  __BORLANDC__
	;
#else
#include <afx.h>
#endif


//Enumerado que representa los posibles valores del paso de un parametro
enum PasoParametro {valor=0, referencia};
//Enumerado que representa los posibles tipos de un parametro
enum TipoParam {ent, cad, real, vec, clas, reg, ptr};
//Enumerado que representa los posibles tipos de un Atributo
enum Tipo {entero=0, cadena, puntero, listaParametros,/*lydia*/arbolsimbolos};

/*###################################################################################
#####################    CAtributo                   #################################
######################################################################################
#PROPOSITO: Clase que representa un atributo de un lexema                            #
#NUMERO DE METODOS: 6  												        	     #
#ATRIBUTOS:                                                                          #
#    char *nombre:Nombre del atributo almacenado									 #
#    CAtributo *siguiente:Ptr al siguiente atributo									 #
####################################################################################*/
class CAtributo
{
/*##############################################################################
#####################    PUBLIC                   ############################
##############################################################################*/
public:
     char *GetNombre() {return nombre;}
     CAtributo(char *nombre);
     void EliminarAtributo() {delete nombre;}
     void SetSig(CAtributo *atr) {siguiente=atr;}
     CAtributo *GetSig() {return siguiente;}
     virtual void ImprimeContenido(ostream &sal)=0;
	 virtual char *DarTipo() = 0;
/*##############################################################################
#####################    PROTECTED                  ############################
##############################################################################*/
protected:
    char *nombre;
    CAtributo *siguiente;

};

/*###################################################################################
#####################    CCadena                   #################################
######################################################################################
#PROPOSITO: Clase que representa un atributo de tipo Cadena                          #
#NUMERO DE METODOS: 4													        	 #
#ATRIBUTOS:                                                                          #
#   char *valor:Valor del atributo de tipo cadena 									 #
####################################################################################*/
class CCadena:public CAtributo
{
/*##############################################################################
#####################    PRIVATE                  ############################
##############################################################################*/
private:
	char *valor;

/*##############################################################################
#####################    PUBLIC                   ############################
##############################################################################*/
public:
    CCadena(char *nombre):CAtributo(nombre){valor=NULL;}
    void InsertarValor(char *valor);
    void ImprimeContenido(ostream &sal){printf("<<%s<<",sal);}
    char *GetValor();
	char *DarTipo(){return "cadena";}
};//CCadena

/*###################################################################################
#####################    CEntero                     #################################
######################################################################################
#PROPOSITO: Clase que representa un atributo de tipo Entero                          #
#NUMERO DE METODOS: 4													        	 #
#ATRIBUTOS:                                                                          #
#  int valor  :Valor del atributo de tipo entero 									 #
#  int ocupado:Representa si dicho atributo tiene valor								 #
####################################################################################*/

class CEntero:public CAtributo
{
/*##############################################################################
#####################    PRIVATE                  ############################
##############################################################################*/
private:
	int valor;
    int ocupado;
/*##############################################################################
#####################    PUBLIC                   ############################
##############################################################################*/
public:
    CEntero(char *nombre):CAtributo(nombre){valor=(-1);}
    void InsertarValor(int valor);
    int GetValor();
    void ImprimeContenido(ostream &sal);
	char *DarTipo() {return "entero";}
};//CEntero

/*###################################################################################
#####################    CPuntero                     #################################
######################################################################################
#PROPOSITO: Clase que representa un atributo de tipo Puntero                         #
#NUMERO DE METODOS: 4													        	 #
#ATRIBUTOS:                                                                          #
#  void * valor  :Valor del atributo de tipo puntero   							     #
####################################################################################*/

class CPuntero:public CAtributo
{
/*##############################################################################
#####################    PRIVATE                  ############################
##############################################################################*/
private:
	void *valor;
/*##############################################################################
#####################    PUBLIC                   ############################
##############################################################################*/
public:
    CPuntero(char *nombre):CAtributo(nombre){valor=NULL;}
    void InsertarValor(void *val){this->valor= val;}
    void *GetValor();
    void ImprimeContenido(ostream &sal) {sal<<"("<<valor<<")";}
	char *DarTipo() {return "puntero";}
};//CPuntero

/*###################################################################################
#####################    CParametro                  #################################
######################################################################################
#PROPOSITO: Clase que representa un atributo de tipo Parametro                       #
#NUMERO DE METODOS: 6													        	 #
#ATRIBUTOS:                                                                          #
#   PasoParametro pparam:Paso del parametro									         #
#   TipoParam tipo:Tipo del parametro                                                #
#   CParametro *siguiente:Ptr al siguiente parametro							     #
####################################################################################*/

class CParametro
{
/*##############################################################################
#####################    PRIVATE                  ############################
##############################################################################*/
private:
	PasoParametro pparam;
    TipoParam tipo;
    CParametro *siguiente;
/*##############################################################################
#####################    PUBLIC                   ############################
##############################################################################*/
public:
    CParametro(PasoParametro pparam, TipoParam tipo);
     void SetSig(CParametro *pparam) {siguiente=pparam;}
     CParametro *GetSig() {return siguiente;}
     PasoParametro GetPasoParametro(){return pparam;}
	 TipoParam GetTipo() {return tipo;}
    void ImprimeContenido(ostream &sal);
};//CParametro

/*###################################################################################
#####################    CListaParametro             #################################
######################################################################################
#PROPOSITO: Clase que representa un atributo de tipo ListaParametro                  #
#NUMERO DE METODOS: 5													        	 #
#ATRIBUTOS:                                                                          #
#	CParametro *listaParam:Lista de parametros                                       #
####################################################################################*/
class CListaParametros:public CAtributo
{
/*##############################################################################
#####################    PRIVATE                  ############################
##############################################################################*/
private:
	CParametro *listaParam;

    CParametro *BuscarParametro(int posicionParam);
/*##############################################################################
#####################    PUBLIC                   ############################
##############################################################################*/
public:
    CListaParametros(char *nombre):CAtributo(nombre){listaParam=NULL;}
    void InsertarParametro(CParametro *parametro);
    PasoParametro ObtenerPasoParametro(int posicionParam);
    TipoParam ObtenerTipoParametro(int posicionParam);
    void ImprimeContenido(ostream &sal);
	char *DarTipo() {return "parametro";}
};

/*class CAtrArbolSimbolos: public CAtributo
{
/*##############################################################################
#####################    PRIVATE                  ############################
##############################################################################*/

/*##############################################################################
#####################    PUBLIC                   ############################
##############################################################################*/
/*public:
	CAtrArbolSimbolos(char *nombre);		
/*##############################################################################
#####################    PROTECTED                  ############################
##############################################################################*/
/*protected:
	//CArbolSimbolos *arbol;
};*/
//---------------------------------------------------------------------------
#endif
