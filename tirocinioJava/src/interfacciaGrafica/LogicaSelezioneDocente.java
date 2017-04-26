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
	private JComboBox doc;

	public LogicaSelezioneDocente(DefaultListModel modLaureandi, CommissioneGrafica cgm, JComboBox commissari2) {
		this.modelloListaLaureandi=modLaureandi;
		this.commissione=cgm;
		this.doc=commissari2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
		Docente d = (Docente)cb.getSelectedItem();
		Docente altroComm=(Docente) this.doc.getSelectedItem();
		this.modelloListaLaureandi.removeAllElements();	
		for(Studente s:this.commissione.getLaureandi()){
			this.modelloListaLaureandi.addElement(s);
		}
		if(altroComm!=null)
			for(Studente s1:altroComm.getLaureandi()){
				if(s1.getTipoLaurea().toUpperCase().equals(this.commissione.getTipoCommissione().toUpperCase()))
					this.modelloListaLaureandi.addElement(s1);
			}
		if(d!=null)
			if(!d.equals(altroComm))
				for(Studente s:d.getLaureandi())
					if(s.getTipoLaurea().toUpperCase().equals(this.commissione.getTipoCommissione().toUpperCase()))
						this.modelloListaLaureandi.addElement(s);

	}

}
