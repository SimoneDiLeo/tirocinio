package interfacciaGrafica;

import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import controller.Controller;
import interfacciaGrafica.listenerBottoni.AvanzaPerSalvare;
import interfacciaGrafica.listenerBottoni.ListenerSalvaFile;
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

		//		scrivi.addActionListener(new ListenerScriviCommissioni(l,nomeFileDocenti));
		JButton salva=new JButton("salva");
		salva.addActionListener(new ListenerSalvaFile(this.f));
		JButton ti=new JButton("Torna Indietro");
		ti.addActionListener(new ListenerTornaIndietro(f, c));;
		box.add(ti);
		JButton b = new JButton("salva");
		b.addActionListener(new AvanzaPerSalvare(this.f,c));
		box.add(b);
		this.panel.add(box);
		this.f.setVisible(true);
	}
}
