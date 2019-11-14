package Models;

public class LocalEsporte {
	private Local local;
	private Esporte esporte;
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
	public LocalEsporte(Local local, Esporte esporte) {
		super();
		this.local = local;
		this.esporte = esporte;
	}
	public LocalEsporte() {
		super();
	}
	
}
