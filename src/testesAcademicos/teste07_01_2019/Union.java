package testesAcademicos.teste07_01_2019;

import java.util.LinkedList;
import java.util.List;

public class Union extends State implements Composition{
    protected final List<State> states=new LinkedList<State>();
    private final String type;

    public Union(String nm, String tp){
        super(nm);
        type=tp;
    }

    public boolean isSovereign() {
        return false;
    }

    public int getArea() {
        throw new UnsupportedOperationException();
    }

    public State find(String nm){
        if(getName().equals(nm)) return this;
        for(State state:states){
            if(state.getName().equals(nm)) return state;
            if(state instanceof Composition){
                State temp=((Composition)state).find(nm);
                if(temp!=null) return temp;
            }
        }
        return null;
    }

    public Composition append(State s) throws StateException {
        if(find(s.getName())==null){
            states.add(s);
        }
        else{
            throw new StateException(s);
        }
        return this;
    }

    public List<State> getStates(){
        return states;
    }

    public String getDescription(String prefix){
        StringBuilder str=new StringBuilder(super.getDescription(prefix)).append(type);
        for(State state:states){
            str.append("\n").append(state.getDescription("\t"+prefix));
        }
        return str.toString();
    }
}
