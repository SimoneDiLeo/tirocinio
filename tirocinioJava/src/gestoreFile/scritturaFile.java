package gestoreFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import classi.Commissione;
import controller.Controller;

public class scritturaFile {
	private String COMMA_DELIMITER=";";
	private String NEW_LINE_SEPARATOR="\n";

	public scritturaFile(){

	}

	public void scrivi(File file,Controller c) throws IOException{
		FileWriter fileCommissione =  new FileWriter(file);
		try {
			for(Commissione co : c.getListaCommissioni().getCommMag()){
				fileCommissione.append("presidente " + co.getPresidente().getNome());
			}



			fileCommissione.flush();
			fileCommissione.close();

		} catch (IOException e) {
			throw e;
		} 

	}
}
