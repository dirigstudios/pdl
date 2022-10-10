//---------------------------------------------------------------------------
#if !defined( _CARBOLSIMBOLOS_H )
#define _CARBOLSIMBOLOS_H
#include <stdio.h>
#include "CNodoSimbolo.h"

typedef char * palabra;
/*###################################################################################
#####################    CLASE: CArbolSimbolos   ####################################
#####################################################################################
#PROPOSITO: Clase que representa un arbol de simbolos                                #
#NUMERO DE METODOS: 28													        	 #
#ATRIBUTOS:                                                                          #
#	CNodoSimbolo *arbol: Arbol de simbolos									         #
#	CAtributo *funcion:Lista de atributos de una entrada de tipo funcion             #
#	CAtributo *identificador:Lista de atributos de una entrada de tipo identificador #
#	CAtributo *clase:Lista de atributos de una entrada de tipo clase                 #
#	CAtributo *procedimiento:Lista de atributos de una entrada de tipo procedimiento #
#	CAtributo *etiqueta:Lista de atributos de una entrada de tipo etiqueta           #
#	CAtributo *registro:Lista de atributos de una entrada de tipo registro           #
#	CAtributo *palabraReserv:Lista de atributos de una entrada de tipo palabraReserv #
####################################################################################*/


class CArbolSimbolos {
/*##############################################################################
#####################    PUBLIC                   ############################
##############################################################################*/

public:
	void CrearTabla();
	void EliminarTabla();
	void IntroducirPalabraReservada(char *palabraReservada);
	int EsPalabraReservada(char *nombre);
	CNodoSimbolo *InsertarSimbolo(char *lexema);
	void CrearEntrada(TipoEntrada tent,char *nombre, Tipo tipo);
	CNodoSimbolo *BuscarSimbolo(char *lexema);
	void InsertarTipo(CNodoSimbolo *simb,TipoEntrada tent);
	TipoEntrada ObtenerTipo(CNodoSimbolo *simb);
	void InsertarValor(CNodoSimbolo *simb,char *nombre, int valor);
	void InsertarValor(CNodoSimbolo *simb,char *nombre, char *valor);
	void InsertarValor(CNodoSimbolo *simb,char *nombre, void *valor);
	void DevolverValor(CNodoSimbolo *simb,char *nombre,int &valor);
	void DevolverValor(CNodoSimbolo *simb,char *nombre,punt &valor);
	void DevolverValor(CNodoSimbolo *simb,char *nombre,palabra &valor);
	void InsertarParametro(CNodoSimbolo *simb, char *nombre, PasoParametro pparam, TipoParam tipo);
	TipoParam ObtenerTipoParametro(CNodoSimbolo *simb,char *nombre,int numeroparam);
	PasoParametro ObtenerPasoParametro(CNodoSimbolo *simb,char *nombre,int numeroparam);
	void ImprimirTabla(char *salida);


private:
/*##############################################################################
#####################    PRIVATE                  ############################
##############################################################################*/
	CNodoSimbolo *arbol;
	CAtributo *estructurafuncion;
	CAtributo *estructuraidentificador;
	CAtributo *estructuraclase;
	CAtributo *estructuraprocedimiento;
	CAtributo *estructuraetiqueta;
	CAtributo *estructuraregistro;
    CAtributo *estructuravector;
	CAtributo *estructurapalabraReserv;

	void Imprimir(CNodoSimbolo *arbol,ostream &sal);
    void Eliminar(CNodoSimbolo *arbol);
	void InsertarAtributoFuncion(CAtributo *atr);
	void InsertarAtributoIdentificador(CAtributo *atr);
	void InsertarAtributoClase(CAtributo *atr);
    void InsertarAtributoVector(CAtributo *atr);
	void InsertarAtributoProcedimiento(CAtributo *atr);
	void InsertarAtributoEtiqueta(CAtributo *atr);
	void InsertarAtributoRegistro(CAtributo *atr);
	void InsertarAtributoPalabraReserv(CAtributo *atr);


};
//---------------------------------------------------------------------------
#endif
