package AnalizadorLexico;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Token
{
	public static List<String> TPR = new ArrayList<>();

	public static boolean isDel(char c)
    {
        if (c == '\n' || c == '\t' || c == ' ' || c == ';')
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

    public static void genToken(String tipo, String atributo, PrintWriter fichero)
    {
		String token;
		
		token = "<" + tipo + ", " + atributo + ">";
		fichero.println(token);
    }
}