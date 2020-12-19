package aulas.aula_19_10;

public class Box {
    public static void repeat(char c, int n) {
        for( ; n>0; --n )
            System.out.print(c);
    }

    public static void line() {
        repeat('#',16);
        System.out.println();
    }

    public static void main(String[] args) {
        line();
        for(int l=0 ; l<6 ; ++l) {
            System.out.print('#');
            repeat( ' ', 14 );
            System.out.println('#');
        }
        line();
    }

}
