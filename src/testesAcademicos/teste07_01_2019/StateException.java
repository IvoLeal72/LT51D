package testesAcademicos.teste07_01_2019;

public class StateException extends Exception{
    private final State state;

    public StateException(String msg){
        super(msg);
        state=null;
    }

    public StateException(State s){
        super(s.getName()+" - invalid state");
        state=s;
    }

    public State getState(){
        return state;
    }
}
