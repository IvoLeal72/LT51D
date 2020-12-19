package gui.demos.layout;


import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

public class GridLayoutDemo1 extends JFrame {

	public GridLayoutDemo1() {
		super("Grid Layout demostraction");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container contentPane = getContentPane();

		// Três linhas, número de colunas necessárias
		contentPane.setLayout( new  GridLayout(3,0) );
		JLabel label1;
		for ( int i = 0; i < 3*4; ++i)  { // Adicionar multiplos de 3
			label1 = new JLabel(String.format(" %2d º label here ", i+1));
			label1.setBorder( new LineBorder(Color.BLUE) );
			contentPane.add(label1);
		}
		pack();
	}
	public static void main(String[] args) {
		JFrame window = new GridLayoutDemo1();
		window.setVisible(true);
	}

}
