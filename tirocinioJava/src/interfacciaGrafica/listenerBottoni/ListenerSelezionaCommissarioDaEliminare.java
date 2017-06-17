package interfacciaGrafica.listenerBottoni;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import classi.Commissione;
import classi.Docente;
import controller.Controller;

public class ListenerSelezionaCommissarioDaEliminare implements ItemListener{
	private Controller c;
	private Commissione cgm;
	
	private DefaultListModel modLaureandi;
	private JComboBox<Docente> jcomboEliminaCommissario;
	private DefaultListModel commissariModel;
	private int numeroComm;
	

	public ListenerSelezionaCommissarioDaEliminare(Controller controller, Commissione cgm,DefaultListModel modLaureandi,JComboBox<Docente> jcomboEliminaCommissario,DefaultListModel commissariModel,int numeroComm){
		this.c=controller;
		this.cgm=cgm;
		
		this.modLaureandi=modLaureandi;
		this.jcomboEliminaCommissario=jcomboEliminaCommissario;
		this.commissariModel=commissariModel;
		this.numeroComm=numeroComm;
		
	}

	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getStateChange() == ItemEvent.DESELECTED) 
		{
			
			
		}
		else if(ie.getStateChange() == ItemEvent.SELECTED)
		{
			this.c.eliminaCommissarioDaCommissione((Docente)ie.getItem(),this.cgm,this.modLaureandi,this.jcomboEliminaCommissario,this.commissariModel,this.numeroComm);
            
		}

	}
}
