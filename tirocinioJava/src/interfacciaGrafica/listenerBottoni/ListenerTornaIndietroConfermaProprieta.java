package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import controller.ControllerProprieta;

public class ListenerTornaIndietroConfermaProprieta implements ActionListener {

	private ControllerProprieta c;
	private JFrame f;

	public ListenerTornaIndietroConfermaProprieta(ControllerProprieta c, JFrame aFrame) {
		this.c=c;
		this.f=aFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.f.dispose();
		this.c.tornaIndietro();
		
	}

}
