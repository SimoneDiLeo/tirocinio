package interfacciaGrafica.logicaChiamate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import classi.Docente;
import classi.ListaDocenti;
import interfacciaGrafica.FinestraErrore;
import interfacciaGrafica.FinestraModifica;
import interfacciaGrafica.FinestraTre;

public class ChiamataModifica implements ActionListener {

	private JFrame f;
	private Docente[] presidentiPotenziali;
	private ListaDocenti docenti;
	private int numTri;
	private int numMag;


	public ChiamataModifica(JFrame f, ListaDocenti docenti, Docente[] presidentiPotenziali2, int numTri,	int numMag) {
		this.f=f;
		this.docenti=docenti;
		presidentiPotenziali=presidentiPotenziali2;
		this.numTri=numTri;
		this.numMag=numMag;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//		if(this.presidentiPotenziali.size()>=this.numMag+this.numTri){
		List<Docente> presidentiConfermati = new ArrayList<>();
		for(Docente d :this.presidentiPotenziali){
			if(d!=null){
				System.out.println(d.toString());
				presidentiConfermati.add(d);
			}
		}

		new FinestraTre(this.docenti,presidentiConfermati,numMag, numTri);
		this.f.dispose();
		//		}
		//		else
		//			new FinestraErrore();
	}

}
