package Models;

public class Esporte {
	
	//atributos
	
	private long id;
	private long pin;
	private Usuario usuario;
	private String descricao;
	private String nome;
	
	//gettes n settes
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPin() {
		return pin;
	}
	public void setPin(long l) {
		this.pin = l;
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
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//construtores
	
	public Esporte() {
		super();
	}
	public Esporte(long id, long pin, Usuario usuario, String descricao, String nome) {
		super();
		this.id = id;
		this.pin = pin;
		this.usuario = usuario;
		this.descricao = descricao;
		this.nome = nome;
	}

	
	//metodos
	

	
	
}
