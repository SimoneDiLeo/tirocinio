package controller;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interfacciaGrafica.FinestraErrore;

public class ControllerProprieta {
	private Properties proprieta;
	private JFrame f;
	private Map<String,JTextField> mappatura=new HashMap<>();

	public ControllerProprieta(JFrame aFrame, Properties props) {
		this.f=aFrame;
		this.proprieta=props;
	}

	private void setFrame(JFrame f){
		this.f=f;
	}



	public Box boxProprieta(){
		Box b= Box.createVerticalBox();
		for (Entry<Object, Object> entry : this.proprieta.entrySet())
		{
			Box box=Box.createHorizontalBox();
			String chiave = (String)entry.getKey();
			box.add(new JLabel(chiave));
			JTextField valore=new JTextField();
			valore.setText((String)entry.getValue());
			this.mappatura.put(chiave, valore);
			valore.getDocument().addDocumentListener(new ListenerDocumentProprieta(this.mappatura,chiave,valore));
			box.add(valore);
			b.add(box);
		}
		return b;

	}

	public Properties getProprieta() {
		return proprieta;
	}

	public void setProprieta(Properties proprieta) {
		this.proprieta = proprieta;
	}

	public void scriviProprieta() {
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter("./resources/properties"));
			for (Entry<String,JTextField> entry : this.mappatura.entrySet())
			{
				System.out.print("\n"+entry.getKey() + " : " + entry.getValue().getText());
				this.proprieta.setProperty((String)entry.getKey(), (String)entry.getValue().getText());
				System.out.print("\n"+this.proprieta.getProperty(entry.getKey()));
			}
			this.proprieta.store(out, null);
			out.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void tornaIndietro() {
		this.f.setVisible(true);
	}

}
