package logica;


import java.util.ArrayList;
import java.util.List;

import classi.Docente;
import classi.ListaDocenti;
//classe di prova per una bozza dell'algoritmo di riempimento di una commissione
public class BozzaAlgoritmo {

	public BozzaAlgoritmo(){

	}


	public List<Docente> trovaTuttiPossibiliPresidenti(ListaDocenti docenti,String ruolo){
		List<Docente> presidentiPotenziali=new ArrayList<>();
		for(Docente d:docenti.getDocenti()){
			if(d.getRuolo().toUpperCase().equals(ruolo)){
				if(!d.getDisponibilita().isEmpty())
					presidentiPotenziali.add(d);
			}
		}

		if(ruolo.equals("PO"))
			presidentiPotenziali.addAll(trovaTuttiPossibiliPresidenti(docenti,"PA"));
		return presidentiPotenziali;

	}
	
	public Docente trovaPossibilePresidente(List<Docente> presPot){
		return presPot.get(0);
	}
	
}
