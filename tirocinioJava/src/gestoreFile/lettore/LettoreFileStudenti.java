package gestoreFile.lettore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gestoreFile.LettoreFile;
import tirocinioJava.classi.Docente;
import tirocinioJava.classi.Studente;

public class LettoreFileStudenti extends LettoreFile{
	String tipoLaurea;
	int indiceMatricola;
	int indiceNome;
	int indiceCognome;
	int indiceRelatore;
	int indiceCorelatore;
	int indiceNote;
	int indiceArgomento;
	//costruttore per la classe con il richiamo alla classe che estende piu inizializzazione della stringa tipoLaurea
	public LettoreFileStudenti(String percorso,String separatore){
		super(percorso,separatore);
		this.tipoLaurea="";
	}

	//metodo che prende in input una stringa nome e una lista di docenti e da in output un docente con quel nome
	private Docente trovaDocenteDaNome(String nomeDocente, List<Docente> docenti) throws Exception {
		Docente trovato = null;
		for(Docente d:docenti){
			if(nomeDocente.toUpperCase().contains(d.getNome()))
				trovato=d;
		}
		return trovato;
	}
	//metodo per inizializzare una lista s di input di studenti con dei dati letti da un file e associandogli i relatori e correlatori in base alla lista docenti di input
	public List<Studente> inizializzaElementiDaFile(List<Docente> docenti) {
		List<Studente> studenti=new ArrayList<>();
		try {
			boolean trovato=false;
			super.br = new BufferedReader(new FileReader(this.csvFile));
			while ((super.line = super.br.readLine()) != null) {
				String[] lineaFile = super.line.split(cvsSplitBy);

				if(lineaFile.length<2)
					verificaTipoCommissione(lineaFile);
				else
					if(!super.verificaStringaNumero(lineaFile[0])){
						trovato=inizializzaIndici(lineaFile);
					}
					else
						if(super.verificaStringaNumero(lineaFile[0]))
							studenti.add(inizializzaStudente(lineaFile,docenti,trovato));

			}
			return studenti;
		} 
		catch (Exception e){
			e.printStackTrace();
		} 
		finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return studenti;
	}

	//metodo che dato un array di stringhe una lista docenti e un booleano(che serve solo in caso di cognome(utile?)) da un output un oggetto studente inizializzato con i dati dell'array
	private Studente inizializzaStudente(String[] lineaFile, List<Docente> docenti,boolean trovato) throws Exception {
		String nome="";
		if(trovato)
			nome = lineaFile[this.indiceNome]+ " " + lineaFile[this.indiceCognome];
		else
			nome = lineaFile[this.indiceNome];

		String matricola=lineaFile[this.indiceMatricola];
		String note=lineaFile[this.indiceNote];
		String argomento=lineaFile[this.indiceArgomento];
		String nomeRelatore=lineaFile[this.indiceRelatore];
		Docente relatore = trovaDocenteDaNome(nomeRelatore.toUpperCase(),docenti);
		String nomeCorrelatore=lineaFile[this.indiceCorelatore];
		Docente correlatore = trovaDocenteDaNome(nomeCorrelatore.toUpperCase(),docenti);
		Studente stud=new Studente(Integer.parseInt(lineaFile[0]),nome,matricola,relatore,correlatore,this.tipoLaurea,argomento,note);
		relatore.addLaureando(stud);
		return stud;
	}

	//metodo per inizializzare gli indici della classe per leggere successivamente piu velocemente ogni riga
	private boolean inizializzaIndici(String[] lineaFile) {
		boolean cognomeTrovato=false;
		for(int i=0;i<lineaFile.length;i++){
			String parola=lineaFile[i].toUpperCase();
			if(parola.contains("NOME")&&!parola.equals("COGNOME"))
				this.indiceNome=i;
			else
				if(parola.equals("COGNOME")){
					this.indiceCognome=i;
					cognomeTrovato=true;
				}
				else
					if(parola.contains("ARGOMENTO"))
						this.indiceArgomento=i;
					else
						if(parola.contains("MATRICOLA"))
							this.indiceMatricola=i;
						else
							if(parola.equals("RELATORE"))
								this.indiceRelatore=i;
							else
								if(parola.contains("CORREALTORE"))
									this.indiceCorelatore=i;
								else
									if(parola.contains("NOTE"))
										this.indiceNote=i;
		}
		return cognomeTrovato;
	}


	//metodo per inizializzare un tipo di commissione in base al valore all'interno della lineaFile
	private void verificaTipoCommissione(String[] lineaFile){		
		for(int i=0;i<lineaFile.length;i++){
			if(lineaFile[i].toUpperCase().contains("TRIENNALE")){
				this.tipoLaurea="TRIENNALE";
			}
			else
				if(lineaFile[i].toUpperCase().contains("MAGISTRALE")){
					this.tipoLaurea="MAGISTRALE";
				}

		}

	}

}

