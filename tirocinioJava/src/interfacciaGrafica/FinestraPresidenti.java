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

import classi.BozzaAlgoritmo;
import classi.CommissioneGrafica;
import classi.Docente;
import classi.DocenteComparatorePresidentiMagistrali;
import classi.DocenteComparatorePresidentiTriennali;
import classi.ListaDocenti;
import controller.Controller;
import interfacciaGrafica.listenerBottoni.ListenerConfermaSceltaPresidenti;
import interfacciaGrafica.listenerBottoni.ListenerModificaPresidenti;
import interfacciaGrafica.listenerBottoni.ListenerTornaIndietro;

public class FinestraPresidenti {
	private JFrame f =new JFrame("Finestra Scelta Presidenti");
	private JPanel p = new JPanel();

	//costruttore
	public FinestraPresidenti(Controller c, int numeroTriennali, int numeroMagistrali){
		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.f.setSize(300,300);
		JScrollPane jScrollPane = new JScrollPane(this.p);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.f.getContentPane().add(jScrollPane);
		Box box = Box.createVerticalBox();
		box.add(new JLabel("Potenziali Presidenti Magistrali"));
		box.add(c.calcolaPresidenti(numeroMagistrali,true));
		box.add(new JLabel("Potenziali Presidenti Triennali"));
		box.add(c.calcolaPresidenti(numeroTriennali,false));
		JButton conferma = new JButton("Conferma");
		conferma.addActionListener(new ListenerConfermaSceltaPresidenti(c,this.f,numeroMagistrali,numeroTriennali));
		box.add(conferma);
		JButton modifica = new JButton("Modifica");
		modifica.addActionListener(new ListenerModificaPresidenti(this.f,c,numeroMagistrali,numeroTriennali));
		JButton tornaIndietro = new JButton("Torna Indietro");
		tornaIndietro.addActionListener(new ListenerTornaIndietro(this.f,c));
		box.add(modifica);
		box.add(tornaIndietro);

		this.p.add(box);
		this.f.setVisible(true);
	}

}
