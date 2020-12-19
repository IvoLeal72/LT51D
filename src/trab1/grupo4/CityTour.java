package trab1.grupo4;

import trab1.grupo1.Date;

public class CityTour extends SimpleItem{
    private final String origin, destination;

    public CityTour(String nm, String ori, String dest, int price, String date){
        super(nm+" from "+ori+" to "+dest, price, new Date(date));
        this.origin=ori;
        this.destination=dest;
    }

    public Date getEndDate() {
        return getStartDate();
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }
}
