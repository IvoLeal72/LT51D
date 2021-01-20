package trab3;

import trab2.*;

public abstract class Call implements Comparable<Call>{
    private final String reg;
    private Date data;
    private Time time;
    private Time duration;

    public String getReg() {
        return reg;
    }

    public Date getData() {
        return data;
    }

    public Time getTime() {
        return time;
    }

    public Time getDuration() {
        return duration;
    }

    public abstract boolean isIncoming();

    public Call(String reg, Date data, Time time, Time duration) {
        this.reg = reg;
        this.data = data;
        this.time = time;
        this.duration = duration;
    }

    public boolean update(Call newCall){
        data = newCall.data;
        time = newCall.time;
        duration = new Time(duration.toSeconds() + newCall.duration.toSeconds());
        return true;
    }

    public int compareTo(Call other) {
        int aux=this.data.compareTo(other.data);
        if(aux!=0) return aux;
        return this.time.compareTo(other.time);
    }
}
