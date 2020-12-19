package gui.demos;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class BordersDemo extends JFrame {

	public JPanel createPanelWithTitleBorder(String msg ) {
		JPanel p = new JPanel();
		FlowLayout f = new FlowLayout();
		f.setAlignment(FlowLayout.LEFT);
		f.setHgap(10);
		p.setLayout(f);
		
		p.setBorder(new TitledBorder(msg+"Border"));
		
		return p;			
	}

	public JLabel createLabelBorder( String msg, AbstractBorder b) {
		JLabel l = new JLabel(msg);
		l.setBorder(b);
		return l;
	}
	
	public BordersDemo() {
		super("Borders Demo");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(5, 1));
		JPanel p = createPanelWithTitleBorder("Bevel");
		p.add(createLabelBorder("Bevel(LOWERED)",
				                new BevelBorder(BevelBorder.LOWERED)));
		p.add(createLabelBorder("Bevel(RAISED)",
              new BevelBorder(BevelBorder.RAISED)));
		cp.add(p);
		
		p = createPanelWithTitleBorder("Etched");
		p.add(createLabelBorder("Etched()",
	              new EtchedBorder()));
		p.add(createLabelBorder("Etched(LOWERED)",
	              new EtchedBorder(EtchedBorder.LOWERED)));
		p.add(createLabelBorder("Etched(RAISED)",
	              new EtchedBorder(EtchedBorder.RAISED)));
		p.add(createLabelBorder("Etched(BLUE, WHITE)",
	              new EtchedBorder(Color.BLUE, Color.CYAN)));
		p.add(createLabelBorder("Etched(LOWERED, BLUE, WHITE)",
	              new EtchedBorder(EtchedBorder.LOWERED, Color.BLUE, Color.CYAN)));
		p.add(createLabelBorder("Etched(RAISED, BLUE, WHITE)",
	              new EtchedBorder(EtchedBorder.RAISED, Color.BLUE, Color.CYAN)));
		cp.add(p);
		
		p = createPanelWithTitleBorder("Line");
		p.add(createLabelBorder("Line(BLUE)",
	              new LineBorder(Color.BLUE)));
		p.add(createLabelBorder("Line(BLUE, 5)",
				                 new LineBorder(Color.BLUE, 5)));
		p.add(createLabelBorder("Line(BLUE, 5, true)",
                new LineBorder(Color.BLUE, 5, true)));
		cp.add(p);
		
		p = createPanelWithTitleBorder("Empty");
		p.add(createLabelBorder("Empty(2,2,2,2)",
	              new EmptyBorder(2,2,2,2)));
		p.add(createLabelBorder("Empty(new Insets(2,2,2,2))",
				                 new EmptyBorder(new Insets(2,2,2,2))));
		cp.add(p);

		p = createPanelWithTitleBorder("Matte");
		p.add(createLabelBorder("Matte(0,2,2,2, BLUE)",
	              new MatteBorder(0,2,2,2, Color.BLUE)));
		p.add(createLabelBorder("Matte(2,0,2,2, BLUE)",
                new MatteBorder(2,0,2,2, Color.BLUE)));
		p.add(createLabelBorder("Matte(2,2,0,2, BLUE)",
                new MatteBorder(2,2,0,2, Color.BLUE)));
		p.add(createLabelBorder("Matte(2,2,2,0,BLUE)",
                new MatteBorder(2,2,2,0, Color.BLUE)));
		cp.add(p);

		pack();
	}
	public static void main(String[] args) {
		new BordersDemo().setVisible( true);
	}

}
