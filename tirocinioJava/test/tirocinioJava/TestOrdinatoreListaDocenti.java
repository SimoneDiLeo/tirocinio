package tirocinioJava;

import java.util.ArrayList;
import java.util.List;

import classi.Docente;
import classi.DocenteComparatorePresidentiMagistrali;
import classi.DocenteComparatorePresidentiTriennali;
import classi.Studente;

public class TestOrdinatoreListaDocenti {
	public static void main(String[] args){
		List<Docente> docenti= new ArrayList<>();
		Docente d2 = new Docente("secondo", "PO",null ,"");
		d2.addLaureando(new Studente("2", "prova", null,"TRIENNALE"));
		d2.addLaureando(new Studente("2", "prova2", null,"TRIENNALE"));
		docenti.add(d2);
		Docente d1 = new Docente("primo", "PO", null, "");
		d1.addLaureando(new Studente("3", "prova", null,"TRIENNALE"));
		d1.addLaureando(new Studente("3", "prova", null,"TRIENNALE"));
		d1.addLaureando(new Studente("3", "prova", null,"TRIENNALE"));
		docenti.add(d1);
		docenti.sort(new DocenteComparatorePresidentiTriennali());
		Docente d=null;
		if(d==null)System.out.println("");
		else
		System.out.println(d.toString());
//		for(Docente d: docenti){
//			System.out.println(d.toString());
//		}
	}
}
