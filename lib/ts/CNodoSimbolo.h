//---------------------------------------------------------------------------
#if !defined( _CNODOSIMBOLO_H )
#define _CNODOSIMBOLO_H
#include <stdio.h>
#include "cENTRADA.h"


//---------------------------------------------------------------------------
/*##############################################################################
#####################    CNodoSimbolo          #################################
######################################################################################
#PROPOSITO: Clase que representa un nodo del arbol de simbolos                       #
#NUMERO DE METODOS: 23													        	 #
#ATRIBUTOS:                                                                          #
#   CEntrada *entrada:Entrada almacenada en el nodo                                  #
#   CNodoSimbolo *hi:Ptr al hijo izquierdo del nodo									 #
#   CNodoSimbolo *hd:Ptr al hijo derecho  del nodo									 #
####################################################################################*/

class CNodoSimbolo {
public:
/*##############################################################################
#####################    PUBLIC                #################################
##############################################################################*/
     CNodoSimbolo(CEntrada *entrada);
     void EliminarNodo();
     CNodoSimbolo *GetHd() {return hd;}
     CNodoSimbolo *GetHi() {return hi;}
     void Copiar(CAtributo *lista){entrada->Copiar(lista);}
     bool EsVacioHi(){return (hi==NULL);}
     bool EsVacioHd(){return (hd==NULL);}
     void InsertarTipo(TipoEntrada tent){entrada->InsertarTipo(tent);}
     TipoEntrada DevolverTipo();
     int CompruebaTipo(TipoEntrada tent){return entrada->CompruebaTipoEntrada(tent);}
     void InsertarValor(char *nombre,int valor);
     void InsertarValor(char *nombre,void *valor);
     void InsertarValor(char *nombre,char *valor);
     void DevolverValor(char *nombre,int &valor);
     void DevolverValor(char *nombre,palabra &valor);
     void DevolverValor(char *nombre,punt &valor);
     void InsertarParam(char *nombre, PasoParametro pparam, TipoParam tipo);
     PasoParametro ObtenerPasoParam(char *nombre, int numeroparam);
     TipoParam ObtenerTipoParam(char *nombre, int numeroparam);
     void SetHi(CNodoSimbolo *nodo){  hi=nodo;}
     void SetHd(CNodoSimbolo *nodo) { hd=nodo;}
     void ImprimirNodo(ostream &sal);
     int Compara(char *lexema){return strcmp(lexema,entrada->GetLexema());}
private:
/*##############################################################################
#####################    PRIVATE             #################################
##############################################################################*/
    CEntrada *entrada;
    CNodoSimbolo *hi;
    CNodoSimbolo *hd;


};

//---------------------------------------------------------------------------
#endif
