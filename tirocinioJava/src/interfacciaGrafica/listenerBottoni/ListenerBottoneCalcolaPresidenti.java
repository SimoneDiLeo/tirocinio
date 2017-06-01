package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.Controller;
import interfacciaGrafica.FinestraPresidenti;

public class ListenerBottoneCalcolaPresidenti implements ActionListener{
	private Controller c;
	private JFrame sFrame;
	private JTextField numeroTriennali;
	private JTextField numeroMagistrali;



	public ListenerBottoneCalcolaPresidenti(JTextField numTriennali, JTextField numMagistrali, Controller c, JFrame f) {
		this.c=c;
		this.numeroMagistrali=numMagistrali;
		this.numeroTriennali=numTriennali;
		this.sFrame=f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.c.settaEccessoStudenti();
		int numeroMagistrali= Integer.valueOf(this.numeroMagistrali.getText());
		int numeroTriennali=Integer.valueOf(this.numeroTriennali.getText());
		this.sFrame.dispose();
		this.c.calcola();
		c.addFrameCorrente(sFrame);
		new FinestraPresidenti(this.c,numeroTriennali,numeroMagistrali);
		
	}

}
