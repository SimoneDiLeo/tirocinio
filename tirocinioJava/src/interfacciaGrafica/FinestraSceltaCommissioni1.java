package interfacciaGrafica;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import classi.Commissione;
import classi.Docente;
import classi.ListaDocenti;
import classi.Studente;
import controller.Controller;
import interfacciaGrafica.listenerBottoni.ListenerConfermaSceltaCommissioni;
import interfacciaGrafica.listenerBottoni.ListenerModificaSceltaCommissioni;
import interfacciaGrafica.listenerBottoni.ListenerTornaIndietro;
import interfacciaGrafica.renderer.RendererLaureandi;


public class FinestraSceltaCommissioni1 {
	private JFrame f =new JFrame("scelta commissione");
	private JPanel p = new JPanel();

	//costruttore
	public FinestraSceltaCommissioni1(Controller controller, int numeroMagistrali , int numeroTriennali){
		this.f.setName("scelta commissione");
		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800,1100);
		f.setLocation(400, 0);
		JScrollPane jScrollPane = new JScrollPane(this.p);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.f.getContentPane().add(jScrollPane);
		Box boxOrizzontale =Box.createHorizontalBox();
		Box boxDestra = Box.createVerticalBox();


		boxOrizzontale.add(boxDestra);

		controller.setListaCommissioni(numeroMagistrali, numeroTriennali);
		controller.inizializzaCommissioniMagistrali(numeroMagistrali);
		controller.inizializzaCommissioniTriennali(numeroTriennali);


		int numeroCommissariMagistrali=Integer.parseInt(controller.getProprieta().getProperty("COMMISSARI_MAGISTRALI"));
		int numeroCommissariTriennali=Integer.parseInt(controller.getProprieta().getProperty("COMMISSARI_TRIENNALI"));



		//-----------------------------------------------------------------COMMISSIONI MAGISTRALI--------------------------------------------------------------


		List<Docente> commissariMagistrali=controller.getDocentiMagistraliOrdinati();
		ListaDocenti commMag=new ListaDocenti(commissariMagistrali);



		List<Docente> commissariTriennali=controller.getDocentiTriennaliOrdinati();
		ListaDocenti commTri=new ListaDocenti(commissariTriennali);

		List<Docente> unioncommMagcommTri=new ArrayList<>();
		unioncommMagcommTri.addAll(commissariMagistrali);
		unioncommMagcommTri.addAll(commissariTriennali);
		List<Docente> union=controller.listaSenzaDoppi(unioncommMagcommTri);
		
		




		boxDestra.add(new JLabel("-----------------------------------------COMMISSIONI MAGISTRALI--------------------------------------------------------"));

		int indexCommissioneMag=1;
		List<Studente> studentiScartatiMagitrali=new ArrayList<>();
		List<Studente> studentiRimastiPresidenti=new ArrayList<>();

