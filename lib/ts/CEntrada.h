//---------------------------------------------------------------------------
#include <string.h>
#ifndef centradaH
#define centradaH
#include <stdio.h>
#include <stdlib.h>
#include <iostream.h>
#include "CATRIBUTO.h"
//Enumerado que representa los diferentes posibles tipos que podra tomar un lexema
enum TipoEntrada {funcion=0, identificador, clase, procedimiento,
                  etiqueta, registro, palabraReserv, vector};
typedef char * palabra;
typedef void * punt;

/*####################################################################################
#####################    CLASE: CEntrada     #########################################
######################################################################################
#PROPOSITO: Clase que representa una entrada del arbol de simbolos                   #
#NUMERO DE METODOS: 19													        	 #
#ATRIBUTOS:                                                                          #
#  char *lexema:Lexema almacenado en la entrada										 #
#  TipoEntrada tipoEntrada:Tipo el lexema almacenado								 #
#  CAtributo *atributos:Atributos del lexema almacenado								 #
#  int tipointroducido: Indica si una entrada ya tiene introducido su tipo           #
####################################################################################*/

class CEntrada {
public:

/*##############################################################################
#####################    PUBLIC              #################################
##############################################################################*/
   EliminarEntrada();
   CEntrada(char *lexema);
   char *GetLexema() const;
   TipoEntrada GetTipo();
   void ImprimirEntrada(ostream &sal);
   void Copiar(CAtributo *lista);
   void InsertarTipo(TipoEntrada tent);
   int CompruebaTipoEntrada(TipoEntrada tent);
   void InsertarValor(char *nombre,int valor);
   void InsertarValor(char *nombre,void *valor);
   void InsertarValor(char *nombre,char *valor);
   void DevolverValor(char *nombre,int &valor);
   void DevolverValor(char *nombre,palabra &valor);
   void DevolverValor(char *nombre,punt &valor);
   void InsertarParametro(char *nombre, PasoParametro pparam, TipoParam tipo);
   PasoParametro ObtenerPasoParam(char *nombre, int numeroparam);
   TipoParam ObtenerTipoParam(char *nombre, int numeroparam);
private:
/*##############################################################################
#####################    PRIVATE             #################################
##############################################################################*/
   char *lexema;
   TipoEntrada tipoEntrada;
   CAtributo *atributos;
   int tipointroducido;


   CAtributo *BuscarAtributo(char *nombre);
   CAtributo *Copia(CAtributo *lista);

}; //end class

//---------------------------------------------------------------------------
#endif
