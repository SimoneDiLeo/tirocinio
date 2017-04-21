package tirocinioJava.classi;


import java.util.List;
import java.util.Scanner;

import javax.management.monitor.CounterMonitorMBean;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
//classe di prova per una bozza dell'algoritmo di riempimento di una commissione
public class BozzaAlgoritmo {
	private Commissione commissione;
	private Docente commissario;
	private List<Docente> commissariDisponibili;
	private List<Docente> commissariPotenziali;
	private List<Studente> laureandi;
	private List<Docente> sostituti;

	public BozzaAlgoritmo(Commissione commissioneVuota,List<Docente>docentiDisponibili){
		this.commissione=commissioneVuota;
		this.commissariDisponibili=docentiDisponibili;
	}

	//metodo gia usato in lettoreFileStudenti 
	//molto probabilemnte dovra essere fatto il refactoring del metodo
	private Docente trovaDocenteDaNome(String nomeDocente, List<Docente> docenti) {
		Docente trovato = null;
		for(Docente d:docenti){
			if(d.getNome().equals(nomeDocente))
				trovato=d;
		}

		return trovato;
	}

	public void riempiCommissione(){
		System.out.println("questi sono i commissari disponibili");
		System.out.println(this.commissariDisponibili.toString());
		System.out.println("seleziona un presidente");
		Scanner input= new Scanner(System.in);
		String nomePresidente= input.nextLine();
		Docente presidente=trovaDocenteDaNome(nomePresidente.toUpperCase(), this.commissariDisponibili);
		this.commissione.inserisciPresidente(presidente);
		if(this.commissione.getTipoLaurea().toUpperCase().contains("TRIENNALE"))
			this.commissione.inserisciGruppoStudenti(presidente.getLaureandiTriennali());
		else
			this.commissione.inserisciGruppoStudenti(presidente.getLaureandiMagistrali());


	}



}
