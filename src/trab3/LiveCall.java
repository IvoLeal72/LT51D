package trab3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Instant;

public class LiveCall{
    private final CallRegFrame src;
    private final Time t;
    private final String destNum;
    public Instant start;
    private final LiveCallFrame lcf1, lcf2;

    private class LiveCallFrame extends JFrame {
        private final LiveCall liveCall;


        public LiveCallFrame(String num1, String num2, LiveCall liveCall) {
            super(num1);
            this.liveCall=liveCall;
            setSize(300, 100);
            JTextField jTextField = new JTextField();
            jTextField.setText("In call with " + num2);
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

    public LiveCall(String num1, String num2, CallRegFrame src, Time t){
        this.src=src;
        this.t=t;
        destNum=num2;
        lcf1=new LiveCallFrame(num1, num2, this);
        lcf2=new LiveCallFrame(num2, num1, this);
        start=Instant.now();
    }

    public void callEnd(ActionEvent actionEvent){
        lcf1.dispose();
        lcf2.dispose();
        src.getCallReg().addSentCall(t, destNum, new Duration(java.time.Duration.between(start, Instant.now()).toSeconds()));
        src.listSent(null);
    }
}
