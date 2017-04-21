package interfacciaGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import tirocinioJava.classi.Docente;

final class LogicaPaginaUno implements ActionListener {
	private final JFrame fFrame;
	private JTextField docenti;
	private JTextField studenti;
	
	LogicaPaginaUno(final JFrame aFrame,JTextField testoInputDocenti,JTextField testoInputStudenti) {
		fFrame = aFrame;
		docenti=testoInputDocenti;
		studenti=testoInputStudenti;
	}

	@Override
	public void actionPerformed(final ActionEvent aEvent) {
		String nomeFileDocenti=this.docenti.getText();
		String nomeFileStudenti=this.studenti.getText();
		ProvaGuiChiamata call=new ProvaGuiChiamata();

		call.inizializza(nomeFileDocenti,nomeFileStudenti);
		List<Docente> docenti=call.getDocenti();
		List<JLabel> nomiDocenti=new ArrayList<>();
		for(Docente d:docenti){ 	
			JLabel nomeDocente = new JLabel(d.getNome()+"\n numero laureandi Triennali : "+d.getNumeroLaureandiTriennali() +"\n numero laureandi Magistrali : " + d.getNumeroLaureandiMagistrali() + "\n Disponibilità : " +d.getDisponibilita());
			nomiDocenti.add(nomeDocente);
			}

		fFrame.dispose();
		new FinestraDue(nomiDocenti,docenti);
	}

}