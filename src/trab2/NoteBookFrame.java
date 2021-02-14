package trab2;

import trab2.noteBook.gui.ContactDialog;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

/**
 * Interface gráfica para visualizar e modificar a agenda.
 */
public class NoteBookFrame extends JFrame {
    private final JFileChooser fileChooser = new JFileChooser( );
    private final NoteBook noteBook = new NoteBook();
    private final ContactDialog contactDialog = new ContactDialog(this, this::addContact );
    private final JTextArea listArea = new JTextArea( 15, 40 );

    public static class ItensMenu extends
        AbstractMap.SimpleEntry<String, ActionListener> {
        public ItensMenu(String s, ActionListener l){ super(s,l);}
    }
    public ItensMenu[] fileMenus = {
            new ItensMenu("load", this::load),
            new ItensMenu("save", this::save),
            new ItensMenu("exit", this::exit)};

    public ItensMenu[] editMenus = {
            new ItensMenu("add contact", this::addContact),
            new ItensMenu("add phone", this::addPhone),
            new ItensMenu("delete contact", this::removeContact)
    };

    public ItensMenu[] listMenus = {
            new ItensMenu("all contacts", this::listAll),
            new ItensMenu("names with this phone", this::listNamesWithThisPhone),
            new ItensMenu("today birthdays", this::listTodayBirthdays),
            new ItensMenu("this month birthdays", this::listMonthBirthdays)
    };

    public ItensMenu[] withMoreMenus = {
            new ItensMenu("contacts with more phones", this::listMoreNumbers),
            new ItensMenu("phones with more contacts", this::listPhonesWithMoreContacts),
            new ItensMenu("dates with more birthdays", this::listDatesWithMoreBirthdays)
    };

