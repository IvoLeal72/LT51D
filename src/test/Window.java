package test;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public static final int WIDTH=500;
    public static final int HEIGHT=70;
    private final JTextField texto;

    public Window(String txt){
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        texto=new JTextField();

        getContentPane().add(texto, BorderLayout.CENTER);
        texto.setText(txt);
    }

}
