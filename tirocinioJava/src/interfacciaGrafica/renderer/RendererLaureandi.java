package interfacciaGrafica.renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.MenuContainer;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import classi.Docente;
import classi.Studente;

public class RendererLaureandi extends JPanel implements ListCellRenderer{

	private static final long serialVersionUID = -1L;
	private Color rosso=new Color(255,0,0);
	private Color verde=new Color(0,255,0);

	private String[] strings;

	JPanel textPanel;
	JLabel text;

	public RendererLaureandi(JList combo) {

		textPanel = new JPanel();
		textPanel.add(this);
		text = new JLabel();
		text.setOpaque(true);
		text.setFont(((MenuContainer) combo).getFont());
		textPanel.add(text);
	}



	public void setStrings(String[] str)
	{
		strings = str;
	}


	public String[] getStrings()
	{
		return strings;
	}



	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		if(value!=null){
			text.setBackground(Color.WHITE);
			Studente s = (Studente) value;
			Docente corr=s.getCorrelatore();
			String corrNome="";
			if(corr!=null){
				corrNome=" Correlatore: " +corr.getNome();
			}
			text.setText(" numero : "+ s. getNumero() + " matricola : " + s.getMatricola() + " Relatore: " + s.getRelatore().getNome()  +corrNome );
			if(((Studente) value).isEccesso())
			          text.setForeground(Color.RED);
			else text.setForeground(Color.BLACK);
			          
			
		}
		else{
			text.setText("");
		}

		return text;
	}

}
