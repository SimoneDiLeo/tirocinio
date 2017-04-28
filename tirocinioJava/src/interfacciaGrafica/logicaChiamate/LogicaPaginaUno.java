package interfacciaGrafica.logicaChiamate;

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

import classi.Docente;
import classi.ListaDocenti;
import interfacciaGrafica.FinestraDue;
import interfacciaGrafica.FinestraErrore;

public final class LogicaPaginaUno implements ActionListener {
	private final JFrame fFrame;
	private JTextField docenti;
	private JTextField studenti;
	private JTextField personale;
	private JTextField controrelatori;

	public LogicaPaginaUno(final JFrame aFrame,JTextField testoInputDocenti,JTextField testoInputStudenti, JTextField nomeFilePersonale, JTextField nomeFileControrelatori){
		fFrame = aFrame;
		docenti=testoInputDocenti;
		studenti=testoInputStudenti;
		personale=nomeFilePersonale;
		controrelatori=nomeFileControrelatori;
	}

	@Override
	public void actionPerformed(final ActionEvent aEvent) {
		String nomeFileDocenti=this.docenti.getText();
		String nomeFileStudenti=this.studenti.getText();
		String nomeFilePersonale=this.personale.getText();
		String nomeFileControrelatori=this.controrelatori.getText();
		CaricatoreTuttiFile call=new CaricatoreTuttiFile();
		boolean tuttoBene=true;
		try{
			call.inizializza(nomeFileDocenti,nomeFileStudenti,nomeFilePersonale,nomeFileControrelatori);
		}
		catch(Exception e){
			tuttoBene=false;
		}
		
		if(tuttoBene){

			ListaDocenti docenti=call.getDocenti();
			List<JLabel> nomiDocenti=new ArrayList<>();
			for(Docente d:docenti.getDocenti()){ 	
				JLabel nomeDocente = new JLabel(d.getNome()+ " Ruolo = "+ d.getRuolo() + " Numero laureandi Triennali : "+d.getNumeroLaureandiTriennali() +" Numero laureandi Magistrali : " + d.getNumeroLaureandiMagistrali() + " Disponibilità : " +d.getDisponibilita());
				nomiDocenti.add(nomeDocente);
			}

			fFrame.dispose();
			new FinestraDue(nomiDocenti,docenti);
		}
		else
			new FinestraErrore();
	}

}