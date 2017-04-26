package interfacciaGrafica;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FinestraErrore {
	private JFrame f = new JFrame("ERRORE");
	private JPanel panel = new JPanel();
	
	public FinestraErrore(){
		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.f.setSize(300,300);
		this.panel.add(new JLabel("ERRORE"));

		this.f.setVisible(true);
		f.getContentPane().add(panel);
	}

}
