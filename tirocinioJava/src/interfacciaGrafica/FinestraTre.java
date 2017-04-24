package interfacciaGrafica;

import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultFocusManager;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tirocinioJava.classi.Docente;
import tirocinioJava.classi.ListaDocenti;
import tirocinioJava.classi.Studente;

public class FinestraTre {
	private JFrame f =new JFrame("terzo");
	private JPanel p = new JPanel();

	//costruttore
	public FinestraTre(ListaDocenti docenti, List<Docente> presidentiPotenziali, int numeroTriennali, int numeroMagistrali){

		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.f.setSize(300,300);
		JScrollPane jScrollPane = new JScrollPane(this.p);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.f.getContentPane().add(jScrollPane);

		Box box = Box.createVerticalBox();
		box.add(new JLabel("Commissioni Magistrali"));
		for(int i=0;i<numeroMagistrali;i++){
			CommissioneGrafica cgm=new CommissioneGrafica(presidentiPotenziali.get(1), docenti,"MAGISTRALE");
			box.add(new JLabel(cgm.getPresidente().getNome()));
			JComboBox commissari1 = new JComboBox(cgm.getCommissari1().toArray());
			DefaultComboBoxModel model = (DefaultComboBoxModel) commissari1.getModel();
			box.add(commissari1);
			JComboBox commissari2 = new JComboBox(cgm.getCommissari2().toArray());
			box.add(commissari2);
			box.add(new JLabel("Laureandi: "));
//			Box laur = Box.createVerticalBox();
			DefaultListModel modLaureandi= new DefaultListModel<>();
			for(Studente s:cgm.getLaureandi())
				modLaureandi.addElement(s);
			JList listaLaureandi= new JList(modLaureandi);
			box.add(listaLaureandi);
			commissari1.addActionListener(new LogicaSelezioneDocente(modLaureandi,cgm));
			JComboBox comp = new JComboBox(cgm.getSlotDisponibilita().toArray());
			box.add(comp);
			comp.addActionListener(new InterazioneDisponibilita(comp,cgm,docenti,model));

		}

		box.add(new JLabel("Commissioni Triennali"));

		for(int i=numeroMagistrali;i<numeroMagistrali+numeroTriennali;i++){
			CommissioneGrafica cgm=new CommissioneGrafica(presidentiPotenziali.get(i), docenti,"TRIENNALE");
			box.add(new JLabel(cgm.getPresidente().getNome()));
			JComboBox commissari1 = new JComboBox(cgm.getCommissari1().toArray());
			DefaultComboBoxModel model = (DefaultComboBoxModel) commissari1.getModel();
			box.add(commissari1);
			JComboBox commissari2 = new JComboBox(cgm.getCommissari2().toArray());
			box.add(commissari2);
			JComboBox disponibilita = new JComboBox(cgm.getSlotDisponibilita().toArray());
			box.add(disponibilita);
			disponibilita.addActionListener(new InterazioneDisponibilita(disponibilita,cgm,docenti,model));
		}


		this.p.add(box);

		this.f.setVisible(true);
	}
}
