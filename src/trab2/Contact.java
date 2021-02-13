package trab2;

import java.io.Serializable;
import java.util.*;

/**
 * Contacto. Um contacto tem nome, data de nascimento e conjunto de números de telefone.
 */
public class Contact implements Comparable<Contact>, Serializable {
    private final Date birthDate;
    private final String name;
    // Os número de telefones devem ser unicos e devem
    // ser obtidos pela ordem que foram adicionados.
    private final Set< String > telephones = new LinkedHashSet<>();

    public Contact( String n, Date d ) {
        this.name = n;
        this.birthDate = d;
    }

    public String getName()    { return name;      }

    public Date getBirthDate() { return birthDate; }

    public Collection<String> getTelephones() {
        return Collections.unmodifiableCollection( telephones );
    }

    public void addTelephones( Collection<String> telephones ) {
        this.telephones.addAll( telephones );
    }

    public boolean join( Contact c ) {
        if ( !this.getBirthDate().equals( c.getBirthDate() ) )
            return false;
        addTelephones(c.getTelephones());
        return true;
    }

    @Override
    public String toString() {
        if ( telephones.isEmpty() )
            return String.format("%s %-40s", birthDate.toString(), name );
        return String.format("%s %-40s %s", birthDate.toString(), name, telephones );
    }

    /**
     * Retorna o número de anos do contacto
     */
    public int getAge() {
        Date today = new Date();
        int age= today.getYear()-birthDate.getYear();
        if(today.getMonth()< birthDate.getMonth() || (today.getMonth()==birthDate.getMonth() && today.getDay()< birthDate.getDay())) age--;
        return age;
    }

    /**
     * Dois contactos são iguais se têm o nome e a data de nascimento iguais.
     * Nos nomes não se deve destinguir letras minusculas de maiusculas.
     * @param o contacto a comparar
     * @return true caso tenham o mesmo nome e data de nascimento.
     */
    @Override
    public boolean equals( Object o ) {
        if(o==null) return false;
        if(o instanceof Contact) {
            Contact other = (Contact) o;
            return name.equalsIgnoreCase((other).getName()) && birthDate.equals((other).getBirthDate());
        }
        return false;
    }

    /**
     * Compara dois contactos.
     * A comparação é por data de nascimento e para a mesma data de nascimento
     * por nome sem distinguir letras minusculas de maiusculas.
     * @param c contacto a comparar
     * @return  0 se this == c; >0 se this > c;  <0 this < c.
     */
    @Override
    public int compareTo( Contact c ) {
        int cmp= birthDate.compareTo(c.getBirthDate());
        if(cmp==0) cmp=name.compareToIgnoreCase(c.getName());
        return cmp;
    }

    /**
     * Obter o hash code. O hash code de um contacto é a soma dos hash codes
     * dos membros (nome e data).
     * @return hash code
     */
    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode()+ birthDate.hashCode();
    }
}

