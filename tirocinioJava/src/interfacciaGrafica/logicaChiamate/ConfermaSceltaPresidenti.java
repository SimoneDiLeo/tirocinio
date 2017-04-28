package interfacciaGrafica.logicaChiamate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;

import classi.Docente;
import classi.ListaDocenti;
import interfacciaGrafica.FinestraTre;

public class ConfermaSceltaPresidenti implements ActionListener {
	private ListaDocenti docenti;
	private List<Docente> presidentiScelti;
	private JFrame f;
	private int numeroMagistrali;
	private int numeroTriennali;
	
	
	public ConfermaSceltaPresidenti(ListaDocenti docenti, List<Docente> presidentiScelti, JFrame f, int numeroMagistrali, int numeroTriennali) {
		// TODO Auto-generated constructor stub
		this.docenti=docenti;
		this.presidentiScelti=presidentiScelti;
		this.f=f;
		this.numeroMagistrali=numeroMagistrali;
		this.numeroTriennali=numeroTriennali;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.f.dispose();
		new FinestraTre(docenti, presidentiScelti, numeroTriennali, numeroMagistrali);

	}

}
