package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import classi.CommissioneGrafica;
import classi.Docente;
import classi.Studente;
import controller.Controller;

public class SelezionaDocenteVecchio implements ActionListener {
	private Controller c;
	private Docente docPrecente;


	public SelezionaDocenteVecchio(Controller controller, Docente docPrecente) {
		this.c=controller;
		this.docPrecente=docPrecente;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
		Docente d = (Docente)cb.getSelectedItem();
		System.out.println(docPrecente);
		this.c.azzeraCommissioniInLista(this.docPrecente);
//		this.c.modificaSelezionatoInDocente(d);
	}
}
//versione precedente
//private DefaultListModel modelloListaLaureandi;
//	private Map<Integer,Docente> commissariInseriti;
//	private int indiceProfessore;
//	private CommissioneGrafica commissione;


//	public LogicaSelezioneDocente( DefaultListModel modLaureandi, int i, CommissioneGrafica cgm) {
//		this.modelloListaLaureandi=modLaureandi;
//		this.indiceProfessore=i;
//		this.commissione=cgm;
//		this.commissariInseriti=cgm.getMappatura();
//	}
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		
//		Docente d = (Docente)cb.getSelectedItem();
//		if(!this.commissariInseriti.values().contains(d))
//			this.commissariInseriti.put(this.indiceProfessore,d);
//		this.modelloListaLaureandi.removeAllElements();	
//		this.commissione.reinizializzaLaureandi();
//		this.commissione.aggiornaLaureandi(this.commissione.getPresidente());
//		this.inserimentoStudenteDocenteSelezionato(this.commissione.getPresidente());
//		for(Docente doc:this.commissariInseriti.values()){
//			this.inserimentoStudenteDocenteSelezionato(doc);
//			this.commissione.aggiornaLaureandi(doc);
//		}
//	}

//	private void inserimentoStudenteDocenteSelezionato(Docente d) {
//		if(this.commissione.getTipoCommissione().contains("TRIENNALE"))
//			for(Studente s : d.getLaureandiTriennali()){
//				if(s!=null)
//					this.modelloListaLaureandi.addElement(s);}
//		else
//			for(Studente s : d.getLaureandiMagistrali())
//				this.modelloListaLaureandi.addElement(s);
//
//	}


