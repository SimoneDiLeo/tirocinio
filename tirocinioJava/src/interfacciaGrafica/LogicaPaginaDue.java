package interfacciaGrafica;

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

import tirocinioJava.classi.Docente;

public class LogicaPaginaDue implements ActionListener{
	private List<Docente> docenti;
	private JFrame sFrame;
	public LogicaPaginaDue(List<Docente> docenti,JFrame secondoFrame){
		this.docenti=docenti;
		this.sFrame=secondoFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String[] nomi= new String[this.docenti.size()];
		for(int i=0;i<this.docenti.size();i++){
			nomi[i]=this.docenti.get(i).getNome() + " Numero Triennali : " + this.docenti.get(i).getNumeroLaureandiTriennali() 
					+  "Numero Magistrali :" +this.docenti.get(i).getNumeroLaureandiMagistrali();
		}
		
		JComboBox disponibili = new JComboBox<>(nomi);
		this.sFrame.dispose();
		new FinestraTre(disponibili,docenti);
	}

}
