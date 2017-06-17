package classi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JCheckBox;
import interfacciaGrafica.listenerBottoni.ListenerItemCheckBoxDisponibilita;


public class Commissione {
	private Docente presidente;
	private List<ArrayList<Docente>> commissariPossibili;


	private List<Studente> laureandi;
	private List<Integer> slotDisponibilita;
	private String tipoCommissione;
	private int numeroCommissari;


	private List<Integer> slotScelto;
	private int numeroCommissione;
	private int maxStudComm; // massimo studenti in commissione

	private List<Docente> listacommissari;





	public int getNumeroCommissari() {
		return numeroCommissari;
	}



	public void setNumeroCommissari(int numeroCommissari) {
		this.numeroCommissari = numeroCommissari;
	}



	public List<Docente> getListacommissari() {
		return listacommissari;
	}



	public void setListacommissari(List<Docente> listacommissari) {
		this.listacommissari = listacommissari;
	}


	public void addCommissario(int i , Docente d){
		this.listacommissari.add(i,d);
	}

	public void eliminaCommissario(int i,Docente d){
		this.listacommissari.remove(d);
	}

	public Commissione(Docente pres, int numeroCommissari,String tipoCommissione){
		this.presidente=pres;
		this.slotDisponibilita=pres.getDisponibilita();
		this.slotScelto=this.slotDisponibilita;
		this.commissariPossibili=new ArrayList<ArrayList<Docente>>();
		this.numeroCommissari=numeroCommissari;
		this.tipoCommissione=tipoCommissione.toUpperCase();
		this.laureandi=new ArrayList<>();
		inizializzaDaTipo(pres);
		this.listacommissari=new ArrayList<>();
	}


	public void reinizializzaLaureandi(){
		this.laureandi=new ArrayList<>();
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

	public void inizializzaCommissariCommissione(ListaDocenti docenti){
		for(int i=0;i<this.numeroCommissari;i++){
			if(this.commissariPossibili.size()<i){
				this.commissariPossibili.set(i, (ArrayList<Docente>) docenti.docentiConDisponibilita(this.slotDisponibilita));
			}
			else {
				this.commissariPossibili.add(i,(ArrayList<Docente>) docenti.docentiConDisponibilita(this.slotDisponibilita));
			}
		}

	}


	public List<ArrayList<Docente>> getCommissariPossibili() {
		return commissariPossibili;
	}

	public void setCommissariPossibili(List<ArrayList<Docente>> commissariPossibili) {
		this.commissariPossibili = commissariPossibili;
	}

	public List<ArrayList<Docente>> getCommissari() {
		return commissariPossibili;
	}
	public void setCommissari(List<ArrayList<Docente>> commissari) {
		this.commissariPossibili = commissari;
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

	public List<JCheckBox> creaCheckBoxDisponibilita() {
		List<JCheckBox> group=new ArrayList<>();
		for(Integer i : this.slotDisponibilita){
			JCheckBox disp = new JCheckBox(Integer.toString(i),true);
			disp.addItemListener(new ListenerItemCheckBoxDisponibilita(this));
			group.add(disp);
		}
		return group;
	}

	public void aggiornaDisponibilita(List<Integer> disponibilita) {
		this.slotDisponibilita=this.selezionaSlotIniziali();
	}

	public void reinizializzaDisponibilita() {
		this.slotDisponibilita=this.presidente.getDisponibilita();
		for(Docente d : this.listacommissari){
			if(d!=null)
				this.aggiornaDisponibilita(d.getDisponibilita());
		}

	}

	public void addLaurenado(Studente s){
		this.laureandi.add(s);
	}

	public List<Integer> getSlotScelto() {
		return slotScelto;
	}

	public void setSlotScelto(List<Integer> slotScelto) {
		this.slotScelto = slotScelto;
	}

	public int getNumeroCommissione() {
		return numeroCommissione;
	}

	public void setNumeroCommissione(int numeroCommissione) {
		this.numeroCommissione = numeroCommissione;
	}

	public int getMaxStudComm() {
		return maxStudComm;
	}

	public void setMaxStudComm(int maxStudComm) {
		this.maxStudComm = maxStudComm;
	}

	public int numeroLaurendiInCommissione(){
		return this.laureandi.size();
	}

	public void aggiungiLaurendi(List<Studente> list){
		this.laureandi.addAll(list);
	}

	public void addSlotScelto(int slotScelto) {
		this.slotScelto.add(slotScelto);
	}

	public void removeSlotScelto(Integer slotScelto) {
		this.slotScelto.remove(slotScelto);
	}



	public List<Integer> selezionaSlotIniziali() {
		boolean ok  =true;
		List<Integer> listaSelezionati=new ArrayList<>();
		for(Integer i: this.presidente.getDisponibilita()){
			ok=true;
			for(Docente d:this.listacommissari){
				if(d!=null)
					if(!d.getDisponibilita().contains(i)){
						ok=false;
					}
			}
			if(ok){
				listaSelezionati.add(i);
			}
		}
		return listaSelezionati;
	}



}
