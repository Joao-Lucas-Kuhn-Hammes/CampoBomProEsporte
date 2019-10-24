package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Equipamento;
import Models.Local;
import com.mysql.jdbc.PreparedStatement;


public class EquipamentosLocalDAO {
		
		private ConexaoMysql conexao;
		private UsuarioDAO usuDAO = new UsuarioDAO();
		
		public EquipamentosLocalDAO() {
			super();
			this.conexao = new ConexaoMysql();
		}
		// Salvar
		public boolean salvar(Equipamento equip, Local local) {
			this.conexao.abrirConexao();
			String sqlInsert = "INSERT INTO equipamentos_local VALUES(?,?,null";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
				statement.setLong(2, equip.getId());
				statement.setLong(1, local.getId());
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
		public boolean excluir(Equipamento equip, Local local) {
			this.conexao.abrirConexao();
			String sqlExcluir = "DELETE FROM equipamentos_local WHERE id_local=? AND id_equipamentos= ?";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlExcluir);
				statement.setLong(2, equip.getId());
				statement.setLong(1, local.getId());
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
		public ArrayList<Local> buscarPorEquipamento(Equipamento equip) {
			ArrayList<Local> al = new ArrayList<>();
			this.conexao.abrirConexao();
			String sqlBuscarPorId = "SELECT * FROM equipamentos_local WHERE id_equipamentos =?";
			Local local = null;
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
				statement.setLong(1, equip.getId());
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
		
		public ArrayList<Equipamento> buscarPorLocal(Local local) {
			ArrayList<Equipamento> al = new ArrayList<>();
			this.conexao.abrirConexao();
			String sqlBuscarPorId = "SELECT * FROM equipamentos_local WHERE id_local =?";
			Equipamento equip = null;
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
				statement.setLong(1, local.getId());
				ResultSet rs = statement.executeQuery();
				// CONVERTER O RESULTSET EM UM OBJETO USUARIO
				while(rs.next()) {
					equip = new Equipamento();
					equip.setId(rs.getLong("id_equipamentos"));
					equip.setDescricao(rs.getString("descricao"));
					equip.setImagem(rs.getString("imagem"));
					equip.setUsuario(usuDAO.buscarPorId(rs.getLong("id_usuario")));
					al.add(equip);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}		
			return al;
		}
}
