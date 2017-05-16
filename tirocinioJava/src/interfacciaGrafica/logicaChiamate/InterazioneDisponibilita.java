package interfacciaGrafica.logicaChiamate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classi.CommissioneGrafica;
import classi.Docente;
import classi.ListaDocenti;

public class InterazioneDisponibilita implements ActionListener {
	private JComboBox disp;
	private CommissioneGrafica cg;
	private ListaDocenti d;
	private Map<JComboBox, DefaultComboBoxModel> modelliJBoxCommissari;


	public InterazioneDisponibilita(JComboBox comp, CommissioneGrafica cgm,ListaDocenti docenti, Map<JComboBox, DefaultComboBoxModel> modelliBox){
		this.cg=cgm;
		this.disp=comp;
		this.d=docenti;
		this.modelliJBoxCommissari=modelliBox;

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int i = (int) this.disp.getSelectedItem();
		this.cg.aggiornaCommissione(i, this.d);
		for(JComboBox jc:this.modelliJBoxCommissari.keySet()){
			for(ArrayList<Docente> d : this.cg.getCommissari()){
				jc.removeAll();
				for(Docente dd:d){
					jc.addItem(dd);
				}
			}

		}
	}

}
