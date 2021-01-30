package testesAcademicos.test2_29_01_2021;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Pag1 {
    public static <V, C extends Collection<V>>
    C getElements(Iterable<V> s, Predicate<V> p, Supplier<C> sup){
        C r= sup.get();
        for (V i: s )
            if ( p.test( i )) r.add( i );
        return r;
    }

    public static List<Integer> mult5( List<Integer> serie ) {
        List<Integer> list=new ArrayList<>();
        getElements(serie, (i)->i%5==0, ()->list);
        return list;
    }
}
