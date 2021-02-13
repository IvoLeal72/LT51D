package trab3;

import java.io.Serializable;

public class RejectedCall extends Call implements Serializable {
    public RejectedCall(Time t, String number) {
        super(t, number);
    }

    public String toString(){
        return "Rejected call: "+super.toString();
    }
}
