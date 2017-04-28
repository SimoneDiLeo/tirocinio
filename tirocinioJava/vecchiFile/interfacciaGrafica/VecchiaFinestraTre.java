//package interfacciaGrafica;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.swing.Box;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//
//import tirocinioJava.classi.Docente;
//import tirocinioJava.classi.Studente;
//
//public class VecchiaFinestraTre {
//	private JFrame f =new JFrame("terzo");
//	private JPanel p = new JPanel();
//
//	//costruttore
//	public VecchiaFinestraTre(List<Docente> docenti, List<Docente>commissari){
//
//		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.f.setSize(300,300);
//		JScrollPane jScrollPane = new JScrollPane(this.p);
//		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//
//		this.f.getContentPane().add(jScrollPane);
//
//		JLabel presidente = new JLabel("Presidente : ");
//		JLabel intermezzo = new JLabel("Laureandi : ");
//		Box box = Box.createVerticalBox();
//		box.add(presidente);
//		box.add(docenti);
//		box.add(intermezzo);
//		
//		this.p.add(box);
//
//		this.f.setVisible(true);
//	}
//}
