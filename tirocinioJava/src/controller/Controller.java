package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import classi.Commissione;
import classi.Controrelatore;
import classi.Docente;
import classi.ListaCommissioni;
import classi.ListaDocenti;
import classi.Personale;
import classi.Studente;
import interfacciaGrafica.listenerBottoni.ListenerItemSelezionaCommissario;
import interfacciaGrafica.renderer.ComboBoxRendererCommissari;
import interfacciaGrafica.FinestraErrore;
import logica.BozzaAlgoritmo;
import logica.CaricatoreTuttiFile;
import logica.DocenteComparatorMag;
import logica.DocenteComparatorTri;
import logica.DocenteComparatorePresidentiMagistrali;
import logica.DocenteComparatorePresidentiTriennali;

public class Controller {
	private Properties proprieta;
	private ListaDocenti docenti;
	private List<Studente> studenti;
	private List<Controrelatore> controrel;
	private List<Personale> personale;
	private List<Docente> listaPotenzialiPresidenti;
	private List<Docente> listaPresidentiScartati; 
	private Map<Integer,Docente> presidentiCorrenti=new HashMap<>();
	private ListaCommissioni listaCommissioni;    
	private List<JFrame> framesPrecedente=new ArrayList<>();
	private String[] giorni;
	private List<Studente> studentiScartatiTriennale;
	private List<Studente> studentiScartatiMagistrale;

	public Controller(){
		this.studentiScartatiTriennale=new ArrayList<>();
		this.studentiScartatiMagistrale=new ArrayList<>();
	}

	public List<Studente> getStudentiScartatiTriennale() {
		return studentiScartatiTriennale;
	}

	public void setStudentiScartatiTriennale(List<Studente> studentiScartatiTriennale) {
		this.studentiScartatiTriennale = studentiScartatiTriennale;
	}

	public List<Studente> getStudentiScartatiMagistrale() {
		return studentiScartatiMagistrale;
	}

	public void setStudentiScartatiMagistrale(List<Studente> studentiScartatiMagistrale) {
		this.studentiScartatiMagistrale = studentiScartatiMagistrale;
	}

	public void aggiungiStudentiScartatiTriennale(List<Studente> list){
		this.studentiScartatiTriennale.addAll(list);
	}
	public void aggiungiStudentiScartatiMagistrale(List<Studente> list){
		this.studentiScartatiMagistrale.addAll(list);
	}

	public void caricaFile(File nomeFileDocenti,File nomeFileStudenti,File nomeFilePersonale,File nomeFileControrelatori){
		CaricatoreTuttiFile call=new CaricatoreTuttiFile();
		try{
			call.inizializza(nomeFileDocenti,nomeFileStudenti,nomeFilePersonale,nomeFileControrelatori);
			this.docenti=call.getDocenti();
			this.studenti=call.getStudenti();
			this.controrel=call.getControrel();
			this.setGiorni(call.getGiorni());
		}
		catch(Exception e){
			new FinestraErrore(this);
		}		
	}

	public int calcolaStudentiTipo(String tipo){
		int i=0;
		tipo=tipo.toUpperCase();
		for(Studente s:this.studenti){
			if(s.getTipoLaurea().contains(tipo)){
				i++;
			}
		}
		return i;
	}





	public Box creaBoxLabel() {
		Box box = Box.createVerticalBox(); 
		List<JLabel> docenti=this.docenti.creaLabelDocenti();
		for(JLabel nomeDocente:docenti){
			if(nomeDocente!=null)
				box.add(nomeDocente);
		}
		return box;
	}

	public void calcola(){
		BozzaAlgoritmo b=new BozzaAlgoritmo();
		this.listaPotenzialiPresidenti=b.trovaTuttiPossibiliPresidenti(docenti, "PO");
		this.listaPresidentiScartati=b.trovaTuttiPossibiliPresidenti(docenti, "PO");
	}

