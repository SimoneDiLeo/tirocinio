package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import classi.Commissione;

public class ListenerRadioBoxDisponibilita implements ActionListener {

	private Commissione c;
	
	public ListenerRadioBoxDisponibilita(Commissione c){
		this.c=c;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JRadioButton cb = (JRadioButton)e.getSource();
		Object[] selectedObjects = cb.getSelectedObjects();
		String stringaSelezionata=(String) selectedObjects[0];
		this.c.setSlotScelto(Integer.parseInt(stringaSelezionata));

	}

}
