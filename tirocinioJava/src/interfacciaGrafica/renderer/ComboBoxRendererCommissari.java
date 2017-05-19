package interfacciaGrafica.renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import classi.Docente;

public class ComboBoxRendererCommissari extends JPanel implements ListCellRenderer
{

	private static final long serialVersionUID = -1L;
	private Color rosso=new Color(255,0,0);
	private Color verde=new Color(0,255,0);

	private String[] strings;

	JPanel textPanel;
	JLabel text;

	public ComboBoxRendererCommissari(JComboBox combo) {

		textPanel = new JPanel();
		textPanel.add(this);
		text = new JLabel();
		text.setOpaque(true);
		text.setFont(combo.getFont());
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
		text.setBackground(Color.BLACK);
		text.setText(value.toString());
		if (index>-2) {
			if(((Docente) value).isSelezionato())
				text.setForeground(rosso);
			else
				text.setForeground(verde);
		}
		
		return text;
	}

}
