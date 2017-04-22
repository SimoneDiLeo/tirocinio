package tirocinioJava.classi;

import java.util.List;

public class ListaDocenti {
	private List<Docente> docenti;

	public ListaDocenti(List<Docente> doc){
		this.docenti=doc;
	}

	public Docente trovaDocenteDaNome(String nome){
		for(Docente d:this.docenti){
			if(nome.contains(d.getNome())){
				return d;
			}
		}
		return null;
	}

	public List<Docente> getDocenti() {
		return docenti;
	}

	public void setDocenti(List<Docente> docenti) {
		this.docenti = docenti;
	}

	@Override
	public String toString() {
		return "ListaDocenti [docenti=" + docenti + "]";
	}
}
