package trab3;

public class RejectedCall extends Call{
    public RejectedCall(Time t, String number) {
        super(t, number);
    }

    public String toString(){
        return "Rejected call: "+super.toString();
    }
}
