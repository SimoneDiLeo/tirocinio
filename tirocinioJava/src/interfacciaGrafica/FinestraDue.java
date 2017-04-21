package interfacciaGrafica;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import tirocinioJava.classi.Docente;

public class FinestraDue {
	private JFrame f = new JFrame("Second");
	private JPanel panel = new JPanel();


	//costruttore
	public FinestraDue(List<JLabel> nomiDocenti, List<Docente> docenti) {


		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(300,300);
		JScrollPane jScrollPane = new JScrollPane(panel);

		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		f.getContentPane().add(jScrollPane);
		f.setVisible(true);
		Box box = Box.createVerticalBox(); 
		JButton provaPrimaCommissione=new JButton("Prova prima commissione");
		List<Docente> docDisponibiliPrimaCommissione=new ArrayList<>();
		for(Docente d:docenti){
			for(Integer i:d.getDisponibilita())
				if(i==2)
				docDisponibiliPrimaCommissione.add(d);
		}
		provaPrimaCommissione.addActionListener(new LogicaPaginaDue(docDisponibiliPrimaCommissione, f));
		
		for(JLabel bot:nomiDocenti){
			box.add(bot);
		}
		box.add(provaPrimaCommissione);
		panel.add(box);
	}

}
