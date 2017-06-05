package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.JFrame;

import controller.Controller;
import interfacciaGrafica.FinestraErrore;
import interfacciaGrafica.FinestraSceltaNumeroCommissioni;
import logica.ContenitoreFile;

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
		try{
			c.caricaFile(this.contenitore.getFileDocente(), this.contenitore.getFileStudente(),this.contenitore.getFilePersonale(), this.contenitore.getFileControrelatori());
			c.addFrameCorrente(fFrame);
			this.fFrame.dispose();
			new FinestraSceltaNumeroCommissioni(c);}
		catch(Exception e ){
			c.addFrameCorrente(this.fFrame);
			this.fFrame.dispose();
			new FinestraErrore(c);
		}
	}

}