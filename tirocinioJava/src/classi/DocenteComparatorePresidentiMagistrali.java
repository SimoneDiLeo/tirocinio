package classi;

import java.util.Comparator;

public class DocenteComparatorePresidentiMagistrali implements Comparator<Docente> {

	@Override
	public int compare(Docente o1, Docente o2) {
		if(o1.getRuolo().equals("PO")&& o2.getRuolo().equals("PO")){
			if(o1.getNumeroLaureandiMagistrali()>o2.getNumeroLaureandiMagistrali())
				return -1;
			else 
				return 1;
		}

		if(o1.getRuolo().equals("PA")&& o2.getRuolo().equals("PA")){
			if(o1.getNumeroLaureandiMagistrali()>o2.getNumeroLaureandiMagistrali())
				return -1;
			else 
				return 1;
		}
		else
			return 1;
	}
}