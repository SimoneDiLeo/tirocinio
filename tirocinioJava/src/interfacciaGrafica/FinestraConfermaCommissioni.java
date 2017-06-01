package interfacciaGrafica;

import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.Controller;
import interfacciaGrafica.listenerBottoni.ListenerScriviCommissioni;
import interfacciaGrafica.listenerBottoni.ListenerTornaIndietro;

public class FinestraConfermaCommissioni {
	private JFrame f = new JFrame("Commissioni Confermate");
	private JPanel panel = new JPanel();
	public FinestraConfermaCommissioni(Controller c) {
		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.f.setSize(300,300);
		JScrollPane jScrollPane = new JScrollPane(this.panel);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.f.getContentPane().add(jScrollPane);

		Box box = Box.createVerticalBox();
		List<JLabel> l=c.calcoloLabelCommissioniConfermate();
		for(JLabel j:l)
			box.add(j);
		JTextField nomeFileDocenti = new JTextField ();
		JButton scrivi=new JButton("Scrivi in csv");
		scrivi.addActionListener(new ListenerScriviCommissioni(l,nomeFileDocenti));
		
		JButton ti=new JButton("Torna Indietro");
		ti.addActionListener(new ListenerTornaIndietro(f, c));;
		box.add(ti);
		
		box.add(nomeFileDocenti);
		box.add(scrivi);
		this.panel.add(box);
		this.f.setVisible(true);
	}
}
