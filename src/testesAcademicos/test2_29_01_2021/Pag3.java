package testesAcademicos.test2_29_01_2021;

import java.io.*;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Pag3 {
    public static int getNumberOfLine( BufferedReader in, int firstLine, Predicate<String> filter ) throws IOException{
        String line=in.readLine();
        while(line!=null){
            if(filter.test(line)) return firstLine;
            firstLine++;
            line=in.readLine();
        }
        return -1;
    }

    public static void foreachImport( String pathname,  Consumer<Integer> actionImport ) throws IOException{
        try(BufferedReader in=new BufferedReader(new FileReader(pathname))){
            int line=1;
            while(line>0){
                line=getNumberOfLine(in, line, s -> {
                    return s.startsWith("import");
                });
                actionImport.accept(line);
                line++;
            }
        }
    }

    public static void printLines( String pathnameIn, String pathnameOut ) throws IOException{
        try(PrintWriter out=new PrintWriter(new FileWriter(pathnameOut))){
            foreachImport(pathnameIn, integer -> out.print("["+integer+"]"));
        }
    }

    public static <V> boolean isDecreasing( Iterable<V> serie, Comparator<V> compareValue ) {
        V temp=null;
        for(V v:serie){
            if(temp==null) temp=v;
            else{
                if(compareValue.compare(v, temp)<0) return false;
                temp=v;
            }
        }
        return true;
    }
}
