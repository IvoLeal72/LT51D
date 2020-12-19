package trab1.grupo3;

public abstract class Quest {
    private final Answer correct;
    private final String txt;

    public Quest(String txt, Answer correct) {
        this.correct = correct;
        this.txt = txt;
    }

    public abstract int getPoints();

    public int checkPoints(String answer) throws InvalidFormatException {
        if(correct.check(answer)){
            return getPoints();
        }
        else return 0;
    }

    public final String toString(){
        return txt;
    }
}
