package Models;

public class LDD {
	
	//atributos
	
	private long id;
	private short tipo;
	private String descricao;
	private Usuario usuario;
	private Equipamento equipamento;
	private Esporte esporte;
	private Local local;
	
	//getters n setters
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public short getTipo() {
		return tipo;
	}
	public void setTipo(short tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Equipamento getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	public Esporte getEsporte() {
		return esporte;
	}
	public void setEsporte(Esporte esporte) {
		this.esporte = esporte;
	}
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	
	//construtores
	
	public LDD(long id, short tipo, String descricao, Usuario usuario, Equipamento equipamento, Esporte esporte,
			Local local) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.equipamento = equipamento;
		this.esporte = esporte;
		this.local = local;
	}
	public LDD() {
		super();
	}
	public LDD(long id, short tipo, String descricao, Usuario usuario) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.descricao = descricao;
		this.usuario = usuario;
	}
	
	//metodos
	
	
	
}
