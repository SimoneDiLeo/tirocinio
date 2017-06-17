package interfacciaGrafica;

import java.io.File;
import javax.swing.JFileChooser;
//import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import controller.Controller;
import gestoreFile.scritturaFile;

public class FinestraSalvaFile {
//	private JFrame f = new JFrame("Salva file");
	private JPanel panel = new JPanel();
	
	public FinestraSalvaFile(Controller c) {
		scritturaFile sc= new scritturaFile();
		try
		{
			JFileChooser chooser = new JFileChooser();
			panel.add(chooser);
			chooser.setCurrentDirectory( new File( "./") );
			int actionDialog = chooser.showSaveDialog(this.panel);
			if ( actionDialog == JFileChooser.APPROVE_OPTION )
			{
				File fileName = new File(chooser.getSelectedFile( )+".txt");
				sc.scrivi(fileName, c);
				if(fileName != null)
				{
					if(fileName.exists())
					{
						actionDialog = JOptionPane.showConfirmDialog(this.panel, "Replace existing file?");

						while (actionDialog == JOptionPane.NO_OPTION)
						{
							actionDialog=chooser.showSaveDialog(this.panel);
							if (actionDialog == JFileChooser.APPROVE_OPTION)
							{
								fileName = new File( chooser.getSelectedFile( ) + ".csv" );
								if(fileName.exists())
								{
									actionDialog = JOptionPane.showConfirmDialog(this.panel, "Replace existing file?");

								}
							}

						}
						if(actionDialog == JOptionPane.YES_OPTION)
						{

						}
					}
					return;
					//AttestDialog.getInstance( ).showErrorDialog(languageBundle.getString("LogFil eAlreadyExists"));
				}


			}



		}catch(Exception e){
			
	}

}
}