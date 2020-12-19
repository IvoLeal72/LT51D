package aulas.aula_14_10;

public class Example3 {
    public static void main(String[] args){
        Student s1=new Student("Ivo Leal", 46493);
        System.out.println(s1.nome +" " + s1.numero);
        Student s2=new Student();
        System.out.println(s2.nome +" " + s2.numero);
    }
}
