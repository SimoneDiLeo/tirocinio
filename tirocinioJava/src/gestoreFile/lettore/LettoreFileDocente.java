package gestoreFile.lettore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import classi.Docente;
import gestoreFile.LettoreFile;

public class LettoreFileDocente extends LettoreFile{
	private boolean puoiCreare=false;
	private int indiceNote=0;
	private String[] giorni;

	//costruttore che richiama il costruttore della classe che viene estesa qui
	public LettoreFileDocente(File docenti,String separatore){
		super(docenti,separatore);
	}

	//metodo che prende in input una lista di docenti(necessaria? molto probabilmente no ma per ora la terrei) e restituisce in output una lista di docenti inizializzati da file
	public List<Docente> inizializzaElementiDaFile() {
		List<Docente> docentiOutput=new ArrayList<>();
		try {

			super.br = new BufferedReader(new FileReader(this.csvFile));
			while ((super.line = super.br.readLine()) != null) {
				String[] lineaLetta = super.line.split(cvsSplitBy);
				if(lineaLetta.length>0)
					if(!this.puoiCreare){
						for(int i=0;i<lineaLetta.length-1;i++){
							if(lineaLetta[i].toUpperCase().contains("GIORNI")){
								setGiorni(lineaLetta);
							}
							if(lineaLetta[i].toUpperCase().contains("NOTE")){
								settaOrarioGiorno(lineaLetta);
								this.puoiCreare=true;
								this.indiceNote=(i);
							}
						}
					}
					else{
						if(!super.verificaStringaNumero(lineaLetta[0])){
							docentiOutput.add(inizializzaDocente(lineaLetta));
						}
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
		return docentiOutput;
	}

	//metodo che prende in input un array di stringhe(una linea di un file) e restituisce in output un docente inizializzato 

	private void settaOrarioGiorno(String[] lineaLetta) {
		int i=0;
		List<String> giorni = new ArrayList<>();
		for(String s : lineaLetta){
			if(i==this.giorni.length&&!s.toUpperCase().contains("NOTE"))
				giorni.add(giorni.size(), this.giorni[this.giorni.length-1] + " " +s);
			if(s.toUpperCase().contains("NOTE")){
				this.giorni=new String[giorni.size()];
				for(int j=0;j<this.giorni.length;j++){
					this.giorni[j]=giorni.get(j);
				}
				break;
			}
			if(s!=null&& s!="" && i<this.giorni.length){
				if(i>0&& this.giorni[i].equals(""))
					giorni.add(i,this.giorni[i-1] + " " + s);
				else
					giorni.add(i,this.giorni[i] + " " + s);
				i++;
			}
		}

	}
	private Docente inizializzaDocente(String[] docente){
		List<Integer> disponibilita=inserimentoDisponibilita(docente);
		Docente doc= new Docente(docente[0],selezionaStringa(docente,this.indiceNote+1), disponibilita,selezionaStringa(docente, this.indiceNote));
		return doc;

	}
	//risolto problema in caso di disponibilita non inserite nel file
	public List<Integer> inserimentoDisponibilita(String[] docente){
		List<Integer> disponibilita=new ArrayList<>();
		try{	
			for(int i=1;i<this.indiceNote;i++){
				if(docente[i].equals("SI"))
					disponibilita.add(i);
			}
			return disponibilita;}
		catch(Exception e){
			return disponibilita;
		}
	}

	//metodo di appoggio per verificare se una stringa all'interno dell'array nella posizione dell'indice esiste oppure � nulla
	//servira anche per studente(da discutere)?
	public String selezionaStringa(String[] docente, int indice){
		String stringa=null;
		try{
			stringa=docente[indice];
		}									
		catch(Exception e){
			stringa="";
		}
		return stringa;
	}
	public String[] getGiorni() {
		return giorni;
	}
	public void setGiorni(String[] giorni) {
		this.giorni=giorni;
	}



}
