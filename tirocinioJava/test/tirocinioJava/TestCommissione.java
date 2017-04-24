//package tirocinioJava;
////classe per fare prove per vedere come strutturare la classe commissione
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//import java.util.Scanner;
//
//import gestoreFile.lettore.LettoreFileDocente;
//import gestoreFile.lettore.LettoreFileStudenti;
//import tirocinioJava.classi.BozzaAlgoritmo;
//import tirocinioJava.classi.Commissione;
//import tirocinioJava.classi.Docente;
//import tirocinioJava.classi.ListaDocenti;
//import tirocinioJava.classi.Studente;
//
//public class TestCommissione {
//
//	public TestCommissione() throws IOException{
//
//		ListaDocenti docenti= new ListaDocenti(inizializzazioneDocenti());
//		List<Studente> studenti= inizializzazioneStudenti(docenti);
//		Properties prop = new Properties(); 
//		URL url = this.getClass().getClassLoader().getSystemResource("properties");
//		prop.load(url.openStream());
//		boolean possoAndare=true;
//		while(possoAndare){
//			System.out.println("inserire tipo di commissione (T=triennale ; M=magistrale) ");
//			Scanner input= new Scanner(System.in);		
//			String tipoCommissione= input.nextLine();
//			possoAndare=false;
//			try {
//				prop.load(url.openStream());
//				if(tipoCommissione.toUpperCase().equals("T")){
//					Commissione commissioneTriennale=new Commissione(Integer.parseInt(prop.getProperty("COMMISSARI_TRIENNALI")),Integer.parseInt(prop.getProperty("NUMERO_MAX_STUDENTI_PER_COMMISSIONE_TRIENNALE")),Integer.parseInt(prop.getProperty("NUMERO_SUPPLENTI_TRIENNALE")),"TRIENNALE");
//					BozzaAlgoritmo bozza = new BozzaAlgoritmo(commissioneTriennale, docenti);
//					bozza.riempiCommissione();
//					System.out.println(commissioneTriennale.toString());
//				}
//				else
//					if(tipoCommissione.toUpperCase().equals("M")){
//						Commissione commissioneMagistrale=new Commissione();
//						System.out.println(commissioneMagistrale.toString());
//					}
//					else
//					{
//						System.out.println("input errato: vuoi riavviare il programma? \n(Scrivere S per riavviare altrimenti il programma terminera)");
//						Scanner inputRestart= new Scanner(System.in);		
//						String restart= inputRestart.nextLine();
//						if(restart.toUpperCase().equals("S")){
//							possoAndare=true;
//						}
//						else{
//							System.out.println("Programma Terminato ");
//						}
//
//					}
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	private List<Studente> inizializzazioneStudenti(ListaDocenti docenti) {
//		System.out.println("inserire nome file degli studenti");
//		Scanner input= new Scanner(System.in);
//		String fileStudenti=input.nextLine();
//		try{
//			LettoreFileStudenti lettoreStudente= new LettoreFileStudenti("./dati/"+ fileStudenti +".csv",";");
//			List<Studente> studenti =lettoreStudente.inizializzaElementiDaFile(docenti);
//			return studenti;
//		}catch(Exception e){
//			System.out.println("errore caricamento file");
//		}
//		return null;
//	}
//
//	private List<Docente> inizializzazioneDocenti()	{
//		System.out.println("inserire nome file dei docenti");
//		Scanner inputDocenti= new Scanner(System.in);
//		String fileDocenti=inputDocenti.nextLine();
//		try{
//			LettoreFileDocente lettoreDoc= new LettoreFileDocente("./dati/"+ fileDocenti +".csv",";");
//			List<Docente> docenti = lettoreDoc.inizializzaElementiDaFile();
//			return docenti;
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//
//	public static void main(String[] args) throws IOException{
//		TestCommissione inizio=new TestCommissione();
//
//	}
//}
