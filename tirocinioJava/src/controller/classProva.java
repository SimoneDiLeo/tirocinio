package controller;

import java.util.ArrayList;
import java.util.List;

public class classProva {
    public static void main(String[] args){
//    	Controller c=new Controller();
//    	c.caricaFile("d", "s", "p", "c");
//    	c.setListaCommissioni(1, 1);
//		c.inizializzaCommissioniMagistrali(1);
//		c.inizializzaCommissioniTriennali(1);
//    	//c.getStudenti();
//    	System.out.println(c.getNumeroStudMagistrali());
//    	System.out.println(c.getNumeroStudTriennali());
//    	System.out.println(c.getListaCommissioni().toStringTriennale());
//    	System.out.println(c.getListaCommissioni().toStringMagistrale());
    	List<Integer> l1=new ArrayList<>();
    	List<Integer> l2=new ArrayList<>();
    	l1.add(1);
    	l2.add(2);
    	l1.addAll(l2);
    	System.out.println(l1.size());
    	
    }
}
