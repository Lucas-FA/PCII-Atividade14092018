package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.VendaItens;

public class VendaItensJdbcDAO {
    private Connection conn;
	
	public VendaItensJdbcDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void salvar(VendaItens vi) throws SQLException {
		String sql = "insert into vendaitens (idvenda, idhardware, qtde, totalitem) values ('"+vi.getIdVenda()+"','"+vi.getIdHardware()+"','"+vi.getQtde()+"','"+vi.getTotalItem()+"')";
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		prepareStatement.executeUpdate();
		prepareStatement.close();
	}
	
	public void deletar(int id) throws SQLException {
		String sql = "delete from vendaitens where iditem = '"+id+"'";
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		prepareStatement.executeUpdate();
		prepareStatement.close();
	}
	
	public void alterar(VendaItens vi, int id) {
		String sql = "update vendaitens set idvenda='"+vi.getIdVenda()+"',idhardware='"+vi.getIdHardware()+"',qtde='"+vi.getQtde()+"',totalitem='"+vi.getTotalItem()+"' where iditem = '"+id+"';";
		PreparedStatement prepareStatement;
		try {
			prepareStatement = this.conn.prepareStatement(sql);
			prepareStatement.executeUpdate();
            prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public List<VendaItens> listar() {
		String sql = "select * from vendaitens";
        List<VendaItens> vendaitens = new ArrayList<VendaItens>();
		try {
			PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next()) {
				VendaItens vi = new VendaItens();
				vi.setId(rs.getInt("iditem"));
				vi.setIdVenda(rs.getInt("idvenda"));
				vi.setIdHardware(rs.getInt("idhardware"));
				vi.setQtde(rs.getInt("qtde"));
				vi.setTotalItem(rs.getFloat("totalitem"));
				vendaitens.add(vi);
			}
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vendaitens;
	}
}
