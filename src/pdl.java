import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Character;

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
        TPR.add("break");
        TPR.add("case");
        TPR.add("function");
        TPR.add("if");
        TPR.add("input");
        TPR.add("int");
        TPR.add("let");
        TPR.add("print");
        TPR.add("return");
        TPR.add("string");
        TPR.add("switch");
    }

    public static int next_stage_S(char c)
    {
        if (isC(c))
            return 2;
        if (c == '\"')
            return 3;
        if (isD(c))
            return 4;
        if (c == '%')
            return 5;
        if (c == '=')
            return 6;
        if (c == '{')
            return 7;
        if (c == '}')
            return 8;
        return -1;
    }

    public static void Automata(int stage)
    {
        char c = 'a'; //Delete intialize
        switch (stage)
        {
            case 1:             // Estado S
            {
                //c = read_next_char(fd);
                Automata(next_stage_S(c));
                break;
            }
            case 2:             // Estado T
            {

            }
            case 3:             // Estado A
            {

            }
            case 4:             // Estado B
        }
    }
    
    public static void main(String[] args) throws IOException {
        initializeTPR();
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
        sc.close();
    }
}