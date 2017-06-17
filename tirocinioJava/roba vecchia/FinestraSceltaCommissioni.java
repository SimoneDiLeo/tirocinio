//package interfacciaGrafica;
//
//import javax.swing.Box;
//import javax.swing.DefaultListModel;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JList;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//
//import classi.Commissione;
//import classi.Studente;
//import controller.Controller;
//import interfacciaGrafica.listenerBottoni.ListenerConfermaSceltaCommissioni;
//import interfacciaGrafica.listenerBottoni.ListenerItemSelezionaNumeroStudente;
//import interfacciaGrafica.listenerBottoni.ListenerTornaIndietro;
//import interfacciaGrafica.renderer.RendererLaureandi;
//
//
//public class FinestraSceltaCommissioni {
//	private JFrame f =new JFrame("scelta commissione");
//	private JPanel p = new JPanel();
//
//	//costruttore
//	public FinestraSceltaCommissioni(Controller controller, int numeroMagistrali , int numeroTriennali){
//		this.f.setName("scelta commissione");
//		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.f.setSize(900,900);
//		this.f.setLocation(500, 400);
//		JScrollPane jScrollPane = new JScrollPane(this.p);
//		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		this.f.getContentPane().add(jScrollPane);
//		Box boxOrizzontale =Box.createHorizontalBox();
//		Box boxDestra = Box.createVerticalBox();
//		Box boxSinistra = Box.createVerticalBox();
//		boxOrizzontale.add(boxSinistra);
//		boxOrizzontale.add(boxDestra);
//		for(JLabel l : controller.getLabelGiorni()){
//			boxSinistra.add(l);
//		}
//		boxDestra.add(new JLabel("Commissioni Magistrali"));
//		//inizializzazione delle commissioni(con solo i presidenti) 
//		controller.setListaCommissioni(numeroMagistrali, numeroTriennali);
//		controller.inizializzaCommissioniMagistrali(numeroMagistrali);
//		controller.inizializzaCommissioniTriennali(numeroTriennali);
//
//		for(Commissione cgm:controller.getListaCommissioni().getCommMag()){
//			if(cgm!=null){	
//
//				boxDestra.add(new JLabel("Presidente: "+cgm.getPresidente().getNome()+"  max Laureandi: "+cgm.getMaxStudComm()));
//
//			}
//			DefaultListModel modLaureandi= new DefaultListModel<>();
//			JComboBox<Integer> jm = new JComboBox<>();
//			controller.aggiornaCommissione(cgm);
//			// coloro quelli in eccesso
//			if(cgm.getLaureandi().size()>cgm.getMaxStudComm()){
//
//				int cont=1;
//				for(Studente s:cgm.getLaureandi()){
//					if(cont>cgm.getMaxStudComm())
//						s.setEccesso(true);
//					modLaureandi.addElement(s);
//					jm.addItem(s.getNumero());
//					cont++;
//
//				}
//			}
//			else{
//				for(Studente s:cgm.getLaureandi()){
//
//					modLaureandi.addElement(s);
//					jm.addItem(s.getNumero());
//				}
//
//			}
//
//			//-----------------------------------
//
//			jm.setSelectedIndex(-1);
//			jm.addItemListener(new ListenerItemSelezionaNumeroStudente(controller,cgm,modLaureandi,jm));
//			JList listaLaureandi= new JList(modLaureandi);
//			RendererLaureandi rendererLaureandi=new RendererLaureandi(listaLaureandi);
//			listaLaureandi.setCellRenderer(rendererLaureandi);
//			boxDestra.add(new JLabel("Seleziona numero laureando da eliminare "));
//			boxDestra.add(jm);
//			boxDestra.add(listaLaureandi);
//
//
//			Box radioBoxDisponibilita = controller.creaRadioBoxDisponibilita(cgm);
//			boxDestra.add(controller.creaJComboCommissari(cgm,radioBoxDisponibilita,modLaureandi,jm));
//			boxDestra.add(new JLabel(" "));
//
//			boxDestra.add(radioBoxDisponibilita);
//		}
//
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
//		JButton conferma=new JButton("conferma scelte");
//		conferma.addActionListener(new ListenerConfermaSceltaCommissioni(this.f,controller));;
//		boxDestra.add(conferma);
//
//		JButton ti=new JButton("Torna Indietro");
//		ti.addActionListener(new ListenerTornaIndietro(f, controller));;
//		boxDestra.add(ti);
//
//		this.p.add(boxOrizzontale);
//
//		this.f.setVisible(true);
//	}
//}
