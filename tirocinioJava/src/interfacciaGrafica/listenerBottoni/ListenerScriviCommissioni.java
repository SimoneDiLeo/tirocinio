package interfacciaGrafica.listenerBottoni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

import gestoreFile.scritturaFile;

public class ListenerScriviCommissioni implements ActionListener {
private List<JLabel> lista;
private JTextField nomeFile;

	public ListenerScriviCommissioni(List<JLabel> labelCommissioni, JTextField nomeFileDocenti) {
		this.lista=labelCommissioni;
		this.nomeFile=nomeFileDocenti;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		scritturaFile scrittore =new scritturaFile();
		List<String> listaStringhe=new ArrayList<>();
		for(JLabel jl : this.lista){
			listaStringhe.add(jl.getText());
		}
		try {
			scrittore.scrivi(this.nomeFile.getText(), listaStringhe);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
