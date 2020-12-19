package aulas.aula_19_10;

import java.util.Scanner;

public class ArrayUtil {
    static public void fill(int[] a, int v) {
        for (int i = 0; i < a.length; ++i)
            a[i] = v;
    }

    static public void copy(int[] src, int[] dest) {
        int len = (src.length < dest.length) ? src.length : dest.length;
        for (int i = 0; i < len; ++i)
            dest[i] = src[i];
    }

    //System.arraycopy(src, srcIndex, dest, destIndex, n);
    static public void copyOfRange(int[] src, int srcIndex,
                                   int[] dest, int destIndex,
                                   int n) {
        for (int i = srcIndex, j = destIndex; n > 0; --n, ++i, ++j)
            dest[j] = src[i];
    }

    static public void writeOutput(int[] a) {
        System.out.print('[');
        if (a.length != 0) {
            System.out.print(a[0]);
            for (int i = 1; i < a.length; ++i)
                System.out.print(", " + a[i]);
        }
        System.out.print(']');
    }

    static public int max(int[] a, int indexStart, int indexEnd) {
        int greater = a[indexStart];
        for (int i = indexStart + 1; i < indexEnd; ++i)
            if (a[i] > greater)
                greater = a[i];
        return greater;
    }

    static public int indexOf(int[] a, int v) {
        for (int i = 0; i < a.length; ++i)
            if (a[i] == v) return i;
        return -1;
    }

    static public boolean contains(int[] a, int v) {
        /*
        for (int i = 0; i < a.length; ++i) {
            int vi = a[i]; */
        for ( int vi: a ) {
            if (vi == v) return true;
        }
        return false;
    }

    static public boolean equals(int[] a, int[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; ++i)
            if (a[i] != b[i]) return false;
        return true;
    }

    static public int[] resize( int[] a, int n ) {
        int[] aCopy = new int [ n ]; // Criar novo
        copy( a, aCopy ); // Copiar para o novo
        return aCopy;     // Retornar o novo array
    }

    static public int[] readNValues(Scanner in, int n ) {
        System.out.println("Introduza os " + n + " valores inteiros.");
        int[] a= new int [ n ];
        for ( int i = 0; i < n; ++i )
            a[i] = in.nextInt();
        return a;
    }

    static public int[] readValues( Scanner in ) {
        System.out.println("Introduza os valores positivos. " +
                           "Termine com um valor negativo.");
        int [] a = new int [10];
        int n = 0, value = in.nextInt();
        while ( value > 0 ) {
            if ( n == a.length ) a = resize( a, a.length * 2 );
            a[ n ] = value;
            ++n;
            value = in.nextInt();
        }
        return resize( a, n );
    }
}
