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

import controller.ControllerProprieta;
import interfacciaGrafica.listenerBottoni.ListenerBottoneModificaProprieta;
import interfacciaGrafica.listenerBottoni.ListenerBottonePaginaUno;



public final class MainGUI {


	public static void main(final String[] args) {
		final MainGUI app = new MainGUI();
		app.buildAndDisplayGui();
	}

	private void buildAndDisplayGui() {

		final JFrame frame = new JFrame("Prima Schermata");

		buildContent(frame);
		frame.setMinimumSize(new Dimension(300, 180));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocation(400, 300);
		frame.setVisible(true);
	}

	private void buildContent(final JFrame aFrame) {
		final JPanel panel = new JPanel();
		Properties props = caricaProprieta();
		JTextField nomeFileDocenti = new JTextField ();
		nomeFileDocenti.setSize(100, 50);
		JTextField nomeFileStudenti = new JTextField ();
		nomeFileStudenti.setSize(100, 50);
		JTextField nomeFilePersonale = new JTextField ();
		nomeFileStudenti.setSize(100, 50);
		JTextField nomeFileControrelatori = new JTextField ();
		nomeFileStudenti.setSize(100, 50);
		final JButton okButton = new JButton("Start");
		Box box = Box.createVerticalBox(); 
		box.add(new JLabel("Inserici in nome del file dei docenti"));
		box.add(nomeFileDocenti);
		box.add(new JLabel("Inserici in nome del file degli studenti"));
		box.add(nomeFileStudenti);
		box.add(new JLabel("Inserici in nome del file del personale"));
		box.add(nomeFilePersonale);
		box.add(new JLabel("Inserici in nome del file dei controrelatori"));
		box.add(nomeFileControrelatori);
		box.add(okButton);
		panel.add(box);
		okButton.addActionListener(new ListenerBottonePaginaUno(props,aFrame,nomeFileDocenti,nomeFileStudenti,nomeFilePersonale,nomeFileControrelatori));
		
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