		for(Commissione cgm:controller.getListaCommissioni().getCommMag()){
			boxDestra.add(new JLabel("-------------COMMISSIONE NUMERO "+indexCommissioneMag+" -----------------------"));
			if(cgm!=null){	

				boxDestra.add(new JLabel("Presidente: "+cgm.getPresidente().getNome()+"  max Laureandi: "+cgm.getMaxStudComm()));

			}
			
			//laurendi rimasti presidente
			//			controller.aggiornaCommissione(cgm);
			//			for(List<Docente> list:cgm.getCommissari()){
			//				boxDestra.add(new JLabel("----------------------"));
			//				for(Docente d:list){
			//					boxDestra.add(new JLabel(d.getNome()));
			//				}
			//			}

			studentiRimastiPresidenti.addAll(cgm.getPresidente().getLaureandiTriennali());


			DefaultListModel modLaureandi= new DefaultListModel<>();


			// coloro quelli in eccesso
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



			boxDestra.add(new JLabel("numero commissari: "+numeroCommissariMagistrali));
			commMag.rimuoviDocente(commMag.trovaDocenteDaNome(cgm.getPresidente().getNome()));

			boxDestra.add(new JLabel("Membri della commissione"));
			boxDestra.add(new JLabel("-------------------------------"));
			boxDestra.add(new JLabel("Laureandi: "));
			//boxDestra.add(new JLabel(cgm.getPresidente().getNumeroLaureandiMagistrali()+ " di "+cgm.getPresidente().getNome()));
			
			
			
			List<Studente> laureandiMagistraliDaInserire=new ArrayList<>();
			List<Docente> docentiDaRimuovere=new ArrayList<>();

			int contMagInseriti=0;
			for(int i=0;i<commMag.getDocenti().size();i++){
				Docente d=commMag.getDocenti().get(i);

				if(controller.intersection(d.getDisponibilita(), cgm.getSlotDisponibilita()).size()!=0){
					if(modLaureandi.size()+d.getNumeroLaureandiMagistrali()<=cgm.getMaxStudComm()){
						if(contMagInseriti<numeroCommissariMagistrali){
							if(union.contains(d)){
								if(d.getNumeroLaureandiTriennali()!=0){
									studentiScartatiMagitrali.addAll(d.getLaureandiTriennali());
									//boxDestra.add(new JLabel(d.getNumeroLaureandiMagistrali()+ " di "+d.getNome()));
									laureandiMagistraliDaInserire.addAll(d.getLaureandiMagistrali());
									docentiDaRimuovere.add(d);
									cgm.aggiungiLaurendi(d.getLaureandiMagistrali());
									cgm.getListacommissari().add(d);
									union.remove(d);
									contMagInseriti++;



								}
								else{
									//boxDestra.add(new JLabel(d.getNumeroLaureandiMagistrali()+ " di "+d.getNome()));
									laureandiMagistraliDaInserire.addAll(d.getLaureandiMagistrali());
									docentiDaRimuovere.add(d);
									cgm.aggiungiLaurendi(d.getLaureandiMagistrali());
									cgm.getListacommissari().add(d);
									union.remove(d);
									contMagInseriti++;

								}
							}
						}
					}

				}



			}
			
			
			boxDestra.add(new JLabel("listaCommissari: "+cgm.getListacommissari().size()));
			boxDestra.add(new JLabel("listaLaurenadi: "+cgm.getLaureandi().size()));
			
			
			if(controller.cercaEventualiCorrelatoriLaureandiDocente(cgm.getPresidente().getNome(), cgm.getLaureandi(), cgm.getListacommissari()).size()!=0){
				boxDestra.add(new JLabel(cgm.getPresidente().getNumeroLaureandiMagistrali()+ " di "+cgm.getPresidente().getNome()+ " (correlatori: "+ controller.cercaEventualiCorrelatoriLaureandiDocente(cgm.getPresidente().getNome(), cgm.getLaureandi(), cgm.getListacommissari()).toString()+" )"));
			
			}
			else boxDestra.add(new JLabel(cgm.getPresidente().getNumeroLaureandiMagistrali()+ " di "+cgm.getPresidente().getNome()));
			
			for(Docente d:cgm.getListacommissari()){
				if(controller.cercaEventualiCorrelatoriLaureandiDocente(d.getNome(), cgm.getLaureandi(), cgm.getListacommissari()).size()!=0)
					boxDestra.add(new JLabel(d.getNumeroLaureandiMagistrali()+ " di "+d.getNome()+ " (correlatori: "+ controller.cercaEventualiCorrelatoriLaureandiDocente(d.getNome(), cgm.getLaureandi(), cgm.getListacommissari()).toString()+" )"));
				else
					boxDestra.add(new JLabel(d.getNumeroLaureandiMagistrali()+ " di "+d.getNome()));
			}
			
			
			Box radioBoxDisponibilita = controller.creaCheckBoxDisponibilita(cgm);
			boxDestra.add(radioBoxDisponibilita);
			
			
			
			// dopo che finisce il for sopra
			

			for(Studente s:laureandiMagistraliDaInserire)
				modLaureandi.addElement(s);

			for(Docente d:docentiDaRimuovere)
				commMag.rimuoviDocente(d);


			JList listaLaureandi= new JList(modLaureandi);
			RendererLaureandi rendererLaureandi=new RendererLaureandi(listaLaureandi);
			listaLaureandi.setCellRenderer(rendererLaureandi);

			boxDestra.add(new JLabel("--------------------"));
			//boxDestra.add(listaLaureandi);





			boxDestra.add(new JLabel(" "));



		//	boxDestra.add(new JLabel("-------------------------------FINE COMMISSIONE NUMERO "+indexCommissioneMag+" ---------------------------------"));
			indexCommissioneMag++;
		}
		boxDestra.add(new JLabel("lista studenti magistrali scartati (sono studenti di triennale che hanno relatore che ha laureandi sia triennali che magistrali)"));

