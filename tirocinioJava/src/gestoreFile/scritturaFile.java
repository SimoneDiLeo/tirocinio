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
		try {
			System.out.println(nomeFile);
			file=new FileWriter("dati\\"+ nomeFile +".csv");
			for(String p: lista){
				file.append(NEW_LINE_SEPARATOR);
	    		file.append(p);
	    	    file.append(COMMA_DELIMITER);
	    	    }
			file.flush();
			file.close();
			
		} catch (IOException e) {
			new FinestraErrore();
		} 
		
	}
}
