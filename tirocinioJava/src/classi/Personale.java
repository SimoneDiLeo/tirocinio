package classi;

import java.util.List;

public class Personale {
	private String nome;
	private String cognome;
	private String ruolo;
	private String gruppoRic;
	private String docenteRif; // docente di riferimento se si tratta di dottorando


	public Personale(String nome,String cognome,String ruolo,String gruppoRic,String docenteRif){
		this.nome=nome;
		this.cognome=cognome;
		this.ruolo=ruolo;
		this.gruppoRic=gruppoRic;
		this.docenteRif=docenteRif;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getRuolo() {
		return ruolo;
	}


	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}


	public String getGruppoRicerca() {
		return gruppoRic;
	}


	public void setGruppoRicerca(String gruppoRicerca) {
		this.gruppoRic = gruppoRicerca;
	}


	public String getDocenteRif() {
		return docenteRif;
	}


	public void setDocenteRif(String docenteRif) {
		this.docenteRif = docenteRif;
	}
	
  
	@Override
	public boolean equals(Object o){
		Personale p=(Personale) o;
		return this.getNome().equals(p.getNome()) && this.getCognome().equals(p.getCognome()) && this.getRuolo().equals(p.getRuolo());
	}
	@Override
	public int hashCode(){
		return this.getNome().hashCode()+this.getCognome().hashCode()+this.getRuolo().hashCode();
	}
	@Override
	public String toString(){
		return "[cognome: "+ this.getCognome()+ " Nome:" +this.getNome()+ " ruolo:" +this.getRuolo()+ " docrif:"+ this.getDocenteRif()+"]";
	}
	
}
