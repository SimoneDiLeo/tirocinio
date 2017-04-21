package gestoreFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



//classe per leggere i file
public class LettoreFile {

	protected String csvFile;
	protected BufferedReader br;
	protected String line;
	protected String cvsSplitBy;

	public LettoreFile(String percorso,String divisore){
		this.csvFile=percorso;
		this.br=null;
		this.line="";
		this.cvsSplitBy=divisore;
	}
	//metodo che legge in file e stampa il suo contenuto a video
	public void leggiFile(){
		try {

			this.br = new BufferedReader(new FileReader(this.csvFile));
			while ((this.line = this.br.readLine()) != null) {
				String[] elementi = this.line.split(cvsSplitBy);
				System.out.println("nuova riga del file \n");
				for(int i=0;i<elementi.length;i++){
					System.out.println("Elemento in posizione numero : " + i +"	" + elementi[i]+"\n" );
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//metodo per verificare se una stringa in input è un numero(output=booleano)
	protected boolean verificaStringaNumero(String string){
		try{
			Integer.parseInt(string);
			return true;
		}catch(Exception e){
			return false;
		}
	}


}





