package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import classi.Docente;
import controller.Controller;

public class ListenerSelezionaPresidente implements ItemListener{
	private Controller c;
	private int numeroCommissione;

	public ListenerSelezionaPresidente(Controller c, int i) {
		this.c=c;
		this.numeroCommissione=i;
	}

	@Override
	public void itemStateChanged(ItemEvent ie){
		try{
			Docente d=(Docente)ie.getItem();
			if(ie.getStateChange() == ItemEvent.DESELECTED) {
				d.setInUnaCommissione(false);
				d.cambiaColoreLabel();
			}
			if(ie.getStateChange() == ItemEvent.SELECTED){
				d.setInUnaCommissione(true);
				d.cambiaColoreLabel();
				c.modificaPresidenteCommissione(this.numeroCommissione, d);
			}
		}
		catch(Exception e ){

		}

	}
}


