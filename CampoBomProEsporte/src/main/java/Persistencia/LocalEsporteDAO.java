package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.Esporte;
import Models.Local;
import com.mysql.jdbc.PreparedStatement;


public class LocalEsporteDAO {
		
		private ConexaoMysql conexao;
		private UsuarioDAO usuDAO = new UsuarioDAO();
		private PinDAO pinDAO = new PinDAO();
		
		public LocalEsporteDAO() {
			super();
			this.conexao = new ConexaoMysql();
		}
		// Salvar
		public boolean salvar(Local local, Esporte esporte) {
			this.conexao.abrirConexao();
			String sqlInsert = "INSERT INTO local_esportes VALUES(?,null,?";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
				statement.setLong(1, local.getId());
				statement.setLong(2, esporte.getId());
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				this.conexao.fecharConexao();
			}
			return true;
		}
		
	//	
	//	
		public boolean excluir(Local local, Esporte esporte) {
			this.conexao.abrirConexao();
			String sqlExcluir = "DELETE FROM local_esportes WHERE id_esportes=? AND id_local=?";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlExcluir);
				statement.setLong(2, local.getId());
				statement.setLong(1, esporte.getId());
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
		public ArrayList<Esporte> buscarPorLocal(Local local) {
			ArrayList<Esporte> al = new ArrayList<>();
			this.conexao.abrirConexao();
			String sqlBuscarPorId = "SELECT * FROM local_esportes WHERE id_equipamentos =?";
			Esporte esporte = null;
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
				statement.setLong(1, local.getId());
				ResultSet rs = statement.executeQuery();
				// CONVERTER O RESULTSET EM UM OBJETO USUARIO
				while(rs.next()) {
					esporte = new Esporte();
					esporte.setDescricao(rs.getString("descricao"));
					esporte.setId(rs.getLong("id_esportes"));
					esporte.setPin(pinDAO.buscarPorId(rs.getLong("id_pin")));
					esporte.setUsuario(usuDAO.buscarPorId(rs.getLong("id_usuario")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}		
			return al;
		}
		
		public ArrayList<Local> buscarPorEsporte(Esporte esporte) {
			ArrayList<Local> al = new ArrayList<>();
			this.conexao.abrirConexao();
			String sqlBuscarPorId = "SELECT * FROM local_esportes WHERE id_esportes =?";
			Local local = null;
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
				statement.setLong(1, esporte.getId());
				ResultSet rs = statement.executeQuery();
				// CONVERTER O RESULTSET EM UM OBJETO USUARIO
				while(rs.next()) {
					local = new Local();
					local.setId(rs.getLong("id_local"));
					local.setDescricao(rs.getString("descricao"));
					local.setImagem(rs.getString("imagem"));
					local.setLongitude(rs.getDouble("longitude"));
					local.setLatitude(rs.getDouble("latitude"));
					local.setUsuario(usuDAO.buscarPorId(rs.getLong("id_usuario")));
					al.add(local);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}		
			return al;
		}
}
