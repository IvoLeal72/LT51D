package trab1.grupo4;

import trab1.grupo1.Date;

public abstract class SimpleItem extends Item{
    private final Date start;

    public SimpleItem(String name, int price, Date start){
        super(name, price);
        this.start=start;
    }

    public Date getStartDate() {
        return start;
    }

    public abstract Date getEndDate();
}
