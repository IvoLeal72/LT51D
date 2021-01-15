package trab2;

import java.io.*;
import java.util.*;

import jdk.jshell.execution.Util;
import trab2.Date;

/**
 * Agenda.
 */
public class NoteBook {
    // Contentor associativo, associa o nome ao contacto.
    // A chave é o nome do contacto.
    // Não podem existir dois contactos com o mesmo nome.
    private Map< String, Contact> contacts = new TreeMap<>();
    // Contentor associativo de número de telefones.
    // A chave é o número de telefone o valor associado são os contactos que têm o mesmo número de telefone.
    private Map< String, SortedSet< Contact > > telephones = new TreeMap<>();
    // Contentor associativo ordenado por datas de nascimento de contactos cujo
    // aniversário é no mesmo dia/mes.
    // A chave data de nascimento o valor associado são os contactos que fazem anos no mesmo número de telefone.
    private SortedMap<Date, SortedSet<Contact> > birthdays = new TreeMap<>();

    /**
     * Adiciona um contacto ao contentor associativo de contactos contact.
     * - Caso não exista um contacto com o mesmo nome adiciona-o.
     * - Caso já exista um contacto com o mesmo nome e data de nascimento,
     *   adiciona os números de telefone ao contacto já existente.
     * - Caso já exista um contacto com o mesmo nome e data de nascimento
     *   diferente não adiciona.
     * Actualiza o contentor de aniversários com os telefones deste contacto.
     * Actualiza o contentor de telefones com os telefones deste contacto.
     *
     * @param contact contacto a adicionar
     * @return true caso tenha adicionado ou atualizado as estruturas.
     */
    public boolean add( Contact contact ) {
        boolean updated=Utils.actualize(contacts, contact::getName, ()->contact, (contact1 -> contact1.join(contact)));
        if(updated){
            contact.getTelephones().forEach((number)-> {
                Utils.actualize(telephones, () -> number, ()->{
                    SortedSet<Contact> list=new TreeSet<>();
                    list.add(contact);
                    return list;
                }, (contacts1 -> contacts1.add(contact)));
            });

            Utils.actualize(birthdays, ()-> new Date(contact.getBirthDate().getDay(), contact.getBirthDate().getMonth(), 0), ()->{
                    SortedSet<Contact> list=new TreeSet<>();
                    list.add(contact);
                    return list;
                }, (contacts1 -> contacts1.add(contact))
            );
        }
        return updated;
    }

    /**
     * Adicionar todos os contactos doutra agenda.
     * Não adiciona caso já exista um contacto com o mesmo nome e data de nascimento, neste caso
     * adiciona os numeros de telefone ao contacto já existente.
     * @param nb agenda
     */
    public void add( NoteBook nb ) {
        nb.contacts.forEach((k, v)->this.add(v));
    }

    /**
     * Remove um contacto com determinado nome dos contactos,
     * dos telefones e das data de nascimento.
     * @param name nome do contacto
     */
    public boolean remove( String name ) {
        Contact toDelete=contacts.remove(name);
        if(toDelete==null) return false;
        toDelete.getTelephones().forEach((number)->{
            SortedSet<Contact> list=telephones.get(number);
            if(list!=null){
                list.remove(toDelete);
            }
        });
        SortedSet<Contact> list=birthdays.get(new Date(toDelete.getBirthDate().getDay(), toDelete.getBirthDate().getMonth(), 0));
        if(list!=null){
            list.remove(toDelete);
        }
        return true;
    }

    /**
     * Remove todos os contactos.
     */
    public void clear(  ) {
        contacts.clear();
        telephones.clear();
        birthdays.clear();
    }

    /*********************************************************************
     * Métodos de consulta
     */
    /**
     * Obter o contacto dado o nome.
     * @param name nome do contacto
     * @return o contacto ou null caso não exista.
     */
    public Contact getContact( String name ) {
        return contacts.get(name);
    }

    /**
     * Obter os contactos.
     * @return uma coleção de contactos inalterável.
     */
    public Iterable<Contact> getAllContacts() {
        return contacts.values();
    }

    /**
     * Obter os contactos que fazem anos num determinado dia/mes.
     * @param day  dia
     * @param month  mês
     * @return sequencia de contactos ordenada por data.
     */
    public Iterable<Contact> getBirthdays(int day, int month ) {
        return birthdays.get( new Date(day, month, 0) );
    }

    /**
     * Obter os contactos que têm determinado numero de telefone.
     * @param phone número de telefone
     * @return sequencia de contactos.
     */
    public Iterable<Contact> getContactsOf( String phone ) {
        return telephones.get(phone);
   }

    /**
     * Obter os contactos que fazem anos num determinado mes.
    * @param month  mês
     * @return sequencia de contactos ordenada por data.
     */
    public Iterable<Contact> getBirthdays( int month ) {
        ArrayList<Contact> list=new ArrayList<>();
        Utils.foreachV(birthdays, (contact -> {
            if(contact.getBirthDate().getMonth()==month){
                list.add(contact);
            }
        }));
        return list;
    }


    /*********************************************************************
     * Leitura e escrita em ficheiro
     */
    /**
     * Lê os contactos do ficheiro de texto para a agenda.
     * @param file ficheiro de leitura.
     * @throws IOException
     */
    public void read( File file ) throws IOException {
        try(BufferedReader rd=new BufferedReader(new FileReader(file))){
            String line=rd.readLine();
            while(line!=null){
                Contact contact;
                Date birthDate=new Date(line.substring(0, 10));
                int idx=line.indexOf('[');
                if(idx<0){
                    contact=new Contact(line.substring(11).trim(), birthDate);
                }
                else{
                    contact=new Contact(line.substring(11, idx-1).trim(), birthDate);
                    Collection<String> phonesList=new ArrayList<>();
                    int secIdx=line.indexOf(' ', idx);
                    while(secIdx>0){
                        phonesList.add(line.substring(idx+1, secIdx-1));
                        idx=secIdx;
                        secIdx=line.indexOf(' ', idx+1);
                    }
                    secIdx=line.indexOf(']');
                    phonesList.add(line.substring(idx+1, secIdx));
                    contact.addTelephones(phonesList);
                }
                this.add(contact);
                line=rd.readLine();
            }
        }
    }

    /**
     * Escreve todos os contactos da agenda num ficheiro de texto.
     * @param file ficheiro de escrita.
     * @throws IOException
     */
    public void write( File file ) throws  IOException {
        try(PrintWriter out=new PrintWriter(new FileWriter(file))){
            contacts.forEach((k,v)->out.println(v));
        }
    }

    /*********************************************************************
     * Métodos para obter os contactos com maior numero de telefones, os
     * telefones com maior número de contactos ou as datas que existem mais
     * mais aniversários
     */

    public Collection<String> mostContactsByPhone(){
        return Utils.greater(telephones, (s1, s2)->s1.size()-s2.size());
    }

    public Collection<Date> mostBirthdays(){
        return Utils.greater(birthdays, ((s1,s2)->s1.size()-s2.size()));
    }

    public Collection<Contact> mostNumbers(){
        Collection<String> list= Utils.greater(contacts, (c1,c2)->c1.getTelephones().size()-c2.getTelephones().size());

        Collection<Contact> finalList = new ArrayList<>();
        list.forEach((name)->finalList.add(contacts.get(name)));
        return finalList;
    }
}
