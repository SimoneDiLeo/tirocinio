package tirocinioJava.classi;


import java.util.ArrayList;
import java.util.List;
//classe di prova per una bozza dell'algoritmo di riempimento di una commissione
public class BozzaAlgoritmo {

	public BozzaAlgoritmo(){

	}


	public List<Docente> trovaPresidenti(int numeroCommissioni,ListaDocenti docenti,String ruolo){
		List<Docente> presidentiPotenziali=new ArrayList<>();
		for(Docente d:docenti.getDocenti()){
			if(d.getRuolo().toUpperCase().equals(ruolo)){
				if(!d.getDisponibilita().isEmpty())
					presidentiPotenziali.add(d);
			}
		}

		if(ruolo.equals("ORD"))
			if(presidentiPotenziali.size()!=numeroCommissioni){
				presidentiPotenziali.addAll(trovaPresidenti(numeroCommissioni,docenti,"ASS"));
			}

		return presidentiPotenziali;

	}
}
