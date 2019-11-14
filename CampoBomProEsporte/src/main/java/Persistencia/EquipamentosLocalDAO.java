package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Equipamento;
import Models.EquipamentosLocal;
import Models.Local;
import com.mysql.jdbc.PreparedStatement;


public class EquipamentosLocalDAO {
		
		private ConexaoMysql conexao;
		private EquipamentosDAO equipDAO = new EquipamentosDAO();
		private LocalDAO localDAO = new LocalDAO();
		
		public EquipamentosLocalDAO() {
			super();
			this.conexao = new ConexaoMysql();
		}
		// Salvar
		public boolean salvar(EquipamentosLocal el) {
			this.conexao.abrirConexao();
			String sqlInsert = "INSERT INTO equipamentos_local VALUES(?,?,null)";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
				statement.setLong(2, el.getEquipamento().getId());
				statement.setLong(1, el.getLocal().getId());
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				this.conexao.fecharConexao();
			}
			return true;
		}
		
	//	s
	//	
		public boolean excluir(EquipamentosLocal el) {
			this.conexao.abrirConexao();
			String sqlExcluir = "DELETE FROM equipamentos_local WHERE id_local=? AND id_equipamentos= ?";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlExcluir);
				statement.setLong(2, el.getEquipamento().getId());
				statement.setLong(1, el.getLocal().getId());
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
			String sqlBuscarPorId = "SELECT id_local FROM equipamentos_local WHERE id_equipamentos =?";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
				statement.setLong(1, equip.getId());
				ResultSet rs = statement.executeQuery();
				// CONVERTER O RESULTSET EM UM OBJETO USUARIO
				while(rs.next()) {
					al.add(localDAO.buscarPorId(rs.getLong("id_local")));
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
			String sqlBuscarPorId = "SELECT id_equipamentos FROM equipamentos_local WHERE id_local =?";
			try {
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
				statement.setLong(1, local.getId());
				ResultSet rs = statement.executeQuery();
				// CONVERTER O RESULTSET EM UM OBJETO USUARIO
				while(rs.next()) {
					al.add(equipDAO.buscarPorId(rs.getLong("id_equipamentos")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}		
			return al;
		}
}