	public Box calcolaPresidenti(int numeroCommissioni, boolean c){
		int j=0;
		BozzaAlgoritmo b=new BozzaAlgoritmo();
		List<Docente> po=b.filtraPo(this.listaPresidentiScartati);
		if(c)
			Collections.sort(po, new DocenteComparatorePresidentiMagistrali());
//			po.sort(new DocenteComparatorePresidentiMagistrali());
		if(!c){
			Collections.sort(po,new DocenteComparatorePresidentiTriennali());
			j=this.presidentiCorrenti.values().size();
		}
		Box box =Box.createVerticalBox();
		for(int i=0;i<numeroCommissioni;i++){
			try{
				Docente presidente = b.trovaPossibilePresidente(po);
				if(presidente!=null){
					box.add(new JLabel(presidente.toString()));
					this.presidentiCorrenti.put((i+j), presidente);
					po.remove(presidente);
					this.listaPresidentiScartati.remove(presidente);
				}
			}
			catch(Exception e){
				box.add(new JLabel("nessun presidente trovato"));
			}
		}
		return box;
	}

	public JComboBox<Docente> comboBoxPresidentiPotenziali(){
		JComboBox<Docente> jm = new JComboBox<>();
		for(Docente d:this.listaPotenzialiPresidenti){
			jm.addItem(d);
		}
		return jm;
	}

	public void modificaPresidenteCommissione(int i,Docente d){
		this.presidentiCorrenti.put(i, d);
	}

	public void setListaCommissioni(int nm,int nt){
		listaCommissioni = new ListaCommissioni(nm, nt, this.proprieta);
	}

	public void inizializzaCommissioniMagistrali(int numeroCommissioniMag){
		this.listaCommissioni.inizializzaPresidentiMagistrali(this.presidentiCorrenti,this.getNumeroStudMagistrali()/numeroCommissioniMag);
	}

	public ListaCommissioni getListaCommissioni() {
		return listaCommissioni;
	}

	public void setListaCommissioni(ListaCommissioni listaCommissioni) {
		this.listaCommissioni = listaCommissioni;
	}

	public void reinizializzaDocentiCorrenti() {
		this.presidentiCorrenti=new HashMap<>();

	}

	public void inizializzaCommissioniTriennali(int numeroCommissioniTri) {
		this.listaCommissioni.inizializzaPresidentiTriennali(this.presidentiCorrenti,this.getNumeroStudTriennali()/numeroCommissioniTri);	
	}

	public void aggiornaCommissione(Commissione cgm) {
		cgm.inizializzaCommissariCommissione(this.docenti);

	}

	public Box creaJComboCommissari(Commissione cgm, Box radioBoxDisponibilita,DefaultListModel modLaureandi,JComboBox<Integer> jc){
		Box box=Box.createVerticalBox();
		int i=0;
		for(List<Docente> docs:cgm.getCommissari()){
			JComboBox<Docente> jm = new JComboBox<>();

			ComboBoxRendererCommissari renderer = new ComboBoxRendererCommissari(jm);

			jm.setRenderer(renderer);




			int j =0;
			//aggiunta per risolvere problema del valore di default
			for(Docente d:docs){
				jm.addItem(d);
			}
			jm.setSelectedIndex(-1);
			jm.addItemListener(new ListenerItemSelezionaCommissario(this,cgm,i,radioBoxDisponibilita,modLaureandi,jc));
			i++;
			box.add(jm);

		}
		return box;
	}

	public void confermaPresidenti() {
		for(Docente d:this.presidentiCorrenti.values())
			d.incrementaSelezionato();

	}

	public Box creaRadioBoxDisponibilita(Commissione cgm) {
		Box box=Box.createHorizontalBox();
		for (Enumeration<AbstractButton> en = cgm.creaRadioBoxDisponibilita().getElements(); en.hasMoreElements();) {
			AbstractButton b = en.nextElement();
			box.add(b);
		}
		return box;
	}

