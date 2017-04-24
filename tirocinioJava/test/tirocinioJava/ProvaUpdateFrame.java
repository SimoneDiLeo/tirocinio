package tirocinioJava;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tirocinioJava.classi.ListaDocenti;

public class ProvaUpdateFrame implements ActionListener {
	private DefaultComboBoxModel jt;
	private DefaultListModel f;


	public ProvaUpdateFrame(DefaultComboBoxModel model, DefaultListModel modelloLista){
		this.jt=model;
		this.f=modelloLista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String [] a = {"3"};
		for(String i : a)
			this.jt.addElement(i);
		if(!this.f.contains("ciao"))
			this.f.addElement("ciao");
	}	

}
