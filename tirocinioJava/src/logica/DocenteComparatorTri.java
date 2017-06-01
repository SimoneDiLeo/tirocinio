package logica;

import java.util.Comparator;

import classi.Docente;



public class DocenteComparatorTri implements Comparator<Docente> {

	@Override
	public int compare(Docente o1, Docente o2) {
		
		 return o1.compareTriTo(o2);
	}

}
