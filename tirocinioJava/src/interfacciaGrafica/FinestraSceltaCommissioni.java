package interfacciaGrafica;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultFocusManager;
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
import classi.ListaCommissioni;
import classi.ListaDocenti;
import classi.Studente;
import controller.Controller;
import interfacciaGrafica.listenerBottoni.ListenerConfermaSceltaCommissioni;
import interfacciaGrafica.listenerBottoni.ListenerItemSelezionaCommissario;
import interfacciaGrafica.listenerBottoni.ListenerItemSelezionaNumeroStudente;
import interfacciaGrafica.listenerBottoni.ListenerTornaIndietro;
import interfacciaGrafica.listenerBottoni.InterazioneDisponibilita;
import interfacciaGrafica.listenerBottoni.SelezionaDocenteVecchio;
import interfacciaGrafica.renderer.RendererLaureandi;
//Parte di lavoro per Pier
//colorazione degli studenti se sopra il limite
//gestire gli studenti con una JList per aggiungerli nelle commissioni
//jList degli studenti di ogni commissione puo essere modificabile (rimozione studente)
//se rimuovo studente il colore che devo assegnarli dipendera dalla commissione dove lo inseriro
//parte di lavoro mia finita(per ora) 19/5/17
//TODO(da vedere con pier) conseguenza scelta commissario:aggiunta studenti nella commissione

public class FinestraSceltaCommissioni {
	private JFrame f =new JFrame("scelta commissione");
	private JPanel p = new JPanel();

	//costruttore
	public FinestraSceltaCommissioni(Controller controller, int numeroMagistrali , int numeroTriennali){
		this.f.setName("scelta commissione");
		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.f.setSize(300,300);
		JScrollPane jScrollPane = new JScrollPane(this.p);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.f.getContentPane().add(jScrollPane);

		Box box = Box.createVerticalBox();
		box.add(new JLabel("Commissioni Magistrali"));
		//inizializzazione delle commissioni(con solo i presidenti) 
		controller.setListaCommissioni(numeroMagistrali, numeroTriennali);
		controller.inizializzaCommissioniMagistrali(numeroMagistrali);
		controller.inizializzaCommissioniTriennali(numeroTriennali);


		for(Commissione cgm:controller.getListaCommissioni().getCommMag()){
			if(cgm!=null){	

				box.add(new JLabel("Presidente: "+cgm.getPresidente().getNome()+"  max Laureandi: "+cgm.getMaxStudComm()));

			}
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

			jm.setSelectedIndex(-1);
			jm.addItemListener(new ListenerItemSelezionaNumeroStudente(controller,cgm,modLaureandi,jm));
			JList listaLaureandi= new JList(modLaureandi);
			RendererLaureandi rendererLaureandi=new RendererLaureandi(listaLaureandi);
			listaLaureandi.setCellRenderer(rendererLaureandi);
			box.add(new JLabel("Seleziona numero laureando da eliminare "));
			box.add(jm);
			box.add(listaLaureandi);


			Box radioBoxDisponibilita = controller.creaRadioBoxDisponibilita(cgm);
			box.add(controller.creaJComboCommissari(cgm,radioBoxDisponibilita,modLaureandi,jm));

			box.add(new JLabel(" "));

			box.add(radioBoxDisponibilita);
		}

		box.add(new JLabel("Commissioni Triennali"));
		for(Commissione cgm:controller.getListaCommissioni().getCommTri()){
			if(cgm!=null){
				box.add(new JLabel("Presidente: "+cgm.getPresidente().getNome()+"  max Laureandi: "+cgm.getMaxStudComm()));

				DefaultListModel modLaureandi= new DefaultListModel<>();
				for(Studente s:cgm.getLaureandi())
					modLaureandi.addElement(s);
				JList listaLaureandi= new JList(modLaureandi);
				box.add(listaLaureandi);

			}



		}
		//					DefaultListModel modLaureandi= new DefaultListModel<>();
		//					for(Studente s:cgm.getLaureandi())
		//						modLaureandi.addElement(s);
		//					JList listaLaureandi= new JList(modLaureandi);
		//					Map<JComboBox, DefaultComboBoxModel> modelliBox=new HashMap<>();
		//		
		//					int i=0;
		//					for(ArrayList<Docente> lista:cgm.getCommissari()){
		//						JComboBox commissari = new JComboBox(lista.toArray());
		//						commissari.setSelectedIndex(-1);
		//						modelliBox.put(commissari,(DefaultComboBoxModel) commissari.getModel());
		//						commissari.addActionListener(new LogicaSelezioneDocente(modLaureandi,i,cgm));
		//						box.add(commissari);
		//						i++;
		//					}
		//		
		//			box.add(new JLabel("Laureandi: "));
		//			box.add(listaLaureandi);
		//
		//			JComboBox comp = new JComboBox(cgm.getSlotDisponibilita().toArray());
		//			comp.addActionListener(new InterazioneDisponibilita(comp,cgm,controller,modelliBox));
		//			box.add(comp);
		//		}
		//
		//		box.add(new JLabel("Commissioni Triennali"));
		//		for(CommissioneGrafica cgm:listaCommissioni.getCommTri()){
		//			box.add(new JLabel(cgm.getPresidente().getNome()));
		//			DefaultListModel modLaureandi= new DefaultListModel<>();
		//			for(Studente s:cgm.getLaureandi())
		//				modLaureandi.addElement(s);
		//			JList listaLaureandi= new JList(modLaureandi);
		//			Map<JComboBox, DefaultComboBoxModel> modelliBox=new HashMap<>();
		//			cgm.aggiornaCommissione(cgm.getSlotCorrente(), controller);
		//			int i = 0;
		//			Map<Integer,Docente> mappatura=new HashMap<>();
		//			for(ArrayList<Docente> lista:cgm.getCommissari()){
		//				JComboBox commissari = new JComboBox(lista.toArray());
		//				commissari.setSelectedIndex(-1);
		//				modelliBox.put(commissari,(DefaultComboBoxModel) commissari.getModel());
		//				commissari.addActionListener(new LogicaSelezioneDocente(modLaureandi,i,cgm));
		//				box.add(commissari);
		//				i++;
		//			}
		//			box.add(new JLabel("Laureandi: "));
		//			box.add(listaLaureandi);
		//			JComboBox comp = new JComboBox(cgm.getSlotDisponibilita().toArray());
		//			comp.addActionListener(new InterazioneDisponibilita(comp,cgm,controller,modelliBox));
		//			box.add(comp);
		//
		//
		//		}
		JButton conferma=new JButton("conferma scelte");
		conferma.addActionListener(new ListenerConfermaSceltaCommissioni(this.f,controller));;
		box.add(conferma);

		JButton ti=new JButton("Torna Indietro");
		ti.addActionListener(new ListenerTornaIndietro(f, controller));;
		box.add(ti);

		this.p.add(box);

		this.f.setVisible(true);
	}
}
