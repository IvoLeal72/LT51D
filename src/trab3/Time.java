package trab3;

import trab2.Date;

import java.util.Calendar;

public class Time implements Comparable<Time>{
    private final Date date;
    private final int hour, min, sec;

    public Time(Date d, int hour, int min, int sec){
        date=d;
        this.hour=hour;
        this.min=min;
        this.sec=sec;
    }

    public Time(){
        Calendar cal=Calendar.getInstance();
        date=new Date(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR));
        hour=cal.get(Calendar.HOUR_OF_DAY);
        min=cal.get(Calendar.MINUTE);
        sec=cal.get(Calendar.SECOND);
    }

    public String toString(){
        return date.toString()+" "+String.format("%02d:%02d:%02d", hour, min, sec);
    }

    public int compareTo(Time other){
        int res=date.compareTo(other.date);
        if(res!=0) return res;
        res=hour - other.hour;
        if(res!=0) return res;
        res=min - other.min;
        if(res!=0) return res;
        return sec - other.sec;
    }

    public Date getDate() {
        return date;
    }

    public int hashCode() {
        int result = date.hashCode();
        result = 100 * result + hour;
        result = 100 * result + min;
        result = 100 * result + sec;
        return result;
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

    public boolean equals(Object o){
        if (!(o instanceof Time)) return false;
        Time t = (Time) o;
        return date.equals(t.date) && hour==t.hour && min==t.hour && sec==t.sec;
    }
}
