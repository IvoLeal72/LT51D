package trab1.grupo2;

public class SequenceScore extends Score implements MultiLevel {
    private Score previous;
    public SequenceScore(Score previous, int points){
        super(previous.getPoints()+points);
        this.previous=previous;
    }

    public int getLevel() {
        return previous.getLevel()+1;
    }

    public int getLowerLevel() {
        if(previous instanceof MultiLevel){
            return ((MultiLevel) previous).getLowerLevel();
        }
        return previous.getLevel();
    }

    public Score getPreviousScore(){
        return previous;
    }

    public String toString(){
        return super.toString()+", "+previous.toString();
    }

}
