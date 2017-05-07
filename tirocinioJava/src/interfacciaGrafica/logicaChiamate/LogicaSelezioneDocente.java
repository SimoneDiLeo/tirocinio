package interfacciaGrafica.logicaChiamate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import classi.CommissioneGrafica;
import classi.Docente;
import classi.Studente;

public class LogicaSelezioneDocente implements ActionListener {
	private DefaultListModel modelloListaLaureandi;
	private String tipoCommissione;
	private List<Docente> commissariInseriti;

	public LogicaSelezioneDocente(DefaultListModel modLaureandi, String string) {
		this.modelloListaLaureandi=modLaureandi;
		this.tipoCommissione=string;
		this.commissariInseriti=null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
		Docente d = (Docente)cb.getSelectedItem();
		this.modelloListaLaureandi.removeAllElements();	
		if(d!=null)
			inserimentoStudenteDocenteSelezionato(d);
	}

	private void inserimentoStudenteDocenteSelezionato(Docente d) {
		System.out.println(d.toString()+d.getNumeroLaureandiTriennali());	
		if(this.tipoCommissione.contains("TRIENNALE"))
			for(Studente s : d.getLaureandiTriennali()){
				this.modelloListaLaureandi.addElement(s);}
		else
			for(Studente s : d.getLaureandiMagistrali())
				this.modelloListaLaureandi.addElement(s);

	}

}
