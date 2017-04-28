package classi;

import java.util.Comparator;

public class DocenteComparatorePresidentiTriennali implements Comparator<Docente> {

	@Override
	public int compare(Docente o1, Docente o2) {
		if(o1.getRuolo().equals("PO"))
			if(o2.getRuolo().equals("PO"))
				if(o1.getNumeroLaureandiTriennali()>o2.getNumeroLaureandiTriennali())
					return -1;
				else
					return 1;
			else 
				return -1;
		else return 0;

	}

}
