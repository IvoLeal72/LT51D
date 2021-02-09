package trab3;

public class AnsweredCall extends Call{
    public AnsweredCall(Time t, String number, String name) {
        super(t, number, name);
    }

    public AnsweredCall(Time t, String number) {
        super(t, number);
    }

    public String toString(){
        return "Answered call: "+super.toString();
    }
}
