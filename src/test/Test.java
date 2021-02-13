package test;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Test{
    private static final SortedSet<Ponto> pontos=new TreeSet<>();
    private static final SortedSet<Ponto> pontosInv=new TreeSet<>(Comparator.reverseOrder());

    public static void main(String[] args) {
        Ponto p1=new Ponto(0,0);
        Ponto p2=new Ponto(1,0);
        Ponto p3=new Ponto(1,1);

        pontos.add(p1);
        pontos.add(p3);
        pontos.add(p2);
        pontosInv.add(p2);
        pontosInv.add(p3);
        pontosInv.add(p1);

        System.out.println(pontos);
        System.out.println(pontosInv);
    }
}
