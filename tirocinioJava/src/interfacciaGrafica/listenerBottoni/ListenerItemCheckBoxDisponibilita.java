package interfacciaGrafica.listenerBottoni;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import classi.Commissione;


public class ListenerItemCheckBoxDisponibilita implements ItemListener{

	private Commissione c;
	
	public ListenerItemCheckBoxDisponibilita(Commissione c){
		this.c=c;
	}
	
	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getStateChange() == ItemEvent.DESELECTED) 
		{
			JCheckBox cb = (JCheckBox)ie.getSource();
			String stringaSelezionata=cb.getText();
			System.out.println(stringaSelezionata);
			this.c.removeSlotScelto(Integer.parseInt(stringaSelezionata));
		}
		else if(ie.getStateChange() == ItemEvent.SELECTED)
		{
			JCheckBox cb = (JCheckBox)ie.getSource();
			String stringaSelezionata=cb.getText();
			this.c.addSlotScelto(Integer.parseInt(stringaSelezionata));    
		}

	}
}


