//package interfacciaGrafica.listenerBottoni;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JFrame;
//
//import controller.Controller;
//import interfacciaGrafica.FinestraModificaPresidenti;
//
//public class ListenerModificaPresidenti implements ActionListener {
//	private JFrame f;
//	private Controller controller;
//	private int numMag;
//	private int numTri;
//
//	public ListenerModificaPresidenti(JFrame f, Controller c, int numeroMagistrali, int numeroTriennali) {
//		this.f=f;
//		this.controller=c;
//		this.numMag=numeroMagistrali;
//		this.numTri=numeroTriennali;
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		this.controller.reinizializzaDocentiCorrenti();
//		this.f.dispose();
//		new FinestraModificaPresidenti(controller,numMag, numTri);
//
//	}
//
//}
