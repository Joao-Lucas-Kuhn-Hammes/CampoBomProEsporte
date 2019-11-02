package Models;


public class Equipamento {
	
	//atributos
	
	private long id;
	private Usuario usuario;
	private String descricao;
	private String nome;
	
	//getters n setters
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	
	
	//contrutores;
	
	public Equipamento(long id, Usuario usuario, String descricao, String nome) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.descricao = descricao;
		this.nome = nome;
	}
	public Equipamento() {
		super();
	}
	
	//metodos
	
	
	
	
}
