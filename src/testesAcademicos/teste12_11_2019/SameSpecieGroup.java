package testesAcademicos.teste12_11_2019;

import trab1.grupo1.Date;

public class SameSpecieGroup implements Register{
    private final OneRegister reg;
    private final String collectiveName;
    private final int dimension;

    public SameSpecieGroup(String collectiveName, int dimension, OneRegister reg) {
        this.reg = reg;
        this.collectiveName = collectiveName;
        this.dimension = dimension;
    }

    public SameSpecieGroup(String collectiveName, int dimension, String specie, int firstRegisterId) {
        this(collectiveName, dimension, new OneRegister(specie, firstRegisterId));
    }

    public boolean contains(int regId) {
        return regId>=reg.registerId && regId<=(reg.registerId+dimension-1);
    }

    public Date getNextVaccinate(){
        return reg.getNextVaccinate();
    }

    public void advanceVaccinateDate(int days){
        reg.advanceVaccinateDate(days);
    }

    public boolean isVaccinateInDay() throws RegisterException {
        return reg.isVaccinateInDay();
    }

    public String toString(){
        return collectiveName+" ("+dimension+") - "+reg.toString()+" a "+(reg.registerId+dimension-1);
    }
}
