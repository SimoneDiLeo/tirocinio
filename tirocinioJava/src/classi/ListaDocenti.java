package classi;

import java.util.ArrayList;
import java.util.List;

public class ListaDocenti {
	private List<Docente> docenti;

	public ListaDocenti(List<Docente> doc){
		this.docenti=doc;
	}

	public void rimuoviDocente(Docente d){
		this.docenti.remove(d);
	}	


public Docente trovaDocenteDaNome(String nome){
	for(Docente d:this.docenti){
		if(nome.contains(d.getNome())){
			return d;
		}
	}
	return null;
}

public List<Docente> docentiConDisponibilita(Integer i){
	List<Docente> docDisponibili=new ArrayList<>();		
	for(Docente d:this.getDocenti()){
		if(d.getDisponibilita().contains(i))
			docDisponibili.add(d);
	}
	return docDisponibili;

}

public void inizializzaRuoloDocenti(List<Personale> personale){
	for(Personale p:personale){
		for(Docente d: this.docenti){
			if(p.getCognome().toUpperCase().contains((d.getNome())))
					d.setRuolo(p.getRuolo());
		}
	}
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
