package gui.demos.layout;


import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

public class FlowLayoutDemo extends JFrame {
	public static final int WIDTH = 200;
	public static final int HEIGHT = 200;
	

	public FlowLayoutDemo() {
		super("Flow Layout demostraction");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		
		Container contentPane = getContentPane();
		
		contentPane.setLayout( new FlowLayout() );
		
		JLabel label1 = new JLabel("First label here");
		label1.setBorder(new LineBorder(Color.BLUE));
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("Second label here");
		label2.setBorder(new LineBorder(Color.BLUE));
		contentPane.add(label2);

		JLabel label3 = new JLabel("Third label here");
		label3.setBorder(new LineBorder(Color.BLUE));
		contentPane.add(label3);
				
	}
	public static void main(String[] args) {
		JFrame window = new FlowLayoutDemo();
		window.setVisible(true);
	}

}
