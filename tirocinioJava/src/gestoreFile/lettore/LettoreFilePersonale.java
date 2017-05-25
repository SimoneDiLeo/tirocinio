package gestoreFile.lettore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import classi.Personale;
import gestoreFile.LettoreFile;

public class LettoreFilePersonale extends LettoreFile{

	private boolean puoiCreare;


	public LettoreFilePersonale(File personale, String divisore) {
		super(personale, divisore);
		// TODO Auto-generated constructor stub
	}



	public List<Personale> inizializzaElementiDaFile() throws IOException {
		List<Personale> listaPersonale=new ArrayList<>();
		try {

			super.br = new BufferedReader(new FileReader(this.csvFile));
			while ((super.line = super.br.readLine()) != null) {
				String[] riga = super.line.split(cvsSplitBy);
				if(riga.length>0)
					if(!this.puoiCreare){
						for(int i=0;i<riga.length-1;i++)
							if(riga[i].contains("gruppo")){

								this.puoiCreare=true;

							}
					}
					else{
						listaPersonale.add(inizializzaPersonale(riga));

					}
			}
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
		return listaPersonale;

	}



	private Personale inizializzaPersonale(String[] riga) {
		Personale pers=new Personale(verificaNulla(riga, 2),verificaNulla(riga, 1),verificaNulla(riga, 3),verificaNulla(riga, 5),verificaNulla(riga, 6));

		return pers;
	}  


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

	// metodo che mi restituisce i dottorandi da personale

	public List<Personale> getDottorandi() throws IOException{
		List<Personale> listaDott=new ArrayList<>();
		List<Personale> listapers=this.inizializzaElementiDaFile();
		for(Personale p: listapers)
			if (p.getRuolo().equals("dottorando"))
				listaDott.add(p);

		return listaDott;
	}


}