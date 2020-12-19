package trab1.grupo4;

import trab1.grupo1.Date;

public class HotelRoom extends SimpleItem{
    public final String stars;
    private final int nights;

    public HotelRoom(String name, int pDay, int nights, Date checkIn, int stars){
        super("Hotel "+name, nights*pDay, checkIn);
        this.nights=nights;
        String str="*";
        this.stars=str.repeat(stars);
    }

    public HotelRoom(String name, int pDay, int stars){
        this(name, pDay, 1, Date.today(), stars);
    }

    public Date getEndDate(){
        return getStartDate().nextDate(nights);
    }

    public String getDescription(String prefix){
        return super.getDescription(prefix+stars+" ");
    }
}
