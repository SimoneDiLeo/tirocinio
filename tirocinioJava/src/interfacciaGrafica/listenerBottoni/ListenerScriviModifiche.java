package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.ControllerProprieta;

public class ListenerScriviModifiche implements ActionListener {

	private ControllerProprieta c;

	public ListenerScriviModifiche(ControllerProprieta cp) {
		this.c=cp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		c.scriviProprieta();
	}

}
