package test;

public class Ponto implements Comparable<Ponto>{
    private final int x, y;

    public Ponto(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int compareTo(Ponto other){
        return (x+y)-(other.x+other.y);
    }

    public String toString(){
        return "("+x+","+y+")";
    }
}
