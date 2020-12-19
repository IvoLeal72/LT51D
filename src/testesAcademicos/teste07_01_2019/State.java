package testesAcademicos.teste07_01_2019;

public abstract class State {
    private final String name;

    public State(String nm){
        name=nm;
    }

    public final String getName(){
        return name;
    }

    public abstract boolean isSovereign();

    public abstract int getArea();

    public State find(String n){
        return name.equals(n) ? this: null;
    }

    public String getDescription(String prefix){
        return prefix + name + " - ";
    }

    public final String toString(){
        return getDescription("");
    }
}
