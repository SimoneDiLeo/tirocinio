package tirocinioJava;

import java.util.List;
import java.util.Scanner;

import gestoreFile.lettore.LettoreFileDocente;
import gestoreFile.lettore.LettoreFileStudenti;
import tirocinioJava.classi.BozzaAlgoritmo;
import tirocinioJava.classi.Docente;
import tirocinioJava.classi.ListaDocenti;
import tirocinioJava.classi.Studente;

public class TestAlgoritmoPresidenti {
	public static void main(String[] args){
		System.out.println("inserire nome file degli studenti");
		Scanner input= new Scanner(System.in);
		String fileStudenti=input.nextLine();
		System.out.println("inserire nome file dei docenti");
		Scanner inputDocenti= new Scanner(System.in);
		String fileDocenti=inputDocenti.nextLine();
		try{

			LettoreFileStudenti lettoreStudente= new LettoreFileStudenti("./dati/"+ fileStudenti +".csv",";");

			LettoreFileDocente lettoreDoc= new LettoreFileDocente("./dati/"+ fileDocenti +".csv",";");

			ListaDocenti docenti= new ListaDocenti(lettoreDoc.inizializzaElementiDaFile());

			List<Studente> studenti=lettoreStudente.inizializzaElementiDaFile(docenti);
			BozzaAlgoritmo bz = new BozzaAlgoritmo();
			for(Docente d:bz.trovaPresidenti(1, docenti, "ORD")){
				System.out.println(d);
			}

		}
		catch(Exception e){

			System.out.println("errore caricamento file");

		}
	}
}
