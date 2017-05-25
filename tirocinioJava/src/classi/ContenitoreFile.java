package classi;

import java.io.File;

public class ContenitoreFile {
	private File fileDocente;
	private File fileStudente;
	private File filePersonale;
	private File fileControrelatori;

	public void setFileDocente(File fileDocente) {
		this.fileDocente = fileDocente;
	}
	public void setFileStudente(File fileStudente) {
		this.fileStudente = fileStudente;
	}
	public void setFilePersonale(File filePersonale) {
		this.filePersonale = filePersonale;
	}
	public File getFileDocente() {
		return fileDocente;
	}
	public File getFileStudente() {
		return fileStudente;
	}
	public File getFilePersonale() {
		return filePersonale;
	}
	public File getFileControrelatori() {
		return fileControrelatori;
	}
	public void setFileControrelatori(File fileControrelatori) {
		this.fileControrelatori = fileControrelatori;
	}
}
