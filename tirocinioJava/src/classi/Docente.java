package classi;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

public class Docente {
	private String nome;
	private String ruolo;
	private String note;
	private List<Integer> disponibilita;
	private List<Studente> laureandi;
	private int numeroCommissioniPartecipanti=0;
	private boolean isInUnaCommissione;
	private JLabel jlabel;

	public Docente(String nome,String ruolo,List<Integer> disponibilita2,String note){
		this.nome=nome;
		this.ruolo=ruolo;
		this.note=note;
		this.disponibilita=disponibilita2;
		this.laureandi=new ArrayList<>();
		this.isInUnaCommissione=false;
		this.jlabel=new JLabel(this.toString());
	}

	public boolean isInUnaCommissione() {
		return isInUnaCommissione;
	}

	public void setInUnaCommissione(boolean isInUnaCommissione) {
		this.isInUnaCommissione = isInUnaCommissione;
	}
	public List<Studente> getLaureandiTriennali(){
		List<Studente> studentiTriennali=new ArrayList<>();
		for(Studente s:this.laureandi){
			if(s.getTipoLaurea().contains("TRIENNALE")){
				studentiTriennali.add(s);
			}
		}
		return studentiTriennali;
	}

	public List<Studente> getLaureandiMagistrali(){
		List<Studente> studentiMagistrali=new ArrayList<>();
		for(Studente s:this.laureandi){
			if(s.getTipoLaurea().contains("MAGISTRALE")){
				studentiMagistrali.add(s);
			}
		}
		return studentiMagistrali;
	}
	
	public int getNumeroLaureandiTriennali(){
		return this.getLaureandiTriennali().size();
	}

	public int getNumeroLaureandiMagistrali(){
		return this.getLaureandiMagistrali().size();
	}

	public int getNumeroLaureandi(){
		return this.laureandi.size();
	}


	public List<Integer> getDisponibilita(){
		return this.disponibilita;
	}


	public void setDisponibilita(List<Integer> list){
		this.disponibilita=list;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getRuolo() {
		return ruolo;
	}


	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public List<Studente> getLaureandi() {
		return laureandi;
	}

	public void setLaureandi(List<Studente> laureandi) {
		this.laureandi = laureandi;
	}

	public void addLaureando(Studente laureando){
		this.laureandi.add(laureando);
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
	


	
	public JLabel getLabel() {
		return this.jlabel;
	}

	public boolean isSelezionato() {
		return numeroCommissioniPartecipanti>0;
	}

	public void incrementaSelezionato() {
		this.numeroCommissioniPartecipanti++;
	}
	
	public void decrementaSelezionato() {
		this.numeroCommissioniPartecipanti--;
	}

	public void azzeraSelezionato() {
		this.numeroCommissioniPartecipanti=0;		
	}

	public int compareMagTo(Docente arg1) {
		return this.getNumeroLaureandiMagistrali() - arg1.getNumeroLaureandiMagistrali();
	}
	
	public int compareTriTo(Docente arg1){
		return this.getNumeroLaureandiTriennali() - arg1.getNumeroLaureandiTriennali();
	}
	
	public void cambiaColoreLabel(){
		if(this.isInUnaCommissione){
			this.jlabel.setForeground(Color.RED);
		}
		else {
			this.jlabel.setForeground(Color.BLACK);
		}
	}
	
	
	//metodi ovverride o in piu da qui in poi
	
	@Override
	public String toString() {
		return this.getNome()+ " T:"+this.getNumeroLaureandiTriennali() +
				" M:" + this.getNumeroLaureandiMagistrali() + " Dis:" +this.getDisponibilita();
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((disponibilita == null) ? 0 : disponibilita.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((ruolo == null) ? 0 : ruolo.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Docente other = (Docente) obj;
		if (disponibilita == null) {
			if (other.disponibilita != null)
				return false;
		} else if (!disponibilita.equals(other.disponibilita))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (ruolo == null) {
			if (other.ruolo != null)
				return false;
		} else if (!ruolo.equals(other.ruolo))
			return false;
		return true;
	}
	
	public String toRappresentazioneCompleta(){
		return "nome=" + nome + ", ruolo=" + ruolo + ", " + "disponibilita=" + disponibilita
		+ ", numero laureandi Triennali=" + this.getNumeroLaureandiTriennali() + ", Numero laureandi Magistrali="+ getNumeroLaureandiMagistrali() 
		+ "\n";
		
	}

	public void setColoreDefaultLabel() {
		this.jlabel.setForeground(Color.BLACK);		
	}

}	
