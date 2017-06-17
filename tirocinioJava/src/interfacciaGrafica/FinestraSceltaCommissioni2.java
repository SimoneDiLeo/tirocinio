package interfacciaGrafica;


import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import classi.Commissione;
import classi.Docente;
import classi.Studente;
import controller.Controller;
import interfacciaGrafica.listenerBottoni.ListenerConfermaSceltaCommissioni;
import interfacciaGrafica.listenerBottoni.ListenerItemSelezionaNumeroStudente;
import interfacciaGrafica.listenerBottoni.ListenerSelezionaCommissarioDaEliminare;
import interfacciaGrafica.listenerBottoni.ListenerTornaIndietroElencoCommissioni;
import interfacciaGrafica.renderer.RendererCommissari;
import interfacciaGrafica.renderer.RendererLaureandi;


public class FinestraSceltaCommissioni2 {
	private JFrame f =new JFrame("modifica scelte");
	private JPanel p = new JPanel();

	//costruttore
	public FinestraSceltaCommissioni2(Controller controller, int numeroMagistrali , int numeroTriennali){
		this.f.setName("scelta commissione");
		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800,1100);
		f.setLocation(400, 0);
		JScrollPane jScrollPane = new JScrollPane(this.p);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.f.getContentPane().add(jScrollPane);
//		Box boxOrizzontale =Box.createHorizontalBox();
		Box boxDestra = Box.createVerticalBox();
		Box boxSinistra = Box.createVerticalBox();
//		boxOrizzontale.add(boxSinistra);
		for(JLabel l : controller.getLabelGiorni()){
			boxSinistra.add(l);
		}
		
//----------------------------------------------------------------- MAGISTRALE -----------------------------------------------------------------------------
		
		boxDestra.add(new JLabel("Commissioni Magistrali"));
		
		int indexCommissioneMag=1;
		for(Commissione cgm:controller.getListaCommissioni().getCommMag()){
			if(cgm!=null){	

				boxDestra.add(new JLabel("Presidente: "+cgm.getPresidente().getNome()+"  max Laureandi: "+cgm.getMaxStudComm()));

			}
		
			
			
			
			
			boxDestra.add(new JLabel("COMMISSIONE MAGISTRALE NUMERO "+indexCommissioneMag));
			boxDestra.add(new JLabel("MAX COMMISSARI "+cgm.getNumeroCommissari()));
			
			
			
			//------------------------------ commissari ------------------------------------------
			DefaultListModel commissariModel= new DefaultListModel<>();
			for(Docente d:cgm.getListacommissari())
				commissariModel.addElement(d);
			
			JList commissariJList= new JList(commissariModel);
			
			
			
			//------------------------------ commissari ------------------------------------------
			
			
			DefaultListModel modLaureandi= new DefaultListModel<>();
			JComboBox<Integer> jm = new JComboBox<>();
			controller.aggiornaCommissione(cgm);
			// coloro quelli in eccesso
			if(cgm.getLaureandi().size()>cgm.getMaxStudComm()){

				int cont=1;
				for(Studente s:cgm.getLaureandi()){
					if(cont>cgm.getMaxStudComm())
						s.setEccesso(true);
					modLaureandi.addElement(s);
					jm.addItem(s.getNumero());
					cont++;

				}
			}
			else{
				for(Studente s:cgm.getLaureandi()){

					modLaureandi.addElement(s);
					jm.addItem(s.getNumero());
				}

			}

			//-----------------------------------
			
			
			
			JComboBox<Docente> jcomboEliminaCommissario = new JComboBox<>();
			for(Docente d:cgm.getListacommissari())
				jcomboEliminaCommissario.addItem(d);
			
			
			

			jm.setSelectedIndex(-1);
			jm.addItemListener(new ListenerItemSelezionaNumeroStudente(controller,cgm,modLaureandi,jm));
			JList listaLaureandi= new JList(modLaureandi);
			RendererLaureandi rendererLaureandi=new RendererLaureandi(listaLaureandi);
			listaLaureandi.setCellRenderer(rendererLaureandi);
			
			RendererCommissari rendererCommissari=new RendererCommissari(commissariJList);
			commissariJList.setCellRenderer(rendererCommissari);
			
			jcomboEliminaCommissario.setSelectedIndex(-1);
			jcomboEliminaCommissario.addItemListener(new ListenerSelezionaCommissarioDaEliminare(controller,cgm,modLaureandi,jcomboEliminaCommissario,commissariModel,cgm.getNumeroCommissari()));
			
			
			
			jcomboEliminaCommissario.addItemListener(new ListenerSelezionaCommissarioDaEliminare(controller,cgm,modLaureandi,jcomboEliminaCommissario,commissariModel,cgm.getNumeroCommissari()));
			boxDestra.add(new JLabel("-----------------------------------------------------------------------"));
			boxDestra.add(new JLabel("Seleziona commissario da eliminare: "));
			boxDestra.add(jcomboEliminaCommissario);
			
			boxDestra.add(commissariJList);
			boxDestra.add(new JLabel("Seleziona numero laureando da eliminare "));
			boxDestra.add(jm);
			boxDestra.add(listaLaureandi);
			boxDestra.add(new JLabel("-----------------------------------------------------------------------"));
			boxDestra.add(new JLabel("Seleziona un commissario(di deafult commissario dato dall'algorimo)"));
			boxDestra.add(new JLabel("-----------------------------------------------------------------------"));
			Box radioBoxDisponibilita = controller.creaCheckBoxDisponibilita(cgm);
			boxDestra.add(controller.creaJComboCommissari(cgm,radioBoxDisponibilita,modLaureandi,jm,commissariModel,jcomboEliminaCommissario,cgm.getNumeroCommissari()));
			boxDestra.add(new JLabel("-----------------------------------------------------------------------"));
			boxDestra.add(new JLabel("Slot disponibilita"));
			boxDestra.add(new JLabel("-----------------------------------------------------------------------"));

			boxDestra.add(radioBoxDisponibilita);
			
			
			indexCommissioneMag++;
			boxDestra.add(new JLabel("-----------------------------------------------------------------------"));
		}
		