	public List<JLabel> calcoloLabelCommissioniConfermate() {
		List<JLabel> b = new ArrayList<>();
		b.add(new JLabel("Commissioni Magistrali"));
		for(Commissione cgm : this.listaCommissioni.getCommMag()){
			b.add(new JLabel("commissione numero : " + cgm.getNumeroCommissione()));
			b.add(new JLabel("Slot scelto = " + cgm.getSlotScelto()));
			b.add(new JLabel("Presidente : " + cgm.getPresidente().getNome()));
			b.add(new JLabel("Commissari :"));
			b.add(new JLabel("numero massimo studenti : "+cgm.getMaxStudComm()));
			for(Docente d : cgm.getMappatura().values())
				b.add(new JLabel(d.getNome() + " " + d.getRuolo()));
			b.add(new JLabel("Laureandi :"));
			for(Studente s:cgm.getLaureandi())
				b.add(new JLabel(s.toString()));
		}
		b.add(new JLabel("Commissioni Triennali"));
		for(Commissione cgm : this.listaCommissioni.getCommTri()){
			b.add(new JLabel("commissione numero :" + cgm.getNumeroCommissione()));
			b.add(new JLabel("Slot scelto = " + cgm.getSlotScelto()));
			b.add(new JLabel("Presidente : " + cgm.getPresidente().getNome()));
			b.add(new JLabel("Commissari :"));
			b.add(new JLabel("numero massimo studenti : "+cgm.getMaxStudComm()));
			for(Docente d : cgm.getMappatura().values())
				b.add(new JLabel(d.getNome() + " " + d.getRuolo()));
			b.add(new JLabel("Laureandi :"));
			for(Studente s:cgm.getLaureandi())
				b.add(new JLabel(s.toString()));
		}


		return b;
	}

	public void addFrameCorrente(JFrame f){
		this.framesPrecedente.add(f);
	}

	public void tornaIndietro() {
		JFrame frameDaVedere=this.framesPrecedente.get(this.framesPrecedente.size()-1);
		frameDaVedere.setVisible(true);
		this.framesPrecedente.remove(frameDaVedere);
	}

	public Properties getProprieta() {
		return proprieta;
	}

	public void setProprieta(Properties proprieta) {
		this.proprieta = proprieta;
	}

	public void reinizializzaPresidenti() {
		for(Docente d:this.presidentiCorrenti.values()){
			this.docenti.azzeraDisponibilitaDocente(d);
		}

	}

	public void reinizializzaCommissioni(){
		this.docenti.reinizializzaTrannePresidenti(this.presidentiCorrenti.values());
	}

	public void modificaSelezionatoInDocente(Docente d, Commissione cgm, int indiceJComboBox) {
		cgm.addCommissario(indiceJComboBox, d);
		this.docenti.incrementaDisponibilitaDocente(d);		
	}

	public void azzeraCommissioniInLista(Docente docPrecente) {
		this.docenti.azzeraDisponibilitaDocente(docPrecente);				
	}

	public void decrementaCommissioniInLista(Docente docPrecente,Commissione cgm, int indiceJComboBox) {
		cgm.eliminaCommissario(indiceJComboBox,docPrecente);
		this.docenti.decrementaDisponibilitaDocente(docPrecente);				
	}

	public void setCommissariDaMappa(Map<Integer, JComboBox> mappa) {
		for(Entry<Integer,JComboBox> entry:mappa.entrySet()){
			Docente d=(Docente) entry.getValue().getSelectedItem();
			d.incrementaSelezionato();
			this.presidentiCorrenti.put(entry.getKey(),d);
		}
	}

	public void modificaPotenzialiCommissari(Docente item, Commissione cgm, int indiceJComboBox) {
		cgm.aggiornaDisponibilita(item.getDisponibilita());
		//		cgm.aggiornaCommissariPossibili(item.getDisponibilita());

	}

	public void modificaBoxDisponibilita(Box radioBoxDisponibilita, Commissione cgm) {
		radioBoxDisponibilita.removeAll();
		for (Enumeration<AbstractButton> en = cgm.creaRadioBoxDisponibilita().getElements(); en.hasMoreElements();) {
			AbstractButton b = en.nextElement();
			radioBoxDisponibilita.add(b);
		}
		radioBoxDisponibilita.revalidate();

	}

