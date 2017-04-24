package interfacciaGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tirocinioJava.classi.Docente;
import tirocinioJava.classi.ListaDocenti;

public class InterazioneDisponibilita implements ActionListener {
	private JComboBox disp;
	private CommissioneGrafica cg;
	private ListaDocenti d;
	private DefaultComboBoxModel comm1;

	public InterazioneDisponibilita(JComboBox comp, CommissioneGrafica cgm,ListaDocenti docenti, DefaultComboBoxModel model){
		this.cg=cgm;
		this.disp=comp;
		this.d=docenti;
		this.comm1=model;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int i = (int) this.disp.getSelectedItem();
		this.cg.aggiornaCommissione(i, this.d);
		this.comm1.removeAllElements();
		for(Docente d : this.cg.getCommissari1()){
			this.comm1.addElement(d);
		}
//		this.comm1= new JComboBox(this.cg.getCommissari1().toArray()); 
		
			
	}

}
