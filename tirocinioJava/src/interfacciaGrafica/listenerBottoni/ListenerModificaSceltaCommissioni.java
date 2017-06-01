package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import controller.Controller;
import interfacciaGrafica.FinestraSceltaCommissioni2;

public class ListenerModificaSceltaCommissioni implements ActionListener  {
	private Controller c;
	private JFrame f;
	private int numeroMagistrali;
	private int numeroTriennali;

	public ListenerModificaSceltaCommissioni(JFrame f,Controller c,int numeroMagistrali,int numeroTriennali) {
		this.c=c;
		this.f=f;
		this.numeroMagistrali=numeroMagistrali;
		this.numeroTriennali=numeroTriennali;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	
		c.addFrameCorrente(f);
		f.dispose();
		new FinestraSceltaCommissioni2(c,numeroMagistrali,numeroTriennali);

	}

	
}


