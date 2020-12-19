package trab1.grupo4;

import trab1.grupo1.Date;

import java.util.Arrays;
import java.util.Comparator;

public class Program extends Item{
    private static class CmpAscendingStartDate implements Comparator<Item>{
        public int compare(Item i1, Item i2){
            return i1.getStartDate().compareTo(i2.getStartDate());
        }
    }
    private final Item[] items;
    private final Date start;
    private final int days;

    public Program(String nm, int days, Item ... items) throws ItensException {
        super(nm, sumPrice(items));
        this.days=days;
        this.items=Arrays.copyOf(items, items.length);
        Arrays.sort(this.items, new CmpAscendingStartDate());
        this.start=this.items[0].getStartDate();
        Date end=start.nextDate(days);
        for(Item item:this.items)
            if(item.getEndDate().compareTo(end) > 0) throw new ItensException(item.getName()+ " - invalid item");
    }

    public Date getStartDate(){
        return start;
    }

    public Date getEndDate() {
        return start.nextDate(days);
    }

    public String getDescription(String prefix){
        StringBuilder str=new StringBuilder(super.getDescription(prefix+"Travel Program: "));
        for(Item item:items)
            str.append("\n").append(item.getDescription(prefix + "\t"));
        return str.toString();
    }

}
