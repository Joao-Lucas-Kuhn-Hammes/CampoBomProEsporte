package Models;


public class Usuario {
	
	//atributos
	
	private long id;
	private String nome;
	private String email;
	private String senha;
	
	//getters n setters
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	//construtores
	
	public Usuario() {
		super();
	}
	public Usuario(long id, String nome, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	//metodos
	
	
	//criptografar senha
	public static String criptografar(String senha) {
		    	int meio = (int) Math.floor(senha.length()/2);
		        String senhanova = "";
		        	for(int i = senha.length();i> 0;i--) {
		        		int aux =((int)(senha.charAt(i-1)));	
		        		if((aux >= 97 && aux <=122) ||(aux >=65 && aux<=90)) {
		        			 aux = aux + 3;
		        		}
		        		if((senha.length()%2)== 0) {
		        			if(i < meio+1) {
		            			aux = aux - 1;	
		        			}
		        		}else {
		        			if(i < meio+2) {
		        				aux = aux - 1;	
		        			}
		        		}
		        	senhanova = senhanova + (char) aux;
		    }
		        return senhanova;
		   
	}
	
}
