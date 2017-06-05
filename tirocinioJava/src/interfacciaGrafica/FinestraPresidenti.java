package interfacciaGrafica;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import classi.Docente;
import controller.Controller;
import interfacciaGrafica.listenerBottoni.ListenerConfermaSceltaPresidenti1;
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
		Box box = Box.createVerticalBox();

		box.add(new JLabel("Potenziali Presidenti"));
		for(Docente d:c.getDocentiOrdinari()){
			box.add(new JLabel(d.toString()));
		}
		box.add(new JLabel("-----------------------------------------------------------------"));
		box.add(new JLabel("-----------------------------------------------------------------"));
		box.add(new JLabel("Scelta dei Presidenti Magistrali"));
		box.add(c.calcolaPresidenti(numeroMagistrali,true));
		box.add(new JLabel("-----------------------------------------------------------------"));
		box.add(new JLabel("Scelta dei Presidenti Triennali"));
		box.add(new JLabel("-----------------------------------------------------------------"));
		box.add(c.calcolaPresidenti(numeroTriennali,false));
		JButton conferma = new JButton("Conferma");
		conferma.addActionListener(new ListenerConfermaSceltaPresidenti1(c,this.f,numeroMagistrali,numeroTriennali));
		box.add(conferma);
		JButton tornaIndietro = new JButton("Torna Indietro");
		tornaIndietro.addActionListener(new ListenerTornaIndietro(this.f,c));
		box.add(tornaIndietro);
		this.p.add(box);
		this.f.setVisible(true);
	}
	//	JButton modifica = new JButton("Modifica");
	//	modifica.addActionListener(new ListenerModificaPresidenti(this.f,c,numeroMagistrali,numeroTriennali));
	//	boxDestra.add(modifica);
}
