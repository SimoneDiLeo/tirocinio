package interfacciaGrafica.listenerBottoni;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import classi.CommissioneGrafica;
import classi.Docente;
import controller.Controller;

public class ProvaListenerItem implements ItemListener{
	private Controller c;
	private CommissioneGrafica cgm;
	private int indiceJComboBox;
	
	public ProvaListenerItem(Controller controller, CommissioneGrafica cgm, int i){
		this.c=controller;
		this.cgm=cgm;
		this.indiceJComboBox=i;
		
	}
	
	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getStateChange() == ItemEvent.DESELECTED) 
		{
			this.c.decrementaCommissioniInLista((Docente)ie.getItem(),this.cgm,this.indiceJComboBox);
		}
		else if(ie.getStateChange() == ItemEvent.SELECTED)
		{
			this.c.modificaSelezionatoInDocente((Docente)ie.getItem(),this.cgm,this.indiceJComboBox);
		}
	}
}