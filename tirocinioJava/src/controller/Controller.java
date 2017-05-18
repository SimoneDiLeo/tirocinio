package controller;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import classi.BozzaAlgoritmo;
import classi.CommissioneGrafica;
import classi.Controrelatore;
import classi.Docente;
import classi.DocenteComparatorePresidentiMagistrali;
import classi.DocenteComparatorePresidentiTriennali;
import classi.ListaCommissioni;
import classi.ListaDocenti;
import classi.Personale;
import classi.Studente;
import interfacciaGrafica.FinestraSceltaNumeroCommissioni;
import interfacciaGrafica.renderer.ComboBoxRendererCommissari;
import interfacciaGrafica.FinestraErrore;
import logica.CaricatoreTuttiFile;

public class Controller {
	private ListaDocenti docenti;
	private List<Studente> studenti;
	private List<Controrelatore> controrel;
	private List<Personale> personale;
	private List<Docente> listaPotenzialiPresidenti;
	private List<Docente> listaPresidentiScartati; 
	private Map<Integer,Docente> presidentiCorrenti=new HashMap<>();
	private ListaCommissioni listaCommissioni;
	private List<JFrame> framesPrecedente=new ArrayList<>();

	public void caricaFile(String nomeFileDocenti,String nomeFileStudenti,String nomeFilePersonale,String nomeFileControrelatori){
		CaricatoreTuttiFile call=new CaricatoreTuttiFile();
		try{
			call.inizializza(nomeFileDocenti,nomeFileStudenti,nomeFilePersonale,nomeFileControrelatori);
			this.docenti=call.getDocenti();
			this.studenti=call.getStudenti();
			this.controrel=call.getControrel();
		}
		catch(Exception e){
			new FinestraErrore(this);
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

	public void aggiornaCommissione(CommissioneGrafica cgm) {
		cgm.inizializzaCommissariCommissione(this.docenti);

	}

	public Box creaJComboCommissari(CommissioneGrafica cgm){
		Box box=Box.createVerticalBox();

		for(List<Docente> docs:cgm.getCommissari()){
			JComboBox<Docente> jm = new JComboBox<>();
			ComboBoxRendererCommissari renderer = new ComboBoxRendererCommissari(jm);
			jm.setRenderer(renderer);
			for(Docente d:docs){
				jm.addItem(d);
			}
			box.add(jm);
		}
		return box;
	}

	public void confermaPresidenti() {
		for(Docente d:this.presidentiCorrenti.values())
			d.setSelezionato(true);

	}

	public Box creaRadioBoxDisponibilita(CommissioneGrafica cgm) {
		Box box=Box.createHorizontalBox();
		for (Enumeration<AbstractButton> en = cgm.creaRadioBoxDisponibilita().getElements(); en.hasMoreElements();) {
			AbstractButton b = en.nextElement();
			box.add(b);
		}
		return box;
	}

	public List<JLabel> calcoloLabelCommissioniConfermate() {
		List<JLabel> b = new ArrayList<>();
		for(CommissioneGrafica cgm : this.listaCommissioni.getCommMag()){
			b.add(new JLabel("commissione numero :"));
			b.add(new JLabel("Presidente : " + cgm.getPresidente().getNome()));
			b.add(new JLabel("Commissari :"));
			for(Docente d : cgm.getMappatura().values())
				b.add(new JLabel(d.getNome() + " " + d.getRuolo()));
			b.add(new JLabel("Laureandi :"));
			for(Studente s:cgm.getLaureandi())
				b.add(new JLabel(s.toString()));
		}

		return b;
	}

	public void addFrameCorrente(JFrame f){
		this.framesPrecedente.add(f);
	}
	
	public void tornaIndietro() {
		JFrame frameDaVedere=this.framesPrecedente.get(this.framesPrecedente.size()-1);
		frameDaVedere.setVisible(true);
		this.framesPrecedente.remove(frameDaVedere);
	}
}