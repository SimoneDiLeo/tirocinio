package interfacciaGrafica;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controller.ControllerProprieta;
import interfacciaGrafica.listenerBottoni.ListenerScriviModifiche;
import interfacciaGrafica.listenerBottoni.ListenerTornaIndietroConfermaProprieta;

public class FinestraProprieta {
	public FinestraProprieta(ControllerProprieta f){

		JFrame frame = new JFrame("Schermata Proprieta");
		buildContent(frame,f);
		frame.setMinimumSize(new Dimension(300, 180));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocation(400, 300);
		frame.setVisible(true);
	}

	private void buildContent(JFrame aFrame,ControllerProprieta c) {
		final JPanel panel = new JPanel();
		Box box = Box.createVerticalBox(); 
		box.add(c.boxProprieta());
		JButton confermaModifiche= new JButton("Conferma Modifiche");
		confermaModifiche.addActionListener(new ListenerScriviModifiche(c));
		Box box2 = Box.createHorizontalBox();
		box2.add(confermaModifiche);
		JButton tornaIndietro=new JButton("Torna Indietro");
		tornaIndietro.addActionListener(new ListenerTornaIndietroConfermaProprieta(c,aFrame));
		box2.add(tornaIndietro);
		box.add(box2);
		panel.add(box);
		aFrame.getContentPane().add(panel);
	}

}
