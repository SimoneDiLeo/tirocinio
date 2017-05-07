package classi;

import java.util.ArrayList;
import java.util.List;


public class CommissioneGrafica {
	private Docente presidente;
	private List<ArrayList<Docente>> commissariPossibili;
	private List<Studente> laureandi;
	private List<Integer> slotDisponibilita;
	private String tipoCommissione;
	private int slotCorrente;
	private int numeroCommissari;

	public CommissioneGrafica(){

	}
	//ListaDocenti docenti, vecchio costruttore
	public CommissioneGrafica(Docente pres, int numeroCommissari,String tipoCommissione){
		this.presidente=pres;
		this.slotDisponibilita=pres.getDisponibilita();
		this.slotCorrente=this.slotDisponibilita.get(0);
		this.commissariPossibili=new ArrayList<ArrayList<Docente>>();
		this.numeroCommissari=numeroCommissari;
		this.tipoCommissione=tipoCommissione.toUpperCase();
		this.laureandi=new ArrayList<>();
		inizializzaDaTipo(pres);
	}

	private void inizializzaDaTipo(Docente d){
		if(this.tipoCommissione.equals("TRIENNALE")){
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

	//	public List<Docente> getCommissari1() {
	//		return commissari1;
	//	}
	//
	//	public void setCommissari1(List<Docente> commissari1) {
	//		this.commissari1 = commissari1;
	//	}
	//
	//	public List<Docente> getCommissari2() {
	//		return commissari2;
	//	}
	//
	//	public void setCommissari2(List<Docente> commissari2) {
	//		this.commissari2 = commissari2;
	//	}

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
		for(int i=0;i<this.numeroCommissari;i++){
			if(this.commissariPossibili.size()<i)
				this.commissariPossibili.set(i, (ArrayList<Docente>) docenti.docentiConDisponibilita(nuovaDisp));
			else 
				this.commissariPossibili.add(i,(ArrayList<Docente>) docenti.docentiConDisponibilita(nuovaDisp));
		}

	}


	public List<ArrayList<Docente>> getCommissari() {
		return commissariPossibili;
	}
	public void setCommissari(List<ArrayList<Docente>> commissari) {
		this.commissariPossibili = commissari;
	}
	public int getSlotCorrente() {
		return slotCorrente;
	}
	public void setSlotCorrente(int slotCorrente) {
		this.slotCorrente = slotCorrente;
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
