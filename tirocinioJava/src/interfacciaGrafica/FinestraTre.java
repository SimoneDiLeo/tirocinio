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

import classi.CommissioneGrafica;
import classi.Docente;
import classi.ListaCommissioni;
import classi.ListaDocenti;
import classi.Studente;
import interfacciaGrafica.logicaChiamate.ConfermaSceltaCommissioni;
import interfacciaGrafica.logicaChiamate.InterazioneDisponibilita;
import interfacciaGrafica.logicaChiamate.LogicaSelezioneDocente;

public class FinestraTre {
	private JFrame f =new JFrame("terzo");
	private JPanel p = new JPanel();

	//costruttore
	public FinestraTre(ListaDocenti docenti, List<Docente> presidentiPotenziali, int numeroMagistrali , int numeroTriennali){

		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.f.setSize(300,300);
		JScrollPane jScrollPane = new JScrollPane(this.p);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


		//finire di modificare parametrizzandolo nel numero di commissari e laureandi(piu avanti)
		this.f.getContentPane().add(jScrollPane);

		Box box = Box.createVerticalBox();
		if(presidentiPotenziali.isEmpty())
			new FinestraErrore();
		else {
			box.add(new JLabel("Commissioni Magistrali"));
			ListaCommissioni listaCommissioni = new ListaCommissioni(numeroMagistrali, numeroTriennali);
			if(numeroMagistrali!=0)
				listaCommissioni.inizializzaPresidentiMagistrali(presidentiPotenziali);
			if(numeroTriennali!=0)
				listaCommissioni.inizializzaPresidentiTriennali(presidentiPotenziali);
			for(CommissioneGrafica cgm:listaCommissioni.getCommMag()){
				box.add(new JLabel(cgm.getPresidente().getNome()));
				DefaultListModel modLaureandi= new DefaultListModel<>();
				for(Studente s:cgm.getLaureandi())
					modLaureandi.addElement(s);
				JList listaLaureandi= new JList(modLaureandi);
				Map<JComboBox, DefaultComboBoxModel> modelliBox=new HashMap<>();
				cgm.aggiornaCommissione(cgm.getSlotCorrente(), docenti);
				int i=0;
				for(ArrayList<Docente> lista:cgm.getCommissari()){
					JComboBox commissari = new JComboBox(lista.toArray());
					commissari.setSelectedIndex(-1);
					modelliBox.put(commissari,(DefaultComboBoxModel) commissari.getModel());
					commissari.addActionListener(new LogicaSelezioneDocente(modLaureandi,i,cgm));
					box.add(commissari);
					i++;
				}
				box.add(new JLabel("Laureandi: "));
				box.add(listaLaureandi);
				JComboBox comp = new JComboBox(cgm.getSlotDisponibilita().toArray());
				comp.addActionListener(new InterazioneDisponibilita(comp,cgm,docenti,modelliBox));
				box.add(comp);
			}

			box.add(new JLabel("Commissioni Triennali"));
			for(CommissioneGrafica cgm:listaCommissioni.getCommTri()){
				box.add(new JLabel(cgm.getPresidente().getNome()));
				DefaultListModel modLaureandi= new DefaultListModel<>();
				for(Studente s:cgm.getLaureandi())
					modLaureandi.addElement(s);
				JList listaLaureandi= new JList(modLaureandi);
				Map<JComboBox, DefaultComboBoxModel> modelliBox=new HashMap<>();
				cgm.aggiornaCommissione(cgm.getSlotCorrente(), docenti);
				int i = 0;
				Map<Integer,Docente> mappatura=new HashMap<>();
				for(ArrayList<Docente> lista:cgm.getCommissari()){
					JComboBox commissari = new JComboBox(lista.toArray());
					commissari.setSelectedIndex(-1);
					modelliBox.put(commissari,(DefaultComboBoxModel) commissari.getModel());
					commissari.addActionListener(new LogicaSelezioneDocente(modLaureandi,i,cgm));
					box.add(commissari);
					i++;
				}
				box.add(new JLabel("Laureandi: "));
				box.add(listaLaureandi);
				JComboBox comp = new JComboBox(cgm.getSlotDisponibilita().toArray());
				comp.addActionListener(new InterazioneDisponibilita(comp,cgm,docenti,modelliBox));
				box.add(comp);


			}
			JButton conferma=new JButton("conferma scelte");
			conferma.addActionListener(new ConfermaSceltaCommissioni(listaCommissioni));;
			box.add(conferma);
		}
		
		
		this.p.add(box);

		this.f.setVisible(true);
	}
}
