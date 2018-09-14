package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Venda;

public class VendaJdbcDAO {
    private Connection conn;
	
	public VendaJdbcDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void salvar(Venda v) throws SQLException {
		String sql = "insert into venda (idcliente, data, vlrtotal, desconto) values ('"+v.getIdCliente()+"','"+v.getData()+"','"+v.getVlrtotal()+"','"+v.getDesconto()+"')";
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		prepareStatement.executeUpdate();
		prepareStatement.close();
	}
	
	public void deletar(int id) throws SQLException {
		String sql = "delete from venda where idvenda = '"+id+"'";
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		prepareStatement.executeUpdate();
		prepareStatement.close();
	}
	
	public void alterar(Venda v, int id) {
		String sql = "update venda set idcliente='"+v.getIdCliente()+"',data='"+v.getData()+"',vlrtotal='"+v.getVlrtotal()+"',desconto='"+v.getDesconto()+"' where idvenda = '"+id+"';";
		PreparedStatement prepareStatement;
		try {
			prepareStatement = this.conn.prepareStatement(sql);
			prepareStatement.executeUpdate();
            prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public List<Venda> listar() {
		String sql = "select * from venda";
        List<Venda> venda = new ArrayList<Venda>();
		try {
			PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next()) {
				Venda v = new Venda();
				v.setId(rs.getInt("idvenda"));
				v.setIdCliente(Integer.parseInt(rs.getString("idcliente")));
   				v.setData(rs.getString("data"));
   				v.setVlrtotal(rs.getFloat("vlrtotal"));
   				v.setDesconto(rs.getFloat("desconto"));
				venda.add(v);
			}
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return venda;
	}
}
