import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class TestProperties {

	public static void main(String[] args) {

		try {
//lettura properties
			FileInputStream in = new FileInputStream("./resources/properties");
			Properties props = new Properties();
			props.load(in);
			System.out.println(props.getProperty("COMMISSARI_TRIENNALI"));
			System.out.println(props.getProperty("COMMISSARI_MAGISTRALI"));
			props.load(in);
			in.close();
//scrittura properties			
			
			FileOutputStream out = new FileOutputStream("./resources/properties");
			System.out.println("inserire numero commissari Triennali nuovi;");
			Scanner tri= new Scanner(System.in);
			String numTri=tri.nextLine();
			props.setProperty("COMMISSARI_TRIENNALI", numTri);
			System.out.println("inserire numero commissari Magistrali nuovi;");
			Scanner mag= new Scanner(System.in);
			String numMag=mag.nextLine();
			props.setProperty("COMMISSARI_MAGISTRALI", numMag);
			props.store(out, null);
			out.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		} 

	}
}

