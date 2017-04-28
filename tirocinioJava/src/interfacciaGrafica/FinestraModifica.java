package interfacciaGrafica;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import classi.Docente;
import classi.ListaDocenti;
import interfacciaGrafica.logicaChiamate.ChiamataModifica;

public class FinestraModifica {
	private JFrame f = new JFrame("Second");
	private JPanel panel = new JPanel();

	public FinestraModifica(ListaDocenti docenti, List<JComboBox> listaMagistrali, List<JComboBox> listaTriennali, int numMag, int numTri) {

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(300,300);
		JScrollPane jScrollPane = new JScrollPane(panel);

		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


		f.getContentPane().add(jScrollPane);
		f.setVisible(true);
		Box box = Box.createVerticalBox(); 
		List<Docente> presidentiPotenziali = new ArrayList<>();
		//inserire mappa int docente che indica int = numero commissione e commissario relativo
		//modificare anche i for di conseguenza
		box.add(new JLabel("PRESIDENTI MAGISTRALI"));
		
		for(JComboBox jm:listaMagistrali){
			presidentiPotenziali.add((Docente) jm.getSelectedItem());
			jm.addActionListener(new SelezionaDocente(presidentiPotenziali,(Docente)jm.getSelectedItem()));
			box.add(jm);
		}

		box.add(new JLabel("PRESIDENTI TRIENNALI"));
		
		for(JComboBox jt:listaTriennali){
			presidentiPotenziali.add((Docente) jt.getSelectedItem());
			jt.addActionListener(new SelezionaDocente(presidentiPotenziali,(Docente)jt.getSelectedItem()));
			box.add(jt);
		}
		JButton conferma = new JButton("CONFERMA");
		box.add(conferma);
		conferma.addActionListener(new ChiamataModifica(this.f,docenti, presidentiPotenziali, numTri, numMag));
		panel.add(box);


	}



}
