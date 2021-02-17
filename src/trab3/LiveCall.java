package trab3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.Instant;

public class LiveCall{
    private final CallRegFrame src;
    private final Time t;
    private final String destNum;
    public Instant start;
    private final LiveCallFrame lcf1, lcf2;

    private static class LiveCallFrame extends JFrame {
        public LiveCallFrame(String num1, String num2, String name2, LiveCall liveCall) {
            super(num1);
            setSize(300, 100);
            JTextField jTextField = new JTextField();
            jTextField.setText("In call with " + (name2!=null?name2:num2));
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            jTextField.setVisible(true);
            add(jTextField, BorderLayout.CENTER);

            JButton button = new JButton("End");
            button.setVisible(true);
            button.addActionListener(liveCall::callEnd);
            add(button, BorderLayout.SOUTH);

            setVisible(true);
        }
    }

    public LiveCall(String num1, String num2, String name1, String name2, CallRegFrame src, Time t){
        this.src=src;
        this.t=t;
        destNum=num2;
        lcf1= new LiveCallFrame(num1, num2, name2, this);
        lcf2= new LiveCallFrame(num2, num1, name1, this);
        start=Instant.now();
    }

    public void callEnd(ActionEvent actionEvent){
        lcf1.dispose();
        lcf2.dispose();
        src.getCallReg().addSentCall(t, destNum, new Duration(java.time.Duration.between(start, Instant.now()).toSeconds()));
        src.listSent(null);
    }
}
