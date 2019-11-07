package Models;


public class Local {
	
	//atributos
	
	private long id;
	private Double longitude;
	private Double latitude;
	private String descricao;
	private Usuario usuario;
	private String endereco;
	private String nome;
	
	//getters n setters
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
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
	
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereço) {
		this.endereco = endereço;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//construtores
	

	public Local() {
		super();
	}
	public Local(long id, Double longitude, Double latitude, String descricao, Usuario usuario, String endereco) {
		super();
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.descricao = descricao;
		this.usuario = usuario;
		this.endereco = endereco;
	}

	//metodos
	
	
}
