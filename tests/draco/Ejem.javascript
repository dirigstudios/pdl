/* Programa de ejemplo  */
/******* Jos� Luis Fuertes, julio, 2022 *********/
/* El ejemplo incorpora elementos del lenguaje opcionales y elementos que no todos los grupos tienen que implementar */

let s string;	/* variable global cadena */

function FactorialRecursivo int (int n)	/* n: par�metro formal de la funci�n entera */
{
	if (n == 0)	return 1;
	return n + FactorialRecursivo (n + 1);	/* llamada recursiva */
}

let uno int = 1;	// la inicializaci�n es de implementaci�n opcional
let UNO int= uno;

function salto string ()
{
	return "\n";
}

function FactorialDo int (int n)
{
	let factorial int = 0 + uno * 1;	// variable local inicializada a uno
	do
	{
		factorial *= n--;	// equivale a: factorial = factorial * n; n = n - 1;
	} while (n != 0);		// mientras n no sea 0
	return factorial;		// devuelve el valor entero de la variable factorial
}

function FactorialWhile int ()
{
	let factorial int = 1;	// variables locales: factorial inicializada a 1
	let i	int;			// variables locales: i inicializada a 0 por omisi�n
	while (i < num)			// num es variable global entera sin declarar
	{
		factorial *= ++i;	// equivale a: i = i + 1; factorial = factorial * i;
	}
	return factorial;
}

function FactorialFor int (int n)
{
	let i int;
	let factorial int = UNO;	/* declaraci�n de variables locales */
	for (i = 1; i <= n; i++)
	{
		factorial *= i;
	}
	return factorial;
}

let For int;
let Do int;
let While int;	// tres variables globales

function imprime (string s, string msg, int f)	/* funci�n que recibe 3 argumentos */
{
	print s;print msg ;print (f);
	print salto();	// imprime un salto de l�nea */
	return;	/* finaliza la ejecuci�n de la funci�n (en este caso, se podr�a omitir) */
}

function cadena string (boolean log)
{
	if (!log) {return s;}
	else      {return"Fin";}
}	// fin cadena: funci�n que devuelve una cadena

// Parte del programa principal:
s = "El factorial ";	// Primera sentencia que se ejecutar�a

print s;
print
 "\nIntroduce un 'n�mero'.";
input 
 num;	/* se lee un n�mero del teclado y se guarda en la variable global num */

switch (num)
{
	case 1:
	case 0: print "El factorial de "; print num; print" siempre es 1.\n"; break;
	default:
		if (num < 0)
		{
			print ('No existe el factorial de un negativo.\n');
		}
		else
		{
			For = FactorialFor (num);
			While = FactorialWhile ();
			Do = FactorialDo (num);
			imprime (cadena (false), 
					"recursivo es: ", 
					FactorialRecursivo (num));
			imprime (s, 
					"con do-while es: ", 
					Do);
			imprime (s, 
					"con while es: ", 
					While);
			imprime (cadena (false), 
					"con for es: ", 
					For);
		}
}

function bisiesto boolean (int a)	
{			
	return 
		(a % 4 == 0 && a % 100 != 0 || a % 400 == 0);	//se tienen en cuenta la precedencia de operadores
} // fin de bisiesto: funci�n l�gica

function dias int (int m, int a)
{
	switch (m)
	{
		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			return 31; break;
		case 4: case 6: case 9: case 11:
			return 30;
		case 2: if (bisiesto (a))  return(29); 
			return(28);
		default: print "Error: mes incorrecto: "; print m; print salto(); return 0;
	}
} // fin de dias. Todos los return devuelven un entero y la funci�n es entera

function esFechaCorrecta boolean (int d, int m, int a)	
{
	return m>=1&&m<=12&&d>=1&&d<=dias(m,a);
} //fin de esFechaCorrecta

function imprimeSuma (int v, int w)	
{
	print v + w;
	print (salto());
} //fin de imprimeSuma

function potencia (int z, int dim)	
{
	let s int;	// Oculta a la global
	for (s=0; s < dim; s++)
	{
		z *= z;
		imprime ("Potencia:", " ", z);
	}
} // fin de potencia: funci�n que no devuelve nada

function demo ()	/* definici�n de la funci�n demo, sin argumentos y que no devuelve nada */
{
	let v1 int; let v2 int; let v3 int;
	let zv int ; // Variables locales

	print'Escriba "tres" n�meros: ';
	input v1; input v2; input v3;
	
	if (v3 == 0) return;
	
	if (!((v1 == v2) && (v1 != v3)))	/* NOT ((v1 igual a v2) AND (v1 distinto de v3))  */
	{
		print 'Escriba su nombre: ';
		let s string;	// Oculta a la s global
		input s;
		if (v2 < v3)	/* si v2<v3, v0=v2; en otro caso v0=1/v3 */
		{
			let  v0 int= v2; // se declara v0 aqu�, por lo que se puede utilizar hasta el final de la funci�n
		}
		else
		{
			v0= 1 / v3;
		}
		print s;
	}
	s = "El primer valor era ";
	if (v1 != 0)
	{
		print (s); print v1; print ".\n";
	}
	else
	{
		print s; imprimeSuma (uno, -UNO); print (".\n");	// imprime: `El primer valor era 0.\n�
	}

	potencia (v0, 4);
	let i int;
	for (i=1; i <= 10; ++i)	{
		zv+=i;
	}
	potencia (zv, 5);
	imprimeSuma (i, num);
	imprime ("", cadena(true), 666);
}

demo();
/* esto constituye la llamada a una funci�n sin argumentos. 
Es en este instante cuando se llama a esta funci�n y, por tanto, 
cuando se ejecuta todo el c�digo de dicha funci�n */
