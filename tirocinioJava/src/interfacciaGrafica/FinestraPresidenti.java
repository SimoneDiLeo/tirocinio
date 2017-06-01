package interfacciaGrafica;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Controller;
import interfacciaGrafica.listenerBottoni.ListenerConfermaSceltaPresidenti1;
import interfacciaGrafica.listenerBottoni.ListenerModificaPresidenti;
import interfacciaGrafica.listenerBottoni.ListenerTornaIndietro;

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
		//conferma.addActionListener(new ListenerConfermaSceltaPresidenti(c,this.f,numeroMagistrali,numeroTriennali));
		conferma.addActionListener(new ListenerConfermaSceltaPresidenti1(c,this.f,numeroMagistrali,numeroTriennali));
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
