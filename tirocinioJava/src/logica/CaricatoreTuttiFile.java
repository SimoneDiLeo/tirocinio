package logica;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import classi.Controrelatore;
import classi.ListaDocenti;
import classi.Personale;
import classi.Studente;
import gestoreFile.lettore.LettoreFileControrelatore;
import gestoreFile.lettore.LettoreFileDocente;
import gestoreFile.lettore.LettoreFilePersonale;
import gestoreFile.lettore.LettoreFileStudenti;

public class CaricatoreTuttiFile {
	private ListaDocenti docenti;
	private List<Studente> studenti;
	private List<Controrelatore> controrel;
	private List<Personale> personale;






	public void inizializza(File docenti,File studenti,File personale,File controrelatori) throws IOException{
		// da usare prima di esportare il jar altrimenti non funzione
		//esportarlo in una cartella che contiene una cartella file_da_caricare che contiene i csv da leggere
		//		String s = (currentRelativePath.toAbsolutePath().toString()+"/file_da_caricare/");
		LettoreFileDocente lettoreDoc= new LettoreFileDocente(docenti,";");
		this.docenti= new ListaDocenti(lettoreDoc.inizializzaElementiDaFile());
		LettoreFileStudenti lettoreStudente= new LettoreFileStudenti(studenti,";");
		this.studenti=lettoreStudente.inizializzaElementiDaFile(this.docenti);
		LettoreFilePersonale lettorePer= new LettoreFilePersonale(personale,";");
		this.personale = lettorePer.inizializzaElementiDaFile();
		LettoreFileControrelatore lettoreContr= new LettoreFileControrelatore(controrelatori,personale,";");
		this.docenti.inizializzaRuoloDocenti(this.personale);
		AssegnaControrelatore ac= new AssegnaControrelatore(this.studenti, lettoreContr.inizializzaMappaContr(), this.personale);	
		ac.assegna();
	}


	public ListaDocenti getDocenti() {
		return docenti;
	}

	public List<Studente> getStudenti() {
		return studenti;
	}


	public List<Controrelatore> getControrel() {
		return controrel;
	}


	public List<Personale> getPersonale() {
		return personale;
	}
}
