package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Equipamento;

import com.mysql.jdbc.PreparedStatement;

public class EquipamentosDAO {
		 
		//atributos
		private ConexaoMysql conexao;
		private UsuarioDAO usuDAO = new UsuarioDAO();
		
		//construtor
		public EquipamentosDAO() {
			super();
			this.conexao = new ConexaoMysql();
		}
		
		//salvar novo equipamento no banco
		public Equipamento salvar(Equipamento eq) {
			this.conexao.abrirConexao();
			String sqlInsert = "INSERT INTO equipamentos VALUES(null,?,?,?)";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
				statement.setString(1, eq.getNome());
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
		
		//editar equipamento existente no banco
		public Equipamento editar(Equipamento eq) {
			this.conexao.abrirConexao();
			String sqlUpdate = "UPDATE equipamentos SET nome=?,descricao=?, id_usuario=? WHERE id_equipamentos=?";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
				statement.setString(1, eq.getNome());
				statement.setString(2, eq.getDescricao());
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
		
		//exclui equipamento no banco
		public boolean excluir(Long id) {
			
			//deleta seus relacionamentos
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
			
			
			//agora exclui do banco
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
		
		//busca no banco equipamento com aquele id
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
					eq.setNome(rs.getString("nome"));

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}		
			return eq;
		}
		
		//buscar todos equipamento criados por um usuario
		public ArrayList<Equipamento> buscarTodosUsuario(Long id) {
			ArrayList<Equipamento> al = new ArrayList<>();
			this.conexao.abrirConexao();
			String sqlBuscarPorId = "SELECT * FROM equipamentos WHERE id_usuario=?";
			Equipamento eq = null;
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
				statement.setLong(1, id);
				ResultSet rs = statement.executeQuery();
				// CONVERTER O RESULTSET EM UM OBJETO USUARIO
	 			while(rs.next()) {
					eq = new Equipamento();
					eq.setId(rs.getLong("id_equipamentos"));
					eq.setDescricao(rs.getString("descricao"));
					eq.setNome(rs.getString("nome"));
					eq.setUsuario(usuDAO.buscarPorId(rs.getLong("id_usuario")));
					al.add(eq);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}		
			return al;
		}
		
		//busca todos equipamentos
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
					eq.setNome(rs.getString("nome"));
					eq.setDescricao(rs.getString("descricao"));
					eq.setUsuario(usuDAO.buscarPorId(rs.getLong("id_usuario")));
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