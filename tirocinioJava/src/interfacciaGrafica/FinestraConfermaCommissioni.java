package interfacciaGrafica;

import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import classi.ListaDocenti;

public class FinestraConfermaCommissioni {
		private JFrame f = new JFrame("Commissioni Confermati");
		private JPanel panel = new JPanel();
		public FinestraConfermaCommissioni(List<JLabel> labelCommissioni) {
			this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.f.setSize(300,300);
			JScrollPane jScrollPane = new JScrollPane(this.panel);
			jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			this.f.getContentPane().add(jScrollPane);
			
			Box box = Box.createVerticalBox();
			for(JLabel jl : labelCommissioni){
				box.add(jl);
			}
			JTextField nomeFileDocenti = new JTextField ();
			JButton scrivi=new JButton("Scrivi in csv");
			scrivi.addActionListener(new FinestraScriviCommissioni(labelCommissioni,nomeFileDocenti));
			box.add(nomeFileDocenti);
			box.add(scrivi);
			this.panel.add(box);
			this.f.setVisible(true);
		}
}
