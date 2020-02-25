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
    public static char DOT = '.';
    public static char SPACE = ' ';
    public static char [] NUMBERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public static String [] KEYWORD = {"read", "write"};

    public static Character [] imp_char = {LPARENT, RPARENT, TIMES, MINUS, PLUS, DIV, OR, AND};

    private String input;
    private ArrayList<Pair<String, String>> input_identifier;

    public Automata(String input)
    {
        input = input.replace("\n", " ").replace("\r", " ");
        this.input = input + " ";
    }

    public void identify()
    {
        input_identifier = new ArrayList<>();
        for(int i = 0; i < this.input.length(); ++i)
        {
            i = state_start(this.input.charAt(i), i);
        }
    }
    
    private boolean has_character(char c)
    {
        for(char t : imp_char)
            if(c == t)
                return true;
        return false;
    }

    private boolean has_number(char c)
    {
        for(char t :  NUMBERS)
            if(t  == c)
                return true;
        return false;
    }

    private int is_number(char c, int pos, int dot)
    {
        int dots = dot;
        int result = 0;

        for(int i = pos; i < this.input.length() - 1; i++)
        {
            if(this.input.charAt(i) == SPACE || has_character(this.input.charAt(i)))
                i = this.input.length();
            else if(this.input.charAt(i) == DOT)
            {
                dots++;
                result++;
                if(dots == 2)
                    return result = -1;
            }
            else if(has_number(this.input.charAt(i)))
                result++;
            else
                return -1;
        }
        return result;

    }
    @SuppressWarnings("unchecked")
    public int state_start(char temp,int pos)
    {
        //check the easiest ones
        if(temp == LPARENT)
            input_identifier.add(new Pair("lparen", '"' + Character.toString(LPARENT) + '"'));
        else if(temp == RPARENT)
            input_identifier.add(new Pair("rparen",'"' +  Character.toString(RPARENT) + '"'));
        else if(temp == TIMES)
            input_identifier.add(new Pair("times",'"' + Character.toString(TIMES) + '"'));
        else if(temp == MINUS)
            input_identifier.add(new Pair("minus",'"' + Character.toString(MINUS) + '"'));
        else if(temp == PLUS)
            input_identifier.add(new Pair("plus", '"' + Character.toString(PLUS) + '"'));
        else if(temp == DIV)
            input_identifier.add(new Pair("div",'"' +  Character.toString(DIV) + '"'));
        else if(temp == ASSIGN.charAt(0) && this.input.charAt(pos + 1) == ASSIGN.charAt(1))
        {
            input_identifier.add(new Pair("assign", '"' + ASSIGN + '"'));
            pos++;
        }
        else if(temp == 'r' && input.substring(pos, pos + 5).compareTo(KEYWORD[0] + " ") == 0)
        {
            input_identifier.add(new Pair("keyword", '"' + KEYWORD[0] + '"'));
            pos += 4;
        }
        else if(temp == 'w' && input.substring(pos, pos + 6).compareTo(KEYWORD[1] + " ") == 0)
        {
            input_identifier.add(new Pair("keyword", '"' + KEYWORD[1] + '"'));
            pos += 5;
        }
        else if(has_number(temp))
        {
            if(input.charAt(pos + 1) == SPACE)
            {
                input_identifier.add(new Pair("number", '"' + Character.toString(temp) + '"'));
                pos++;
            }
            else
                {
                int res = is_number(temp, pos, 0);
                if(res != -1)
                {
                    input_identifier.add(new Pair("number", '"' + input.substring(pos, pos+res) + '"'));
                    pos+=res - 1;
                }
            }
        }
        else if(temp == DOT)
        {
            int res = is_number(temp, pos, 0);
            if(res != -1)
            {
                input_identifier.add(new Pair("number", '"' + input.substring(pos, pos+res) + '"'));
                pos+=res;
         
            }
        }
        else
        {
            if(this.input.charAt(pos) != SPACE)
            {
                int res = 0;
                for(int i = pos; i < this.input.length() - 1; ++i)
                {
                    if(this.input.charAt(i) == SPACE || has_character(this.input.charAt(i)) || this.input.charAt(i) == '=')
                        break;
                    else
                        res++;
                }
                input_identifier.add(new Pair("id", '"' + input.substring(pos, pos+res) + '"'));
                pos += res - 1;
            }
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