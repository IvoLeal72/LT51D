package trab1.grupo1;

import java.util.Arrays;
import java.util.Calendar;

public class Date implements Comparable<Date>{
    private static final String FORMAT="%02d-%02d-%04d";
    private final int d, m, y;

    public Date(int day, int month, int year){
        if(isValid(day, month, year)) {
            this.d = day;
            this.m = month;
            this.y = year;
        }
        else{
            Calendar now= Calendar.getInstance();
            this.d=now.get(Calendar.DAY_OF_MONTH);
            this.m=now.get(Calendar.MONTH)+1;
            this.y=now.get(Calendar.YEAR);
        }
    } //O que fazer quando parametros invalidos?

    public Date(String str){
        int firstIndex, secondIndex;
        firstIndex=str.indexOf('-');
        secondIndex=str.indexOf('-', firstIndex+1);

        String strd=str.substring(0, firstIndex);
        String strm=str.substring(firstIndex+1, secondIndex);
        String stry=str.substring(secondIndex+1);

        int day=Integer.parseInt(strd);
        int month=Integer.parseInt(strm);
        int year=Integer.parseInt(stry);

        if(isValid(day, month, year)) {
            this.d = day;
            this.m = month;
            this.y = year;
        }
        else{
            Calendar now= Calendar.getInstance();
            this.d=now.get(Calendar.DAY_OF_MONTH);
            this.m=now.get(Calendar.MONTH)+1;
            this.y=now.get(Calendar.YEAR);
        }
    }

    public Date(){
        Calendar now= Calendar.getInstance();
        this.d=now.get(Calendar.DAY_OF_MONTH);
        this.m=now.get(Calendar.MONTH)+1;
        this.y=now.get(Calendar.YEAR);
    }

    public String toString(){
        return String.format(FORMAT, this.d, this.m, this.y);
    }

    public int getDay() {
        return d;
    }

    public int getMonth() {
        return m;
    }

    public int getYear() {
        return y;
    }

    public boolean equals(Date other){
        if(this==other) return true;
        if(other==null) return false;
        return this.d==other.d && this.m==other.m && this.y==other.y;
    }

    public boolean equals(Object other){
        if(other instanceof Date){
            return this.equals((Date)other);
        }
        return false;
    }

    public int compareTo(Date other){
        if(other==null) return -1;
        int x=this.y-other.y;
        if(x!=0) return x;
        x=this.m-other.m;
        if(x!=0) return x;
        return this.d-other.d;
    }

    public Date nextDate(int n){
        if(n<0) return this;
        int day=this.d + n;
        int month=this.m;
        int year=this.y;
        while(day>daysInMonth(month, year)){
            day-=daysInMonth(month, year);
            month++;
            if(month>12){
                month=1;
                year++;
            }
        }
        return new Date(day, month, year);
    }

    public static int getDays(Date d1, Date d2){
        int aux=d1.compareTo(d2);
        if(aux==0) return 0;

        if(aux>0){
            Date temp=d1;
            d1=d2;
            d2=temp;
        }

        int day=d1.d;
        int month=d1.m;
        int year=d1.y;

        aux=0;

        while(year<d2.y){
            if(month<=2) aux+=(isLeapYear(year)?366:365);
            else aux+=(isLeapYear(year+1)?366:365);
            year++;
        }

        while(month!=d2.m){
            if(month<d2.m){
                aux+=daysInMonth(month, year);
                month++;
            }
            else{
                month--;
                aux-=daysInMonth(month, year);
            }
        }

        aux+=d2.d-day;
        return aux;
    }

    public static Date today(){
        return new Date();
    }

    private static boolean isLeapYear(int year){
        return year%400==0 || (year%4==0 && year%100!=0);
    }

    private static int daysInMonth(int month, int year){
        return switch (month) {
            case 2 -> isLeapYear(year) ? 29 : 28;
            case 4, 6, 9, 11 -> 30;
            default -> 31;
        };
    }
    
    private static boolean isValid(int day, int month, int year){
        if(year<0 || month<1 || month>12 || day<1) return false;
        return day <= daysInMonth(month, year);
    }

    public static Date[] lessDates(Date ... dates){
        Date today=Date.today();
        int n=0;
        for(int i=0; i<dates.length; i++){
            n=i;
            for(int j=i+1; j< dates.length; j++){
                if(dates[j].compareTo(dates[i])<0){
                    Date temp=dates[j];
                    dates[j]=dates[i];
                    dates[i]=temp;
                }
            }
            if(dates[i].compareTo(today)>0) break;
        }
        return Arrays.copyOf(dates, n);
    }
}
