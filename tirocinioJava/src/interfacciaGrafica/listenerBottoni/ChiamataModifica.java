package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import classi.Docente;
import classi.ListaDocenti;
import controller.Controller;
import interfacciaGrafica.FinestraErrore;
import interfacciaGrafica.FinestraModifica;
import interfacciaGrafica.FinestraSceltaCommissioni;

public class ChiamataModifica implements ActionListener {

	private JFrame f;
	private Controller c;
	private int numTri;
	private int numMag;


	public ChiamataModifica(JFrame f, Controller c, int numTri,	int numMag) {
		this.f=f;
		this.c=c;
		this.numTri=numTri;
		this.numMag=numMag;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		new FinestraSceltaCommissioni(c,numMag, numTri);
		this.f.dispose();
	}

}
