package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import Models.Pin;


public class PinDAO {
	
	private ConexaoMysql conexao;
	
	public PinDAO() {
		super();
		this.conexao = new ConexaoMysql();
	}
	
	public Pin buscarPorId(long id) {
		this.conexao.abrirConexao();
		String sqlBuscarPorId = "SELECT * FROM pin WHERE id_pin=?";
		Pin pin = null;
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			// CONVERTER O RESULTSET EM UM OBJETO USUARIO
			if(rs.next()) {
				pin = new Pin();
				pin.setId(rs.getLong("id_pin"));
				pin.setImagem(rs.getString("imagem"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return pin;
	}
	public ArrayList<Pin> buscarTodos() {
		ArrayList<Pin> al = new ArrayList<>();
		this.conexao.abrirConexao();
		String sqlBuscarPorId = "SELECT * FROM pin";
		Pin pin = null;
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
			ResultSet rs = statement.executeQuery();
			// CONVERTER O RESULTSET EM UM OBJETO USUARIO
			while(rs.next()) {
				pin = new Pin();
				pin.setId(rs.getLong("id_pin"));
				pin.setImagem(rs.getString("imagem"));
				al.add(pin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return al;
	}
}
	

