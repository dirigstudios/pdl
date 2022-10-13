import java.io.FileReader;
import java.io.*;

public class pdl {

    public static boolean isDel(char c)
    {
        if (c == '\n' || c == '\t' || c == ' ' || c == ';')
            return true;
        return false;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileReader sc = new FileReader(args[0]);
        String fin = "";
        int valor = 0;
        while (valor != -1 && !isDel((char)valor)) {
            try {
                valor = sc.read();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (valor != -1)
                fin = ((char) valor + fin);
        }
        int length = fin.length() - 1;
        while (length != 0){
            System.out.printf("%c", fin.charAt(length));
            length--;
        }
    }
}