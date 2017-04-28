package interfacciaGrafica.logicaChiamate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;

import classi.Docente;
import classi.ListaDocenti;
import interfacciaGrafica.FinestraErrore;
import interfacciaGrafica.FinestraModifica;
import interfacciaGrafica.FinestraTre;

public class ChiamataModifica implements ActionListener {

	private JFrame f;
	private List<Docente> presidentiPotenziali;
	private ListaDocenti docenti;
	private int numTri;
	private int numMag;


	public ChiamataModifica(JFrame f, ListaDocenti docenti, List<Docente> presidentiPotenziali, int numTri,	int numMag) {
		this.f=f;
		this.docenti=docenti;
		this.presidentiPotenziali=presidentiPotenziali;
		this.numTri=numTri;
		this.numMag=numMag;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.presidentiPotenziali.size()>=this.numMag+this.numTri)
			new FinestraTre(this.docenti,this.presidentiPotenziali,numMag, numTri);
		else
			new FinestraErrore();
	}

}
