package interfacciaGrafica.listenerBottoni;

import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ListenerDocumentProprieta implements DocumentListener {
	private Map<String,JTextField> mappatura;
	private String chiave;
	private JTextField valore;

	public ListenerDocumentProprieta(Map<String, JTextField> mappatura2, String chiave, JTextField valore) {
		this.mappatura=mappatura2;
		this.chiave=chiave;
		this.valore=valore;
	}
	public void changedUpdate(DocumentEvent e) {
		aggiornaValore();
	}
	public void removeUpdate(DocumentEvent e) {
		aggiornaValore();
	}
	public void insertUpdate(DocumentEvent e) {
		aggiornaValore();
	}

	public void aggiornaValore() {
		this.mappatura.put(chiave, valore);
	}
}
