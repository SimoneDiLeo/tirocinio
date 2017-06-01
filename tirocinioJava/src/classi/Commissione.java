package classi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import interfacciaGrafica.listenerBottoni.ListenerRadioBoxDisponibilita;

import javax.swing.JRadioButton;


public class Commissione {
	private Docente presidente;
	private List<ArrayList<Docente>> commissariPossibili;
	
	
	private List<Studente> laureandi;
	private List<Integer> slotDisponibilita;
	private String tipoCommissione;
	private int numeroCommissari;
	private Map<Integer,Docente> commissariScelti=new HashMap<>();
	private int slotScelto;
	private int numeroCommissione;
	private int maxStudComm; // massimo studenti in commissione
	
	private List<Docente> listacommissari;
	
	


	public Commissione(){
		this.listacommissari=new ArrayList<>();
	}
	


	public Map<Integer, Docente> getMappatura() {
		return commissariScelti;
	}

	public void setMappatura(Map<Integer, Docente> mappatura) {
		this.commissariScelti = mappatura;
	}

	public void addCommissario(int i , Docente d){
		this.commissariScelti.put(i,d);
	}

	public void eliminaCommissario(int i,Docente d){
		this.commissariScelti.remove(i, d);
	}

	public Commissione(Docente pres, int numeroCommissari,String tipoCommissione){
		this.presidente=pres;
		this.slotDisponibilita=pres.getDisponibilita();
		this.commissariPossibili=new ArrayList<ArrayList<Docente>>();
		this.numeroCommissari=numeroCommissari;
		this.tipoCommissione=tipoCommissione.toUpperCase();
		this.laureandi=new ArrayList<>();
		inizializzaDaTipo(pres);
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

	public ButtonGroup creaRadioBoxDisponibilita() {
		ButtonGroup group = new ButtonGroup();
		for(Integer i : this.slotDisponibilita){
			JRadioButton disp = new JRadioButton(Integer.toString(i));
			disp.addActionListener(new ListenerRadioBoxDisponibilita(this));
			group.add(disp);
		}
		return group;
	}

	public void aggiornaDisponibilita(List<Integer> disponibilita) {
		List<Integer> nuoveDisponibilita=new ArrayList<>();
		for(Integer i : this.slotDisponibilita){
			if(disponibilita.contains(i)){
				nuoveDisponibilita.add(i);
			}
			this.slotDisponibilita=nuoveDisponibilita;
		}

	}

	public void reinizializzaDisponibilita() {
		this.slotDisponibilita=this.presidente.getDisponibilita();
		for(Docente d : this.commissariScelti.values()){
			if(d!=null)
				this.aggiornaDisponibilita(d.getDisponibilita());
		}

	}
	
	public void addLaurenado(Studente s){
		this.laureandi.add(s);
	}

	public int getSlotScelto() {
		return slotScelto;
	}

	public void setSlotScelto(int slotScelto) {
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

	

}
