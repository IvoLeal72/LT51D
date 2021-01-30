package trab2.grupo1;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class FirstWindow extends JFrame{
    public static final int WIDTH=500;
    public static final int HEIGHT=600;
    private final JTextArea main;
    private final JTextField pathname;
    private final JTextField turma;
    private final JTextField info;

    public FirstWindow(){
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle("Alunos");

        JPanel north=new JPanel(new BorderLayout());
        pathname=new JTextField();
        pathname.setBorder(new TitledBorder("pathname"));
        north.add(pathname, BorderLayout.CENTER);
        turma = new JTextField(5);
        turma.setBorder(new TitledBorder("turma"));
        north.add(turma, BorderLayout.EAST);

        getContentPane().add(north, BorderLayout.NORTH);

        main=new JTextArea();
        main.setBorder(new TitledBorder("Lista"));
        main.setEditable(false);

        getContentPane().add(main, BorderLayout.CENTER);

        JPanel south=new JPanel(new BorderLayout());
        info=new JTextField();
        info.setBorder(new TitledBorder("Informação"));
        info.setEditable(false);
        south.add(info, BorderLayout.CENTER);
        JButton button= new JButton("list");
        button.addActionListener(this::actionPerformed);
        south.add(button, BorderLayout.EAST);

        getContentPane().add(south, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("list")){
            String teamId= turma.getText();
            main.setBorder(new TitledBorder(teamId));
            StringBuilder str=new StringBuilder();
            int subs=0;
            try {
                subs=Grupo1.copyTeam(pathname.getText(), teamId, (num, name)-> str.append(num).append(" ").append(name).append("\n"));
                info.setText("A turma "+teamId+" tem "+subs+" alunos");
            } catch (IOException ioException) {
                info.setText(ioException.getMessage());
            }
            main.setText(str.toString());
        }
    }
}
