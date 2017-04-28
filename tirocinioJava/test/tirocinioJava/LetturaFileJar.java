package tirocinioJava;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import classi.Docente;
import classi.ListaDocenti;
import gestoreFile.lettore.LettoreFileDocente;


public class LetturaFileJar {
	public static void main(String[] args) throws URISyntaxException{
		System.out.println("inserire nome file dei docenti");
		Scanner inputDocenti= new Scanner(System.in);
		String fileDocenti=inputDocenti.nextLine();
		String percorso= System.getProperty("user.dir");
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);
		LettoreFileDocente lettoreDoc= new LettoreFileDocente(s +"/file_da_caricare/"+ fileDocenti +".csv",";");
		ListaDocenti docenti= new ListaDocenti(lettoreDoc.inizializzaElementiDaFile());
		for(Docente d : docenti.getDocenti()){
			System.out.println(d.toString());
		}

	}
}