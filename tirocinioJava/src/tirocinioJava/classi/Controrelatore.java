package tirocinioJava.classi;

public class Controrelatore {
    private String disponibilita;
    private String gruppoRicerca;
    private String anzianita;
    
    
    
    public Controrelatore(String dis,String grup,String anz){
    	this.setDisponibilita(dis);
    	this.setGruppoRicerca(grup);
    	this.setAnzianita(anz);
    	
    }



	public String getDisponibilita() {
		return this.disponibilita;
	}



	public void setDisponibilita(String disponibilita) {
		this.disponibilita = disponibilita;
	}



	public String getGruppoRicerca() {
		return this.gruppoRicerca;
	}



	public void setGruppoRicerca(String gruppoRicerca) {
		this.gruppoRicerca = gruppoRicerca;
	}



	public String getAnzianita() {
		return this.anzianita;
	}



	public void setAnzianita(String anzianita) {
		this.anzianita = anzianita;
	}
    
    
    @Override
    public String toString(){
    	return "disponibilità: "+this.getDisponibilita() + " gruppo ricerca: "+ this.getGruppoRicerca()+ " anzianità: "+ this.getAnzianita();
    }
    
    @Override
    public boolean equals(Object o){
    	Controrelatore c=(Controrelatore) o;
    	return this.getDisponibilita().equals(c.getDisponibilita()) && this.getGruppoRicerca().equals(c.getGruppoRicerca()) && this.getAnzianita().equals(c.getAnzianita());
    	
    }
    
    @Override
    public int hashCode(){
    	return this.getDisponibilita().hashCode()+this.getGruppoRicerca().hashCode()+this.getAnzianita().hashCode();
    }
    
   
}