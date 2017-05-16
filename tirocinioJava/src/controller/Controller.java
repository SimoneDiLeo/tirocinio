package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import classi.BozzaAlgoritmo;
import classi.Controrelatore;
import classi.Docente;
import classi.DocenteComparatorePresidentiMagistrali;
import classi.DocenteComparatorePresidentiTriennali;
import classi.ListaCommissioni;
import classi.ListaDocenti;
import classi.Personale;
import classi.Studente;
import interfacciaGrafica.FinestraSceltaNumeroCommissioni;
import interfacciaGrafica.FinestraErrore;
import logica.CaricatoreTuttiFile;

public class Controller {
	public List<Docente> getListaPotenzialiPresidenti() {
		return listaPotenzialiPresidenti;
	}

	public void setListaPotenzialiPresidenti(List<Docente> listaPotenzialiPresidenti) {
		this.listaPotenzialiPresidenti = listaPotenzialiPresidenti;
	}

	private ListaDocenti docenti;
	private List<Studente> studenti;
	private List<Controrelatore> controrel;
	private List<Personale> personale;
	private List<Docente> listaPotenzialiPresidenti;
	private List<Docente> listaPresidentiScartati; 
	private Map<Integer,Docente> presidentiCorrenti=new HashMap<>();
	private ListaCommissioni listaCommissioni;

	public void caricaFile(String nomeFileDocenti,String nomeFileStudenti,String nomeFilePersonale,String nomeFileControrelatori){
		CaricatoreTuttiFile call=new CaricatoreTuttiFile();
		try{
			call.inizializza(nomeFileDocenti,nomeFileStudenti,nomeFilePersonale,nomeFileControrelatori);
			this.docenti=call.getDocenti();
			this.studenti=call.getStudenti();
			this.controrel=call.getControrel();
		}
		catch(Exception e){
			new FinestraErrore();
		}		
	}

	public Box creaBoxLabel() {
		Box box = Box.createVerticalBox(); 
		List<JLabel> docenti=this.docenti.creaLabelDocenti();
		for(JLabel nomeDocente:docenti){
			if(nomeDocente!=null)
				box.add(nomeDocente);
		}
		return box;
	}

	public void calcola(){
		BozzaAlgoritmo b=new BozzaAlgoritmo();
		this.listaPotenzialiPresidenti=b.trovaTuttiPossibiliPresidenti(docenti, "PO");
		this.listaPresidentiScartati=b.trovaTuttiPossibiliPresidenti(docenti, "PO");
	}

	public Box calcolaPresidenti(int numeroCommissioni, boolean c){
		int j=0;
		if(c)
			listaPresidentiScartati.sort(new DocenteComparatorePresidentiMagistrali());
		if(!c){
			listaPresidentiScartati.sort(new DocenteComparatorePresidentiTriennali());
			j=this.presidentiCorrenti.values().size();
		}
		BozzaAlgoritmo b=new BozzaAlgoritmo();
		Box box =Box.createVerticalBox();
		for(int i=0;i<numeroCommissioni;i++){
			try{
				Docente presidente = b.trovaPossibilePresidente(listaPresidentiScartati);
				if(presidente!=null){
					box.add(new JLabel(presidente.toString()));
					this.presidentiCorrenti.put((i+j), presidente);
					listaPresidentiScartati.remove(presidente);
				}
			}
			catch(Exception e){
				box.add(new JLabel("nessun presidente trovato"));
			}
		}
		return box;
	}

	public JComboBox comboBoxPresidentiPotenziali(){
		JComboBox jm = new JComboBox<>();
		for(Docente d:this.listaPotenzialiPresidenti){
			jm.addItem(d);
		}
		return jm;
	}

	public void modificaPresidenteCommissione(int i,Docente d){
		this.presidentiCorrenti.put(i, d);
	}

	public void setListaCommissioni(int nm,int nt){
		listaCommissioni = new ListaCommissioni(nm, nt);
	}

	public void inizializzaCommissioniMagistrali(){
		this.listaCommissioni.inizializzaPresidentiMagistrali(this.presidentiCorrenti);
	}

	public ListaCommissioni getListaCommissioni() {
		return listaCommissioni;
	}

	public void setListaCommissioni(ListaCommissioni listaCommissioni) {
		this.listaCommissioni = listaCommissioni;
	}

	public void reinizializzaDocentiCorrenti() {
		this.presidentiCorrenti=new HashMap<>();
		
	}

	public void inizializzaCommissioniTriennali() {
		this.listaCommissioni.inizializzaPresidentiTriennali(this.presidentiCorrenti);	
	}

}
