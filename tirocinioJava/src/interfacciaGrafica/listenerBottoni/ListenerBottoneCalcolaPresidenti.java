package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.Controller;
import interfacciaGrafica.FinestraErrore;
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
		this.c.resettaColoreLabelDocenti();
		try{
			int numeroMagistrali= Integer.valueOf(this.numeroMagistrali.getText());
			int numeroTriennali=Integer.valueOf(this.numeroTriennali.getText());
			this.c.calcola();
			c.addFrameCorrente(this.sFrame);
			this.sFrame.dispose();
			new FinestraPresidenti(this.c,numeroTriennali,numeroMagistrali);}
		catch(Exception err){
			this.c.addFrameCorrente(this.sFrame);
			this.sFrame.dispose();
			new FinestraErrore(this.c);
		}
	}

}
