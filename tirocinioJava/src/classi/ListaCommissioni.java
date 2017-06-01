package classi;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

public class ListaCommissioni {
	private Commissione[] commMag;
	private Commissione[] commTri;
	private Properties prop;
	
	public ListaCommissioni(int numMag,int numTri,Properties prop){
		this.commMag=new Commissione[numMag];
		this.commTri=new Commissione[numTri];
		this.prop=prop;
	}

	public void inizializzaPresidentiMagistrali(Map<Integer,Docente> presidentiPotenziali,int numeroMaxStud){
		for(int i=0;i<this.commMag.length;i++){
			if(presidentiPotenziali.get(i)!=null)
				this.commMag[i]=new Commissione(presidentiPotenziali.get(i), Integer.parseInt(prop.getProperty("COMMISSARI_MAGISTRALI")),"MAGISTRALE");
				this.commMag[i].setNumeroCommissione(i+1);
				this.commMag[i].setMaxStudComm(numeroMaxStud);
				
		}
	}

	public void inizializzaPresidentiTriennali(Map<Integer, Docente> presidentiCorrenti,int numeroMaxStud){
		for(int i=0;i<this.commTri.length;i++){
			if(presidentiCorrenti.values().size()>i+this.commMag.length)
				this.commTri[i]=new Commissione(presidentiCorrenti.get(i+this.commMag.length), Integer.parseInt(prop.getProperty("COMMISSARI_TRIENNALI")),"TRIENNALE");
				this.commTri[i].setNumeroCommissione(i+1+this.commMag.length);
				this.commTri[i].setMaxStudComm(numeroMaxStud);
		}
	}
	
	
	
	
	
	
	

	public Commissione[] getCommMag() {
		return commMag;
	}

	public void setCommMag(Commissione[] commMag) {
		this.commMag = commMag;
	}

	public Commissione[] getCommTri() {
		return commTri;
	}

	public void setCommTri(Commissione[] commTri) {
		this.commTri = commTri;
	}


	public String toStringMagistrale() {
		return "ListaCommissioni [commMag=" + Arrays.toString(commMag) + "]";
	}

	public String toStringTriennale() {
		return "ListaCommissioni [commMag=" + Arrays.toString(commTri) + "]";
	}
	
	

}
