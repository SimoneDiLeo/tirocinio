package tirocinioJava.classi;


import java.util.Comparator;

public class Studente {
	private int numero;
	private String nome;
	private String matricola;
	private String tipoLaurea;
	private Docente relatore;
	private Docente correlatore;
	private Docente controrelatore;
	private String note;
	private String argomentoTirocinio;

	public Studente ( int numero, String nome, String matricola, Docente relatore,Docente correlatore,String tipoLaurea, String argomentoTesi,String note){
		this.numero=numero;
		this.nome=nome;
		this.matricola=matricola;
		this.tipoLaurea=tipoLaurea;
		this.argomentoTirocinio=argomentoTesi;
		this.note=note;
		this.relatore=relatore;
		this.correlatore=correlatore;
	}

	//costruttore per test
	public Studente(String matricola, String nome,Docente relatore){
		this.matricola=matricola;
		this.nome=nome;
		this.relatore=relatore;
	}

	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMatricola() {
		return matricola;
	}
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	public String getTipoLaurea() {
		return tipoLaurea;
	}
	public void setTipoLaurea(String tipoLaurea) {
		this.tipoLaurea = tipoLaurea;
	}
	public Docente getRelatore() {
		return relatore;
	}
	public void setRelatore(Docente relatore) {
		this.relatore = relatore;
	}
	public Docente getCorrelatore() {
		return correlatore;
	}
	public void setCorrelatore(Docente correlatore) {
		this.correlatore = correlatore;
	}
	public Docente getControrelatore() {
		return controrelatore;
	}
	public void setControrelatore(Docente controrelatore) {
		this.controrelatore = controrelatore;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getArgomentoTirocinio() {
		return argomentoTirocinio;
	}
	public void setArgomentoTirocinio(String argomentoTirocinio) {
		this.argomentoTirocinio = argomentoTirocinio;
	}


	@Override
	public String toString() {
		String nomeControrelatore="";
		if(this.controrelatore!=null)
			nomeControrelatore=this.controrelatore.getNome();
		return "Studente [numero=" + numero + ", nome= " + nome + ", matricola=" + matricola + ", tipoLaurea="
		+ tipoLaurea + ", relatore= " + relatore.getNome() + ", correlatore=" +
		nomeControrelatore + ", controrelatore="
		+ controrelatore + ", note=" + note + ", argomentoTirocinio=\n" + argomentoTirocinio + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((argomentoTirocinio == null) ? 0 : argomentoTirocinio.hashCode());
		result = prime * result + ((controrelatore == null) ? 0 : controrelatore.hashCode());
		result = prime * result + ((correlatore == null) ? 0 : correlatore.hashCode());
		result = prime * result + ((matricola == null) ? 0 : matricola.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + numero;
		result = prime * result + ((relatore == null) ? 0 : relatore.hashCode());
		result = prime * result + ((tipoLaurea == null) ? 0 : tipoLaurea.hashCode());
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
		Studente other = (Studente) obj;
		if (argomentoTirocinio == null) {
			if (other.argomentoTirocinio != null)
				return false;
		} else if (!argomentoTirocinio.equals(other.argomentoTirocinio))
			return false;
		if (controrelatore == null) {
			if (other.controrelatore != null)
				return false;
		} else if (!controrelatore.equals(other.controrelatore))
			return false;
		if (correlatore == null) {
			if (other.correlatore != null)
				return false;
		} else if (!correlatore.equals(other.correlatore))
			return false;
		if (matricola == null) {
			if (other.matricola != null)
				return false;
		} else if (!matricola.equals(other.matricola))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (numero != other.numero)
			return false;
		if (relatore == null) {
			if (other.relatore != null)
				return false;
		} else if (!relatore.equals(other.relatore))
			return false;
		if (tipoLaurea == null) {
			if (other.tipoLaurea != null)
				return false;
		} else if (!tipoLaurea.equals(other.tipoLaurea))
			return false;
		return true;
	}








}
