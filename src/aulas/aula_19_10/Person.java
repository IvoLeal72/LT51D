package aulas.aula_19_10;

public class Person {
    // Variáveis de classe
    public static final int MIN_AGE = 0;
    // Variáveis de instância
    public String name;
    public int age;

    // Métodos de instância
    public void incAge() {
        ++this.age;
    }
    public void writeOuput() {
        System.out.println("Nome:  " + this.name);
        System.out.println("Idade: " + this.age);
    }


    public String getName()     { return name;         }
    public int getAge()         { return age;          }

    public void set( String n ) { name = n;            }
    public void set( int a )    { if ( a > 0) age= a;  }
    public void set( String n, int a ){ set(n);set(a); }
    public String toString() {
        return getName()+ " - "+ getAge()+ " years";
    }
    public static void main(String[] args) {
        Person friend = new Person();
        friend.name = "Lara";
        friend.age = 18;
        friend.writeOuput();
    }
}
