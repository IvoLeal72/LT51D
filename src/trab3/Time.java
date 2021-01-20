package trab3;

public class Time implements Comparable<Time>{
    private final int hour, min, sec;

    public Time(int hour, int min, int sec){
        this.hour=hour;
        this.min=min;
        this.sec=sec;
    }

    public Time(int timeInSecs){
        this(timeInSecs/3600, (timeInSecs%3600)/60, timeInSecs%60);
    }

    public int toSeconds(){
        return hour*3600+min*60+sec;
    }

    public int getHours() {
        return hour;
    }

    public int getMinutes() {
        return min;
    }

    public int getSeconds() {
        return sec;
    }

    public int compareTo(Time other) {
        return this.toSeconds()-other.toSeconds();
    }
}
