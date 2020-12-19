package aulas.aula_20_10;

public class Person {
    public final static int MIN_AGE=0;
    public final String name;
    private int age;

    public Person(String nm, int age){
        this.name=nm;
        set(age);
    }

    public Person(String nm){
        this.name=nm;
    }

    public void print(){
        System.out.println(name+":"+age);
    }
    public void set( int age ){
        if(age>=MIN_AGE) this.age=age;
    }

    public int getAge(){
        return age;
    }
}
