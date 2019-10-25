package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import Models.Usuario;
import com.mysql.jdbc.PreparedStatement;


public class UsuarioDAO {
		
		private ConexaoMysql conexao;
		
		public UsuarioDAO() {
			super();
			this.conexao = new ConexaoMysql();
		}
		// Salvar
		public Usuario salvar(Usuario usuario) {
			this.conexao.abrirConexao();
			String sqlInsert = "INSERT INTO usuario VALUES(?,?,null,?)";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
				statement.setString(1, usuario.getSenha());
				statement.setString(2, usuario.getNome());
				statement.setString(3, usuario.getEmail());
				
				statement.executeUpdate();
				ResultSet rs = statement.getGeneratedKeys();
				if(rs.next()) {
					long id = rs.getLong(1);
					usuario.setId(id);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return usuario;
		}
		
		public Usuario editar(Usuario usuario) {
			this.conexao.abrirConexao();
			String sqlUpdate = "UPDATE usuario SET nome=?, senha=?, email=? WHERE id_usuario=?";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
				statement.setString(1, usuario.getNome());
				statement.setString(2, usuario.getSenha());
				statement.setString(3, usuario.getEmail());
				statement.setLong(4, usuario.getId());
				/*int linhasAfetadas =*/ statement.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return usuario;
		}
	//	
	//	
		public boolean excluir(long id) {
			this.conexao.abrirConexao();
			String sqlExcluir = "DELETE FROM usuario WHERE id_usuario=?";
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
		public Usuario buscarPorId(long id) {
			this.conexao.abrirConexao();
			String sqlBuscarPorId = "SELECT * FROM usuario WHERE id_usuario=?";
			Usuario usuario = null;
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
				statement.setLong(1, id);
				ResultSet rs = statement.executeQuery();
				// CONVERTER O RESULTSET EM UM OBJETO USUARIO
				if(rs.next()) {
					usuario = new Usuario();
					usuario.setSenha(rs.getString("senha"));
					usuario.setNome(rs.getString("nome"));
					usuario.setId(rs.getLong("id_usuario"));
					usuario.setEmail(rs.getString("email"));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}		
			return usuario;
		}
		
		public Usuario buscarLogin(String login, String senha) {
			this.conexao.abrirConexao();
			String sqlBuscarPorId = "SELECT * FROM usuario WHERE email=? and senha=?";
			Usuario usuario = null;
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
				statement.setString(1, login);
				statement.setString(2, senha);
				ResultSet rs = statement.executeQuery();
				// CONVERTER O RESULTSET EM UM OBJETO USUARIO
				if(rs.next()) {
					usuario = new Usuario();
					usuario.setSenha(rs.getString("senha"));
					usuario.setNome(rs.getString("nome"));
					usuario.setId(rs.getLong("id_usuario"));
					usuario.setEmail(rs.getString("email"));
					
				}else {
					return new Usuario();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}		
			return usuario;
		}
}

