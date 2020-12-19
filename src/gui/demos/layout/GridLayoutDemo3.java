package gui.demos.layout;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GridLayoutDemo3 extends JFrame {

	public GridLayoutDemo3() {
		super("Grid Layout demostraction");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container contentPane = getContentPane();

		// Quatro linhas, 5 colunas por linha
		contentPane.setLayout( new  GridLayout(4,5) );
		JLabel label1;
		for ( int i = 0; i < 4*5; ++i)  {
			label1 = new JLabel(String.format(" %2d º label here ", i+1));
			label1.setBorder( new LineBorder(Color.BLUE) );
			contentPane.add(label1);
		}
		pack();
	}
	public static void main(String[] args) {
		JFrame window = new GridLayoutDemo3();
		window.setVisible(true);
	}

}
