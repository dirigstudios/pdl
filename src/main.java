import java.io.FileReader;
import java.io.*;
import java.util.Scanner;

public class main {

    public static final char[] del = {'\n','\t',' ',';'};

    public static String read_next_char(File fd, Scanner sc)
    {
            if (sc.hasNext())
                return sc.next();
            else
                return "0";
    }

    public static void main(String[] args) throws FileNotFoundException {
        File fd = new File("C:\\Proyectos\\pdl\\src\\texto.txt");
        Scanner sc = new Scanner(fd);
        while(sc.hasNext())
            System.out.println(sc.next());
    }
}