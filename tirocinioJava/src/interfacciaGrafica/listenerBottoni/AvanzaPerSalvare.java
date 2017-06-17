package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import controller.Controller;
import interfacciaGrafica.FinestraSalvaFile;

public class AvanzaPerSalvare implements ActionListener {

	private Controller c;
	private JFrame f;

	public AvanzaPerSalvare(JFrame f,Controller c) {
		this.f=f;
		this.c=c;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.f.dispose();
		this.c.addFrameCorrente(f);
		new FinestraSalvaFile(c);
	
	}

}
