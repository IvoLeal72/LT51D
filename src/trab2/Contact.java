package trab2;

import java.util.*;
import java.util.Date;

/**
 * Contacto. Um contacto tem nome, data de nascimento e conjunto de números de telefone.
 */
public class Contact implements Comparable<Contact> {
    private final java.util.Date birthDate;
    private final String name;
    // Os número de telefones devem ser unicos e devem
    // ser obtidos pela ordem que foram adicionados.
    private Set< String > telephones = new LinkedHashSet< String >();

    public Contact( String n, java.util.Date d ) {
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
     * Retorna o número de anos do contacto.
     * @return
     */
    public int getAge() {
        Calendar today = Calendar.getInstance();
        Calendar bd = Calendar.getInstance();
        bd.setTime(birthDate);
        int age = today.get(Calendar.YEAR)-bd.get(Calendar.YEAR);
        if(today.get(Calendar.MONTH)>bd.get(Calendar.MONTH)) return age;
        if(today.get(Calendar.MONTH) == bd.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) > bd.get(Calendar.DAY_OF_MONTH)) return age;
        return age-1;
    }

    /**
     * Dois contactos são iguais se têm o nome e a data de nascimento iguais.
     * Nos nomes não se deve destinguir letras minusculas de maiusculas.
     * @param o contacto a comparar
     * @return true caso tenham o mesmo nome e data de nascimento.
     */
    @Override
    public boolean equals( Object o ) {
        // todo
        throw new UnsupportedOperationException("Contact::equals not implements");
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
        // todo
        throw new UnsupportedOperationException("Contact::compareTo not implements");
    }

    /**
     * Obter o hash code. O hash code de um contacto é a soma dos hash codes
     * dos membros (nome e data).
     * @return hash code
     */
    @Override
    public int hashCode() {
        // Ter em atenção que o método equals e o método hashCode têm que ser consistentes.
        // O método equals() é consistente com o método hashCode(), se e só se, dois objectos
        // iguais tiverem o mesmo valor de hash.
        //todo
        throw new UnsupportedOperationException("Contact::hashCode not implements");
    }
}

