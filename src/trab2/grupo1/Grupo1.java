package trab2.grupo1;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Grupo1 {
    public static int replaceTabs(String pathnameIn, PrintWriter out, int n) throws IOException {
        int subs=0;
        String s=" ".repeat(n);
        BufferedReader rd=new BufferedReader(new FileReader(pathnameIn));
        int character=rd.read();
        while(character!=-1){
            if(((char)character)=='\t'){
                out.print(s);
                subs++;
            }
            else{
                out.print((char) character);
            }
            character= rd.read();
        }
        rd.close();
        return subs;
    }

    public static int filter(BufferedReader in, Predicate<String> criteria, Consumer<String> action) throws IOException {
        String str=in.readLine();
        int ctt=0;
        while(str!=null){
            if(criteria.test(str)){
                action.accept(str);
                ctt++;
            }
            str=in.readLine();
        }
        return ctt;
    }

    public static int copyTeam(String pathnameIn, String teamId, BiConsumer<Integer, String> action) throws IOException {
        BufferedReader rd=new BufferedReader(new FileReader(pathnameIn));
        int subs=filter(rd, (str)->str.startsWith(teamId), (str)->{
            int firstIdx=str.indexOf(' ');
            int secondIdx=str.indexOf(' ', firstIdx+1);

            int number=Integer.parseInt(str.substring(firstIdx+1, secondIdx));
            String name=str.substring(secondIdx+1);
            action.accept(number, name);
        }
        );

        rd.close();
        return subs;
    }

    public static int copyTeam(String pathnameIn, String teamId) throws IOException{
        PrintWriter out=new PrintWriter(new FileWriter(teamId+".txt"));
        int subs = copyTeam(pathnameIn, teamId, (number, name)-> out.println(number+" "+name));
        out.close();
        return subs;
    }

    public static void printTeam(String pathnameIn, String teamId) throws IOException{
        copyTeam(pathnameIn, teamId, (num, name)->System.out.println(num+" "+name));
    }

    public static void main(String[] args) throws IOException{
        FirstWindow window = new FirstWindow();
        window.setVisible(true);
    }
}
