package trab1.grupo2;

public class SingleScore extends Score{
    private final int level;

    public SingleScore(int level, int points){
        super(points);
        this.level=level;
    }

    public SingleScore(int points){
        this(0, points);
    }

    public int getLevel() {
        return this.level;
    }
}
