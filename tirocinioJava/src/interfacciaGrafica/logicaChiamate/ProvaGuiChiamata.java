package interfacciaGrafica.logicaChiamate;

import java.util.ArrayList;
import java.util.List;

import gestoreFile.lettore.LettoreFileDocente;
import gestoreFile.lettore.LettoreFileStudenti;
import tirocinioJava.classi.Docente;
import tirocinioJava.classi.ListaDocenti;
import tirocinioJava.classi.Studente;

public class ProvaGuiChiamata {
	private ListaDocenti docenti;
	private List<Studente> studenti;

	

	public void inizializza(String docenti,String studenti){

		LettoreFileDocente lettoreDoc= new LettoreFileDocente("./dati/"+docenti+".csv",";");
		this.docenti= new ListaDocenti(lettoreDoc.inizializzaElementiDaFile());
		LettoreFileStudenti lettoreStudente= new LettoreFileStudenti("./dati/"+ studenti +".csv",";");
		this.studenti=lettoreStudente.inizializzaElementiDaFile(this.docenti);
	}

	public ListaDocenti getDocenti() {
		return docenti;
	}

	public void setDocenti(ListaDocenti docenti) {
		this.docenti = docenti;
	}
}
