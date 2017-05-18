package interfacciaGrafica;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ControllerProprieta;
import interfacciaGrafica.listenerBottoni.ListenerBottonePaginaUno;
import interfacciaGrafica.listenerBottoni.ListenerScriviModifiche;

public class FinestraProprieta {
	public FinestraProprieta(){

		JFrame frame = new JFrame("Schermata Proprieta");
		buildContent(frame);
		frame.setMinimumSize(new Dimension(300, 180));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocation(400, 300);
		frame.setVisible(true);
	}

	private void buildContent(JFrame aFrame) {
		final JPanel panel = new JPanel();
		final JButton okButton = new JButton("Start");
		Box box = Box.createVerticalBox(); 
		ControllerProprieta cp= new ControllerProprieta();
		cp.caricaProprieta();
		box.add(cp.boxProprieta());
		JButton confermaModifiche= new JButton("Conferma Modifiche");
		confermaModifiche.addActionListener(new ListenerScriviModifiche(cp));
		box.add(confermaModifiche);
		panel.add(box);
	//	okButton.addActionListener(new ListenerBottonePaginaUno(aFrame,nomeFileDocenti,nomeFileStudenti,nomeFilePersonale,nomeFileControrelatori));
		aFrame.getContentPane().add(panel);
	}

}
