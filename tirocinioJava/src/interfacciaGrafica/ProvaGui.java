package interfacciaGrafica;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import javax.swing.JTextField;

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
		JTextField nomeFileStudenti = new JTextField ();
		nomeFileStudenti.setSize(100, 50);

		final JButton okButton = new JButton("Start");
		Box box = Box.createVerticalBox(); 
		box.add(new JLabel("Inserici in nome del file dei docenti"));
		box.add(nomeFileDocenti);
		box.add(new JLabel("Inserici in nome del file degli studenti"));
		box.add(nomeFileStudenti);
		box.add(okButton);
		panel.add(box);
		okButton.addActionListener(new LogicaPaginaUno(aFrame,nomeFileDocenti,nomeFileStudenti));
		aFrame.getContentPane().add(panel);
	}

}