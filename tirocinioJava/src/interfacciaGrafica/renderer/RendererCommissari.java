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

public class RendererCommissari extends JPanel implements ListCellRenderer{

	private static final long serialVersionUID = -1L;
	

	private String[] strings;

	JPanel textPanel;
	JLabel text;

	public RendererCommissari(JList combo) {

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
			Docente s = (Docente) value;
			text.setText(s.toString());
			
			if(((Docente) value).isEccesso())
		          text.setForeground(Color.RED);
			else text.setForeground(Color.BLACK);
		
			          
			
		}
		else{
			text.setText("");
		}

		return text;
	}

}

