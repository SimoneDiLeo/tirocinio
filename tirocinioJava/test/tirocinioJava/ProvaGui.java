package tirocinioJava;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

import interfacciaGrafica.logicaChiamate.LogicaPaginaUno;
import tirocinioJava.classi.Docente;


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
		JComboBox okButton = new JComboBox();
		DefaultComboBoxModel model = (DefaultComboBoxModel) okButton.getModel();
		for(String i : a)
		model.addElement(i);
		okButton.setModel(model);
		Box box = Box.createVerticalBox(); 
		box.add(new JLabel("PROVA"));
		box.add(nomeFileDocenti);
		box.add(okButton);
		DefaultListModel modelloLista= new DefaultListModel<>();
		JList lista= new JList(modelloLista);
		box.add(lista);
		panel.add(box);
		
		okButton.addActionListener(new ProvaUpdateFrame(model,modelloLista));
		aFrame.getContentPane().add(panel);
	}

}