	public void rimuoviCommissario(Docente item, Commissione cgm, int indiceJComboBox) {
		cgm.eliminaCommissario(indiceJComboBox, item);
		cgm.reinizializzaDisponibilita();
	}
	public void getStudenti(){
		for(Studente s:this.studenti)
			System.out.println(s.toString());

	}

	public void setStudenti(List<Studente> studenti) {
		this.studenti = studenti;
	}

	public int getNumeroStudMagistrali(){
		int cont=0;
		for(Studente s:this.studenti){
			if(s.getTipoLaurea().equals("MAGISTRALE"))
				cont++;
		}

		return cont;
	}

	public int getNumeroStudTriennali(){
		int cont=0;
		for(Studente s:this.studenti){
			if(s.getTipoLaurea().equals("TRIENNALE"))
				cont++;
		}

		return cont;
	}

	public void aggiungiLureandiInCommissione(Docente item, Commissione cgm, DefaultListModel modLaureandi,JComboBox<Integer> jc) {
		//		modLaureandi.size()>cgm.getMaxStudComm();
		//		modLaureandi.lastElement().
		String tipoCommissione=cgm.getTipoCommissione();
		if(tipoCommissione.equals("MAGISTRALE")){
			for(Studente s:item.getLaureandiMagistrali()){ // metodo in docente che mi seleziona il tipo di laureando
				if(modLaureandi.size()>=cgm.getMaxStudComm())
					s.setEccesso(true);
				if(!(cgm.getLaureandi().contains(s))){
					modLaureandi.addElement(s);
					cgm.getLaureandi().add(s);
					jc.addItem(s.getNumero());
					jc.revalidate();
				}
			}
			//jc.revalidate();
		}
		else{
			for(Studente s:item.getLaureandiTriennali()){  // metodo in docente che mi seleziona il tipo di laureando
				if(modLaureandi.size()>=cgm.getMaxStudComm())
					s.setEccesso(true);
				if((cgm.getLaureandi().contains(s) && modLaureandi.contains(s))==false){
					modLaureandi.addElement(s);
					cgm.getLaureandi().add(s);
					jc.addItem(s.getNumero());
					jc.revalidate();
				}
			}
			//jc.revalidate();
		}






	}
	public void togliLureandiInCommissione(Docente item, Commissione cgm, DefaultListModel modLaureandi,JComboBox<Integer> jc) {
		String tipoCommissione=cgm.getTipoCommissione();
		if(tipoCommissione.equals("MAGISTRALE")){
			for(Studente s:item.getLaureandiMagistrali()){  // metodo in docente che mi seleziona il tipo di laureando
				modLaureandi.removeElement(s);
				cgm.getLaureandi().remove(s);
				jc.removeItem(s.getNumero());
			}
		}
		else{
			for(Studente s:item.getLaureandiTriennali()){  // metodo in docente che mi seleziona il tipo di laureando
				modLaureandi.removeElement(s);
				cgm.getLaureandi().remove(s);
				jc.removeItem(s.getNumero());
			}
		}





	}

	public void eliminaLaureandoDaCommissione(int numeroStudente,Commissione cgm, DefaultListModel modLaureandi, JComboBox<Integer> jc) {
		Studente st=null;
		modLaureandi.removeAllElements();
		//setto eccesso studente tutti a false
		for(Studente s: cgm.getLaureandi())
			s.setEccesso(false);

		for(Studente s:cgm.getLaureandi()){
			if(s.getNumero()==numeroStudente)
				st=s;
		}
		cgm.getLaureandi().remove(st);



		// coloro quelli in eccesso -------------

		if(cgm.getLaureandi().size()>cgm.getMaxStudComm()){
			int cont=1;
			for(Studente s:cgm.getLaureandi()){

				if(cont>cgm.getMaxStudComm())
					s.setEccesso(true);
				modLaureandi.addElement(s);

				cont++;

			}
		}
		else{
			for(Studente s:cgm.getLaureandi()){


				modLaureandi.addElement(s);



			}
		}



		//-----------------------------------------



		//        int cont=0;

		//        int i;
		//        for(i=0;i<diff;i++)
		//        	modLaureandi.addElement(cgm.getLaureandi().get(i));

		//        for(Studente s:cgm.getLaureandi()){
		//        	if(cont==cgm.getLaureandi().size()-1)
		//        	   s.setEccesso(false);
		//        	modLaureandi.addElement(s);
		//        	
		//        	cont++;
		//        	
		//        }




		//		cgm.getLaureandi().remove(st);
		//		modLaureandi.removeElement(st);
		//		jc.remove(st.getNumero());
		//		jc.validate();
		//		((Studente) modLaureandi.get(cgm.getLaureandi().size()-cgm.getMaxStudComm())).setEccesso(false);







	}



