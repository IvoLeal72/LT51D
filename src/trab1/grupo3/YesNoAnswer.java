package trab1.grupo3;

public class YesNoAnswer implements Answer{
    private final boolean correct;

    public YesNoAnswer(boolean correct){
        this.correct=correct;
    }

    public boolean check(String answer) throws InvalidFormatException {
        if(answer.equalsIgnoreCase("sim")){
            return correct;
        }
        if(answer.equalsIgnoreCase("não") || answer.equalsIgnoreCase("nao")){
            return !correct;
        }
        throw new InvalidFormatException(answer, "nem é sim nem é nao");
    }
}