		controller.aggiungiStudentiScartatiMagistrale(studentiScartatiMagitrali);
		controller.aggiungiStudentiScartatiMagistrale(studentiRimastiPresidenti);
		
		DefaultListModel studentiScartatiMag= new DefaultListModel<>();
		for(Studente s:studentiScartatiMagitrali)
			studentiScartatiMag.addElement(s);

		for(Studente s:studentiRimastiPresidenti)
			studentiScartatiMag.addElement(s);

		JList listaScarti= new JList(studentiScartatiMag);
		RendererLaureandi rendererScarti=new RendererLaureandi(listaScarti);
		listaScarti.setCellRenderer(rendererScarti);

		boxDestra.add(listaScarti);

	//	boxDestra.add(new JLabel("------------------------FINE COMMISSIONI MAGISTRALI----------------------------------------------------"));

		//---------------------------------------------------- FINE COMMISSIONI MAGISTRALI--------------------------------------------------------------------

		
		
		//-------------------------------------------------------------- COMMISSIONI TRIENNALI ------------------------------------------------------------







		boxDestra.add(new JLabel("-----------------------------------------COMMISSIONI TRIENNALI--------------------------------------------------------"));

		int indexCommissioneTri=1;
		List<Studente> studentiScartatiTriennali=new ArrayList<>();
		List<Studente> studentiRimastiPresidentiTriennali=new ArrayList<>();

		for(Commissione cgm:controller.getListaCommissioni().getCommTri()){
			boxDestra.add(new JLabel("-------------COMMISSIONE NUMERO "+indexCommissioneTri+" -----------------------"));
			if(cgm!=null){	

				boxDestra.add(new JLabel("Presidente: "+cgm.getPresidente().getNome()+"  max Laureandi: "+cgm.getMaxStudComm()));

			}
			//laurendi rimasti presidente
			controller.aggiornaCommissione(cgm);
			studentiRimastiPresidentiTriennali.addAll(cgm.getPresidente().getLaureandiMagistrali());


			DefaultListModel modLaureandi= new DefaultListModel<>();


			// coloro quelli in eccesso
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


			boxDestra.add(new JLabel("numero commissari: "+numeroCommissariTriennali));
			commTri.rimuoviDocente(commTri.trovaDocenteDaNome(cgm.getPresidente().getNome()));

			boxDestra.add(new JLabel("Membri della commissione"));
			
			boxDestra.add(new JLabel("----------------------------"));
			boxDestra.add(new JLabel("Laureandi: "));
			
			
			
			
			
			List<Studente> laureandiTriennaliDaInserire=new ArrayList<>();
			List<Docente> docentiDaRimuovereTriennali=new ArrayList<>();

			int contTriInseriti=0;
			for(int i=0;i<commTri.getDocenti().size();i++){
				Docente d=commTri.getDocenti().get(i);

				if(controller.intersection(d.getDisponibilita(), cgm.getSlotDisponibilita()).size()!=0){
					if(modLaureandi.size()+d.getNumeroLaureandiTriennali()<=cgm.getMaxStudComm()){
						if(contTriInseriti<numeroCommissariTriennali){
							if(union.contains(d)){
								if(d.getNumeroLaureandiMagistrali()!=0){
									studentiScartatiTriennali.addAll(d.getLaureandiMagistrali());
									//boxDestra.add(new JLabel(d.getNumeroLaureandiTriennali()+ " di "+d.getNome()));
									laureandiTriennaliDaInserire.addAll(d.getLaureandiTriennali());
									docentiDaRimuovereTriennali.add(d);
									cgm.aggiungiLaurendi(d.getLaureandiTriennali());
									cgm.getListacommissari().add(d);
									union.remove(d);
									contTriInseriti++;

								}
								else{
									//boxDestra.add(new JLabel(d.getNumeroLaureandiTriennali()+ " di "+d.getNome()));
									laureandiTriennaliDaInserire.addAll(d.getLaureandiTriennali());
									docentiDaRimuovereTriennali.add(d);
									cgm.aggiungiLaurendi(d.getLaureandiTriennali());
									
									cgm.getListacommissari().add(d);
									union.remove(d);
									contTriInseriti++;

								}
							}
						}
					}

				}

			}
			
			

			
			boxDestra.add(new JLabel("size listaCommissari: "+cgm.getListacommissari().size()));
			boxDestra.add(new JLabel("size listaLaurenadi: "+cgm.getLaureandi().size()));
			
			if(controller.cercaEventualiCorrelatoriLaureandiDocente(cgm.getPresidente().getNome(), cgm.getLaureandi(), cgm.getListacommissari()).size()!=0){
				boxDestra.add(new JLabel(cgm.getPresidente().getNumeroLaureandiTriennali()+ " di "+cgm.getPresidente().getNome()+ " (correlatori: "+ controller.cercaEventualiCorrelatoriLaureandiDocente(cgm.getPresidente().getNome(), cgm.getLaureandi(), cgm.getListacommissari()).toString()+" )"));
			}
			
			else boxDestra.add(new JLabel(cgm.getPresidente().getNumeroLaureandiTriennali()+ " di "+cgm.getPresidente().getNome()));
					
				
			for(Docente d:cgm.getListacommissari()){
				if(controller.cercaEventualiCorrelatoriLaureandiDocente(d.getNome(), cgm.getLaureandi(), cgm.getListacommissari()).size()!=0)
					boxDestra.add(new JLabel(d.getNumeroLaureandiTriennali()+ " di "+d.getNome()+ " (correlatori: "+ controller.cercaEventualiCorrelatoriLaureandiDocente(d.getNome(), cgm.getLaureandi(), cgm.getListacommissari()).toString()+" )"));
				else
					boxDestra.add(new JLabel(d.getNumeroLaureandiTriennali()+ " di "+d.getNome()));
			}
			
			//boxDestra.add(new JLabel("relatore del primo laueando: "+cgm.getLaureandi().get(0).toString()));


			Box radioBoxDisponibilita = controller.creaCheckBoxDisponibilita(cgm);
			boxDestra.add(radioBoxDisponibilita);



			// dopo che finisce il for sopra

			for(Studente s:laureandiTriennaliDaInserire)
				modLaureandi.addElement(s);

			for(Docente d:docentiDaRimuovereTriennali)
				commTri.rimuoviDocente(d);


			JList listaLaureandi= new JList(modLaureandi);
			RendererLaureandi rendererLaureandi=new RendererLaureandi(listaLaureandi);
			listaLaureandi.setCellRenderer(rendererLaureandi);

			boxDestra.add(new JLabel("--------------------"));
			//boxDestra.add(listaLaureandi);





			boxDestra.add(new JLabel(" "));



		//	boxDestra.add(new JLabel("-------------------------------FINE COMMISSIONE NUMERO "+indexCommissioneTri+" ---------------------------------"));
			indexCommissioneTri++;
		}
		boxDestra.add(new JLabel("lista studenti triennali scartati (sono studenti di magistrale che hanno relatore che ha laureandi sia triennali che magistrali)"));


