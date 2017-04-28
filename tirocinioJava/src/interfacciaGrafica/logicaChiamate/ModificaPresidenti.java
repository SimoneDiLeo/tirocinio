package interfacciaGrafica.logicaChiamate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import classi.Docente;
import classi.ListaDocenti;
import interfacciaGrafica.FinestraModifica;

public class ModificaPresidenti implements ActionListener {
	private JFrame f;
	private List<Docente> presidenti;
	private int numMag;
	private int numTri;
	private ListaDocenti docenti;

	public ModificaPresidenti(JFrame f, List<Docente> listaPresidentiPotenzialiRiunita, int numeroMagistrali, int numeroTriennali, ListaDocenti docenti) {
		// TODO Auto-generated constructor stub
		this.f=f;
		this.presidenti=listaPresidentiPotenzialiRiunita;
		this.numMag=numeroMagistrali;
		this.numTri=numeroTriennali;
		this.docenti=docenti;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.f.dispose();
		List<JComboBox> listaMagistrali=new ArrayList<>();
		
		for(int i = 0;i<this.numMag;i++){
			listaMagistrali.add(new JComboBox(this.presidenti.toArray()));
		}
		
		List<JComboBox> listaTriennali=new ArrayList<>();
		for(int i = 0;i<this.numMag;i++){
			listaTriennali.add(new JComboBox(this.presidenti.toArray()));
		}
		
		new FinestraModifica(docenti, listaMagistrali, listaTriennali, numMag, numTri);

	}

}
