package test;

import java.io.File;
import java.util.Objects;

public class Test{

    public static void fileRecursvivePrint(File f, String prefix){
        if(f.isDirectory()){
            System.out.println(prefix+f.getName()+"\\");
            File[] list=f.listFiles();
            if(list!=null)
                for(File temp: list){
                    fileRecursvivePrint(temp, "-"+prefix);
                }
            return;
        }
        if(f.isFile()){
            System.out.println(prefix+f.getName());
        }
    }

    public static void main(String[] args) {
        File f= new File("D:\\OneDrive - Instituto Superior de Engenharia de Lisboa");
        fileRecursvivePrint(f, "");
    }
}
