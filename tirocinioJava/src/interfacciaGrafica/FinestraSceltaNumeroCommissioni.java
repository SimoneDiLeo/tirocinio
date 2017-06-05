package interfacciaGrafica;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.Controller;
import interfacciaGrafica.listenerBottoni.ListenerBottoneCalcolaPresidenti;
import interfacciaGrafica.listenerBottoni.ListenerTornaIndietro;

public class FinestraSceltaNumeroCommissioni {
	private JFrame f = new JFrame("Seconda Schermata");
	private JPanel panel = new JPanel();
	
	//costruttore
	public FinestraSceltaNumeroCommissioni(Controller c) {

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800,1100);
		f.setLocation(400, 0);
		JScrollPane jScrollPane = new JScrollPane(panel);

		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
	
		f.getContentPane().add(jScrollPane);
		f.setVisible(true);
		Box box = Box.createVerticalBox(); 
		JButton provaPrimaCommissione=new JButton("Calcola i possibili Presidenti di Commissione");
		JLabel labelDescrizioneSlot = new JLabel("Descrizione Slot di disponibilita");
		labelDescrizioneSlot.setForeground(Color.BLUE);
		box.add(labelDescrizioneSlot);
		for(JLabel l : c.getLabelGiorni()){
			box.add(l);
		}
		box.add(new JLabel("--------------------------------------------------------------------"));
		JLabel jlabelconScrittoCommissari = new JLabel("COMMISSARI");
		jlabelconScrittoCommissari.setForeground(new Color(121, 10, 232));
		box.add(jlabelconScrittoCommissari);
		box.add(new JLabel("--------------------------------------------------------------------"));
		box.add(c.creaBoxLabel());;
		box.add(new JLabel("--------------------------------------------------------------------"));
		box.add(new JLabel("numero studenti magistrali : " +c.calcolaStudentiTipo("magistrale")));
		box.add(new JLabel("numero studenti triennali : " +c.calcolaStudentiTipo("triennale")));
		box.add(new JLabel("--------------------------------------------------------------------"));
		box.add(new JLabel("Numero Commissioni Triennali"));
		JTextField numTriennali = new JTextField();
		box.add(numTriennali);
		box.add(new JLabel("Numero Commissioni Magistrali"));
		JTextField numMagistrali= new JTextField();
		box.add(numMagistrali);
		box.add(new JLabel("--------------------------------------------------------------------"));
		box.add(provaPrimaCommissione);
		
		JButton tornaIndietro=new JButton("Torna Indietro");
		tornaIndietro.addActionListener(new ListenerTornaIndietro(f, c));
		box.add(new JLabel("--------------------------------------------------------------------"));
		box.add(tornaIndietro);		
		provaPrimaCommissione.addActionListener(new ListenerBottoneCalcolaPresidenti(numTriennali,numMagistrali,c,f));
		panel.add(box);
	}

}
