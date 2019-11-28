package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.Local;

import com.mysql.jdbc.PreparedStatement;

public class LocalDAO {
		
		//atributos
		private ConexaoMysql conexao;
		private UsuarioDAO usuDAO = new UsuarioDAO();
		
		//construtor
		public LocalDAO() {
			super();
			this.conexao = new ConexaoMysql();
		}
		
		//salva no banco novo local
		public Local salvar(Local l) {
			this.conexao.abrirConexao();
			String sqlInsert = "INSERT INTO local VALUES(?,?,null,?,?,?,?)";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
				statement.setDouble(1, l.getLatitude());
				statement.setString(2, l.getDescricao());
				statement.setDouble(3, l.getLongitude());
				statement.setString(4, l.getEndereco());
				statement.setLong(5, l.getUsuario().getId());
				statement.setString(6, l.getNome());

				statement.executeUpdate();
				ResultSet rs = statement.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					l.setId(id);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return l;
		}
		
		//editar local existente no banco
		public Local editar(Local l) {
			this.conexao.abrirConexao();
			String sqlUpdate = "UPDATE local SET latitude=?,descricao=?, longitude=?, id_usuario=?, endereço=?,nome=? WHERE id_local=?";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
				statement.setDouble(3, l.getLongitude());
				statement.setString(2, l.getDescricao());
				statement.setDouble(1, l.getLatitude());
				statement.setLong(4, l.getUsuario().getId());
				statement.setString(5, l.getEndereco());
				statement.setString(6, l.getNome());
				statement.setLong(7, l.getId());
				/*int linhasAfetadas = */statement.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return l;
		}

		//exclui um local do banco
		public boolean excluir(long id) {
			
			//primeiro exclui relacionamentos com equipamentos
			this.conexao.abrirConexao();
			String sqlExcluir = "DELETE FROM equipamentos_local WHERE id_local=?";
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
			
			//depois exclui relacionamentos com esporte
			this.conexao.abrirConexao();
			sqlExcluir = "DELETE FROM local_esportes WHERE id_local=?";
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
			
			//finamento exclui o local
			this.conexao.abrirConexao();
			sqlExcluir = "DELETE FROM local WHERE id_local=?";
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
		//busca no banco por id
		public Local buscarPorId(long id) {
			this.conexao.abrirConexao();
			String sqlBuscarPorId = "SELECT * FROM local WHERE id_local=?";
			Local l = null;
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
				statement.setLong(1, id);
				ResultSet rs = statement.executeQuery();
				// CONVERTER O RESULTSET EM UM OBJETO USUARIO
	 			if(rs.next()) {
					l = new Local();
					l.setId(rs.getLong("id_local"));
					l.setDescricao(rs.getString("descricao"));
					l.setUsuario(usuDAO.buscarPorId(rs.getLong("id_usuario")));
					l.setLongitude(rs.getDouble("longitude"));
					l.setLatitude(rs.getDouble("latitude"));
					l.setNome(rs.getString("nome"));
					l.setEndereco(rs.getString("endereço"));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}		
			return l;
		}
		
		//busca todos os locais no banco
		public ArrayList<Local> buscarTodos() {
			ArrayList<Local> al = new ArrayList<>();
			this.conexao.abrirConexao();
			String sqlBuscarPorId = "SELECT * FROM local";
			Local l = null;
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);

				ResultSet rs = statement.executeQuery();
				// CONVERTER O RESULTSET EM UM OBJETO USUARIO
	 			while(rs.next()) {
					l = new Local();
					l.setId(rs.getLong("id_local"));
					l.setDescricao(rs.getString("descricao"));
					l.setUsuario(usuDAO.buscarPorId(rs.getLong("id_usuario")));
					l.setLongitude(rs.getDouble("longitude"));
					l.setLatitude(rs.getDouble("latitude"));
					l.setNome(rs.getString("nome"));
					l.setEndereco(rs.getString("endereço"));
					al.add(l);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}		
			return al;
		}
		
		//busca todos os locais criados por um usuario
		public ArrayList<Local> buscarTodosUsuario(Long id) {
			ArrayList<Local> al = new ArrayList<>();
			this.conexao.abrirConexao();
			String sqlBuscarPorId = "SELECT * FROM local WHERE id_usuario = ?";
			Local l = null;
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
				statement.setLong(1, id);
				ResultSet rs = statement.executeQuery();
				// CONVERTER O RESULTSET EM UM OBJETO USUARIO
	 			while(rs.next()) {
					l = new Local();
					l.setId(rs.getLong("id_local"));
					l.setDescricao(rs.getString("descricao"));
					l.setUsuario(usuDAO.buscarPorId(rs.getLong("id_usuario")));
					l.setLongitude(rs.getDouble("longitude"));
					l.setLatitude(rs.getDouble("latitude"));
					l.setNome(rs.getString("nome"));
					l.setEndereco(rs.getString("endereço"));
					al.add(l);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}		
			return al;
		}
}
