package trab1.grupo1;

public class ExampleDate {
    public static void main(String[] args) {
        Date d1=Date.today();
        System.out.println(d1.toString());
        Date d2=d1.nextDate(365);
        System.out.println(d2.toString());
        System.out.println("Dias entre as datas= "+Date.getDays(d2, d1));
    }
}
