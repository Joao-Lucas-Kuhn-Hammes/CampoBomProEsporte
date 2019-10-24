package Models;


public class Pin {
	
	//atributos
	
	private long id;
	private String imagem;
	
	//getters n setters
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	//construtores
	
	public Pin() {
		super();
	}
	public Pin(long id, String imagem) {
		super();
		this.id = id;
		this.imagem = imagem;
	}

	//metodos
	
	
	
}
