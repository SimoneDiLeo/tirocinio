package controller;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
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
import interfacciaGrafica.listenerBottoni.ListenerItemCheckBoxDisponibilita;
import interfacciaGrafica.listenerBottoni.ListenerSelezionaPresidente;
import interfacciaGrafica.renderer.ComboBoxRendererCommissari;
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

	public void caricaFile(File nomeFileDocenti,File nomeFileStudenti,File nomeFilePersonale,File nomeFileControrelatori) throws IOException{
		CaricatoreTuttiFile call=new CaricatoreTuttiFile();
		call.inizializza(nomeFileDocenti,nomeFileStudenti,nomeFilePersonale,nomeFileControrelatori);
		this.docenti=call.getDocenti();
		this.studenti=call.getStudenti();
		this.controrel=call.getControrel();
		this.setGiorni(call.getGiorni());

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

	public List<Docente> getDocentiOrdinari(){
		return this.docenti.prendiDocentiPo();
	}

	public Box calcolaPresidenti(int numeroCommissioni, boolean c){
		int j=0;
		BozzaAlgoritmo b=new BozzaAlgoritmo();
		List<Docente> po=b.filtraPo(this.listaPresidentiScartati);
		if(c)
			Collections.sort(po, new DocenteComparatorePresidentiMagistrali());
		if(!c){
			Collections.sort(po,new DocenteComparatorePresidentiTriennali());
			j=this.presidentiCorrenti.values().size();
		}
		Box box =Box.createVerticalBox();
		for(int i=0;i<numeroCommissioni;i++){
			try{
				Docente presidente = b.trovaPossibilePresidente(po);
				if(presidente!=null){
					JComboBox<Docente> selettoreDocente = this.comboBoxPresidentiPotenziali();
					selettoreDocente.addItemListener(new ListenerSelezionaPresidente(this, i+j));
					selettoreDocente.setSelectedItem(presidente);
					box.add(selettoreDocente);
					this.presidentiCorrenti.put((i+j), presidente);
					po.remove(presidente);
					this.listaPresidentiScartati.remove(presidente);
				}
			}
			catch(Exception e){
				JComboBox<Docente> selettoreDocente = this.comboBoxPresidentiPotenziali();
				selettoreDocente.setSelectedIndex(-1);
				selettoreDocente.addItemListener(new ListenerSelezionaPresidente(this, i+j));
				this.presidentiCorrenti.put((i+j), null);
				box.add(selettoreDocente);
			}
		}
		return box;
	}

	public void resettaColoreLabelDocenti(){
		this.docenti.resettaColoreLabel();
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

	public ListaCommissioni getListaCommissioni() {
		return listaCommissioni;
	}

	public void setListaCommissioni(ListaCommissioni listaCommissioni) {
		this.listaCommissioni = listaCommissioni;
	}

	public void reinizializzaDocentiCorrenti() {
		this.presidentiCorrenti=new HashMap<>();

	}

	public void inizializzaCommissioniMagistrali(int numeroCommissioniMag){
		if(numeroCommissioniMag!=0)
			this.listaCommissioni.inizializzaPresidentiMagistrali(this.presidentiCorrenti,this.getNumeroStudMagistrali()/numeroCommissioniMag);
	}

	public void inizializzaCommissioniTriennali(int numeroCommissioniTri) {
		if(numeroCommissioniTri!=0)
			this.listaCommissioni.inizializzaPresidentiTriennali(this.presidentiCorrenti,this.getNumeroStudTriennali()/numeroCommissioniTri);	
	}

	public void aggiornaCommissione(Commissione cgm) {
		cgm.inizializzaCommissariCommissione(this.docenti);

	}

	public Box creaJComboCommissari(Commissione cgm, Box radioBoxDisponibilita,DefaultListModel modLaureandi,JComboBox<Integer> jc,DefaultListModel commissariModel,JComboBox<Docente> jcomboEliminaCommissario,int numeroComm){
		Box box=Box.createVerticalBox();
		for(int i=0; i<cgm.getNumeroCommissari();i++){
			List<Docente> docs=cgm.getCommissariPossibili().get(i);
			JComboBox<Docente> jm = new JComboBox<>();
			ComboBoxRendererCommissari renderer = new ComboBoxRendererCommissari(jm);
			jm.setRenderer(renderer);
			for(Docente d:docs){
				jm.addItem(d);
			}
			if(i<cgm.getListacommissari().size())
				cgm.getListacommissari().get(i).incrementaSelezionato();
			jm.setSelectedItem(cgm.getListacommissari().get(i));
			jm.addItemListener(new ListenerItemSelezionaCommissario(this,cgm,i,radioBoxDisponibilita,modLaureandi,jc,commissariModel,jcomboEliminaCommissario,numeroComm));
			box.add(jm);

		}
		box.setPreferredSize(new Dimension(25, 50));
		return box;
	}

	public void confermaPresidenti() {
		for(Docente d:this.presidentiCorrenti.values())
			d.incrementaSelezionato();

	}

	public Box creaCheckBoxDisponibilita(Commissione cgm) {
		Box box=Box.createHorizontalBox();
		cgm.setSlotScelto(cgm.selezionaSlotIniziali());
		for(Integer i : cgm.getSlotScelto()){
			JCheckBox jc= new JCheckBox(i.toString(),true);
			jc.addItemListener(new ListenerItemCheckBoxDisponibilita(cgm));
			box.add(jc);
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
			b.add(new JLabel("numero massimo studenti : "+cgm.getMaxStudComm()));
			b.add(new JLabel("Commissari :"));
			for(int i =0;i<cgm.getNumeroCommissari();i++)
				b.add(new JLabel(cgm.getListacommissari().get(i).toString()));
			b.add(new JLabel("Laureandi :"));
			Map<Docente,Integer> rapprCompatta=new HashMap<>();
			for(Studente s:cgm.getLaureandi())
				if(rapprCompatta.containsKey(s.getRelatore()))
					rapprCompatta.put(s.getRelatore(), rapprCompatta.get(s.getRelatore())+1);
				else
					rapprCompatta.put(s.getRelatore(), 1);
			for(Docente d : rapprCompatta.keySet()){
				b.add(new JLabel(d.getNome() + " " + rapprCompatta.get(d)));
			}
		}
		b.add(new JLabel("Commissioni Triennali"));
		for(Commissione cgm : this.listaCommissioni.getCommTri()){
			b.add(new JLabel("commissione numero :" + cgm.getNumeroCommissione()));
			b.add(new JLabel("Slot scelto = " + cgm.getSlotScelto()));
			b.add(new JLabel("Presidente : " + cgm.getPresidente().getNome()));
			b.add(new JLabel("Commissari :"));
			b.add(new JLabel("Commissari :"));
			for(int i =0;i<cgm.getNumeroCommissari();i++)
				b.add(new JLabel(cgm.getListacommissari().get(i).toString()));
			b.add(new JLabel("numero massimo studenti : "+cgm.getMaxStudComm()));
			b.add(new JLabel("Laureandi :"));
			Map<Docente,Integer> rapprCompatta=new HashMap<>();
			for(Studente s:cgm.getLaureandi())
				if(rapprCompatta.containsKey(s.getRelatore()))
					rapprCompatta.put(s.getRelatore(), rapprCompatta.get(s.getRelatore())+1);
				else
					rapprCompatta.put(s.getRelatore(), 1);
			for(Docente d : rapprCompatta.keySet()){
				b.add(new JLabel(d.getNome() + " " + rapprCompatta.get(d)));
			}
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
		cgm.setListacommissari(this.listaSenzaDoppi(cgm.getListacommissari()));
		cgm.aggiornaDisponibilita(item.getDisponibilita());
		//		cgm.aggiornaCommissariPossibili(item.getDisponibilita());

	}

	public void modificaBoxDisponibilita(Box checkBoxDisponibilita, Commissione cgm) {
		checkBoxDisponibilita.removeAll();
		List<JCheckBox> checkBox = cgm.creaCheckBoxDisponibilita();
		for (JCheckBox jc : checkBox){
			checkBoxDisponibilita.add(jc);
		}
		checkBoxDisponibilita.revalidate();
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

	public void aggiungiLureandiInCommissione(Docente item, Commissione cgm, DefaultListModel modLaureandi,JComboBox<Integer> jc, DefaultListModel commissariModel,JComboBox<Docente> jcomboEliminaCommissario,int numeroComm) {



		String tipoCommissione=cgm.getTipoCommissione();


		if(tipoCommissione.equals("MAGISTRALE")){

			if(cgm.getListacommissari().size()>=numeroComm && !(cgm.getListacommissari().contains(item))){
				item.setEccesso(true);
			}
			if(!(cgm.getListacommissari().contains(item)) && item.getLaureandiMagistrali().size()!=0 && !(cgm.getPresidente().equals(item))){
				commissariModel.addElement(item);
				jcomboEliminaCommissario.addItem(item);
				jcomboEliminaCommissario.revalidate();
			}



			for(Studente s:item.getLaureandiMagistrali()){ // metodo in docente che mi seleziona il tipo di laureando
				if(modLaureandi.size()>=cgm.getMaxStudComm())
					s.setEccesso(true);
				if(!(cgm.getLaureandi().contains(s))){
					modLaureandi.addElement(s);
					cgm.getLaureandi().add(s);
					cgm.getListacommissari().add(item);

					jc.addItem(s.getNumero());
					jc.revalidate();
				}
			}
			//jc.revalidate();
		}
		else{

			if(cgm.getListacommissari().size()>=numeroComm && !(cgm.getListacommissari().contains(item))){
				item.setEccesso(true);
			}
			if(!(cgm.getListacommissari().contains(item)) && item.getLaureandiTriennali().size()!=0 && !(cgm.getPresidente().equals(item))){
				commissariModel.addElement(item);
				jcomboEliminaCommissario.addItem(item);
				jcomboEliminaCommissario.revalidate();

			}	
			for(Studente s:item.getLaureandiTriennali()){  // metodo in docente che mi seleziona il tipo di laureando
				if(modLaureandi.size()>=cgm.getMaxStudComm())
					s.setEccesso(true);
				if((cgm.getLaureandi().contains(s) && modLaureandi.contains(s))==false){
					modLaureandi.addElement(s);
					cgm.getLaureandi().add(s);
					cgm.getListacommissari().add(item);

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

	public void eliminaLaureandoDaCommissione(int numeroStudente,Commissione cgm, DefaultListModel modLaureandi, JComboBox<Integer> jc) {   //<---------------------------------------------------------------------
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

	public void settaEccessoDocenti(){
		for(Docente d:this.getDocenti().getDocenti())
			d.setEccesso(false);
	}


	public ListaDocenti getDocenti() {
		return docenti;
	}

	public void setDocenti(ListaDocenti docenti) {
		this.docenti = docenti;
	}


	public List<JLabel> getLabelGiorni() {
		List<JLabel> giorni = new ArrayList<>();
		int i =1;
		for(String s : this.giorni){
			if(!s.toUpperCase().contains("GIORNI")){
				giorni.add(new JLabel("lo slot numero " + i + " corrisponde al giorno :  " + s));
				i++;}
		}
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


	/*   Elenco dei laureandi: non lo date nel dettaglio, ma soltanto "3 di
    Atzeni, 2 di Pascucci, ecc. specificando anche in qualche modo se
    hanno correlatore tra i possibili commissari. Ad esempio:
    3 di Atzeni (correlatori: Cabibbo, Weitschek)    "  */


	// restituisce la lista di docenti che sono correlatori di qualche laureando di nomeDocente

	public List<String> cercaEventualiCorrelatoriLaureandiDocente(String nomeDocente,List<Studente> laureandi,List<Docente> commissari){
		List<String> correlatori=new ArrayList<>();
		Map<String,Integer> corr=new HashMap<>(); // uso la mappa cos� non vengono doppi

		for(Studente s:laureandi){
			if(s.getRelatore().getNome().equals(nomeDocente)){
				if(commissari.contains(s.getCorrelatore()))
					corr.put(s.getCorrelatore().getNome(), 0);

			}
		}

		for (Map.Entry<String,Integer> entry : corr.entrySet()){
			correlatori.add(entry.getKey());
		}


		return correlatori;

	}

	public void eliminaCommissarioDaCommissione(Docente item, Commissione cgm, DefaultListModel modLaureandi,
			JComboBox<Docente> jcomboEliminaCommissario, DefaultListModel commissariModel,int numeroComm) {
		
		List<Studente> listaLaurendi=new ArrayList<>();

		if(cgm.getTipoCommissione().equals("MAGISTRALE")){
			listaLaurendi=item.getLaureandiMagistrali();
		}

		else{
			listaLaurendi=item.getLaureandiTriennali();
		}


		cgm.getLaureandi().removeAll(listaLaurendi);
		modLaureandi.removeAllElements();
		commissariModel.removeAllElements();

		//---------------------------- reinizializzo modlaureandi ----------------------------------------------



		for(Studente s: cgm.getLaureandi())
			s.setEccesso(false);


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






		//---------------------------------------------------------------------------------------------------------------







		cgm.getListacommissari().remove(item);
		for(Docente d: cgm.getListacommissari())
			d.setEccesso(false);

		if(cgm.getListacommissari().size()>numeroComm){ // non viene mai verificataa
			this.settaApartireDa(numeroComm,cgm.getListacommissari());

			for(Docente d:cgm.getListacommissari()){

				commissariModel.addElement(d);

			}


			//		int cont=1;
			//		for(Docente d:cgm.getListacommissari()){
			//			
			//			if(cont>cgm.getNumeroCommissari())
			//				d.setEccesso(true);
			//			commissariModel.addElement(d);
			//			
			//			
			//			cont++;
			//			
			//			}


		}

		else{
			for(Docente d:cgm.getListacommissari())
				commissariModel.addElement(d);
		}





	}


	public void settaApartireDa(int posizione,List<Docente> listDoc){
		int i;
		for(i=0;i<listDoc.size();i++){
			if(i>=posizione)
				listDoc.get(i).setEccesso(true);
		}






	}




}