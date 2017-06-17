package gestoreFile.lettore;

import java.io.BufferedReader;
import java.io.File;
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
		int posPer=0; // posizione della stringa personale
		try {

			super.br = new BufferedReader(new FileReader(this.csvFile));
			while ((super.line = super.br.readLine()) != null) {
				String[] riga = super.line.split(cvsSplitBy);
				if(this.contieneStringa(riga,"Personale")){
					posPer=this.posizioneStringa(riga,"Personale");
				}
				if(riga.length>0)
					if(!this.puoiCreare){
						for(int i=0;i<riga.length-1;i++){
							
							if(riga[i].contains("gruppo")){

								this.puoiCreare=true;

							}
						}
					}
					else{
						listaPersonale.add(inizializzaPersonale(riga,posPer));

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



	private Personale inizializzaPersonale(String[] riga,int posPer) {
		// soluzione spartana
		Personale pers;
		if(posPer==1){
			pers=new Personale(verificaNulla(riga, 2),verificaNulla(riga, 1),verificaNulla(riga, 3),verificaNulla(riga, 5),verificaNulla(riga, 6));
		}
		else{
			pers=new Personale(verificaNulla(riga, 3),verificaNulla(riga, 2),verificaNulla(riga, 4),verificaNulla(riga, 6),verificaNulla(riga, 7));
		}
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
	
	// trova la posizione di una stringa
	public int posizioneStringa(String[] riga,String st){
		int pos=0;
		int i;
		for(i=0;i<riga.length;i++){
			if(riga[i].equals(st))
				pos=i;
		}
		
		
		
		
		return pos;
	}
	
	public boolean contieneStringa(String[] riga,String st){
		
		int i;
		for(i=0;i<riga.length;i++){
			if(riga[i].equals(st))
				return true;
		}
		
		
		
		
		return false;
	}


}