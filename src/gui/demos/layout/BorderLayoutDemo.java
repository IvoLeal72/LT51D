package gui.demos.layout;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

public class BorderLayoutDemo extends JFrame {
	public static final int WIDTH = 400;
	public static final int HEIGHT = 200;
	

	public BorderLayoutDemo() {
		super("Border Layout demostraction");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		
		Container contentPane = getContentPane(); // Por omissão o layout é BorderLayout

		JLabel label1 = new JLabel("NORTH label here");
		label1.setBorder(new LineBorder(Color.BLUE));
		contentPane.add(label1, BorderLayout.NORTH);
		
		JLabel label2 = new JLabel("SOUTH label here");
		label2.setBorder(new LineBorder(Color.BLUE));
		contentPane.add(label2, BorderLayout.SOUTH);

		JLabel label3 = new JLabel("CENTER label here");
		label3.setBorder(new LineBorder(Color.BLUE));
		contentPane.add(label3, BorderLayout.CENTER);

		JLabel label4 = new JLabel("EAST label here");
		label4.setBorder(new LineBorder(Color.BLUE));
		contentPane.add(label4, BorderLayout.EAST);

		JLabel label5 = new JLabel("WEST label here");
		label5.setBorder(new LineBorder(Color.BLUE));
		contentPane.add(label5, BorderLayout.WEST);
	}
	public static void main(String[] args) {
		JFrame window = new BorderLayoutDemo();
		window.setVisible(true);
	}

}
