package interfacciaGrafica.logicaChiamate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import interfacciaGrafica.FinestraTre;
import tirocinioJava.classi.BozzaAlgoritmo;
import tirocinioJava.classi.Docente;
import tirocinioJava.classi.ListaDocenti;

public class LogicaPaginaDue implements ActionListener{
	private ListaDocenti docenti;
	private JFrame sFrame;
	private JTextField numeroTriennali;
	private JTextField numeroMagistrali;
	
	

	public LogicaPaginaDue(JTextField numTriennali, JTextField numMagistrali, ListaDocenti docenti2, JFrame f) {
		this.docenti=docenti2;
		this.numeroMagistrali=numMagistrali;
		this.numeroTriennali=numTriennali;
		this.sFrame=f;
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		int numeroMagistrali= Integer.valueOf(this.numeroMagistrali.getText());
		int numeroTriennali=Integer.valueOf(this.numeroTriennali.getText());
//		String[] nomi= new String[this.docenti.size()];
//		for(int i=0;i<this.docenti.size();i++){
//			nomi[i]=this.docenti.get(i).getNome() + " Numero Triennali : " + this.docenti.get(i).getNumeroLaureandiTriennali() 
//					+  "Numero Magistrali :" +this.docenti.get(i).getNumeroLaureandiMagistrali();
//		}
//		
//		JComboBox disponibili = new JComboBox<>(nomi);
		BozzaAlgoritmo b=new BozzaAlgoritmo();
		List<Docente>potCommissari=new ArrayList<>();
		potCommissari=b.trovaPresidenti(numeroMagistrali+numeroTriennali, docenti, "PO");
		this.sFrame.dispose();
		new FinestraTre(docenti,potCommissari,numeroTriennali,numeroMagistrali);
	}

}