		JComboBox<Studente> jcomboScartiTriennali = new JComboBox<>();
		for(Studente s:controller.getStudentiScartatiTriennale())
			jcomboScartiTriennali.addItem(s);
		
		jcomboScartiTriennali.setSelectedIndex(-1);
		boxDestra.add(new JLabel("elenco laureandi esclusi Magistrale"));
		boxDestra.add(jcomboScartiTriennali);
		boxDestra.add(new JLabel("-----------------------------------------------------------------------"));
//		DefaultListModel scartiTriennali= new DefaultListModel<>();
//		for(Studente s:controller.getStudentiScartatiTriennale())
//			scartiTriennali.addElement(s);
//		JList scartiTri =new JList(scartiTriennali);
//		RendererLaureandi
		
		//--------------------------------------------------------------------------
		
		//----------------------------------------------------------------- MAGISTRALE -----------------------------------------------------------------------------	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//----------------------------------------------------------------- TRIENNALE -----------------------------------------------------------------------------
		boxDestra.add(new JLabel("-----------------------------------------------------------------------"));
		boxDestra.add(new JLabel("Commissioni Triennali"));
		
		int indexCommissioneTri=1;
		for(Commissione cgm:controller.getListaCommissioni().getCommTri()){
			if(cgm!=null){	

				boxDestra.add(new JLabel("Presidente: "+cgm.getPresidente().getNome()+"  max Laureandi: "+cgm.getMaxStudComm()));

			}
			
			boxDestra.add(new JLabel("COMMISSIONE TRIENNALE NUMERO "+indexCommissioneTri));
			boxDestra.add(new JLabel("MAX COMMISSARI "+cgm.getNumeroCommissari()));
			

			//------------------------------ commissari ------------------------------------------
			DefaultListModel commissariModel= new DefaultListModel<>();
			for(Docente d:cgm.getListacommissari())
				commissariModel.addElement(d);
			
			JList commissariJList= new JList(commissariModel);
			
			
			
			//------------------------------ commissari ------------------------------------------
			
			
			
			DefaultListModel modLaureandi= new DefaultListModel<>();
			JComboBox<Integer> jm = new JComboBox<>();
			controller.aggiornaCommissione(cgm);
			// coloro quelli in eccesso
			if(cgm.getLaureandi().size()>cgm.getMaxStudComm()){

				int cont=1;
				for(Studente s:cgm.getLaureandi()){
					if(cont>cgm.getMaxStudComm())
						s.setEccesso(true);
					modLaureandi.addElement(s);
					jm.addItem(s.getNumero());
					cont++;

				}
			}
			else{
				for(Studente s:cgm.getLaureandi()){

					modLaureandi.addElement(s);
					jm.addItem(s.getNumero());
				}

			}

			//-----------------------------------
			
			

			JComboBox<Docente> jcomboEliminaCommissario = new JComboBox<>();
			for(Docente d:cgm.getListacommissari())
				jcomboEliminaCommissario.addItem(d);
			

			jm.setSelectedIndex(-1);
			jm.addItemListener(new ListenerItemSelezionaNumeroStudente(controller,cgm,modLaureandi,jm));
			JList listaLaureandi= new JList(modLaureandi);
			RendererLaureandi rendererLaureandi=new RendererLaureandi(listaLaureandi);
			listaLaureandi.setCellRenderer(rendererLaureandi);
			
			RendererCommissari rendererCommissari=new RendererCommissari(commissariJList);
			commissariJList.setCellRenderer(rendererCommissari);
			
			
			boxDestra.add(new JLabel("Seleziona commissario da eliminare: "));
			jcomboEliminaCommissario.setSelectedIndex(-1);
			boxDestra.add(jcomboEliminaCommissario);
			
			boxDestra.add(commissariJList);
			
			boxDestra.add(new JLabel("-----------------------------------------------------------------------"));
			boxDestra.add(new JLabel("Seleziona numero laureando da eliminare "));
			boxDestra.add(jm);
			boxDestra.add(listaLaureandi);
			boxDestra.add(new JLabel("-----------------------------------------------------------------------"));
			boxDestra.add(new JLabel("Selezionare i commissari(di default ci sono i commissari messi dall'algoritmo)"));
			boxDestra.add(new JLabel("-----------------------------------------------------------------------"));
			Box radioBoxDisponibilita = controller.creaCheckBoxDisponibilita(cgm);
			boxDestra.add(controller.creaJComboCommissari(cgm,radioBoxDisponibilita,modLaureandi,jm,commissariModel,jcomboEliminaCommissario,cgm.getNumeroCommissari()));
			boxDestra.add(new JLabel("-----------------------------------------------------------------------"));
			boxDestra.add(new JLabel("Slot disponibilita"));
			boxDestra.add(radioBoxDisponibilita);
			boxDestra.add(new JLabel("-----------------------------------------------------------------------"));
			indexCommissioneTri++;
		}
		
		
		
		
		JComboBox<Studente> jcomboScartiMagistrali = new JComboBox<>();
		for(Studente s:controller.getStudentiScartatiMagistrale())
			jcomboScartiMagistrali.addItem(s);
		