	public void settaEccessoStudenti() {
		for(Studente s:this.studenti)
			s.setEccesso(false);

	}

	public List<JLabel> getLabelGiorni() {
		List<JLabel> giorni = new ArrayList<>();
		for(int i=0;i<this.giorni.length;i++){
			if(!this.giorni[i].toUpperCase().contains("GIORNI"))
				giorni.add(new JLabel("lo slot numero " + i + " corrisponde al giorno :  " + this.giorni[i]));
		}
		giorni.add(new JLabel("lo slot numero " + this.giorni.length + " corrisponde al giorno :  " + this.giorni[this.giorni.length-1]  + " pomeriggio"));
		return giorni;
	}

	public void setGiorni(String[] giorni) {
		this.giorni = giorni;
	}
	// metodo per inserire docenti all interno di una commissione
	// metodo che restituisce una lista ordinata di docenti magistrali da quello che ha piu laurendi a quello che ne ha meno


	public List<Docente> getDocentiMagistraliOrdinati(){
		List<Docente> listDoc=new ArrayList<>();



		//--------------

		for (Docente d:this.docenti.getDocenti()){

			if(d.getLaureandiMagistrali().size()!=0&&!d.isSelezionato()){
				listDoc.add(d);
			}


		}

		//--------

		Collections.sort(listDoc, new DocenteComparatorMag());
		Collections.reverse(listDoc);

		return listDoc;
	}


	// lista di tutti i docenti che hanno laureandi triennali in ordine decrescente
	public List<Docente> getDocentiTriennaliOrdinati(){
		List<Docente> listDoc=new ArrayList<>();



		//--------------

		for (Docente d:this.docenti.getDocenti()){

			if(d.getLaureandiTriennali().size()!=0&&!d.isSelezionato()){
				listDoc.add(d);
			}


		}

		//--------

		Collections.sort(listDoc, new DocenteComparatorTri());
		Collections.reverse(listDoc);

		return listDoc;
	}





	// lista dei docenti che non hanno nessuon laureando (se ci sono)
	//---- implementa

	public List<Docente> getDocentiSenzaLaureandi(String presidente){
		List<Docente> list=new ArrayList<>();
		for(Docente d:this.docenti.getDocenti()){
			if(!(d.getNome().equals(presidente))){
				if(d.getLaureandi().size()==0)
					list.add(d);

			}

		}

		return list;
	}



	public List<Integer> intersection(List<Integer> l1,List<Integer> l2){
		List<Integer> list=new ArrayList<>();
		for(Integer i:l1){
			for(Integer j: l2){
				if(i==j)
					list.add(i);
			}
		}

		return list;
	}


	public Map<Docente,Integer> eliminaDoppi_aux(List<Docente> list){
		Map<Docente,Integer> mp=new HashMap<>();
		for(Docente d:list){
			mp.put(d, 0);
		}




		return mp;

	}


	public List<Docente> listaSenzaDoppi(List<Docente> list){
		Map<Docente,Integer> map=eliminaDoppi_aux(list);
		List<Docente> result=new ArrayList<>();
		for (Map.Entry<Docente,Integer> entry : map.entrySet()){
			result.add(entry.getKey());
		}


		return result;
	}



}