import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Token
{
    public static PrintWriter fichToken;

    public static void genToken(String tipo, String atributo, PrintWriter fichero)
    {
		String token;
		
		token = "<" + tipo + ", " + atributo + ">";
		fichero.println(token);
    }

	public static void main(String[] args) throws FileNotFoundException {
		fichToken = new PrintWriter("./tests/tokens.txt");
		genToken("palabraReservada", "int", fichToken);
		fichToken.close();
	}
}