		jcomboScartiMagistrali.setSelectedIndex(-1);
		boxDestra.add(new JLabel("elenco laureandi esclusi Triennale"));
		boxDestra.add(jcomboScartiMagistrali);
		
		
		
	//-----------------------------------------------------------------------------------	
		
//		boxDestra.add(new JLabel("Commissioni Triennali"));
//		for(Commissione cgm:controller.getListaCommissioni().getCommTri()){
//			if(cgm!=null){
//				boxDestra.add(new JLabel("Presidente: "+cgm.getPresidente().getNome()+"  max Laureandi: "+cgm.getMaxStudComm()));
//
//				DefaultListModel modLaureandi= new DefaultListModel<>();
//				for(Studente s:cgm.getLaureandi())
//					modLaureandi.addElement(s);
//				JList listaLaureandi= new JList(modLaureandi);
//				boxDestra.add(listaLaureandi);
//
//			}
//		}
		
		
		//----------------------------------------------------------------- TRIENNALE -----------------------------------------------------------------------------
		
		
		JButton conferma=new JButton("conferma scelte");
		conferma.addActionListener(new ListenerConfermaSceltaCommissioni(this.f,controller));;
		boxDestra.add(conferma);

		JButton ti=new JButton("Torna Indietro");
		ti.addActionListener(new ListenerTornaIndietroElencoCommissioni(f, controller));;
		boxDestra.add(ti);

		this.p.add(boxDestra);

		this.f.setVisible(true);
	}
}

