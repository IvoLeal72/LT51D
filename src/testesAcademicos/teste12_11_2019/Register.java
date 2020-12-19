package testesAcademicos.teste12_11_2019;


import trab1.grupo1.Date;

public interface Register {
    boolean contains(int regId);
    Date getNextVaccinate();
    void advanceVaccinateDate(int days);
    boolean isVaccinateInDay() throws RegisterException;
}
