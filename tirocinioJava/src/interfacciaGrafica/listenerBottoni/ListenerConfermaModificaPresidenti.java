package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import controller.Controller;
import interfacciaGrafica.FinestraSceltaCommissioni;
import interfacciaGrafica.FinestraSceltaCommissioni1;

public class ListenerConfermaModificaPresidenti implements ActionListener {

	private JFrame f;
	private Controller c;
	private int numTri;
	private int numMag;
	private Map<Integer, JComboBox> mappa;


	public ListenerConfermaModificaPresidenti(JFrame f, Controller c, int numTri,	int numMag, Map<Integer, JComboBox> mappaturaPresidenteCommissione) {
		this.f=f;
		this.c=c;
		this.numTri=numTri;
		this.numMag=numMag;
		this.mappa=mappaturaPresidenteCommissione;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		c.addFrameCorrente(f);
		c.setCommissariDaMappa(this.mappa);
		new FinestraSceltaCommissioni1(c,numMag, numTri);
		this.f.dispose();
	}

}
