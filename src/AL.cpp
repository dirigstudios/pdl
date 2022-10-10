#include "../lib/ts/CArbolSimbolos.h"

// 
void inicializarTabla(CArbolSimbolo tabla)
{
	tabla.InsertarSimbolo("suma"); //TODO: ver como meter tokens dentro
}

int main()
{
	CArbolSimbolo ts;

	ts.CrearTabla();
	//TODO: leer del fichero

	return (0);
}
