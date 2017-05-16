package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class ListenerTornaIndietro implements ActionListener {
	private JFrame frameChiamata;
	private JFrame frameDaChiamare;
	
	public ListenerTornaIndietro(JFrame f, JFrame sFrame) {
		this.frameChiamata= f;
		this.frameDaChiamare=sFrame;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.frameChiamata.dispose();
		this.frameDaChiamare.setVisible(true);
	}

}
