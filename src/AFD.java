import java.util.Scanner;

public class AFD 
{
    public static void getToken(Scanner fichero)
    {
        String line;
        char c;
        int i;

        i = 0;
        while(fichero.hasNextLine())
        {
            line = fichero.nextLine();
            //c = line.charAt(i);
        }
    }

    public static void main(String[] args) 
    {
        Scanner fichero = new Scanner("../tests/ejemplo");
        getToken(fichero);
        fichero.close();
    }
}
