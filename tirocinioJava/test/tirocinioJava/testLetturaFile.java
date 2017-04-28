package tirocinioJava;
//classe di test per lettura di file
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classi.Controrelatore;
import classi.Docente;
import classi.ListaDocenti;
import classi.Personale;
import classi.Studente;
import gestoreFile.LettoreFile;
import gestoreFile.lettore.LettoreFileControrelatore;
import gestoreFile.lettore.LettoreFileDocente;
import gestoreFile.lettore.LettoreFilePersonale;
import gestoreFile.lettore.LettoreFileStudenti;

public class testLetturaFile {
	public static void main(String[] args){
		//		System.out.println("inserire nome file degli studenti");
		//		Scanner input= new Scanner(System.in);
		//		String fileStudenti=input.nextLine();
//		System.out.println("inserire nome file dei docenti");
//		Scanner inputDocenti= new Scanner(System.in);
//		String fileDocenti=inputDocenti.nextLine();
//		System.out.println("inserire nome file del personale");
		Scanner inputPers= new Scanner(System.in);
		String filePers=inputPers.nextLine();
		System.out.println("inserire nome file dei controrelatori");
		Scanner inputContro= new Scanner(System.in);
		String fileContro=inputContro.nextLine();

		try{
			LettoreFilePersonale personale= new LettoreFilePersonale("./dati/" +  filePers +".csv",";");
			LettoreFileControrelatore lettoreControrelatore = new LettoreFileControrelatore("./dati/" +  fileContro +".csv","./dati/" +  filePers +".csv" ,";");
			List<Controrelatore> contro =lettoreControrelatore.inizializzaElementiDaFile();
			System.out.println(contro.size());
			for(Controrelatore c: contro){
				System.out.println(c.toString());
			}
//						LettoreFileStudenti lettoreStudente= new LettoreFileStudenti("./dati/"+ fileStudenti +".csv",";");
			
//			List<Personale> listaPers= personale.inizializzaElementiDaFile();
//			LettoreFileDocente lettoreDoc= new LettoreFileDocente("./dati/"+ fileDocenti +".csv",";");
//			ListaDocenti docenti= new ListaDocenti(lettoreDoc.inizializzaElementiDaFile());
//			docenti.inizializzaRuoloDocenti(listaPers);
//			for(Docente d : docenti.getDocenti()){
//				System.out.println(d.toString());
//			}
//			
			//
			//			List<Studente> studenti=lettoreStudente.inizializzaElementiDaFile(docenti);
			//
			//			for(Studente s:studenti){
			//				System.out.println(s.getNome());
			//			}
			//			System.out.println(docenti.toString());

		}
		catch(Exception e){

			System.out.println("errore caricamento file");

		}
	}
}
