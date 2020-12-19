package trab1.grupo2;

public class ScoreException  extends Exception{
    public ScoreException(){
        super("invalid sequence");
    }
    public ScoreException(String msg){
        super(msg);
    }
}
