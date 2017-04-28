package gestoreFile.lettore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import classi.Controrelatore;
import classi.Personale;
import gestoreFile.LettoreFile;

public class LettoreFileControrelatore extends LettoreFile{
	private boolean puoiCreare=false;
	private String percorso2;
	
	private LettoreFilePersonale lettoreFile;


	//costruttore che richiama il costruttore della classe che viene estesa qui
	public LettoreFileControrelatore(String percorso,String separatore){
			super(percorso,separatore);
			}

	//il costruttore deve avere come parametri 2 percorsi perchè devo leggere in un file le disponibilità ed in un altro i gruppi di ricerce 
	public LettoreFileControrelatore(String percorso1,String percorso2,String separatore){
		super(percorso1,separatore);
		this.percorso2=percorso2;
		this.lettoreFile=new LettoreFilePersonale(this.percorso2,separatore);
	}


	// metodo . dato un cognome mi restituisce quel personale, ammesso che il nome si trovi in personale.

	public Personale getPersonale(List<Personale> list,String cognome){
		for(Personale p:list)
			if (p.getCognome().equals(cognome) || p.getCognome().equals(cognome.toUpperCase()) ) return p; // in personale il nome può essere maiscolo o minuscolo
		return null;
	}

	//metodo che prende in input una riga del file letta dal metodo sotto,cerca il gruppo del nome relativo a quella riga

	public String cercaGruppoRicerca(String[] riga) throws IOException{
		String cognome=riga[1];
		Personale per=null;
		List<Personale> listaPer=new ArrayList<>();
		listaPer=lettoreFile.inizializzaElementiDaFile();

		per=this.getPersonale(listaPer, cognome);
		if (per==null) return "Oggetto non presente!!" ;
		else{
			if (per.getRuolo().equals("dottorando")) 
				return this.getPersonale(listaPer,per.getDocenteRif()).getGruppoRicerca();

			else return per.getGruppoRicerca();  
		}




	} 


	public List<Controrelatore> inizializzaElementiDaFile() {
		List<Controrelatore> controrelatoreOutput=new ArrayList<>();
		try {

			super.br = new BufferedReader(new FileReader(this.csvFile));
			while ((super.line = super.br.readLine()) != null) {
				String[] riga = super.line.split(cvsSplitBy);
				if(riga.length>0)
					if(!this.puoiCreare){
						for(int i=0;i<riga.length-1;i++)
							if(riga[i].contains("Note")){

								this.puoiCreare=true;

							}
					}
					else{
						controrelatoreOutput.add(inizializzaControrelatore(riga,cercaGruppoRicerca(riga)));

					}
			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}  finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return controrelatoreOutput;

	}  

	private Controrelatore inizializzaControrelatore(String[] riga,String gruppo){
		Controrelatore con=new Controrelatore(verificaNulla(riga,2),verificaNulla(riga,3),verificaNulla(riga,0),gruppo);
		return con;



	}
   // verifica se nulla oppure no
	public String verificaNulla(String[] riga, int indice){
		String stringa=null;
		try{
			stringa=riga[indice];
		}									
		catch(Exception e){
			stringa="";
		}
		return stringa;
	}
}
