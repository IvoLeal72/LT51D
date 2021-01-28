package testesAcademicos.teste2_07_01_2019;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Grupo2 {
    public static void intercalateLines(String filename1, String filename2, Consumer<String> action) throws IOException{
        try(BufferedReader in1=new BufferedReader(new FileReader(filename1))) {
            try(BufferedReader in2=new BufferedReader(new FileReader(filename2))){
                String line1=in1.readLine();
                String line2=in2.readLine();
                while(line1!=null || line2!=null){
                    if(line1!=null) action.accept(line1);
                    if(line2!=null) action.accept(line2);
                    line1=in1.readLine();
                    line2=in2.readLine();
                }
            }
        }
    }

    public static void intercalate(String filename1, String filename2) throws IOException{
        try(PrintWriter out=new PrintWriter(new FileWriter("intercalate.txt"))){
            intercalateLines(filename1, filename2, out::println);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Window window=new Window();
        boolean visible=true;
        while(true){
            window.setVisible(visible);
            visible=!visible;
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
