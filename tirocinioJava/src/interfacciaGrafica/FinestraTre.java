package interfacciaGrafica;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

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

import classi.CommissioneGrafica;
import classi.Docente;
import classi.ListaDocenti;
import classi.Studente;
import interfacciaGrafica.logicaChiamate.InterazioneDisponibilita;
import interfacciaGrafica.logicaChiamate.LogicaSelezioneDocente;

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
		Properties prop = new Properties(); 
		URL url = this.getClass().getClassLoader().getSystemResource("properties");
		try {
			prop.load(url.openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//finire di modificare parametrizzandolo nel numero di commissari e laureandi(piu avanti)
		this.f.getContentPane().add(jScrollPane);

		Box box = Box.createVerticalBox();
		if(presidentiPotenziali.isEmpty())
			new FinestraErrore();
		else {
			box.add(new JLabel("Commissioni Magistrali"));
			for(int i=0;i<numeroMagistrali;i++){
				CommissioneGrafica cgm=new CommissioneGrafica(presidentiPotenziali.get(i), docenti,"MAGISTRALE");
				box.add(new JLabel(cgm.getPresidente().getNome()));
				JComboBox[] commissari= new JComboBox[Integer.parseInt(prop.getProperty("COMMISSARI_MAGISTRALI"))];
				DefaultListModel modLaureandi= new DefaultListModel<>();
				for(Studente s:cgm.getLaureandi())
					modLaureandi.addElement(s);
				JList listaLaureandi= new JList(modLaureandi);
				JComboBox commissari1 = new JComboBox(cgm.getCommissari1().toArray());
				DefaultComboBoxModel model = (DefaultComboBoxModel) commissari1.getModel();
				box.add(commissari1);
				JComboBox commissari2 = new JComboBox(cgm.getCommissari2().toArray());
				DefaultComboBoxModel model2 = (DefaultComboBoxModel) commissari2.getModel();
				box.add(commissari2);

				//for(int j=0;j<(Integer.parseInt(prop.getProperty("COMMISSARI_MAGISTRALI")));j++){
				//					commissari[i]= new JComboBox(cgm.getCommissari1().toArray());
				//					DefaultComboBoxModel model = (DefaultComboBoxModel) commissari[i].getModel();
				//					commissari[i].addActionListener(new LogicaSelezioneDocente(modLaureandi,cgm,commissari));	
				//				}
				//				for(JComboBox jc : commissari)
				//					box.add(jc);
				box.add(new JLabel("Laureandi: "));
				//			Box laur = Box.createVerticalBox();

				box.add(listaLaureandi);
				commissari1.addActionListener(new LogicaSelezioneDocente(modLaureandi,cgm,commissari2));
				commissari2.addActionListener(new LogicaSelezioneDocente(modLaureandi,cgm,commissari1));
				JComboBox comp = new JComboBox(cgm.getSlotDisponibilita().toArray());
				box.add(comp);
				comp.addActionListener(new InterazioneDisponibilita(comp,cgm,docenti,model,model2));

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
				DefaultComboBoxModel model2= (DefaultComboBoxModel) commissari2.getModel();
				disponibilita.addActionListener(new InterazioneDisponibilita(disponibilita,cgm,docenti,model,model2));
			}
		}


		this.p.add(box);

		this.f.setVisible(true);
	}
}
