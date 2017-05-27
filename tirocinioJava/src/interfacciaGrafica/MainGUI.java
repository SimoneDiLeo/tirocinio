package interfacciaGrafica;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classi.ContenitoreFile;
import controller.ControllerProprieta;
import interfacciaGrafica.listenerBottoni.ListenerBottoneModificaProprieta;
import interfacciaGrafica.listenerBottoni.ListenerBottonePaginaUno;
import interfacciaGrafica.listenerBottoni.ListenerSelettoreFile;



public final class MainGUI {
	//modificare radiobox in checkBox
	//modificare vista studente senza l'output del nome
	//aggiungere "algoritmo" dei commissari con backtracking
	//possibile modifica delle commissioni
	//scelta controrelatori alla fine

	public static void main(final String[] args) {
		final MainGUI app = new MainGUI();
		app.buildAndDisplayGui();
	}

	private void buildAndDisplayGui() {

		final JFrame frame = new JFrame("Prima Schermata");

		buildContent(frame);
		frame.setMinimumSize(new Dimension(500, 300));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocation(400, 300);
		frame.setVisible(true);
	}

	private void buildContent(final JFrame aFrame) {
		final JPanel panel = new JPanel();
		ContenitoreFile cf=new ContenitoreFile();
		Properties props = caricaProprieta();
		JButton apriFileDocenti = new JButton("Apri File Docente");
		apriFileDocenti.setName("doc");
		apriFileDocenti.addActionListener(new ListenerSelettoreFile(cf,aFrame));
		apriFileDocenti.setSize(100, 50);
		JButton apriFileStud = new JButton("Apri File Studenti");
		apriFileStud.setName("stud");
		apriFileStud.setSize(100, 50);
		apriFileStud.addActionListener(new ListenerSelettoreFile(cf, aFrame));
		JButton apriFilePers = new JButton("Apri File Personale");
		apriFilePers.setName("pers");
		apriFilePers.addActionListener(new ListenerSelettoreFile(cf, aFrame));
		apriFilePers.setSize(100, 50);
		JButton apriFileContro = new JButton("Apri File Controrelatore");
		apriFileContro.setSize(100, 50);
		apriFileContro.setName("contro");
		apriFileContro.addActionListener(new ListenerSelettoreFile(cf, aFrame));
		final JButton okButton = new JButton("Start");
		Box box = Box.createVerticalBox(); 
		box.add(new JLabel("Inserici in nome del file dei docenti"));
		box.add(apriFileDocenti);
		box.add(new JLabel("Inserici in nome del file degli studenti"));
		box.add(apriFileStud);
		box.add(new JLabel("Inserici in nome del file del personale"));
		box.add(apriFilePers);
		box.add(new JLabel("Inserici in nome del file dei controrelatori"));
		box.add(apriFileContro);
		box.add(okButton);
		panel.add(box);
		okButton.addActionListener(new ListenerBottonePaginaUno(props,aFrame,cf));

		box.add(new JLabel("Modifica o controlla le proprieta cliccando su questo bottone"));
		JButton modifica = new JButton("Modifica Proprieta");
		modifica.addActionListener(new ListenerBottoneModificaProprieta(new ControllerProprieta(aFrame,props)));
		box.add(modifica);		

		aFrame.getContentPane().add(panel);
	}
	private Properties caricaProprieta(){
		Properties proprieta=null;
		try{
			FileInputStream in = new FileInputStream("./resources/properties");
			proprieta = new Properties();
			proprieta.load(in);
			in.close();
			return proprieta;
		}
		catch(Exception e){
			new FinestraErrore();
		}
		return proprieta;
	}

}