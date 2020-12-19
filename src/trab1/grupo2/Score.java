package trab1.grupo2;

public abstract class Score implements Comparable<Score> {
    private final int points;

    protected Score(int points){
        this.points=points;
    }


    public final int getPoints() {return this.points;}

    public String toString() {
        return getLevel() + ": " + getPoints();
    }

    public abstract int getLevel();

    public int compareTo(Score other){
        if(this==other) return 0;
        int temp=this.getLevel()-other.getLevel();
        if(temp!=0) return temp;
        temp=this.getPoints()-other.getPoints();
        return temp;
    }

    public boolean equals(Object other){
        if(other==null) return false;
        if(this==other) return true;
        if(other instanceof Score){
            return this.getLevel()==((Score)other).getLevel() && this.getPoints()==((Score)other).getPoints();
        }
        return false;
    }

    public static int sumPoints(Score ... scores){
        int res=0;
        for (Score score : scores) {
            res += score.getPoints();
        }
        return res;
    }
}

