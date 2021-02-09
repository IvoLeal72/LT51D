package trab3;

public class Duration implements Comparable<Duration>{
    private final int hour, min, sec;

    public Duration(int h, int m, int s){
        hour=h; min=m; sec=s;
    }

    public Duration(long secs){
        hour=(int) secs/3600;
        secs%=3600;
        min=(int) secs/60;
        sec=(int) secs%60;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public int getSec() {
        return sec;
    }

    public long toSeconds(){
        return hour* 3600L +min* 60L +sec;
    }

    public Duration add(Duration other){
        return new Duration(this.toSeconds() + other.toSeconds());
    }

    public String toString(){
        if(hour>0) return String.format("%d:%02d:%02d", hour, min, sec);
        return String.format("%02d:%02d", min, sec);
    }

    public int compareTo(Duration other){
        int res=hour - other.hour;
        if(res!=0) return res;
        res=min - other.min;
        if(res!=0) return res;
        return sec - other.sec;
    }

    public boolean equals(Object o){
        if(!(o instanceof Duration)) return false;
        Duration d=(Duration) o;
        return  hour==d.hour && min==d.min && sec==d.sec;
    }
}
