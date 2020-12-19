package test;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JFrame implements ActionListener{
    public static final int WIDTH=300;
    public static final int HEIGHT=300;
    private final JTextField a, b, c, d, x1, x2;

    public Test() {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle("FormRes");
        a=new JTextField(6);
        b=new JTextField(6);
        c=new JTextField(6);
        d=new JTextField(6);
        x1=new JTextField(8);
        x2=new JTextField(8);


        a.setBorder(new TitledBorder("a"));
        b.setBorder(new TitledBorder("b"));
        c.setBorder(new TitledBorder("c"));
        JPanel north=new JPanel();
        north.setBorder(new TitledBorder("ax^2 + bx +c = 0"));
        north.add(a);
        north.add(b);
        north.add(c);
        getContentPane().add(north, BorderLayout.NORTH);

        d.setBorder(new TitledBorder("b^2 -4ac"));
        d.setEditable(false);
        getContentPane().add(d, BorderLayout.WEST);

        x1.setBorder(new TitledBorder("x1"));
        x1.setEditable(false);
        x2.setBorder(new TitledBorder("x2"));
        x2.setEditable(false);

        JPanel east= new JPanel(new BorderLayout());
        east.add(x1, BorderLayout.WEST);
        east.add(x2, BorderLayout.EAST);

        getContentPane().add(east, BorderLayout.EAST);

        JButton button = new JButton("calculate");
        button.addActionListener(this);
        getContentPane().add(button, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("calculate")){
            double a=Double.parseDouble(this.a.getText());
            double b=Double.parseDouble(this.b.getText());
            double c=Double.parseDouble(this.c.getText());

            double d=b*b - 4*a*c;

            this.d.setText(""+d);

            if(d<0){
                this.x1.setText("Imp");
                this.x2.setText("Imp");
            }
            else{
                double x1, x2;
                x1=(-b-Math.sqrt(d))/2*a;
                x2=(-b+Math.sqrt(d))/2*a;
                this.x1.setText(""+x1);
                this.x2.setText(""+x2);
            }
        }
    }

    public static void main(String[] args) {
        Test window=new Test();
        window.setVisible(true);
    }
}
