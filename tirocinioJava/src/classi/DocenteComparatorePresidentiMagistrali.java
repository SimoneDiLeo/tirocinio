package classi;

import java.util.Comparator;

public class DocenteComparatorePresidentiMagistrali implements Comparator<Docente> {

	@Override
	public int compare(Docente d1, Docente d2) {
		if(d1.getRuolo().equals("PO"))
			if(d2.getRuolo().equals("PO"))
				if(d1.getNumeroLaureandiMagistrali()>d2.getNumeroLaureandiMagistrali())
					return -1;
				else
					return 1;
			else 
				return -1;
		else return 0;

	}
}