    public NoteBookFrame(){
        super("NoteBook");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Adicionar a TextArea para a listagem, com barra de scroll
        listArea.setBorder(new TitledBorder("list"));
        JScrollPane sp = new JScrollPane(listArea);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(sp);

        // Adicionar o butão para adicionar contacto
        JPanel buttons = new JPanel();
        ((FlowLayout) buttons.getLayout()).setAlignment(FlowLayout.RIGHT);
        JButton b = new JButton("add contact");
        b.addActionListener(this::addContact);
        buttons.add(b);
        b = new JButton("add phone");
        b.addActionListener(this::addPhone);
        buttons.add(b);
        add(buttons, BorderLayout.SOUTH);

        // Adicionar os menus
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createJMenu("File", fileMenus));
        menuBar.add(createJMenu("Edit", editMenus));
        menuBar.add(createJMenu("List", listMenus));
        menuBar.add(createJMenu("With more", withMoreMenus));
        setJMenuBar( menuBar );
        pack();
    }

    /**
     * Instância um menu e adiciona os itens descritos no array de itens
     * @param name nome do menu
     * @param itens array contendo a descrição dos itens (nome e ação a efetuar).
     * @return o menu instanciado
     */
    protected static JMenu createJMenu( String name, ItensMenu[] itens ){
        JMenu menu = new JMenu( name );
        for (ItensMenu item : itens) {
            JMenuItem mi = new JMenuItem(item.getKey());
            mi.addActionListener(item.getValue());
            menu.add(mi);
        }
        return menu;
    }

    /**
     * Método chamado quando é premido o botão "add contact".
     * Coloca visível uma janela de dialogo correspondente à inserção dos dados
     * do contacto, quando é premido o botão submit é chamada o método apply
     * do Consumer passado por parâmetro no construtor.
     * @param actionEvent evento do action listener.
     */
    private void addContact(ActionEvent actionEvent) {
        contactDialog.setValue( null );
        contactDialog.setVisible(true);
    }

    /**
     * Método chamado quando é premido o botão "submit" da janela de diálogo
     * para introdução dos dados do contacto.
     * Adiciona o contacto na agenda e lista os contactos.
     * @param c contacto a adicionar
     */
    private void addContact(Contact c) {
        if (noteBook.add( c ) )
            list("Contact List", noteBook.getAllContacts(), Contact::toString );
    }
    /**
     * Método chamado quando é premido o botão "add phone".
     * Obtém o nome do contacto e caso exista um contacto com o nome, atualiza e
     * coloca visível uma janela de dialogo correspondente à inserção dos dados.
     * @param actionEvent evento do action listener.
     */
    private void addPhone(ActionEvent actionEvent) {
        String name = JOptionPane.showInputDialog(this, "Contact Name", "Add phone", JOptionPane.QUESTION_MESSAGE);
        if ( name != null ) {
            Contact c = noteBook.getContact( name );
            if ( c != null ) {
                contactDialog.setValue(c);
                contactDialog.setVisible(true);
            }
            else
                JOptionPane.showMessageDialog(this, "Contact not exist", "Add phone",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Remove da agenda o contacto com .
     * Obtém o nome do contacto a remover e remove-o da agenda.
     * @param actionEvent evento do action listener.
     */
    private void removeContact(ActionEvent actionEvent) {
        String name = JOptionPane.showInputDialog(this, "Contact Name", "Delete", JOptionPane.QUESTION_MESSAGE);
        if ( name != null ) {
            if ( !noteBook.remove( name ) ) {
                JOptionPane.showMessageDialog(this, "Contact not exist", "Delete",JOptionPane.ERROR_MESSAGE);
            }
            list("Contact List", noteBook.getAllContacts(), Contact::toString );
        }
    }

    /**
     * Limpa a area de texto e lista uma sequencia de contactos, um por linha.
     * @param title - titulo a colocar na cercadura da area de texto
     * @param seq sequência de Elementos.
     * @param toList - Função para obter o valor a listar
     */
    private <E, V> void list(String title, Iterable<E> seq, Function<E, V> toList){
       ((TitledBorder)listArea.getBorder()).setTitle( title );
       listArea.setText( "" );
       if ( seq == null )
           listArea.append( "Not exist contacts \n");
       else for( E e : seq )
           listArea.append( toList.apply( e ) + "\n");
    }

    /***************************************************
     *  Métodos associados aos itens do menu "File"
     *
     ***************************************************/
    private void exit( ActionEvent actionEvent ) {
        int res = JOptionPane.showConfirmDialog(this, "Save notebook", "save", JOptionPane.YES_NO_CANCEL_OPTION);
        if ( res != JOptionPane.CANCEL_OPTION ) {
            if ( res == JOptionPane.YES_OPTION )
                save(actionEvent);
            System.exit(0);
        }
    }

    private void save(ActionEvent actionEvent) {
        fileChooser.setCurrentDirectory(new File("."));
        if ( JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(this) )
            try {
                noteBook.write(fileChooser.getSelectedFile());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error file: " + e.getMessage());
            }
    }

    private void load(ActionEvent actionEvent) {
        fileChooser.setCurrentDirectory(new File("."));
        if ( JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(this) )
            try {
                noteBook.read(fileChooser.getSelectedFile());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error file: " + e.getMessage());
            }
        list("Contact List", noteBook.getAllContacts(), Contact::toString );
    }

    /**
     * Método chamado quando é selecionado o item "all contacts".
     * Lista todos os contactos.
     */
    private void listAll(ActionEvent actionEvent) {
        list("Contact List", noteBook.getAllContacts(), Contact::toString );
    }

    /**
     * Método chamado quando é selecionado o item "month birthdays".
     * Coloca visível uma janela de dialogo para a inserção do mês.
     * Após ter a idade lista os contactos com a idade pretendida.
     */
    private void listMonthBirthdays( ActionEvent actionEvent ) {
        String month=JOptionPane.showInputDialog(this, "Search By Birth Month", "Month", JOptionPane.QUESTION_MESSAGE);
        try{
            int mon=Integer.parseInt(month);
            list("Contacts born in month "+mon, noteBook.getBirthdays(mon),  (contact -> contact.getName()+" "+contact.getAge()));
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Wrong Month Format");
        }
     }

    /**
     * Método chamado quando é selecionado o item "names with this phone".
     * Coloca visível uma janela de dialogo para a inserção do número de telefone.
     * Após ter o nome lista os nomes que contêm este número de telefone.
     */
    private void listNamesWithThisPhone(ActionEvent actionEvent) {
        String number=JOptionPane.showInputDialog(this, "Search By Phone", "Phone", JOptionPane.QUESTION_MESSAGE);
        list("Contacts with phone "+number, noteBook.getContactsOf(number), Contact::getName);
    }

    /**
     * Método chamado quando é selecionado o item "today birthdays".
     */
    private void listTodayBirthdays(ActionEvent actionEvent) {
        Date today=new Date();
        list("Contacts born in "+today.getDay()+"/"+today.getMonth(), noteBook.getBirthdays(today.getDay(), today.getMonth()), (contact -> contact.getName()+" "+contact.getAge()));
    }

    /***************************************************
     *  Métodos associados aos itens do menu "With more"
     *
     ***************************************************/

    private void listMoreNumbers(ActionEvent actionEvent) {
        list("Contacts with most phone numbers", noteBook.mostNumbers(), (str)->str);
    }

    private void listPhonesWithMoreContacts(ActionEvent actionEvent) {
        list("Numbers with more contacts", noteBook.mostContactsByPhone(), (str)->str);
    }

    private void listDatesWithMoreBirthdays(ActionEvent actionEvent) {
        list("Dates with more birthdays", noteBook.mostBirthdays(), Date::toString);
    }

    public static void main(String[] args) {
        new NoteBookFrame().setVisible( true );
    }
}
