package tirocinioJava.classi;


import java.util.ArrayList;
import java.util.List;

public class Docente{
	private String nome;
	private String ruolo;
	private String note;
	private List<String> disponibilita;
	private List<Studente> laureandi;

	public Docente(String nome,String ruolo,List<String> disponibilita,String note){
		this.nome=nome;
		this.ruolo=ruolo;
		this.note=note;
		this.disponibilita=disponibilita;
		this.laureandi=new ArrayList<>();
	}

	public int getNumeroLaureandiTriennali(){
		return this.getLaureandiTriennali().size();
	}

	public int getNumeroLaureandiMagistrali(){
		return this.getLaureandiMagistrali().size();
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

	public int getNumeroLaureandi(){
		return this.laureandi.size();
	}


	public List<String> getDisponibilita(){
		return this.disponibilita;
	}


	public void setDisponibilita(List<String> list){
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

	@Override
	public String toString() {
		return "Docente [nome=" + nome + ", ruolo=" + ruolo + ", note=" + note + ", disponibilita=" + disponibilita
				+ ", numero laureandi Triennali=" + this.getNumeroLaureandiTriennali() + ", Numero laureandi Magistrali="+ getNumeroLaureandiMagistrali() +"]\n";
	}
}	
