package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import classi.ContenitoreFile;
import classi.Docente;
import classi.ListaDocenti;
import controller.Controller;
import interfacciaGrafica.FinestraSceltaNumeroCommissioni;
import interfacciaGrafica.FinestraErrore;

public final class ListenerBottonePaginaUno implements ActionListener {
	private final JFrame fFrame;
	private ContenitoreFile contenitore;
	private Properties props;

	public ListenerBottonePaginaUno(Properties props, final JFrame aFrame,ContenitoreFile cf){
		fFrame = aFrame;
		contenitore=cf;
		this.props=props;
	}

	@Override
	public void actionPerformed(final ActionEvent aEvent) {
		Controller c=new Controller();
		c.setProprieta(this.props);
		c.caricaFile(this.contenitore.getFileDocente(), this.contenitore.getFileStudente(),this.contenitore.getFilePersonale(), this.contenitore.getFileControrelatori());
		c.addFrameCorrente(fFrame);
		this.fFrame.dispose();
		new FinestraSceltaNumeroCommissioni(c);
	}

}