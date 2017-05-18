package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import controller.Controller;

public class ListenerTornaIndietro implements ActionListener {
	private JFrame frameChiamata;
	private Controller c;
	
	public ListenerTornaIndietro(JFrame f, Controller c) {
		this.frameChiamata=f;
		this.c=c;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.frameChiamata.dispose();
		c.tornaIndietro();
		}

}
