package gui.demos.layout;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GridLayoutDemo2 extends JFrame {
	public GridLayoutDemo2() {
		super("Grid Layout demostraction");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container contentPane = getContentPane();

		// 3 colunas,  número de linhas necessárias
		contentPane.setLayout( new  GridLayout(0,3) );
		JLabel label1;
		for ( int i = 0; i < 3*5; ++i)  {
			label1 = new JLabel(String.format(" %2d º label here ", i+1));
			label1.setBorder( new LineBorder(Color.BLUE) );
			contentPane.add(label1);
		}
		pack();
	}
	public static void main(String[] args) {
		JFrame window = new GridLayoutDemo2();
		window.setVisible(true);
	}

}
