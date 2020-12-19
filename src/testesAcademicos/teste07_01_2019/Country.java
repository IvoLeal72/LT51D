package testesAcademicos.teste07_01_2019;

public class Country extends State{
    private final int area;
    public final boolean sovereign;

    public Country(String nm, int a, boolean sovereign){
        super(nm);
        area=a;
        this.sovereign=sovereign;
    }

    public int getArea(){
        return area;
    }

    public boolean isSovereign(){
        return sovereign;
    }

    public String getDescription(String prefix){
        return super.getDescription(prefix) + "Estado "+ (sovereign? "soberano":"autónomo") + " ("+area+" km²)";
    }
}
