package interfacciaGrafica;

import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import classi.Docente;
import classi.ListaDocenti;
import interfacciaGrafica.logicaChiamate.LogicaPaginaDue;

public class FinestraDue {
	private JFrame f = new JFrame("Second");
	private JPanel panel = new JPanel();
	
	//costruttore
	public FinestraDue(List<JLabel> nomiDocenti, ListaDocenti docenti) {


		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(300,300);
		JScrollPane jScrollPane = new JScrollPane(panel);

		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
	
		f.getContentPane().add(jScrollPane);
		f.setVisible(true);
		Box box = Box.createVerticalBox(); 
		JButton provaPrimaCommissione=new JButton("Calcola le possibili commissioni");
	
		for(JLabel bot:nomiDocenti){
			box.add(bot);
		}
		
		box.add(new JLabel("Numero Commissioni Triennali"));
		JTextField numTriennali = new JTextField();
		box.add(numTriennali);
		box.add(new JLabel("Numero Commissioni Magistrali"));
		JTextField numMagistrali= new JTextField();
		box.add(numMagistrali);
		box.add(provaPrimaCommissione);
		provaPrimaCommissione.addActionListener(new LogicaPaginaDue(numTriennali,numMagistrali,docenti,f));
			
		panel.add(box);
	}

}
