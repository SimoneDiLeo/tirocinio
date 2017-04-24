package interfacciaGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import tirocinioJava.classi.Docente;
import tirocinioJava.classi.Studente;

public class LogicaSelezioneDocente implements ActionListener {
	private DefaultListModel modelloListaLaureandi;
	private CommissioneGrafica commissione;


	public LogicaSelezioneDocente(DefaultListModel modLaureandi, CommissioneGrafica cgm) {
		this.modelloListaLaureandi=modLaureandi;
		this.commissione=cgm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
		Docente d = (Docente)cb.getSelectedItem();
		this.modelloListaLaureandi.removeAllElements();	
		for(Studente s:this.commissione.getLaureandi()){
			this.modelloListaLaureandi.addElement(s);
		}
		if(d!=null)
			for(Studente s:d.getLaureandi())
				if(s.getTipoLaurea().toUpperCase().equals(this.commissione.getTipoCommissione().toUpperCase()))
					this.modelloListaLaureandi.addElement(s);

	}

}
