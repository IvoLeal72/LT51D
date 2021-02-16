package trab3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Instant;

public class LiveCall{
    public volatile boolean ended=false;
    public Instant start;
    private final LiveCallFrame lcf1, lcf2;

    private class LiveCallFrame extends JFrame {
        public LiveCallFrame(String num1, String num2) {
            super(num1);
            setSize(300, 100);
            JTextField jTextField = new JTextField();
            jTextField.setText("In call with " + num2);
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            add(jTextField, BorderLayout.CENTER);

            JButton button = new JButton("End");
            button.addActionListener(event -> ended = true);
            add(button, BorderLayout.SOUTH);
        }
    }

    public LiveCall(String num1, String num2){
        lcf1=new LiveCallFrame(num1, num2);
        lcf2=new LiveCallFrame(num2, num1);
        lcf1.setVisible(true);
        lcf2.setVisible(true);
        start=Instant.now();
    }

    public Duration waitForCallEnd(){
        while (!ended) Thread.onSpinWait();
        lcf1.dispose();
        lcf2.dispose();
        return new Duration(java.time.Duration.between(start, Instant.now()).toSeconds());
    }
}
