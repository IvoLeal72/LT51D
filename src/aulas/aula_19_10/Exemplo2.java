package aulas.aula_19_10;

public class Exemplo2 {

    public static int greaterSequencial( int[] array ){
        int max = array[0];
        for ( int i= 1; i < array.length; ++i )
            if ( array[i] > max) max = array[i];
            // else max= max;
            //max = (array[i] > max)? array[i]: max;
        return max;
    }

    public static boolean findBinary( int[] array , int val ){
        int min= 0, max= array.length-1, middle;
        while (min <= max ) {
            middle = (min+max)/2;
            if ( array[middle] == val) return true;
            else if ( array[middle] > val) max = middle-1;
            else min = middle+1;
        }
        return false;
    }
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "aa";
        //s2 = s2 + 'c';
        System.out.println(s1 +'-' + s2);

        int resultCmp = s1.compareTo(s2);
        System.out.println( resultCmp + " " + ('b'-'a'));
        if ( resultCmp == 0 ) {
            System.out.println( "As duas Strings têm a mesma sequencia de caracter");
        }
        else if ( resultCmp > 0 )
            System.out.println(s1 + " é maior lexicograficamente que " + s2);
        else
            System.out.println( s1 + " é menor lexicograficamente que " + s2 );

        char c = 'a';
        switch ( c ) {
            case 'a': System.out.println("aa");
            case 'b': System.out.println("bb"); break;
            case 'c': System.out.println("cc");
        }
    }
}
