package aulas.aula_19_10;

public class Exemplo1 {
    public static void main(String[] args) {
        exampleIntLong();
        exampleIntChar();
        exampleInc();
        exampleFloat();
        exampleAvaliacaCurta();
        exmpleStringsEquals();
    }

    private static void exmpleStringsEquals() {
        String  s1 = "abc";
        String s2 = "ab";
        System.out.println("------");
        System.out.print ( s1 == s2);
        System.out.println( " " + s1.equals(s2 ));
        String s3 = s2 + 'c'; // "ab"+'c' -> "abc"
        System.out.print( s1 == s3);
        System.out.println(  " " + s1.equals(s3 ));
        String s4 = s1;
        System.out.print( s1 == s4 );
        System.out.println(  " " + s1.equals(s4 ));
        String s5  ="abc";
        System.out.print( s1 == s5); //ERRADO
        System.out.println(  " " + s1.equals(s5 ));//CERTO
    }

    private static void exampleAvaliacaCurta() {
        String s = "";
        //if ( s.charAt(0) == 'S' && s.length() != 0   ) //ERRADO
        if ( s.length() != 0 && s.charAt(0) == 'S'  )
            System.out.println ( s );
        else
            System.out.println("Error");
    }

    private static void exampleFloat() {
        int i;
        float f = 5.5F;
        i = (int)f;
        System.out.println( i );
        System.out.println( Math.round( f ));
        System.out.println( Math.ceil( f ));
        System.out.println( Math.floor( f ));
    }

    private static void exampleInc() {
        int i =9;
        int j = ++i;
        System.out.println( i + " " + j + " " + ++i);
        i = 9;
        j= i++;
        System.out.println(i + " " + j + " " + i++);
    }

    private static void exampleIntChar() {
        char c = '0';
        int i = 9;
        System.out.println( ((c + i)  + " char -> " ) + (char)(c + i));
        // '0' - 48; '1' - 49== '0'+1; '2' - 50= '0'+2; .. '9' - 57= '0'+9

        c = (char) ('0' + i) ; // Inteiro para caracter digito
        System.out.println( c );

        i= c-'0';             //Caracter digito para inteiro
        System.out.println( i );
    }

    private static void exampleIntLong() {
        int i = 0x7fffffff;
        long l;
        System.out.println( i );
        l = i+1;
        System.out.println( i );
        //l = (long)i+1;
        l = i+1L;
        System.out.println( l );
    }
}