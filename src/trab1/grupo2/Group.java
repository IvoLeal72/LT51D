package trab1.grupo2;

import testesAcademicos.teste12_11_2019.Register;
import testesAcademicos.teste12_11_2019.RegisterException;
import trab1.grupo1.Date;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class Group implements Register {
    private final List<Register> registers=new LinkedList<>();

    public boolean contains(int regId) {
        for(Register reg:registers){
            if(reg.contains(regId)) return true;
        }
        return false;
    }

    public Date getNextVaccinate(){
        Date res=null;
        for(Register reg:registers){
            Date temp=reg.getNextVaccinate();
            if(temp!=null){
                if(temp.compareTo(res)<0) res=temp;
            }
        }
        return res;
    }

    public void advanceVaccinateDate(int days) {
        for(Register reg:registers){
            reg.advanceVaccinateDate(days);
        }
    }

    public boolean isVaccinateInDay() throws RegisterException {
        for(Register reg:registers){
            if(!reg.isVaccinateInDay()) return false;
        }
        return true;
    }

    public Group append(Register reg){
        registers.add(reg);
        return this;
    }

    public List<Register> getRegisters(Predicate<Register> filter){
        List<Register> res=new LinkedList<>();
        for(Register reg:registers){
            if(filter.test(reg)) res.add(reg);
        }
        return res;
    }

    public String toString(){
        StringBuilder str=new StringBuilder();
        for(Register reg:registers){
            str.append(reg.toString()).append("\n");
        }
        return str.toString();
    }

    public static int getNullNextVaccinate(Group g){
        return g.getRegisters(register -> register.getNextVaccinate()==null).size();
    }

    public static List<Register> getRegisters(Group g){
        return g.getRegisters(register -> true);
    }
}
