package tirocinioJava;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import javax.swing.JTextField;

import classi.Docente;
import interfacciaGrafica.listenerBottoni.ListenerBottonePaginaUno;


public final class ProvaGui {


	public static void main(final String[] args) {
		final ProvaGui app = new ProvaGui();

		app.buildAndDisplayGui();
	}

	private void buildAndDisplayGui() {

		final JFrame frame = new JFrame("Prova");

		buildContent(frame);
		frame.setMinimumSize(new Dimension(300, 180));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocation(400, 300);
		frame.setVisible(true);
	}


	private void buildContent(final JFrame aFrame) {
		final JPanel panel = new JPanel();

		JTextField nomeFileDocenti = new JTextField ();
		nomeFileDocenti.setSize(100, 50);
		String [] a= {"1","2"};
		JComboBox okButton;
		Box box = Box.createVerticalBox(); 
		box.add(new JLabel("PROVA"));
		box.add(nomeFileDocenti);
		Map<JComboBox,DefaultComboBoxModel> prova= new HashMap<>();
		DefaultListModel modelloLista= new DefaultListModel<>();
		JList lista= new JList(modelloLista);
		for(int i =0; i<3;i++){
		okButton = new JComboBox();
		 prova.put(okButton,(DefaultComboBoxModel) okButton.getModel());
		for(String s : a)
			prova.get(okButton).addElement(s);
		okButton.setModel(prova.get(okButton));
		box.add(okButton);
		okButton.addActionListener(new ProvaUpdateFrame(prova,modelloLista));
		}
		
		
		
		
		box.add(lista);
		panel.add(box);

		
		aFrame.getContentPane().add(panel);
	}

}