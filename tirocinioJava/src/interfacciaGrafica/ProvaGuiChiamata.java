package interfacciaGrafica;

import java.util.ArrayList;
import java.util.List;

import gestoreFile.lettore.LettoreFileDocente;
import gestoreFile.lettore.LettoreFileStudenti;
import tirocinioJava.classi.Docente;
import tirocinioJava.classi.Studente;

public class ProvaGuiChiamata {
	private List<Docente> docenti;
	private List<Studente> studenti;

	public ProvaGuiChiamata(){
		this.docenti=new ArrayList<>();
	}

	public void inizializza(String docenti,String studenti){

		LettoreFileDocente lettoreDoc= new LettoreFileDocente("./dati/"+docenti+".csv",";");
		this.docenti=lettoreDoc.inizializzaElementiDaFile();
		LettoreFileStudenti lettoreStudente= new LettoreFileStudenti("./dati/"+ studenti +".csv",";");
		this.studenti=lettoreStudente.inizializzaElementiDaFile(this.docenti);
	}

	public List<Docente> getDocenti() {
		return docenti;
	}

	public void setDocenti(List<Docente> docenti) {
		this.docenti = docenti;
	}
}
