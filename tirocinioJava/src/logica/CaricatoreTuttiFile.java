package logica;

import java.io.File;
import java.io.IOException;
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
	private String[] giorni;






	public void inizializza(File docenti,File studenti,File personale,File controrelatori) throws IOException{
		LettoreFileDocente lettoreDoc= new LettoreFileDocente(docenti,"\t");
		this.docenti= new ListaDocenti(lettoreDoc.inizializzaElementiDaFile());
		this.setGiorni(lettoreDoc.getGiorni());
		LettoreFileStudenti lettoreStudente= new LettoreFileStudenti(studenti,"\t");
		this.studenti=lettoreStudente.inizializzaElementiDaFile(this.docenti);
		LettoreFilePersonale lettorePer= new LettoreFilePersonale(personale,"\t");
		this.personale = lettorePer.inizializzaElementiDaFile();
		LettoreFileControrelatore lettoreContr= new LettoreFileControrelatore(controrelatori,personale,"\t");
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


	public String[] getGiorni() {
		return giorni;
	}


	public void setGiorni(String[] giorni) {
		this.giorni = giorni;
	}
}
