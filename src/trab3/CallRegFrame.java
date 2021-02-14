package trab3;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.AbstractMap;

public class CallRegFrame extends JFrame {


    public static class ItensMenu extends
            AbstractMap.SimpleEntry<String, ActionListener> {
        public ItensMenu(String s, ActionListener l){ super(s,l);}
    }


}
