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
		Docente[] presidentiPotenziali = new Docente[numMag+numTri];
		//inserire mappa int docente che indica int = numero commissione e commissario relativo
		//modificare anche i for di conseguenza
		box.add(new JLabel("PRESIDENTI MAGISTRALI"));
		int indicePresidente=0;
		for(JComboBox jm:listaMagistrali){
			jm.setSelectedIndex(-1);
			presidentiPotenziali[indicePresidente]=((Docente) jm.getSelectedItem());
			jm.addActionListener(new SelezionaDocente(presidentiPotenziali,indicePresidente));
			box.add(jm);
			indicePresidente++;
		}

		box.add(new JLabel("PRESIDENTI TRIENNALI"));
		for(JComboBox jt:listaTriennali){
			jt.setSelectedIndex(-1);
			presidentiPotenziali[indicePresidente]=((Docente) jt.getSelectedItem());
			jt.addActionListener(new SelezionaDocente(presidentiPotenziali, indicePresidente));
			box.add(jt);
			indicePresidente++;
		}
		JButton conferma = new JButton("CONFERMA");
		box.add(conferma);
		conferma.addActionListener(new ChiamataModifica(this.f,docenti, presidentiPotenziali, numTri, numMag));
		panel.add(box);


	}



}