//package interfacciaGrafica;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.swing.Box;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import controller.Controller;
//import interfacciaGrafica.listenerBottoni.ListenerConfermaModificaPresidenti;
//import interfacciaGrafica.listenerBottoni.ListenerSelezionaPresidente;
//import interfacciaGrafica.listenerBottoni.ListenerTornaIndietro;
//
//public class FinestraModificaPresidenti {
//	private JFrame f = new JFrame("Finestra Modifica Presidenti");
//	private JPanel panel = new JPanel();
//
//	public FinestraModificaPresidenti(Controller controller, int numMag, int numTri) {
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		f.setSize(300,300);
//		JScrollPane jScrollPane = new JScrollPane(panel);
//
//		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//
//
//		f.getContentPane().add(jScrollPane);
//		f.setVisible(true);
//		Box box = Box.createVerticalBox(); 
//
//		box.add(new JLabel("PRESIDENTI MAGISTRALI"));
//		Map<Integer,JComboBox> mappaturaPresidenteCommissione=new HashMap<>();
//
//		for(int i=0;i<numMag;i++){
//			JComboBox jm = controller.comboBoxPresidentiPotenziali();
//			jm.setSelectedIndex(-1);			
//			jm.addActionListener(new ListenerSelezionaPresidente(controller,i));
//			box.add(jm);
//			mappaturaPresidenteCommissione.put(i, jm);
//		}
//
//		box.add(new JLabel("PRESIDENTI TRIENNALI"));
//		for(int i=0;i<numTri;i++){
//			JComboBox jm = controller.comboBoxPresidentiPotenziali();
//			jm.setSelectedIndex(-1);			
//			jm.addActionListener(new ListenerSelezionaPresidente(controller,(i+numMag)));
//			box.add(jm);
//			mappaturaPresidenteCommissione.put(i+numMag, jm);
//			
//		}
//		JButton conferma = new JButton("CONFERMA");
//		box.add(conferma);
//		conferma.addActionListener(new ListenerConfermaModificaPresidenti(this.f,controller, numTri, numMag,mappaturaPresidenteCommissione));
//		
//		JButton ti=new JButton("Torna Indietro");
//		ti.addActionListener(new ListenerTornaIndietro(f, controller));;
//		box.add(ti);
//		
//		panel.add(box);
//
//
//	}
//
//
//
//}
