package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;

import classi.Docente;
import classi.ListaDocenti;
import controller.Controller;
import interfacciaGrafica.FinestraSceltaCommissioni;

public class ListenerConfermaSceltaPresidenti implements ActionListener {
	private Controller controller;
	private List<Docente> presidentiScelti;
	private JFrame f;
	private int numeroMagistrali;
	private int numeroTriennali;
	
	
	public ListenerConfermaSceltaPresidenti(Controller c, JFrame f, int numeroMagistrali, int numeroTriennali) {
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
		new FinestraSceltaCommissioni(controller, numeroMagistrali, numeroTriennali);

	}

}
