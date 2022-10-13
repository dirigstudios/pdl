import java.io.FileReader;
import java.io.*;

public class pdl {

    public static int isDel(char c)
    {
        if (c == '\n' || c == '\t' || c == ' ' || c == ';')
            return 1;
        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File fd = new File(args[0]);
        FileReader sc = new FileReader(fd);
        int valor = 0;
        do
        {
            try {
                valor = sc.read();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.print((char) valor);
        }
        while (valor != -1);
    }
}