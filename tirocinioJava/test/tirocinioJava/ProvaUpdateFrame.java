package tirocinioJava;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classi.ListaDocenti;

public class ProvaUpdateFrame implements ActionListener {
	private Map<JComboBox, DefaultComboBoxModel> jt;
	private DefaultListModel f;


	public ProvaUpdateFrame(Map<JComboBox, DefaultComboBoxModel> prova, DefaultListModel modelloLista){
		this.jt=prova;
		this.f=modelloLista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
		String [] a = {"3"};
		for(JComboBox j:this.jt.keySet())
			if(cb!=j)
				for(String i : a)
					this.jt.get(j).addElement(i);
		if(!this.f.contains("ciao"))
			this.f.addElement("ciao");
	}	

}
