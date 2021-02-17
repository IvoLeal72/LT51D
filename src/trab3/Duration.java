package trab3;

import java.io.Serializable;

public class Duration implements Comparable<Duration>, Serializable {
    private final long secs;

    public Duration(int h, int m, int s){
        secs=h* 3600L +m* 60L +s;
    }

    public Duration(long secs){
        this.secs=secs;
    }

    public Duration(){
        this(0);
    }

    public int getHour() {
        return (int) (secs/3600);
    }

    public int getMin() {
        return (int) ((secs%3600)/60);
    }

    public int getSec() {
        return (int) (secs%3600%60);
    }

    public long toSeconds(){
        return secs;
    }

    public Duration add(Duration other){
        return new Duration(this.toSeconds() + other.toSeconds());
    }

    public String toString(){
        if(getHour()>0) return String.format("%d:%02d:%02d", getHour(), getMin(), getSec());
        return String.format("%02d:%02d", getMin(), getSec());
    }

    public int compareTo(Duration other){
        return (int) (toSeconds()-other.toSeconds());
    }

    public boolean equals(Object o){
        if(!(o instanceof Duration)) return false;
        Duration d=(Duration) o;
        return  toSeconds()==d.toSeconds();
    }
}
