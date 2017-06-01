package logica;

import java.util.Comparator;

import classi.Docente;

public class DocenteComparatorMag implements Comparator<Docente>{

	@Override
	public int compare(Docente arg0, Docente arg1) {
		
       	  return arg0.compareMagTo(arg1);
        
       
		
	}
	
	
}
