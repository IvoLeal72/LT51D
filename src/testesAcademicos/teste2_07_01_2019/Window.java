package testesAcademicos.teste2_07_01_2019;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Window extends JFrame {
    public static final int WIDTH=500;
    public static final int HEIGHT=600;
    private final JTextField file1;
    private final JTextField file2;
    private final JTextArea result;

    public Window(){
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle("Intercalate");

        JPanel top=new JPanel(new BorderLayout());
        file1=newJTextField("filename1");
        file2=newJTextField("filename2");
        top.add(file1, BorderLayout.NORTH);
        top.add(file2, BorderLayout.SOUTH);

        getContentPane().add(top, BorderLayout.NORTH);

        result=newJTextArea("intercalated lines", 20);
        getContentPane().add(result, BorderLayout.CENTER);

        JButton button=new JButton("intercalate");
        button.addActionListener(this::buttonAction);
        getContentPane().add(button, BorderLayout.SOUTH);
    }

    public void buttonAction(ActionEvent actionEvent){
        StringBuilder str=new StringBuilder();
        try{
            Grupo2.intercalateLines(file1.getText(), file2.getText(), s -> str.append(s).append('\n'));
        }
        catch(IOException e){
            showMessage(e.getMessage());
        }
        result.setText(str.toString());
    }

    public static JTextField newJTextField(String title){
        JTextField tf=new JTextField();
        tf.setBorder(new TitledBorder(title));
        return tf;
    }

    public static JTextArea newJTextArea(String title, int lines){
        JTextArea ta = new JTextArea(lines, 15);
        ta.setBorder(new TitledBorder(title));
        return ta;
    }

    public static void showMessage(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }
}
