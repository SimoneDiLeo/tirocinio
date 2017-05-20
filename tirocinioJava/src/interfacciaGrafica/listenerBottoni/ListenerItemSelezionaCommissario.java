package interfacciaGrafica.listenerBottoni;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;

import classi.Commissione;
import classi.Docente;
import controller.Controller;

public class ListenerItemSelezionaCommissario implements ItemListener{
	private Controller c;
	private Commissione cgm;
	private int indiceJComboBox;
	private Box radioBoxDisponibilita;

	public ListenerItemSelezionaCommissario(Controller controller, Commissione cgm, int i, Box radioBoxDisponibilita){
		this.c=controller;
		this.cgm=cgm;
		this.indiceJComboBox=i;
		this.radioBoxDisponibilita=radioBoxDisponibilita;
	}

	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getStateChange() == ItemEvent.DESELECTED) 
		{
			this.c.decrementaCommissioniInLista((Docente)ie.getItem(),this.cgm,this.indiceJComboBox);
			this.c.rimuoviCommissario((Docente)ie.getItem(), cgm, indiceJComboBox);
			this.c.modificaBoxDisponibilita(this.radioBoxDisponibilita,cgm);
		}
		else if(ie.getStateChange() == ItemEvent.SELECTED)
		{
			this.c.modificaSelezionatoInDocente((Docente)ie.getItem(),this.cgm,this.indiceJComboBox);
			this.c.modificaPotenzialiCommissari((Docente)ie.getItem(), cgm, indiceJComboBox);
			this.c.modificaBoxDisponibilita(this.radioBoxDisponibilita,cgm);

		}

	}
}