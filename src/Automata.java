import java.util.ArrayList;
import java.util.stream.Collectors;
public class Automata
{
    public static char LPARENT = '(';
    public static char RPARENT = ')';
    public static String ASSIGN = ":=";
    public static char TIMES = '*';
    public static char MINUS = '-';
    public static char PLUS = '+';
    public static char DIV = '/';
    public static char OR = '|';
    public static char AND = '&';
    public static char [] NUMBERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public static String [] KEYWORD = {"read", "write"};

    private String input;
    private ArrayList<Pair<String, String>> input_identifier;

    public Automata(String input)
    {
        this.input = input;
    }

    public void identify()
    {
        input_identifier = new ArrayList<>();
        for(int i = 0; i < this.input.length(); ++i)
        {
            i = state_start(this.input.charAt(i), i);
        }
    }
    
    public int state_start(char temp,int pos)
    {
        //check the easiest ones
        if(temp == LPARENT)
            input_identifier.add(new Pair("lparent", '"' + LPARENT + '"'));
        else if(temp == RPARENT)
            input_identifier.add(new Pair("rparent",'"' +  RPARENT+ '"'));
        else if(temp == TIMES)
            input_identifier.add(new Pair("times",'"' + TIMES + '"'));
        else if(temp == MINUS)
            input_identifier.add(new Pair("minus",'"' + MINUS + '"'));
        else if(temp == PLUS)
            input_identifier.add(new Pair("plus", '"' + PLUS + '"'));
        else if(temp == DIV)
            input_identifier.add(new Pair("div",'"' +  DIV + '"'));
        else if(temp == ASSIGN.charAt(0) && this.input.charAt(pos + 1) == ASSIGN.charAt(1))
        {
            input_identifier.add(new Pair("assign", '"' + ASSIGN + '"'));
            pos++;
        }
        return pos;
    }

    public String toString()
    {
        return input_identifier.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }

    public boolean is_keyword(String a)
    {
        for(String s : KEYWORD)
            if(s.equals(a))
                return true;
        return false;
    }

    public boolean is_number(char a)
    {
        for(char s : NUMBERS)
            if(s == a)
                return true;
        return false;
    }

}