package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import interfacciaGrafica.FinestraErrore;
import logica.ContenitoreFile;

public class ListenerSalvaFile implements ActionListener {

	
	private JFrame f;
	JFileChooser fileChooser;

	public ListenerSalvaFile(JFrame aFrame) {
		
		this.f=aFrame;
		fileChooser= new JFileChooser();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			JButton b=(JButton) e.getSource();
			
			int n = fileChooser.showOpenDialog(f);
			if (n == JFileChooser.APPROVE_OPTION) {
				File f = fileChooser.getSelectedFile();
			}
		} catch (Exception ex) {
			new FinestraErrore();
		}
	}

}
