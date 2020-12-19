package trab1.grupo4;

import trab1.grupo1.Date;

public class test {
    public static void main(String[] args) throws ItensException {
        Item altis = new HotelRoom("Altis", 125, 2, new Date(12,4,2021), 5),
                tour = new CityTour("Lisbon Tour", "Alcântara", "Castelo", 50, "13-4-2021"),
                lisbon = new Program("Lisbon", 2, tour, altis ),
                ibis = new HotelRoom("Ibis",60, 4), // Executado a 21/10/2020
                tourNight = new CityTour("Lisbon Tour at night","Restauradores","Belém",75, "12-4-2021");
        TravelAgency travel = new TravelAgency();
        try {
            travel.append(altis).append(tour).append(tourNight).append(lisbon).append(ibis).append(ibis);
        } catch ( ItensException ex) { System.out.println( ex.getMessage() ); }
        System.out.println();
        travel.forEachByPrice( (item) -> System.out.println( item ));
        System.out.println();
        travel.forEachPerDate( (i) -> System.out.println(i.getStartDate() + " – " + i.getName() ) );
        System.out.println();
        System.out.println( travel.getStartDates() );
    }
}