		controller.aggiungiStudentiScartatiTriennale(studentiScartatiTriennali);
		controller.aggiungiStudentiScartatiTriennale(studentiRimastiPresidentiTriennali);

		DefaultListModel studentiScartatiTri= new DefaultListModel<>();
		for(Studente s:studentiScartatiTriennali)
			studentiScartatiTri.addElement(s);

		for(Studente s:studentiRimastiPresidentiTriennali)
			studentiScartatiTri.addElement(s);

		JList listaScar= new JList(studentiScartatiTri);
		RendererLaureandi rendererScartiTri=new RendererLaureandi(listaScar);
		listaScar.setCellRenderer(rendererScartiTri);

		boxDestra.add(listaScar);

	//	boxDestra.add(new JLabel("------------------------FINE COMMISSIONI TRIENNALI----------------------------------------------------"));


		//--------------------------------------------------------------------------------------		

		JButton modifica=new JButton("modifica scelte");
		modifica.addActionListener(new ListenerModificaSceltaCommissioni(this.f,controller,numeroCommissariMagistrali,numeroCommissariTriennali));;
		boxDestra.add(modifica);
		
		
		
		JButton conferma=new JButton("conferma scelte");
		conferma.addActionListener(new ListenerConfermaSceltaCommissioni(this.f,controller));;
		boxDestra.add(conferma);
		
		

		JButton ti=new JButton("Torna Indietro");
		ti.addActionListener(new ListenerTornaIndietro(f, controller));;
		boxDestra.add(ti);

		this.p.add(boxOrizzontale);

		this.f.setVisible(true);
	}
}

