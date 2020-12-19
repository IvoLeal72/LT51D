package trab1.grupo1;

public class Friend {
    private final String name, birth;
    public Friend(String nm, int d, int m){
        this.name=nm;
        this.birth=String.format("%2d\\%2d", d, m);
    }

    public String toString(){
        return this.name + " birth in " + this.birth;
    }

    public boolean equals(Friend other){
        if(this==other) return true;
        if(other==null) return false;
        return this.name.equals(other.name) && this.birth.equals(other.birth);
    }
}
