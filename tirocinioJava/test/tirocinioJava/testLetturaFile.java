package tirocinioJava;
//classe di test per lettura di file
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import gestoreFile.LettoreFile;
import gestoreFile.lettore.LettoreFileDocente;
import gestoreFile.lettore.LettoreFileStudenti;
import tirocinioJava.classi.Docente;
import tirocinioJava.classi.ListaDocenti;
import tirocinioJava.classi.Studente;

public class testLetturaFile {
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

			for(Studente s:studenti){
				System.out.println(s.getNome());
			}
			System.out.println(docenti.toString());

		}
		catch(Exception e){

			System.out.println("errore caricamento file");

		}
	}
}
