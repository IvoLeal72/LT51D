package testesAcademicos.teste07_01_2019;

import java.util.List;

public class Federation extends Union{

    public Federation(String nm){
        super(nm, "Estado federal");
    }

    public boolean isSovereign() {
        return true;
    }

    public int getArea(){
        int area=0;
        for(State state:super.states){
            area+=state.getArea();
        }
        return area;
    }

    public Federation append(State s) throws StateException {
        if(s.isSovereign()) throw new StateException(s);
        super.append(s);
        return this;
    }

    public Federation append(String stName, int stArea) throws StateException {
        return append(new Country(stName, stArea, false));
    }
}
