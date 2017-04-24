package interfacciaGrafica;

import java.util.ArrayList;
import java.util.List;

import tirocinioJava.classi.Docente;
import tirocinioJava.classi.ListaDocenti;
import tirocinioJava.classi.Studente;


public class CommissioneGrafica {
	private Docente presidente;
	private List<Docente> commissari1;
	private List<Docente> commissari2;
	private List<Studente> laureandi;
	private List<Integer> slotDisponibilita;
	private String tipoCommissione;
	private int slotCorrente;

	public CommissioneGrafica(Docente pres,ListaDocenti docenti,String tipoCommissione){
		this.presidente=pres;
		ListaDocenti doc=docenti;
		doc.rimuoviDocente(this.presidente);
		this.slotDisponibilita=pres.getDisponibilita();
		this.slotCorrente=this.slotDisponibilita.get(0);
		this.commissari1=doc.docentiConDisponibilita(this.slotCorrente);
		this.commissari2=doc.docentiConDisponibilita(this.slotCorrente);
		this.tipoCommissione=tipoCommissione.toUpperCase();
		this.laureandi=new ArrayList<>();
		inizializzaDaTipo(pres);
	}

	private void inizializzaDaTipo(Docente d){
		if(this.tipoCommissione.charAt(0)=='T'){
			inizializzaStudT(d);
		}else{
			inizializzaStudM(d);
		}
	}

	private void inizializzaStudT(Docente d){
		for(Studente s:d.getLaureandiTriennali()){
			this.laureandi.add(s);
		}
	}

	private void inizializzaStudM(Docente d){
		for(Studente s:d.getLaureandiMagistrali()){
			this.laureandi.add(s);
		}
	}

	public Docente getPresidente() {
		return presidente;
	}

	public void setPresidente(Docente presidente) {
		this.presidente = presidente;
	}

	public List<Docente> getCommissari1() {
		return commissari1;
	}

	public void setCommissari1(List<Docente> commissari1) {
		this.commissari1 = commissari1;
	}

	public List<Docente> getCommissari2() {
		return commissari2;
	}

	public void setCommissari2(List<Docente> commissari2) {
		this.commissari2 = commissari2;
	}

	public List<Studente> getLaureandi() {
		return laureandi;
	}

	public void setLaureandi(List<Studente> laureandi) {
		this.laureandi = laureandi;
	}

	public List<Integer> getSlotDisponibilita() {
		return slotDisponibilita;
	}

	public void setSlotDisponibilita(List<Integer> slotDisponibilita) {
		this.slotDisponibilita = slotDisponibilita;
	}

	public String getTipoCommissione() {
		return tipoCommissione;
	}

	public void setTipoCommissione(String tipoCommissione) {
		this.tipoCommissione = tipoCommissione;
	}

	public void aggiornaCommissione(int nuovaDisp,ListaDocenti docenti){
		this.commissari1= docenti.docentiConDisponibilita(nuovaDisp);
		this.commissari2= docenti.docentiConDisponibilita(nuovaDisp);
	}
	
	public void aggiornaLaureandi(Docente d){
		inizializzaDaTipo(d);
	}
	
	public void eliminaLaureandi(Docente d){
		for(Studente s:d.getLaureandi()){
			if(this.laureandi.contains(s))
				this.laureandi.remove(s);
		}
	}

}
