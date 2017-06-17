package interfacciaGrafica.listenerBottoni;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import classi.Commissione;
import classi.Docente;
import controller.Controller;

public class ListenerItemSelezionaCommissario implements ItemListener{
	private Controller c;
	private Commissione cgm;
	private int indiceJComboBox;
	private Box radioBoxDisponibilita;
	private DefaultListModel modLaureandi;
	private DefaultListModel commissariModel;
	private JComboBox<Integer> jc;
	private JComboBox<Docente> jcomboEliminaCommissario;
	private int numeroComm;

	public ListenerItemSelezionaCommissario(Controller controller, Commissione cgm, int i, Box radioBoxDisponibilita,DefaultListModel modLaureandi,JComboBox<Integer> jc,DefaultListModel commissariModel,JComboBox<Docente> jcomboEliminaCommissario,int numeroComm){
		this.c=controller;
		this.cgm=cgm;
		this.indiceJComboBox=i;
		this.radioBoxDisponibilita=radioBoxDisponibilita;
		this.modLaureandi=modLaureandi;
		this.commissariModel=commissariModel;
		this.jc=jc;
		this.jcomboEliminaCommissario=jcomboEliminaCommissario;
		this.numeroComm=numeroComm;
		
	}

	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getStateChange() == ItemEvent.DESELECTED) 
		{
			this.c.decrementaCommissioniInLista((Docente)ie.getItem(),this.cgm,this.indiceJComboBox);
			this.c.rimuoviCommissario((Docente)ie.getItem(), cgm, indiceJComboBox);
			//this.c.togliLureandiInCommissione((Docente)ie.getItem(),this.cgm,this.modLaureandi,this.jc);
			
		}
		else if(ie.getStateChange() == ItemEvent.SELECTED)
		{
			this.c.modificaSelezionatoInDocente((Docente)ie.getItem(),this.cgm,this.indiceJComboBox);
			this.c.modificaPotenzialiCommissari((Docente)ie.getItem(), cgm, indiceJComboBox);
			this.c.modificaBoxDisponibilita(this.radioBoxDisponibilita,cgm);
			this.c.aggiungiLureandiInCommissione((Docente)ie.getItem(),this.cgm,this.modLaureandi,this.jc,this.commissariModel,this.jcomboEliminaCommissario,this.numeroComm);
			
            
		}

	}
}