//package classi;
//
////vecchia versione da tenere per ispirazione
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class CommissioneVecchia{
//	private Docente[] elementi;//elemento[0] -> presidente commissione
//	//private Map<Docente,List<Studente> > studenti; // array chiave index relatore valore array studenti
//	private List<Studente> studenti;
//	private String tipoLaurea;
//	private boolean complete;
//	private Docente[] supplenti;
//
//	public CommissioneVecchia (int numeroCommissari,int numeroStudenti,int numeroSupplenti,String tipoLaurea){
//		this.tipoLaurea=tipoLaurea;
//		this.elementi=new Docente[numeroCommissari];
//		this.studenti= new ArrayList<>();
//		this.complete=false;
//		this.supplenti=new Docente[numeroSupplenti];
//	}
//	//costruttore dummy per test semplici
//	//da eliminare piu avanti nell'implementazione
//	public CommissioneVecchia (){
//		this.elementi=new Docente[1];
//		this.studenti= new ArrayList<>();
//		this.complete=false;
//		this.tipoLaurea="MAGISTRALI";
//		this.supplenti=new Docente[1];
//	}
//
//
//
//	public Docente getPresidente(){
//		return this.elementi[0];
//	}
//
//	private int contaInseriti(){
//		int i=0;
//		for(Docente doc:this.elementi){
//			if(doc!=null){
//				i++;
//			}
//		}
//		return i;
//	}
//
//	public boolean isComplete(){
//		if(this.tipoLaurea.equals("TRIENNALE") ){
//			if(this.contaInseriti()==3){
//				this.complete= true;
//			}
//			else{
//				this.complete= false;
//			}
//		}
//		else{
//			if(this.contaInseriti()==5){
//				this.complete= true;
//			}
//			else{
//				this.complete= false;
//			}
//		}
//
//		return this.complete;
//
//	}
//
//	public int getStudentiLength(){
//		int cont=0;
//		for(Studente s:this.studenti){
//			if(s!=null)
//				cont++;
//		}
//		return cont;
//	}
//
//
//
//	public void inserisciPresidente(Docente var){
//		this.elementi[0]=var;
//	}
//
//	public void inserisciDocente(Docente var){
//		for(int i = 0; i<this.supplenti.length;i++){
//			if(this.elementi[i].equals(var))
//				this.elementi[i]=null;
//		}
//	}
//
//	public void inserisciDocenteInPosizione(int pos, Docente var){
//		this.elementi[pos]=var;
//	}
//
//	public void rimuoviDocente(Docente var){
//		for(int i = 0; i<this.supplenti.length;i++){
//			if(this.supplenti[i].equals(var))
//				this.supplenti[i]=null;
//		}
//	}
//
//
//
//
//	public void rimuoviUltimoDocente(){
//		for(int i = this.elementi.length-1; i==0;i--){
//			if(this.elementi[i]!=null)
//				this.elementi[i]=null;
//		}
//	}
//
//
//	public void inserisciGruppoStudenti(List<Studente> chiave){
//		this.studenti.addAll(chiave);
//	}
//
//	private List<Studente> listaUnita(List<Studente> list, List<Studente> var) {
//		list.addAll(var);
//		return list;
//	}
//
//
//
//
//	public void rimuoviGruppoStudenti(Docente chiave){
//		this.studenti.remove(chiave);
//	}
//
//	public void inserisciStudente(Studente s){
//		this.studenti.add(s);
//	}
//
//
//	public void rimuoviStudente(int idStudente){
//		this.studenti.remove(trovaStudenteDaChiave(this.studenti,idStudente));
//	}
//
//	private Studente trovaStudenteDaChiave(List<Studente> lista,int idStudente) {
//		for(Studente s:lista){
//			if(s.getMatricola().equals(Integer.toString(idStudente)))
//					return s;
//		}
//		return null;
//	}
//
//	public void inserisciSupplente(Docente sup){
//		for(int i = 0; i<this.supplenti.length;i++){
//			if(this.supplenti[i]==null)
//				this.supplenti[i]=sup;
//		}
//	}
//
//
//	public void eliminaSupplente(Docente sup){
//		for(int i = 0; i<this.supplenti.length;i++){
//			if(this.supplenti[i].equals(sup))
//				this.supplenti[i]=null;
//		}
//	}
//
//	public Docente[] getElementi() {
//		return this.elementi;
//	}
//
//
//
//
//	public void setElementi(Docente[] elementi) {
//		this.elementi = elementi;
//	}
//
//
//
//
//	public List<Studente> getStudenti() {
//		return studenti;
//	}
//
//
//
//
//	public void setStudenti(List<Studente> studenti) {
//		this.studenti = studenti;
//	}
//
//
//
//
//	public String getTipoLaurea() {
//		return tipoLaurea;
//	}
//
//
//
//
//	public void setTipoLaurea(String tipoLaurea) {
//		this.tipoLaurea = tipoLaurea;
//	}
//
//
//
//
//	public boolean getComplete() {
//		return complete;
//	}
//
//
//
//
//	public void setComplete(boolean complete) {
//		this.complete = complete;
//	}
//
//
//
//
//	public Docente[] getSupplenti() {
//		return supplenti;
//	}
//
//
//
//
//	public void setSupplenti(Docente[] supplenti) {
//		this.supplenti = supplenti;
//	}
//
//	@Override
//	public String toString() {
//		String commissari="";
//		for(Docente d:this.elementi){
//			if(d!=null)
//				commissari+=d.getNome()+"\n";
//		}
//
//		return "Commissione [elementi=" +commissari+ "\n studenti= " + studenti.toString() + ", tipoLaurea="
//		+ tipoLaurea + ", complete=" + complete + ", supplenti=" + Arrays.toString(supplenti) + "]";
//	}
//
//}