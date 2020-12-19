package trab1.grupo2;


import java.util.Arrays;

public class MultiScore extends Score implements MultiLevel {
    private final SingleScore[] list;

    public MultiScore(SingleScore ... scores) throws ScoreException {
        super(sumPoints(scores));
        if(scores.length<2) throw new ScoreException("invalid number of scores");
        this.list=Arrays.copyOf(scores, scores.length);
        Arrays.sort(this.list);
        for(int i=0; i< list.length-1; i++){
            if(this.list[i].equals(this.list[i+1])) throw new ScoreException();
        }
    }

    public int getLowerLevel() {
        return list[0].getLevel();
    }

    public int getLevel() {
        return list[list.length-1].getLevel();
    }

    public String toString(){
        StringBuilder str= new StringBuilder(super.toString() + "(");
        for(int i=0; i<list.length-1; i++){
            str.append(list[i].toString()+",");
        }
        str.append(list[list.length-1].toString()+")");
        return str.toString();
    }

    private boolean hasRepeated(){
        for(int i=0; i< list.length-1;i++){
            if(list[i].equals(list[i+1])) return true;
        }
        return false;
    }
}
