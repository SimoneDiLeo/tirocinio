package classi;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ListaCommissioni {
	private CommissioneGrafica[] commMag;
	private CommissioneGrafica[] commTri;


	public ListaCommissioni(int numMag,int numTri){
		this.commMag=new CommissioneGrafica[numMag];
		this.commTri=new CommissioneGrafica[numTri];
	}

	public void inizializzaPresidentiMagistrali(Map<Integer,Docente> presidentiPotenziali){
		URL url = this.getClass().getClassLoader().getSystemResource("properties");
		Properties prop = new Properties();
		try {
			prop.load(url.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i=0;i<this.commMag.length;i++){
			if(presidentiPotenziali.get(i)!=null)
				this.commMag[i]=new CommissioneGrafica(presidentiPotenziali.get(i), Integer.parseInt(prop.getProperty("COMMISSARI_MAGISTRALI")),"MAGISTRALE");
		}
	}

	public void inizializzaPresidentiTriennali(Map<Integer, Docente> presidentiCorrenti){
		URL url = this.getClass().getClassLoader().getSystemResource("properties");
		Properties prop = new Properties();
		try {
			prop.load(url.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i=0;i<this.commTri.length;i++){
			if(presidentiCorrenti.values().size()>i+this.commMag.length)
				this.commTri[i]=new CommissioneGrafica(presidentiCorrenti.get(i+this.commMag.length), Integer.parseInt(prop.getProperty("COMMISSARI_TRIENNALI")),"TRIENNALE");
		}
	}

	public CommissioneGrafica[] getCommMag() {
		return commMag;
	}

	public void setCommMag(CommissioneGrafica[] commMag) {
		this.commMag = commMag;
	}

	public CommissioneGrafica[] getCommTri() {
		return commTri;
	}

	public void setCommTri(CommissioneGrafica[] commTri) {
		this.commTri = commTri;
	}


	public String toStringMagistrale() {
		return "ListaCommissioni [commMag=" + Arrays.toString(commMag) + "]";
	}

	public String toStringTriennale() {
		return "ListaCommissioni [commMag=" + Arrays.toString(commTri) + "]";
	}

}
