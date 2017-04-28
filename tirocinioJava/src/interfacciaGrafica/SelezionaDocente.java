package interfacciaGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import classi.Docente;

public class SelezionaDocente implements ActionListener {
	
	private List<Docente> docenti;
	private Docente docentePrecedente;

	public SelezionaDocente(List<Docente> presidentiPotenziali, Docente docente) {
		this.docenti=presidentiPotenziali;
		this.docentePrecedente=docente;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox jcb= (JComboBox)e.getSource();	
		Docente docenteSelezionato= (Docente) jcb.getSelectedItem();
		this.docenti.remove(this.docentePrecedente);
		this.docenti.add(docenteSelezionato);
	}
		
}
