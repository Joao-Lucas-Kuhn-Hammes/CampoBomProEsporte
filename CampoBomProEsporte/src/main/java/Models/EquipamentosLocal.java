package Models;

public class EquipamentosLocal {
	
	private Local local;
	private Equipamento equipamento;
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	public Equipamento getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	public EquipamentosLocal(Local local, Equipamento equipamento) {
		super();
		this.local = local;
		this.equipamento = equipamento;
	}
	public EquipamentosLocal() {
		super();
	}
	
	
}
