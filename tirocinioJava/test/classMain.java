
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class classMain {
    public static void main(String[] args) throws IOException{
    	
    	String COMMA_DELIMITER=";";
    	String NEW_LINE_SEPARATOR="\n";
    	String FILE_HEADER="nome;cognome";
    	
    	List<Persona> listaPer=new ArrayList<>();
    	listaPer.add(new Persona("Gino","Paoli"));
    	listaPer.add(new Persona("Mario","Rossi"));
    	listaPer.add(new Persona("Giulio","Golia"));
    	
    	FileWriter file=new FileWriter("dati\\prova.csv"); // dati è una source folder
    	file.append(FILE_HEADER);
    	
    	for(Persona p: listaPer){
    		file.append(NEW_LINE_SEPARATOR);
    		file.append(p.getNome());
    	    file.append(COMMA_DELIMITER);
    		file.append(p.getCognome());
    		
    	}
    	file.flush();
		file.close();
		System.out.println("done");
    	
    }
}
