package testesAcademicos.teste07_01_2019;

import java.util.List;

public interface Composition {
    Composition append(State s) throws StateException;
    List<State> getStates();
    State find(String nm);
}
