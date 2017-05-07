package interfacciaGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import classi.Docente;

public class SelezionaDocente implements ActionListener {

	private Docente[] docenti;
	private int indiceCommissione;

	public SelezionaDocente(Docente[] presidentiPotenziali, int indicePresidente) {
		this.docenti=presidentiPotenziali;
		this.indiceCommissione=indicePresidente;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox jcb= (JComboBox)e.getSource();	
		Docente docenteSelezionato= (Docente) jcb.getSelectedItem();
		this.docenti[indiceCommissione]=docenteSelezionato;
	}
}


