package testesAcademicos.test2_29_01_2021;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.*;

public class ExercFrame extends JFrame {
    public static final int WIDTH=250;
    public static final int HEIGHT=150;
    private final JTextField pathname;
    private final JTextField information;

    private  JTextField newJTextField( String title ) {
        JTextField t = new javax.swing.JTextField( 30); t.setBorder( new TitledBorder( title )); return t;
    }

    public ExercFrame( ) {
        super();
        setSize(WIDTH, HEIGHT);
        setResizable( false ); // Nao é possivel alterar a dimensão
        setTitle("File info");

        pathname=newJTextField("pathname");
        information=newJTextField("information");

        getContentPane().add(pathname, BorderLayout.CENTER);
        getContentPane().add(information, BorderLayout.SOUTH);


        JPanel top=new JPanel();

        JButton clear=new JButton("clear");
        clear.addActionListener(this::clearAction);
        top.add(clear);

        JButton info=new JButton("info");
        info.addActionListener(this::infoAction);
        top.add(info);

        getContentPane().add(top, BorderLayout.NORTH);

    }

    public void clearAction(ActionEvent actionEvent){
        pathname.setText("");
        information.setText("");
    }

    public void infoAction(ActionEvent actionEvent){
        File f=new File(pathname.getText());
        if(!f.exists()) information.setText("File not exist");
        else if(f.isDirectory()) information.setText("directory - "+f.getAbsolutePath());
        else if(f.isFile()) information.setText(f.getAbsolutePath()+" - "+f.length()/1024+" KB");
    }

    public static void main(String[] args) {
        ExercFrame test=new ExercFrame();
        test.setVisible(true);
    }
}
