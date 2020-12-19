package aulas.aula_19_10;

public class Figure {
    private static void repeat( char c, int n )
    { while ( n-- > 0 ) System.out.print( c );  }

    private static void print( int o, char c, int n )
    { repeat( ' ', o ); repeat( c, n );        }

    private static void printLine( int o, char c, int n )
    { print( o, c, n ); System.out.println();   }

    private static void middleLine( int o, char c, int m )
    { print( o, c, 1 ); printLine( m-2, c, 1 ); }

    public static void box( int offsetHor, char c, int width, int height ) {
        printLine( offsetHor, c, width );
        for ( int l= 2; l < height; ++l )
            middleLine( offsetHor, c, width );
        printLine( offsetHor, c, width );
    }

    public static void triangle( int offsetHor, char c, int base ) {
        int spaces = base >> 1;
        if ( ( base & 0x1 ) != 0 ) printLine( offsetHor + spaces, c, 1 );
        while ( --spaces != 0 )
            middleLine( offsetHor + spaces, c , base - ( spaces << 1 ));
        printLine( offsetHor, c, base );
    }

}
