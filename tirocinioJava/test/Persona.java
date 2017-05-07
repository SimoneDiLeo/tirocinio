

public class Persona {

	private String nome;
	private String cognome;

	public Persona(String nome,String cognome) {
		this.nome = nome;
		this.setCognome(cognome);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public String toString() {
		return this.getNome();
	}
	@Override
	public boolean equals(Object o){
		Persona p= (Persona)o;
		return this.getNome().equals(p.getNome());
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

}
