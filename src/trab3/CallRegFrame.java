package trab3;

import trab2.Contact;
import trab2.NoteBookFrame;
import trab2.NoteBookFrame.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;


public class CallRegFrame extends JFrame {
    private static final WL wl=new WL();
    public static final Map<String, CallRegFrame> opened=new HashMap<>();
    private final JFileChooser fileChooser = new JFileChooser( );
    private final CallReg callReg;
    private final JTextArea listArea = new JTextArea( 15, 40 );

    public CallReg getCallReg() {
        return callReg;
    }

    protected static JMenu createJMenu(String name, ItensMenu[] itens ){
        JMenu menu = new JMenu( name );
        for (ItensMenu item : itens) {
            JMenuItem mi = new JMenuItem(item.getKey());
            mi.addActionListener(item.getValue());
            menu.add(mi);
        }
        return menu;
    }

    private static class WL extends WindowAdapter{
        static int n=0;

        public void windowOpened(WindowEvent arg0) {
            ++n;
        }
        public void windowClosed(WindowEvent arg0) {
            Object obj=arg0.getSource();
            if(obj instanceof CallRegFrame){
                try {
                    ((CallRegFrame) obj).callReg.autoSave();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                opened.remove(((CallRegFrame) obj).callReg.getNumber());
            }
            if(obj instanceof NoteBookFrame){
                if (((NoteBookFrame) obj).getMaster()!=null){
                    try {
                        ((NoteBookFrame) obj).getMaster().getCallReg().autoSave();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (--n <= 0) System.exit(0);
        }
    }

    public ItensMenu[] fileMenus = {
            new ItensMenu("open", CallRegFrame::new_CallRegFrame),
            new ItensMenu("open contact list", this::open_notebook),
            new ItensMenu("import", this::import_callReg),
            new ItensMenu("export", this::export),
            new ItensMenu("clear", this::clear),
            new ItensMenu("exit", this::exit)
    };

    public ItensMenu[] listMenus = {
            new ItensMenu("list all calls", this::listAll),
            new ItensMenu("list answered calls", this::listAnswered),
            new ItensMenu("list rejected calls", this::listRejected),
            new ItensMenu("list sent calls", this::listSent)
    };

    public CallRegFrame(String number) throws IOException, ClassNotFoundException {
        super(number);
        callReg = new CallReg(number);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        listArea.setBorder(new TitledBorder("list"));
        JScrollPane sp = new JScrollPane(listArea);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(sp, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createJMenu("File", fileMenus));
        menuBar.add(createJMenu("List", listMenus));
        setJMenuBar( menuBar );

        JPanel south=new JPanel();
        ((FlowLayout) south.getLayout()).setVgap(0);

        JButton button1=new JButton("Call");
        button1.addActionListener(this::makeCall_num);
        south.add(button1);

        JButton button2=new JButton("Call contact");
        button2.addActionListener(this::makeCall_name);
        south.add(button2);

        add(south, BorderLayout.SOUTH);
        pack();

        listAll(null);
    }

    public static void new_CallRegFrame(ActionEvent actionEvent){
        String number="";
        while(number.length()!=9) {
            number = JOptionPane.showInputDialog("Open Telephone", "number");
            if(number==null) return;
        }
        new_CallRegFrame_num(number);
    }

    public static void new_CallRegFrame_num(String number){
        if(opened.containsKey(number)) return;
        try {
            CallRegFrame frame = new CallRegFrame(number);
            frame.setVisible(true);
            frame.addWindowListener(wl);
            opened.put(number, frame);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void new_CallRegFrame(){
        new_CallRegFrame(null);
    }

    private void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    private void export(ActionEvent actionEvent) {
        fileChooser.setCurrentDirectory(new File("."));
        if ( JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(this) )
            try {
                callReg.save(fileChooser.getSelectedFile());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error file: " + e.getMessage());
            }
    }

    private void import_callReg(ActionEvent actionEvent) {
        fileChooser.setCurrentDirectory(new File("."));
        if ( JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(this) )
            try {
                callReg.load(fileChooser.getSelectedFile());
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Error file: " + e.getMessage());
            }
    }

    private void open_notebook(ActionEvent actionEvent) {
        NoteBookFrame nbf=new NoteBookFrame(callReg.getNumber(), this);
        nbf.setVisible(true);
        nbf.addWindowListener(wl);
    }

    private <E, V> void list(String title, Iterable<E> seq, Function<E, V> toList){
        ((TitledBorder)listArea.getBorder()).setTitle( title );
        listArea.setText( "" );
        if ( seq == null || !seq.iterator().hasNext() )
            listArea.append( "No calls \n");
        else for( E e : seq )
            listArea.append( toList.apply( e ) + "\n");
    }

    public void listSent(ActionEvent actionEvent) {
        list("Sent calls", callReg.getSentCalls(), callReg::toStringCallWithName);
    }

    public void listRejected(ActionEvent actionEvent) {
        list("Rejected calls", callReg.getRejectedCalls(), callReg::toStringCallWithName);
    }

    public void listAnswered(ActionEvent actionEvent) {
        list("Answered calls", callReg.getAnsweredCalls(), callReg::toStringCallWithName);
    }

    public void listAll(ActionEvent actionEvent) {
        list("Calls", callReg.getAllCalls(), callReg::toStringCallWithNameAndType);
    }

    public static boolean showConfirmDialogWithTimeout(Object params, String title, int timeout_ms) {
        final JOptionPane msg = new JOptionPane(params, JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
        final JDialog dlg = msg.createDialog(title);

        msg.setInitialSelectionValue(JOptionPane.NO_OPTION);
        dlg.setAlwaysOnTop(true);
        dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        final boolean[] closedByTime = {false};
        dlg.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                final Timer t = new Timer(timeout_ms, e1 -> {
                    closedByTime[0] =true;
                    dlg.setVisible(false);
                });
                t.start();
            }
        });
        dlg.setVisible(true);

        Object selectedValue = msg.getValue();
        return !selectedValue.equals(JOptionPane.NO_OPTION) && !closedByTime[0];
    }

    public boolean receiveCall(Time t, String number) {
        String name=getCallReg().getNameFromNum(number);
        boolean answered = showConfirmDialogWithTimeout(new JLabel((name!=null?name:number)+" calling. Answer?"), callReg.getNumber(), 10000);
        callReg.addReceivedCall(t, number, answered);
        if(answered) listAnswered(null);
        else listRejected(null);
        return answered;
    }

    public void makeCall(String number){
        new_CallRegFrame_num(number);
        CallRegFrame destination=opened.get(number);
        Time t=new Time();
        boolean accepted= destination.receiveCall(t, callReg.getNumber());
        if(!accepted){
            callReg.addSentCall(t, number, new Duration());
            listSent(null);
        }
        else {
            new LiveCall(callReg.getNumber(), number, destination.getCallReg().getNameFromNum(callReg.getNumber()), callReg.getNameFromNum(number), this, t);
        }
    }

    public void makeCall_num(ActionEvent actionEvent){
        String number="";
        while(number.length()!=9) {
            number = JOptionPane.showInputDialog("Call", "number");
            if(number==null) return;
        }
        makeCall(number);
    }

    public void makeCall_name(ActionEvent actionEvent){
        Iterable<Contact> iterable=getCallReg().getNoteBook().getAllContacts();
        if(!iterable.iterator().hasNext()) return;
        ArrayList<String> options=new ArrayList<>();
        iterable.forEach(contact -> {
            if(contact.getTelephones().size()>0) options.add(contact.getName());
        });
        String name=(String)JOptionPane.showInputDialog(this, "Contacts:", "Call",
                                    JOptionPane.QUESTION_MESSAGE, null, options.toArray(), options.get(0));
        Collection<String> numList= getCallReg().getNoteBook().getContact(name).getTelephones();
        String number=numList.iterator().next();
        if(numList.size()>1){
            number=(String)JOptionPane.showInputDialog(this, "Numbers:", "Call "+name,
                                JOptionPane.QUESTION_MESSAGE, null, numList.toArray(), numList.iterator().next());
        }
        makeCall(number);
    }

    public void clear(ActionEvent actionEvent){
        callReg.clear();
        listAll(null);
    }

    public static void main(String[] args) {
        new_CallRegFrame();
    }
}
