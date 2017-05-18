package interfacciaGrafica;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import interfacciaGrafica.listenerBottoni.ListenerTornaIndietro;

public class FinestraErrore {
	private JFrame f = new JFrame("ERRORE");
	private JPanel panel = new JPanel();
	
	public FinestraErrore(){
		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.f.setSize(300,300);
		this.panel.add(new JLabel("ERRORE"));
		this.f.setVisible(true);
	}
	
	
	public FinestraErrore(Controller c){
		c.addFrameCorrente(this.f);
		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.f.setSize(300,300);
		this.panel.add(new JLabel("ERRORE"));
		this.f.setVisible(true);
		JButton ti=new JButton("Torna Indietro");
		ti.addActionListener(new ListenerTornaIndietro(f, c));
		this.panel.add(ti);
		f.getContentPane().add(panel);
	}

}
