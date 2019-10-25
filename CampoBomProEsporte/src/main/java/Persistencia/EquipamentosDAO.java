package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Equipamento;

import com.mysql.jdbc.PreparedStatement;

public class EquipamentosDAO {
		 
		private ConexaoMysql conexao;
		private UsuarioDAO usuDAO = new UsuarioDAO();
		
		
		public EquipamentosDAO() {
			super();
			this.conexao = new ConexaoMysql();
		}
		// Salvar
		public Equipamento salvar(Equipamento eq) {
			this.conexao.abrirConexao();
			String sqlInsert = "INSERT INTO equipamentos VALUES(null,?,?,?)";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
				statement.setString(1, eq.getImagem());
				statement.setString(2, eq.getDescricao());
				statement.setLong(3, eq.getUsuario().getId());
				statement.executeUpdate();
				ResultSet rs = statement.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					eq.setId(id);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return eq;
		}
		
		public Equipamento editar(Equipamento eq) {
			this.conexao.abrirConexao();
			String sqlUpdate = "UPDATE equipamentos SET descricao=?, imagem=?, id_usuario=? WHERE id_equipamentos=?";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
				statement.setString(1, eq.getDescricao());
				statement.setString(2, eq.getImagem());
				statement.setLong(3, eq.getUsuario().getId());
				statement.setLong(4, eq.getId());
				/*int linhasAfetadas = */statement.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return eq;
		}
	//	
	//	
		public boolean excluir(Long id) {
			
			this.conexao.abrirConexao();
			String sqlExcluir = "DELETE FROM equipamentos_local WHERE id_equipamentos=?";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlExcluir);
				statement.setLong(1, id);
				int linhasAfetadas = statement.executeUpdate();
				if(linhasAfetadas>0) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			
			this.conexao.abrirConexao();
			sqlExcluir = "DELETE FROM equipamentos_esportes WHERE id_equipamentos=?";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlExcluir);
				statement.setLong(1, id);
				int linhasAfetadas = statement.executeUpdate();
				if(linhasAfetadas>0) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			
			this.conexao.abrirConexao();
			sqlExcluir = "DELETE FROM equipamentos WHERE id_equipamentos=?";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlExcluir);
				statement.setLong(1, id);
				int linhasAfetadas = statement.executeUpdate();
				if(linhasAfetadas>0) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return false;
		
	}
		public Equipamento buscarPorId(long id) {
			this.conexao.abrirConexao();
			String sqlBuscarPorId = "SELECT * FROM equipamentos WHERE id_equipamentos=?";
			Equipamento eq = null;
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
				statement.setLong(1, id);
				ResultSet rs = statement.executeQuery();
				// CONVERTER O RESULTSET EM UM OBJETO USUARIO
	 			if(rs.next()) {
					eq = new Equipamento();
					eq.setId(rs.getLong("id_equipamentos"));
					eq.setDescricao(rs.getString("descricao"));
					eq.setUsuario(usuDAO.buscarPorId(rs.getLong("id_usuario")));
					eq.setImagem(rs.getString("imagem"));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}		
			return eq;
		}
		public ArrayList<Equipamento> buscarTodos() {
			ArrayList<Equipamento> al = new ArrayList<>();
			this.conexao.abrirConexao();
			String sqlBuscarPorId = "SELECT * FROM equipamentos";
			Equipamento eq = null;
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
				
				ResultSet rs = statement.executeQuery();
				// CONVERTER O RESULTSET EM UM OBJETO USUARIO
	 			while(rs.next()) {
					eq = new Equipamento();
					eq.setId(rs.getLong("id_equipamentos"));
					eq.setDescricao(rs.getString("descricao"));
					eq.setUsuario(usuDAO.buscarPorId(rs.getLong("id_usuario")));
					eq.setImagem(rs.getString("imagem"));
					al.add(eq);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}		
			return al;
		}
}