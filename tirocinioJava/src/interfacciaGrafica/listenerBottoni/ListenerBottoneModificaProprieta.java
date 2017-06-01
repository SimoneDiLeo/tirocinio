package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.ControllerProprieta;
import interfacciaGrafica.FinestraProprieta;

public class ListenerBottoneModificaProprieta implements ActionListener {

	private ControllerProprieta f;

	public ListenerBottoneModificaProprieta(ControllerProprieta co) {
		f=co;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new FinestraProprieta(f);
	}

}
