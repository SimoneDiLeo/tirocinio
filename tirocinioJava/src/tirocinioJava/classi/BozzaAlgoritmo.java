package tirocinioJava.classi;


import java.util.List;
import java.util.Scanner;

import javax.management.monitor.CounterMonitorMBean;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
//classe di prova per una bozza dell'algoritmo di riempimento di una commissione
public class BozzaAlgoritmo {
	private Commissione commissione;
	private Docente commissario;
	private ListaDocenti commissariDisponibili;
	private List<Docente> commissariPotenziali;
	private List<Studente> laureandi;
	private List<Docente> sostituti;

	public BozzaAlgoritmo(Commissione commissioneVuota,ListaDocenti docenti){
		this.commissione=commissioneVuota;
		this.commissariDisponibili=docenti;
	}



	public void riempiCommissione(){
		System.out.println("questi sono i commissari disponibili");
		System.out.println(this.commissariDisponibili.toString());
		System.out.println("seleziona un presidente");
		Scanner input= new Scanner(System.in);
		String nomePresidente= input.nextLine();
		Docente presidente=this.commissariDisponibili.trovaDocenteDaNome(nomePresidente.toUpperCase());
		this.commissione.inserisciPresidente(presidente);
		if(this.commissione.getTipoLaurea().toUpperCase().contains("TRIENNALE"))
			this.commissione.inserisciGruppoStudenti(presidente.getLaureandiTriennali());
		else
			this.commissione.inserisciGruppoStudenti(presidente.getLaureandiMagistrali());


	}



}
