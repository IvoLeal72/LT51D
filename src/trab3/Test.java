package trab3;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CallRegFrame.new_CallRegFrame(null);
        while (CallRegFrame.opened==null);
        while (CallRegFrame.opened.size()==0);
        CallRegFrame.opened.values().iterator().next().receiveCall("123");
    }
}
