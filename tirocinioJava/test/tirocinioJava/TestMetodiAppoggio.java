package tirocinioJava;
//classe di test per provare singoli metodi di appoggio 
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import gestoreFile.lettore.LettoreFileStudenti;

public class TestMetodiAppoggio {
	public static void main (String[] args){
		
		LettoreFileStudenti lettoreStudente= new LettoreFileStudenti("./dati/laureandi.csv",";");
		lettoreStudente.leggiFile();
	}
}
