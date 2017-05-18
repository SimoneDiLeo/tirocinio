package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import interfacciaGrafica.FinestraProprieta;

public class ListenerBottoneModificaProprieta implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new FinestraProprieta();
	}

}
