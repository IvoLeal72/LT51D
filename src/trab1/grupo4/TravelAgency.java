package trab1.grupo4;

import trab1.grupo1.Date;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TravelAgency {
    List<Item> items= new LinkedList<>();

    public List<Item> getItens(Predicate<Item> filter){
        List<Item> res = new LinkedList<>();

        for(Item item:items)
            if(filter.test(item)) res.add(item);

        return res;
    }

    public TravelAgency append(Item item) throws ItensException {
        Predicate<Item> itemPredicate = new Predicate<Item>() {
            @Override
            public boolean test(Item itemToTest) {
                return itemToTest.getName().equals(item.getName());
            }
        };
        if(getItens(itemPredicate).size() >0){
            throw new ItensException(item.getName()+" - this name already exists");
        }
        items.add(item);
        return this;
    }

    public List<Item> getBetween(int min, int max){
        return getItens((item -> item.getPrice()>=min && item.getPrice()<=max));
    }

    public List<Item> getBetween(Date min, Date max){
        return getItens(item -> item.getStartDate().compareTo(min) >= 0 && item.getEndDate().compareTo(max) <= 0);
    }

    public List<Item> getBetween(String minDate, String maxDate){
        return getBetween(new Date(minDate), new Date(maxDate));
    }

    public int getUpperPrice(Comparator<Item> cmp){
        Item upper=items.get(0);
        for(Item item:items){
            if(cmp.compare(item, upper) > 0) upper=item;
        }
        return upper.getPrice();
    }

    public int getLowerPrice(){
        return getUpperPrice((item1, item2) -> item2.getPrice() - item1.getPrice());
    }

    public void forEachPerDate(Consumer<Item> action){
        Comparator<Item> cmp= (item1, item2) -> {
            int temp=item1.getStartDate().compareTo(item2.getStartDate());
            if(temp!=0) return temp;
            return item1.getName().compareTo(item2.getName());
        };
        items.sort(cmp);
        for(Item item:items){
            action.accept(item);
        }
    }

    public void forEachByPrice(Consumer<Item> action){
        items.sort((item1, item2) -> item1.getPrice()-item2.getPrice());
        for(Item item:items)
            action.accept(item);
    }

    public List<Date> getStartDates(){
        List<Date> dates= new LinkedList<>();
        Consumer<Item> action = item -> {
            if(dates.size()==0 || !dates.get(dates.size()-1).equals(item.getStartDate())) dates.add(item.getStartDate());
        };
        forEachPerDate(action);
        return dates;
    }
}
