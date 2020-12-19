package testesAcademicos.teste12_11_2019;

import trab1.grupo1.Date;

public class OneRegister implements Register{
    private final String specie;
    public final int registerId;
    private Date nextVaccinate;

    public OneRegister(String specie, int regId){
        this.specie=specie;
        registerId=regId;
    }

    public boolean contains(int regId) {
        return registerId==regId;
    }

    public Date getNextVaccinate() {
        return nextVaccinate;
    }

    public boolean isVaccinateInDay() throws RegisterException {
        if(nextVaccinate==null) throw new RegisterException();
        return nextVaccinate.compareTo(new Date()) > 0;
    }

    public void advanceVaccinateDate(int days){
        if(nextVaccinate==null) nextVaccinate=new Date();
        nextVaccinate=nextVaccinate.nextDate(days);
    }

    public String toString(){
        return specie+" registado com o número "+registerId;
    }
}
