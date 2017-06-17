package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import controller.Controller;

import interfacciaGrafica.FinestraSceltaCommissioni1;

public class ListenerConfermaSceltaPresidenti1 implements ActionListener {
	private Controller controller;
	private JFrame f;
	private int numeroMagistrali;
	private int numeroTriennali;
	
	
	public ListenerConfermaSceltaPresidenti1(Controller c, JFrame f, int numeroMagistrali, int numeroTriennali) {
		this.controller=c;
		this.f=f;
		this.numeroMagistrali=numeroMagistrali;
		this.numeroTriennali=numeroTriennali;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.f.dispose();
		this.controller.addFrameCorrente(f);
		this.controller.confermaPresidenti();
		new FinestraSceltaCommissioni1(controller, numeroMagistrali, numeroTriennali);

	}

}

