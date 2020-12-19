package trab1.grupo3;

public class SimpleQuest extends Quest{
    private final int points;

    public SimpleQuest(String txt){
        super(txt, new YesNoAnswer(true));
        this.points=100;
    }

    public SimpleQuest(String txt, Answer answer, int points){
        super(txt, answer);
        this.points=points;
    }

    public int getPoints(){
        return this.points;
    }
}
