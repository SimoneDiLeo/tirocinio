package gestoreFile.lettore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	//metodo che dice se una stringa è un numero .Se lo è vuol dire che è un dottorando
	public boolean isNumber(String num){
		try{
			Integer.parseInt(num);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	public int getNumeroSpazi(String st){
		int cont;
		cont=0;
		for(char c: st.toCharArray())
			if (c==' ')
				cont++;

		return cont;
	}

	public String estraiCognome(String cognomeNome){
		char[] arr=cognomeNome.toCharArray();
		int lung=cognomeNome.length()-1;
		if (arr[lung]==' ')
			return estraiCognome(cognomeNome.substring(0, lung));
		else if(arr[0]==' ') return estraiCognome(cognomeNome.substring(1));
		else{
			if (this.getNumeroSpazi(cognomeNome)==2 ||this.getNumeroSpazi(cognomeNome)==1 )
				return cognomeNome.substring(0, cognomeNome.lastIndexOf(" "));
			else{
				String st=cognomeNome.substring(0, cognomeNome.lastIndexOf(" "));
				return cognomeNome.substring(0, st.lastIndexOf(" "));
			}
		}

	}

	//	metodo che prende in input una riga del file letta dal metodo sotto,cerca il gruppo del nome relativo a quella riga
	public String cercaGruppoRicerca(String[] riga) throws IOException{
		String nomedocenteRif=riga[1];
		String nomeContr=riga[2];
		Personale per=null;
		List<Personale> listaPer=new ArrayList<>();
		listaPer=this.lettoreFile.inizializzaElementiDaFile();
		per=this.getPersonale(listaPer, nomedocenteRif);
		if (per==null) return "oggetto non trovato";
		else if(isNumber(riga[0]) || riga[0].equals("assegnista")) return per.getGruppoRicerca();
		else{
			String nomeContrSezaNom=null;
			try{
				nomeContrSezaNom=estraiCognome(nomeContr);
			}
			catch (Exception e){
				nomeContrSezaNom="";
			}
			Personale per1=this.getPersonale(listaPer,nomeContrSezaNom );
			if (per1==null) return "oggetto non trovato pt 2";
			else return per1.getGruppoRicerca();
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
	// data la lista di contr mi restituisci il nome specificato
	public String getControrelatore(String nome){
		for(Controrelatore c: this.inizializzaElementiDaFile())
			if (c.getNome().equals(nome.toUpperCase()))
				return c.toString();

		return "non presente!!!";
	}

	public List<Controrelatore> getContrDisp(){
		List<Controrelatore> listaContDisp=new ArrayList<>();
		for(Controrelatore c:this.inizializzaElementiDaFile())
			if (c.getDisponibilita().toUpperCase().equals("SI"))
				listaContDisp.add(c);

		return listaContDisp;
	}
	// mi serve in AssegnaControrelazioni (il valore della mappa indica il numero di controrelazioni)...per adesso tutti inizializzati a 0
	public Map<Controrelatore,Integer> inizializzaMappaContr(){
		Map<Controrelatore,Integer> cont=new HashMap<>();
		for(Controrelatore c: this.getContrDisp())
			cont.put(c,0);
		return cont;	
	}


}
