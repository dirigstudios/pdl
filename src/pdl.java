import java.io.FileReader;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class pdl {

    public static final ArrayList<String> TPR = new ArrayList<>();
    public static boolean isDel(char c)
    {
        if (c == '\n' || c == '\t' || c == ' ' || c == ';')
            return true;
        return false;
    }
    public static boolean isD(char c)
    {
        if ((int)c <= 48 && (int)c >= 57)
            return true;
        return false;
    }

    public static boolean isC(char c)
    {
        if ((int)c <= 97 && (int)c >= 122)
            return true;
        return false;
    }

    public static void initializeTPR()
    {
        TPR.add("boolean");
        TPR.add("boolean");
        TPR.add("boolean");
        TPR.add("boolean");
        TPR.add("boolean");
        TPR.add("boolean");
        TPR.add("boolean");
        TPR.add("boolean");
        TPR.add("boolean");
        TPR.add("boolean");
        TPR.add("boolean");
        TPR.add("boolean");
        TPR.add("boolean");
        TPR.add("boolean");

    }

    public static void main(String[] args) throws FileNotFoundException {
        FileReader sc = new FileReader("C:\\Proyectos\\pdl\\pdl\\src\\texto.txt");
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