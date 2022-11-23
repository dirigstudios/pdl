package AnalizadorLexico;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Token
{
	public static List<String> TPR = new ArrayList<>();
    private String atributo;
    private String tipo;

    public Token(String tipo, String atributo)
    {
        this.tipo = tipo;
        this.atributo = atributo;
    }

    public String getAtributo() {
        return atributo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "<";
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public void setAtributo(String atributo)
    {
        this.atributo = atributo;
    }

	public static boolean isDel(char c)
    {
        if (c == '\n' || c == '\t' || c == ' ')
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
        TPR.add("default");
    }

    public static void genToken(String tipo, String atributo, PrintWriter fichero)
    {
		String token;
		
        if (tipo.equals("cadena"))
            atributo = atributo;
		token = "<" + tipo + ", " + atributo + ">";
		fichero.println(token);
    }
}