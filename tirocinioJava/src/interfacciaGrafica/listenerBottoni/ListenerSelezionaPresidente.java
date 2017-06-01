package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

import classi.Docente;
import controller.Controller;

public class ListenerSelezionaPresidente implements ActionListener {
	private Controller c;
	private int numeroCommissione;
	
	public ListenerSelezionaPresidente(Controller c, int i) {
		this.c=c;
		this.numeroCommissione=i;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox jcb= (JComboBox)e.getSource();	
		Docente docenteSelezionato= (Docente) jcb.getSelectedItem();
		docenteSelezionato.incrementaSelezionato();
		c.modificaPresidenteCommissione(this.numeroCommissione, docenteSelezionato);
	}
}


