package testesAcademicos.teste2_07_01_2019;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Grupo1 {
    public static <R> Collection<String> getStatesName(Collection<State> states, Function<State, R> stateToR, Predicate<R> filter){
        Collection<String> list=new ArrayList<>();
        states.forEach(state -> {
            R r=stateToR.apply(state);
            if(filter.test(r)) list.add(state.getName());
        });
        return list;
    }

    public static Collection<String> lessArea(Federation federation, int value, Comparator<Integer> cmp){
        return getStatesName(federation.getStates(), State::getArea, (area -> cmp.compare(area, value)<0));
    }

    public static Collection<String> greaterArea(Federation federation, int value){
        return lessArea(federation, value, (i1, i2)->i2-i1);
    }

    public static SortedMap<String, Integer> countPerPolitical(Collection<State> statesOfWorld, Comparator<String> cmp){
        SortedMap<String, Integer> map = new TreeMap<>(cmp);
        statesOfWorld.forEach(state -> {
            Integer temp=map.remove(state.getSocioPolitical());
            if(temp==null) map.put(state.getSocioPolitical(), 1);
            else{
                map.put(state.getSocioPolitical(), temp+1);
            }
        });
        return map;
    }

    public static String greaterPolitical(Collection<State> states){
        SortedMap<String, Integer> map=countPerPolitical(states, String::compareToIgnoreCase);
        int max=0;
        for(int i:map.values()){
            if(i>max) max=i;
        }
        for(String k:map.keySet()){
            if(map.get(k)==max) return k;
        }
        return null;
    }
}
