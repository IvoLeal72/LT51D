package test;


import java.util.ArrayList;
import java.util.Collection;

public class Test{
    public static void main(String[] args) {
        String test="07-02-2000 Ivo Leal [12345, 6789, 123, 789, 123456789, 987654321]";
        System.out.println(test.substring(0,11));
        System.out.println();
        int idx=test.indexOf('[');
        int secIdx=test.indexOf(']');
        String[] arr=test.substring(idx+1, secIdx).split(", ");
        for(String str:arr) System.out.println(str);
        if(idx<0) System.out.println(test.substring(11));
        else{
            System.out.println(test.substring(11, idx-1));
            Collection<String> phonesList=new ArrayList<>();
            secIdx=test.indexOf(' ', idx);
            while(secIdx>0){
                phonesList.add(test.substring(idx+1, secIdx-1));
                idx=secIdx;
                secIdx=test.indexOf(' ', idx+1);
            }
            secIdx=test.indexOf(']');
            phonesList.add(test.substring(idx+1, secIdx));
            System.out.println(phonesList);
            System.out.println(phonesList.size());
        }
    }
}
