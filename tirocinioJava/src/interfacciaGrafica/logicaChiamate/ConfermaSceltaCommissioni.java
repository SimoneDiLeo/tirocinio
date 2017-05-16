package interfacciaGrafica.logicaChiamate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import classi.CommissioneGrafica;
import classi.Docente;
import classi.ListaCommissioni;
import classi.Studente;
import interfacciaGrafica.FinestraConfermaCommissioni;

public class ConfermaSceltaCommissioni implements ActionListener  {
	private ListaCommissioni commissioniScelte;

	public ConfermaSceltaCommissioni(ListaCommissioni listaCommissioni) {
		this.commissioniScelte=listaCommissioni;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		List<JLabel> listaLabel=new ArrayList<>();
		listaLabel.add(new JLabel("Commissioni Magistrali"));
		calcoloLabel(listaLabel,this.commissioniScelte.getCommMag());
		listaLabel.add(new JLabel("Commissioni Triennali"));
		calcoloLabel(listaLabel, this.commissioniScelte.getCommTri());
		

		new FinestraConfermaCommissioni(listaLabel);

	}

	private void calcoloLabel(List<JLabel> listaLabel,CommissioneGrafica[] comm) {
		for(CommissioneGrafica cgm : comm){
			listaLabel.add(new JLabel("commissione numero :"));
			listaLabel.add(new JLabel("Presidente : " + cgm.getPresidente().getNome()));
			listaLabel.add(new JLabel("Commissari :"));
			for(Docente d : cgm.getMappatura().values())
				listaLabel.add(new JLabel(d.getNome() + " " + d.getRuolo()));
			listaLabel.add(new JLabel("Laureandi :"));
			for(Studente s:cgm.getLaureandi())
				listaLabel.add(new JLabel(s.toString()));


		}
	}
}

