package interfacciaGrafica.logicaChiamate;

import java.io.IOException;
import java.util.List;
import gestoreFile.lettore.LettoreFileControrelatore;
import gestoreFile.lettore.LettoreFileDocente;
import gestoreFile.lettore.LettoreFilePersonale;
import gestoreFile.lettore.LettoreFileStudenti;
import interfacciaGrafica.FinestraErrore;
import tirocinioJava.classi.Controrelatore;
import tirocinioJava.classi.ListaDocenti;
import tirocinioJava.classi.Personale;
import tirocinioJava.classi.Studente;

public class ProvaGuiChiamata {
	private ListaDocenti docenti;
	private List<Studente> studenti;
	private List<Controrelatore> controrel;
	private List<Personale> personale;
	


	


	public void inizializza(String docenti,String studenti,String personale,String controrelatori) throws IOException{
			LettoreFileDocente lettoreDoc= new LettoreFileDocente("./dati/"+docenti+".csv",";");
			this.docenti= new ListaDocenti(lettoreDoc.inizializzaElementiDaFile());
			LettoreFileStudenti lettoreStudente= new LettoreFileStudenti("./dati/"+ studenti +".csv",";");
			this.studenti=lettoreStudente.inizializzaElementiDaFile(this.docenti);
			LettoreFilePersonale lettorePer= new LettoreFilePersonale("./dati/"+personale+".csv",";");
			this.personale = lettorePer.inizializzaElementiDaFile();
			LettoreFileControrelatore lettoreContr= new LettoreFileControrelatore("./dati/"+controrelatori+".csv","./dati/"+personale+".csv",";");
			this.controrel = lettoreContr.inizializzaElementiDaFile();
			this.docenti.inizializzaRuoloDocenti(this.personale);
		}
	
	
	public ListaDocenti getDocenti() {
		return docenti;
	}

	public void setDocenti(ListaDocenti docenti) {
		this.docenti = docenti;
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
