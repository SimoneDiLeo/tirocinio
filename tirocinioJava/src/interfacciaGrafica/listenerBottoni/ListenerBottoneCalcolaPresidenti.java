package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import classi.Docente;
import classi.ListaDocenti;
import controller.Controller;
import interfacciaGrafica.FinestraSceltaNumeroCommissioni;
import logica.BozzaAlgoritmo;
import logica.DocenteComparatorePresidentiMagistrali;
import interfacciaGrafica.FinestraErrore;
import interfacciaGrafica.FinestraPresidenti;
import interfacciaGrafica.FinestraSceltaCommissioni;

public class ListenerBottoneCalcolaPresidenti implements ActionListener{
	private Controller c;
	private JFrame sFrame;
	private JTextField numeroTriennali;
	private JTextField numeroMagistrali;



	public ListenerBottoneCalcolaPresidenti(JTextField numTriennali, JTextField numMagistrali, Controller c, JFrame f) {
		this.c=c;
		this.numeroMagistrali=numMagistrali;
		this.numeroTriennali=numTriennali;
		this.sFrame=f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int numeroMagistrali= Integer.valueOf(this.numeroMagistrali.getText());
		int numeroTriennali=Integer.valueOf(this.numeroTriennali.getText());
		this.sFrame.dispose();
		this.c.calcola();
		c.addFrameCorrente(sFrame);
		new FinestraPresidenti(this.c,numeroTriennali,numeroMagistrali);
	}

}
