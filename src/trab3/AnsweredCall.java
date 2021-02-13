package trab3;

import java.io.Serializable;

public class AnsweredCall extends Call implements Serializable {
    public AnsweredCall(Time t, String number) {
        super(t, number);
    }

    public String toString(){
        return "Answered call: "+super.toString();
    }
}
