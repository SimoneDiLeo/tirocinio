package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import classi.Docente;
import classi.ListaDocenti;
import controller.Controller;
import interfacciaGrafica.FinestraSceltaNumeroCommissioni;
import interfacciaGrafica.FinestraErrore;

public final class ListenerBottonePaginaUno implements ActionListener {
	private final JFrame fFrame;
	private JTextField docenti;
	private JTextField studenti;
	private JTextField personale;
	private JTextField controrelatori;

	public ListenerBottonePaginaUno(final JFrame aFrame,JTextField testoInputDocenti,JTextField testoInputStudenti, JTextField nomeFilePersonale, JTextField nomeFileControrelatori){
		fFrame = aFrame;
		docenti=testoInputDocenti;
		studenti=testoInputStudenti;
		personale=nomeFilePersonale;
		controrelatori=nomeFileControrelatori;
	}

	@Override
	public void actionPerformed(final ActionEvent aEvent) {
		String nomeFileDocenti=this.docenti.getText();
		String nomeFileStudenti=this.studenti.getText();
		String nomeFilePersonale=this.personale.getText();
		String nomeFileControrelatori=this.controrelatori.getText();
		Controller c=new Controller();
		c.caricaFile(nomeFileDocenti, nomeFileStudenti, nomeFilePersonale, nomeFileControrelatori);
		c.addFrameCorrente(fFrame);
		this.fFrame.dispose();
		new FinestraSceltaNumeroCommissioni(c);
	}

}