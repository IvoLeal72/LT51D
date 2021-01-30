package testesAcademicos.test2_29_01_2021;

import java.util.*;

public class Pag2 {
    public static Set<String> getSortedSurnamesGreaterN(Map<String, ? extends Set<String>> m, int n, Comparator<String> cmp) {
        SortedSet<String> set=new TreeSet<>(cmp);
        m.forEach((k, v)->{
            if(v.size()>n) set.add(k);
        });
        return set;
    }
}
