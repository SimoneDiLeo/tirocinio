package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import interfacciaGrafica.FinestraErrore;
import logica.ContenitoreFile;

public class ListenerSelettoreFile implements ActionListener {

	private ContenitoreFile cf;
	private JFrame f;
	JFileChooser fileChooser;

	public ListenerSelettoreFile(ContenitoreFile cf, JFrame aFrame) {
		this.cf=cf;
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
				if(b.getName().equals("doc")){
					this.cf.setFileDocente(f);
				}
				if(b.getName().equals("stud")){
					this.cf.setFileStudente(f);
				}
				if(b.getName().equals("pers")){
					this.cf.setFilePersonale(f);
				}
				if(b.getName().equals("contro")){
					this.cf.setFileControrelatori(f);
				}
			}
		} catch (Exception ex) {
			new FinestraErrore();
		}
	}

}
