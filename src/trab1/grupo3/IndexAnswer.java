package trab1.grupo3;

public class IndexAnswer implements Answer {
    private final int correct;

    public IndexAnswer(int correct) {
        this.correct = correct;
    }

    public boolean check(String answer) throws InvalidFormatException {
        try {
            int choice = Integer.parseInt(answer);
            return choice==correct;
        }
        catch (NumberFormatException e) {
            throw new InvalidFormatException(answer, "não é um número");
        }
    }
}
