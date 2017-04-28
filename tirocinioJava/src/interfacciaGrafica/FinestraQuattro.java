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
import interfacciaGrafica.logicaChiamate.ConfermaSceltaPresidenti;
import interfacciaGrafica.logicaChiamate.ModificaPresidenti;
import interfacciaGrafica.logicaChiamate.tornaIndietro;

public class FinestraQuattro {
	private JFrame f =new JFrame("terzo");
	private JPanel p = new JPanel();

	//costruttore
	public FinestraQuattro(ListaDocenti docenti, List<Docente> presidentiPotenziali, int numeroTriennali, int numeroMagistrali, JFrame sFrame){

		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.f.setSize(300,300);
		JScrollPane jScrollPane = new JScrollPane(this.p);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.f.getContentPane().add(jScrollPane);
		Box box = Box.createVerticalBox();
		List<Docente> presidentiScelti=new ArrayList<>();
		Docente presidenti = null;
		Docente d=null;
		BozzaAlgoritmo alg=new BozzaAlgoritmo();

		box.add(new JLabel("Potenziali Presidenti Magistrali"));
		presidentiPotenziali.sort(new DocenteComparatorePresidentiMagistrali());

		for(int i=0;i<numeroMagistrali;i++){
			presidenti = trovaPresidente(presidentiPotenziali, d, alg);
			box.add(new JLabel(presidenti.toString()));
			presidentiScelti.add(presidenti);
			presidentiPotenziali.remove(presidenti);
		}

		presidentiPotenziali.sort(new DocenteComparatorePresidentiTriennali());
		box.add(new JLabel("Potenziali Presidenti Triennali"));
		d=null;

		for(int i=0;i<numeroTriennali;i++){
			presidenti = trovaPresidente(presidentiPotenziali, d, alg);
			if(presidenti!=null)
			box.add(new JLabel(presidenti.toString()));
			else box.add(new JLabel("Presidente Non Trovato"));
			presidentiScelti.add(presidenti);
			presidentiPotenziali.remove(presidenti);
		}

		JButton conferma = new JButton("Conferma");
		conferma.addActionListener(new ConfermaSceltaPresidenti(docenti,presidentiScelti,this.f,numeroMagistrali,numeroTriennali));
		box.add(conferma);

		JButton modifica = new JButton("Modifica");
		List<Docente> listaPresidentiPotenzialiRiunita = presidentiPotenziali;
		listaPresidentiPotenzialiRiunita.addAll(presidentiScelti);
		modifica.addActionListener(new ModificaPresidenti(this.f,listaPresidentiPotenzialiRiunita,numeroMagistrali,numeroTriennali,docenti));
		JButton tornaIndietro = new JButton("Torna Indietro");
		tornaIndietro.addActionListener(new tornaIndietro(this.f,sFrame));
		box.add(modifica);
		box.add(tornaIndietro);

		this.p.add(box);
		this.f.setVisible(true);
	}


	private Docente trovaPresidente(List<Docente> presidentiPotenziali, Docente d, BozzaAlgoritmo alg) {
		Docente presidenti=null;
		if(presidentiPotenziali.size()>0){
			if(d!=null)
				presidentiPotenziali.remove(d);
		presidenti= alg.trovaPossibilePresidente(presidentiPotenziali);}
		return presidenti;
	}
}
