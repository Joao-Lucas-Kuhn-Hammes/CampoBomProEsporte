package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Equipamento;
import Models.Esporte;
import com.mysql.jdbc.PreparedStatement;


public class EquipamentosEsportesDAO {
		
		private ConexaoMysql conexao;
		private EquipamentosDAO equiDAO = new EquipamentosDAO();
		private EsporteDAO esporteDAO = new EsporteDAO();
		
		public EquipamentosEsportesDAO() {
			super();
			this.conexao = new ConexaoMysql();
		}
		// Salvar
		public boolean salvar(Equipamento equip, Esporte esporte) {
			this.conexao.abrirConexao();
			String sqlInsert = "INSERT INTO equipamentos_esportes VALUES(?,?,null)";
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
			String sqlBuscarPorId = "SELECT id_esportes FROM equipamentos_esportes WHERE id_equipamentos =?";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
				statement.setLong(1, equip.getId());
				ResultSet rs = statement.executeQuery();
				// CONVERTER O RESULTSET EM UM OBJETO USUARIO
				while(rs.next()) {
					al.add(esporteDAO.buscarPorId(rs.getLong("id_esportes")));
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
			String sqlBuscarPorId = "SELECT id_equipamentos FROM equipamentos_esportes WHERE id_esportes =?";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
				statement.setLong(1, esporte.getId());
				ResultSet rs = statement.executeQuery();
				// CONVERTER O RESULTSET EM UM OBJETO USUARIO
				while(rs.next()) {
					al.add(equiDAO.buscarPorId(rs.getLong("id_equipamentos")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}		
			return al;
		}
}
