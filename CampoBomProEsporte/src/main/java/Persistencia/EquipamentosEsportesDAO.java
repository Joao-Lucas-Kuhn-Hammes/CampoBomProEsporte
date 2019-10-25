package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Equipamento;
import Models.Esporte;
import com.mysql.jdbc.PreparedStatement;


public class EquipamentosEsportesDAO {
		
		private ConexaoMysql conexao;
		private UsuarioDAO usuDAO = new UsuarioDAO();
		private PinDAO pinDAO = new PinDAO();
		
		public EquipamentosEsportesDAO() {
			super();
			this.conexao = new ConexaoMysql();
		}
		// Salvar
		public boolean salvar(Equipamento equip, Esporte esporte) {
			this.conexao.abrirConexao();
			String sqlInsert = "INSERT INTO equipamentos_esportes VALUES(?,?,null";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
				statement.setLong(2, equip.getId());
				statement.setLong(1, esporte.getId());
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
		public boolean excluir(Equipamento equip, Esporte esporte) {
			this.conexao.abrirConexao();
			String sqlExcluir = "DELETE FROM equipamentos_esportes WHERE id_esportes=? AND id_equipamentos=?";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlExcluir);
				statement.setLong(2, equip.getId());
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
		public ArrayList<Esporte> buscarPorEquipamento(Equipamento equip) {
			ArrayList<Esporte> al = new ArrayList<>();
			this.conexao.abrirConexao();
			String sqlBuscarPorId = "SELECT * FROM equipamentos_esportes WHERE id_equipamentos =?";
			Esporte esporte = null;
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
				statement.setLong(1, equip.getId());
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
		
		public ArrayList<Equipamento> buscarPorEsporte(Esporte esporte) {
			ArrayList<Equipamento> al = new ArrayList<>();
			this.conexao.abrirConexao();
			String sqlBuscarPorId = "SELECT * FROM equipamentos_esportes WHERE id_esportes =?";
			Equipamento equip = null;
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
				statement.setLong(1, esporte.getId());
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
