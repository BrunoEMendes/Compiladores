import java.util.Iterator;
import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String s = "";
        while(sc.hasNextLine())
            s += sc.nextLine() + "\n";
            Automata a = new Automata(s);
            a.identify();
            System.out.print(a);
            
        System.out.println(s);
    }
}