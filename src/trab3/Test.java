package trab3;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        CallRegFrame.new_CallRegFrame(null);
        while(true){
            Thread.sleep(1000);
            System.out.println(CallRegFrame.opened);
        }
    }
}
