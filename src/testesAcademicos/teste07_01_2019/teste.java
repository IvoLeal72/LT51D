package testesAcademicos.teste07_01_2019;

public class teste {
    public static void main(String[] args) throws StateException {
        State p = new Country("Portugal", 92391, true);
        State f = new Country("França", 154077, true);
        String onuName = "Organização Nações Unidas";
        String onuType = "Organização Internacional";
        Union onu = new Union( onuName, onuType );
        onu.append( p ).append( f );
        System.out.println( onu );
        State g = new Country("Geórgia", 154077, false);
        Federation usa = new Federation("Estados Unidos");
        try {
            usa.append( g ).append("Flórida",170451);
            System.out.println( usa );
            usa.append( p );
        }
        catch ( StateException e ) {
            System.out.println(e.getMessage() );
        }
        onu.append( usa );
        System.out.println( onu );
        State s;
        System.out.println();
        if (onu.find(onuName) == onu && (s= onu.find("Flórida"))!=null)
            System.out.println(s);
    }
}
