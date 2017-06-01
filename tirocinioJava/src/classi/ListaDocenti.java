package classi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JLabel;

public class ListaDocenti {
	private List<Docente> docenti;

	public ListaDocenti(List<Docente> doc){
		this.docenti=doc;
	}

	public void rimuoviDocente(Docente d){
		this.docenti.remove(d);
	}	


	public List<Docente> filtraListaRest(List<Docente> pres){
		List<Docente> lista=new ArrayList<>();
		for(Docente d:this.docenti){
			if(!pres.contains(d))
				lista.add(d);
		}
		return lista;
	}

	public Docente trovaDocenteDaNome(String nome){
		for(Docente d:this.docenti){
			if(nome.contains(d.getNome())){
				return d;
			}
		}
		return null;
	}

	public List<Docente> docentiConDisponibilita(List<Integer> slotDisponibilita){
		List<Docente> docDisponibili=new ArrayList<>();		
		for(Docente d:this.getDocenti()){
			for(Integer i:slotDisponibilita)
				if(!docDisponibili.contains(d)&&d.getDisponibilita().contains(i))
					docDisponibili.add(d);
		}
		return docDisponibili;

	}

	public void inizializzaRuoloDocenti(List<Personale> personale){
		for(Personale p:personale){
			for(Docente d: this.docenti){
				if(p.getCognome().toUpperCase().contains((d.getNome())))
					d.setRuolo(p.getRuolo());
			}
		}
	}

	public List<JLabel> creaLabelDocenti(){
		List<JLabel> lista=new ArrayList<>();
		for(Docente d:this.docenti){ 	
			if(d.getDisponibilita().size()>0){
				JLabel nomeDocente = new JLabel(d.getNome()+ " T : "+d.getNumeroLaureandiTriennali() +" M: " + d.getNumeroLaureandiMagistrali() + " Dis : " +d.getDisponibilita());
				lista.add(nomeDocente);}
		}
		return lista;
	}	


	public List<Docente> getDocenti() {
		return docenti;
	}

	public void setDocenti(List<Docente> docenti) {
		this.docenti = docenti;
	}

	@Override
	public String toString() {
		return "ListaDocenti [docenti=" + docenti + "]";
	}

	public void incrementaDisponibilitaDocente(Docente d) {
		for(int i=0;i<this.docenti.size();i++){
			if(this.docenti.get(i).equals(d)){
				d=this.docenti.get(i);
				d.incrementaSelezionato();
				this.docenti.set(i, d);
			}
		}
	}

	public void azzeraDisponibilitaDocente(Docente d) {
		for(int i=0;i<this.docenti.size();i++){
			if(this.docenti.get(i).equals(d)){
				d=this.docenti.get(i);
				d.azzeraSelezionato();
				this.docenti.set(i, d);
			}
		}
	}

	public void decrementaDisponibilitaDocente(Docente d) {
		for(int i=0;i<this.docenti.size();i++){
			if(this.docenti.get(i).equals(d)){
				d=this.docenti.get(i);
				d.decrementaSelezionato();
				this.docenti.set(i, d);
			}
		}
	}


	public void reinizializzaTrannePresidenti(Collection<Docente> values) {
		for(Docente d:this.docenti){
			for(Docente nonPresidente:values){
				if(!nonPresidente.equals(d)){
					d.azzeraSelezionato();
				}
			}
		}

	}
}
