package Models;

public class Esporte {
	
	//atributos
	
	private long id;
	private Pin pin;
	private Usuario usuario;
	private String descricao;
	
	//gettes n settes
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Pin getPin() {
		return pin;
	}
	public void setPin(Pin pin) {
		this.pin = pin;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	//construtores
	
	public Esporte() {
		super();
	}
	public Esporte(long id, Pin pin, Usuario usuario, String descricao) {
		super();
		this.id = id;
		this.pin = pin;
		this.usuario = usuario;
		this.descricao = descricao;
	}
	
	//metodos
	

	
	
}
