package classi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssegnaControrelatore {

	private List<Studente> listaStud;
	private Map<Controrelatore,Integer> controrelatori;
	private List<Personale> listaPer;


	// listCont = lista di controrelatori disponibili
	public AssegnaControrelatore(List<Studente> listaStud,Map<Controrelatore,Integer> controrelatori,List<Personale> listaPer){
		this.listaStud=listaStud;
		this.controrelatori=controrelatori;
		this.listaPer=listaPer;
	}

	// numero controrelazioni che può fare un controrelatore 
	public int getNumeroControrelazioni(){

		double num1=(double) this.listaStud.size();
		double num2=(double) this.controrelatori.size();
		double tot=Math.ceil(num1/num2);

		int ris=(int) tot;

		return ris;
	}

	// metodo. dato il docente (relatore) mi restituisce il gruppo di ricerca

	public String getGruppoDocente(Docente doc){
		for(Personale p: this.listaPer)
			if (p.getCognome().equals(doc.getNome()) || p.getCognome().equals(doc.getNome().toLowerCase()) || p.getCognome().equals(doc.getNome().toUpperCase()) ){  // in  docente il nome sarebbe cognome
				return p.getGruppoRicerca();
			}

		return null;
	}





	public Controrelatore prendiControrelatore(String anzianita){
		for (Map.Entry<Controrelatore,Integer> entry : this.controrelatori.entrySet() )
			if (entry.getKey().getAnzianita().equals(anzianita) && entry.getValue()<this.getNumeroControrelazioni())
				return entry.getKey();


		return null;

	}

	// metodo che prende come controrelatore quello dello stesso gruppo del relatore
	public Controrelatore prendiControrelatoreGruppo(String anzianita,String gruppoRelatore ){
		for (Map.Entry<Controrelatore,Integer> entry : this.controrelatori.entrySet() )
			if (entry.getKey().getAnzianita().equals(anzianita) && entry.getValue()<this.getNumeroControrelazioni() && entry.getKey().getGruppoRic().equals(gruppoRelatore) )
				return entry.getKey();


		return null;

	}

	public boolean isNumber(String num){
		try{
			Integer.parseInt(num);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	public Controrelatore prendiDottMinore(){
		int min=1000;
		Controrelatore minore=null;
		for (Map.Entry<Controrelatore,Integer> entry : this.controrelatori.entrySet())
			if(this.isNumber(entry.getKey().getAnzianita())){
				if(Integer.parseInt(entry.getKey().getAnzianita())<min && entry.getValue()<this.getNumeroControrelazioni()){
					min=Integer.parseInt(entry.getKey().getAnzianita());
					minore=entry.getKey();
				}
			}

		return minore;
	}
	// prende dottorando con ciclo minore dello stesso gruppo(se esiste)
	public Controrelatore prendiDottMinoreGruppo(String gruppoRelatore){
		int min=1000;
		Controrelatore minore=null;
		for (Map.Entry<Controrelatore,Integer> entry : this.controrelatori.entrySet())
			if(this.isNumber(entry.getKey().getAnzianita())){
				if(Integer.parseInt(entry.getKey().getAnzianita())<min && entry.getValue()<this.getNumeroControrelazioni() && entry.getKey().getGruppoRic().equals(gruppoRelatore)){
					min=Integer.parseInt(entry.getKey().getAnzianita());
					minore=entry.getKey();
				}
			}

		return minore;
	}





	public Controrelatore prendiDottMaggiore(){
		int max=0;
		Controrelatore maggiore=null;
		for (Map.Entry<Controrelatore,Integer> entry : this.controrelatori.entrySet())
			if(this.isNumber(entry.getKey().getAnzianita())){
				if(Integer.parseInt(entry.getKey().getAnzianita())>max && entry.getValue()<this.getNumeroControrelazioni()){
					max=Integer.parseInt(entry.getKey().getAnzianita());
					maggiore=entry.getKey();
				}
			}

		return maggiore;
	}


	//prende dottorando con ciclo maggiore dello stesso gruppo(se esiste)

	public Controrelatore prendiDottMaggioreGruppo(String gruppoRelatore){
		int max=0;
		Controrelatore maggiore=null;
		for (Map.Entry<Controrelatore,Integer> entry : this.controrelatori.entrySet())
			if(this.isNumber(entry.getKey().getAnzianita())){
				if(Integer.parseInt(entry.getKey().getAnzianita())>max && entry.getValue()<this.getNumeroControrelazioni() && entry.getKey().getGruppoRic().equals(gruppoRelatore)){
					max=Integer.parseInt(entry.getKey().getAnzianita());
					maggiore=entry.getKey();
				}
			}

		return maggiore;
	}


	public Controrelatore prendiStessoGruppo(String gruppoRelatore){
		for (Map.Entry<Controrelatore,Integer> entry : this.controrelatori.entrySet())
			if (entry.getKey().getGruppoRic().equals(gruppoRelatore)  && entry.getValue()<this.getNumeroControrelazioni())
				return entry.getKey();


		return null;

	}

	public Controrelatore prendi(){
		for (Map.Entry<Controrelatore,Integer> entry : this.controrelatori.entrySet() )
			if( entry.getValue()<this.getNumeroControrelazioni())
				return entry.getKey();
		return null;
	}







	public void assegna(){
		for(Studente s:this.listaStud){
			if (s.getControrelatore()==null){
				if(s.getTipoLaurea().equals("MAGISTRALE")){

					Controrelatore c=prendiControrelatoreGruppo("assegnista",this.getGruppoDocente(s.getRelatore()));
					if (c!=null){
						s.setControrelatore(c);
						this.controrelatori.put(c,this.controrelatori.get(c)+1);
					}
					else{


						c=prendiControrelatoreGruppo("cultore materia",this.getGruppoDocente(s.getRelatore()));
						if (c!=null){
							s.setControrelatore(c);
							this.controrelatori.put(c,this.controrelatori.get(c)+1);
						}
						else{

							c=prendiDottMinoreGruppo(this.getGruppoDocente(s.getRelatore()));
							if(c!=null){
								s.setControrelatore(c);
								this.controrelatori.put(c,this.controrelatori.get(c)+1);
							}
							else{



								c=prendiControrelatore("assegnista");
								if (c!=null){
									s.setControrelatore(c);
									this.controrelatori.put(c,this.controrelatori.get(c)+1);
								}
								else{

									c=prendiControrelatore("cultore materia");
									if(c!=null){
										s.setControrelatore(c);
										this.controrelatori.put(c,this.controrelatori.get(c)+1);
									}
									else{



										c=prendiDottMinore();
										if(c!=null){
											s.setControrelatore(c);
											this.controrelatori.put(c,this.controrelatori.get(c)+1);
										}

										else{
											c=prendiStessoGruppo(this.getGruppoDocente(s.getRelatore()));
											if(c!=null){
												s.setControrelatore(c);
												this.controrelatori.put(c,this.controrelatori.get(c)+1);
											}
											else{
												c=prendi();
												s.setControrelatore(c);
												this.controrelatori.put(c,this.controrelatori.get(c)+1);
											}
										}

									}
								}
							}

						}
					}
				}

				//------------------------------------------------------------------------------------------------------------
				else {   // se si tratta di triennale

					Controrelatore c1=prendiDottMaggioreGruppo(this.getGruppoDocente(s.getRelatore()));
					if(c1!=null){
						s.setControrelatore(c1);
						this.controrelatori.put(c1,this.controrelatori.get(c1)+1);
					}
					else{


						c1=prendiControrelatoreGruppo("assegnista",this.getGruppoDocente(s.getRelatore()));
						if(c1!=null){
							s.setControrelatore(c1);
							this.controrelatori.put(c1,this.controrelatori.get(c1)+1);
						}
						else{

							c1=prendiControrelatoreGruppo("cultore materia",this.getGruppoDocente(s.getRelatore()));
							if(c1!=null){
								s.setControrelatore(c1);
								this.controrelatori.put(c1,this.controrelatori.get(c1)+1);
							}
							else{


								c1=prendiDottMaggiore();
								if(c1!=null){
									s.setControrelatore(c1);
									this.controrelatori.put(c1,this.controrelatori.get(c1)+1);
								}
								else{

									c1=prendiControrelatore("assegnista");
									if(c1!=null){
										s.setControrelatore(c1);
										this.controrelatori.put(c1,this.controrelatori.get(c1)+1);
									}
									else{


										c1=prendiControrelatore("cultore materia");
										if(c1!=null){
											s.setControrelatore(c1);
											this.controrelatori.put(c1,this.controrelatori.get(c1)+1);
										}
										else{
											c1=prendiStessoGruppo(this.getGruppoDocente(s.getRelatore()));
											if(c1!=null){
												s.setControrelatore(c1);
												this.controrelatori.put(c1,this.controrelatori.get(c1)+1);
											}
											else{
												c1=prendi();
												s.setControrelatore(c1);
												this.controrelatori.put(c1,this.controrelatori.get(c1)+1);
											}
										}
									}
								}

							}

						}
					}
				}
			}
		}

	}

	public List<Studente> getListaStud(){
		return this.listaStud;
	}

}

