package interfacciaGrafica.listenerBottoni;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import classi.Commissione;
import controller.Controller;

public class ListenerItemSelezionaNumeroStudente implements ItemListener{
	private Controller c;
	private Commissione cgm;
	
	private DefaultListModel modLaureandi;
	private JComboBox<Integer> jc;

	public ListenerItemSelezionaNumeroStudente(Controller controller, Commissione cgm,DefaultListModel modLaureandi,JComboBox<Integer> jc){
		this.c=controller;
		this.cgm=cgm;
		
		
		this.modLaureandi=modLaureandi;
		this.jc=jc;
	}

	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getStateChange() == ItemEvent.DESELECTED) 
		{
			
			
		}
		else if(ie.getStateChange() == ItemEvent.SELECTED)
		{
			this.c.eliminaLaureandoDaCommissione((Integer)ie.getItem(),this.cgm,this.modLaureandi,this.jc);
            
		}

	}
}
