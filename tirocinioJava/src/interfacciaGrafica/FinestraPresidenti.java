package interfacciaGrafica;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import classi.Commissione;
import classi.Docente;
import classi.ListaDocenti;
import controller.Controller;
import interfacciaGrafica.listenerBottoni.ListenerConfermaSceltaPresidenti;
import interfacciaGrafica.listenerBottoni.ListenerModificaPresidenti;
import interfacciaGrafica.listenerBottoni.ListenerTornaIndietro;
import logica.BozzaAlgoritmo;
import logica.DocenteComparatorePresidentiMagistrali;
import logica.DocenteComparatorePresidentiTriennali;

public class FinestraPresidenti {
	private JFrame f =new JFrame("Finestra Scelta Presidenti");
	private JPanel p = new JPanel();

	//costruttore
	public FinestraPresidenti(Controller c, int numeroTriennali, int numeroMagistrali){
		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.f.setSize(600,600);
		this.f.setLocation(500, 300);
		JScrollPane jScrollPane = new JScrollPane(this.p);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.f.getContentPane().add(jScrollPane);
		Box box = Box.createHorizontalBox();
		Box boxDestra = Box.createVerticalBox();
		Box boxSinistra = Box.createVerticalBox();
		box.add(boxSinistra);
		box.add(boxDestra);
		boxDestra.add(new JLabel("Potenziali Presidenti Magistrali"));
		boxDestra.add(c.calcolaPresidenti(numeroMagistrali,true));
		boxDestra.add(new JLabel("Potenziali Presidenti Triennali"));
		boxDestra.add(c.calcolaPresidenti(numeroTriennali,false));
		JButton conferma = new JButton("Conferma");
		conferma.addActionListener(new ListenerConfermaSceltaPresidenti(c,this.f,numeroMagistrali,numeroTriennali));
		boxDestra.add(conferma);
		JButton modifica = new JButton("Modifica");
		modifica.addActionListener(new ListenerModificaPresidenti(this.f,c,numeroMagistrali,numeroTriennali));
		JButton tornaIndietro = new JButton("Torna Indietro");
		tornaIndietro.addActionListener(new ListenerTornaIndietro(this.f,c));
		boxDestra.add(modifica);
		boxDestra.add(tornaIndietro);
		for(JLabel l : c.getLabelGiorni()){
			boxSinistra.add(l);
		}

		this.p.add(box);
		this.f.setVisible(true);
	}

}
