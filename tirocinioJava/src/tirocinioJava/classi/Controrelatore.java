package tirocinioJava.classi;

public class Controrelatore {
	private String nome;
    private String disponibilita;
    private String anzianita;
    private String gruppoRic;
    
    
    
    public Controrelatore(String nome,String dis ,String anz,String gruppoRic){
    	this.setNome(nome);
    	this.setDisponibilita(dis);
    	
    	this.setAnzianita(anz);
    	this.setGruppoRic(gruppoRic);
    	
    }



	public String getDisponibilita() {
		return this.disponibilita;
	}



	public void setDisponibilita(String disponibilita) {
		this.disponibilita = disponibilita;
	}





	public String getAnzianita() {
		return this.anzianita;
	}



	public void setAnzianita(String anzianita) {
		this.anzianita = anzianita;
	}
    
    
    @Override
    public String toString(){
    	return "[ Nome: "+ this.getNome()+" "+ "disponibilità: "+this.getDisponibilita() + " anzianità: "+ this.getAnzianita()+" gruppo:"+this.getGruppoRic()+"]" ;
    }
    
    @Override
    public boolean equals(Object o){
    	Controrelatore c=(Controrelatore) o;
    	return this.getNome().equals(c.getNome()) && this.getDisponibilita().equals(c.getDisponibilita())  && this.getAnzianita().equals(c.getAnzianita());
    	
    }
    
    @Override
    public int hashCode(){
    	return this.getDisponibilita().hashCode()+this.getNome().hashCode()+this.getAnzianita().hashCode();
    }



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getGruppoRic() {
		return gruppoRic;
	}



	public void setGruppoRic(String gruppoRic) {
		this.gruppoRic = gruppoRic;
	}
    
   
}