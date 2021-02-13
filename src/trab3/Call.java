package trab3;

import trab2.Contact;

public abstract class Call implements Comparable<Call>{
    private Time time;
    private final String number;
    private final String name;

    public Call(Time t, String number, String name){
        time=t;
        this.number=number;
        this.name=name;
    }

    public Call(Time t, String number){
        this(t, number, null);
    }

    public Time getTime() {
        return time;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int compareTo(Call other){
        return time.compareTo(other.time);
    }

    public String toString(){
        return (name==null?number:name)+" "+time.toString();
    }

    public boolean merge(Call other){
        time=other.time;
        return true;
    }
}
