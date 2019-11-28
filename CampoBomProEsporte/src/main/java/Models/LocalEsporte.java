package Models;

public class LocalEsporte {
	
	//atributos
	
	private Local local;
	private Esporte esporte;
	
	//getters n setters
	
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	public Esporte getEsporte() {
		return esporte;
	}
	public void setEsporte(Esporte esporte) {
		this.esporte = esporte;
	}
	
	//construtores
	
	public LocalEsporte(Local local, Esporte esporte) {
		super();
		this.local = local;
		this.esporte = esporte;
	}
	public LocalEsporte() {
		super();
	}
	
}
