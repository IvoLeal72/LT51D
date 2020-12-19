package testesAcademicos.teste12_11_2019;

public class Pet extends OneRegister{
    private final String name;

    public Pet(String name, String specie, int regId){
        super(specie, regId);
        this.name=name;
    }

    public final String getName(){
        return name;
    }

    public String toString(){
        return "\""+name+"\" - "+super.toString();
    }
}
