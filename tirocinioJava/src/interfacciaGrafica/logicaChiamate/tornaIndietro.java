package interfacciaGrafica.logicaChiamate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class tornaIndietro implements ActionListener {
	private JFrame frameChiamata;
	private JFrame frameDaChiamare;
	
	public tornaIndietro(JFrame f, JFrame sFrame) {
		this.frameChiamata= f;
		this.frameDaChiamare=sFrame;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.frameChiamata.dispose();
		this.frameDaChiamare.setVisible(true);
	}

}
