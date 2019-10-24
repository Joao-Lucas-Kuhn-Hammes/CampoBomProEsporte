package Models;


public class Equipamento {
	
	//atributos
	
	private long id;
	private Usuario usuario;
	private String descricao;
	private String imagem;
	
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
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	//contrutores;
	
	public Equipamento(long id, Usuario usuario, String descricao, String imagem) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.descricao = descricao;
		this.imagem = imagem;
	}
	public Equipamento() {
		super();
	}
	
	//metodos
	
	
	
	
}
