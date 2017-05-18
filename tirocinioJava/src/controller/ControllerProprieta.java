package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;

import interfacciaGrafica.FinestraErrore;

public class ControllerProprieta {
	private Properties proprieta;

	public void caricaProprieta(){
		try{
		FileInputStream in = new FileInputStream("./resources/properties");
		proprieta = new Properties();
		proprieta.load(in);
		in.close();
		}
		catch(Exception e){
			new FinestraErrore();
		}
	}

	public Box boxProprieta(){
		//TODO
		Box b= Box.createVerticalBox();
		for (Entry<Object, Object> entry : this.proprieta.entrySet())
		{
			Box box=Box.createHorizontalBox();
		    box.add(new JLabel((String)entry.getKey()));
		    JTextField valore=new JTextField();
		    valore.setText((String)entry.getValue());
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
//		FileOutputStream out = new FileOutputStream("./resources/properties");
//		String numTri=tri.nextLine();
//		this.proprieta.setProperty("COMMISSARI_TRIENNALI", numTri);
//		System.out.println("inserire numero commissari Magistrali nuovi;");
//		Scanner mag= new Scanner(System.in);
//		String numMag=mag.nextLine();
//		props.setProperty("COMMISSARI_MAGISTRALI", numMag);
//		props.store(out, null);
//		out.close();
//		
	}





}
