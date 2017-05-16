package gestoreFile;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import interfacciaGrafica.FinestraErrore;

public class scritturaFile {
	private String COMMA_DELIMITER=";";
	private String NEW_LINE_SEPARATOR="\n";

	public scritturaFile(){

	}

	public void scrivi(String nomeFile,List<String> lista){
		FileWriter file;
		FileWriter fileCommissione;
		int j=1;
		try {
			String m="Magistrale";
			boolean filtra=false;
			fileCommissione=new FileWriter("tentativi\\"+ nomeFile +"commissione"+m+"Numero"+j+".csv");
			file=new FileWriter("tentativi\\"+ nomeFile +".csv");
			for(String p: lista){
				if(p.contains("Commissioni Magistrali")){
					filtra=true;
				}
				else
					filtra=false;
				if(p.contains("Commissioni Triennali")){
					j=1;
					m="Triennale";	
					filtra=true;
				}
				else 
					filtra=false;

				if(p.contains("commissione numero")){
					fileCommissione.flush();
					fileCommissione.close();
					fileCommissione=new FileWriter("tentativi\\"+ nomeFile +"commissione"+ m +"Numero"+j+".csv");
					j++;
				}
				String [] s =p.split("\t");
				fileCommissione.append(NEW_LINE_SEPARATOR);
				file.append(NEW_LINE_SEPARATOR);
				for(int i=0;i<s.length;i++){
					if(!filtra){
						fileCommissione.append(s[i]);
						fileCommissione.append(COMMA_DELIMITER);
					}
					file.append(s[i]);
					file.append(COMMA_DELIMITER);
				}
			}
			file.flush();
			file.close();
			fileCommissione.flush();
			fileCommissione.close();

		} catch (IOException e) {
			new FinestraErrore();
		} 

	}
}
