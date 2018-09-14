package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Hardware;

public class HardwareJdbcDAO {
    private Connection conn;
	
	public HardwareJdbcDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void salvar(Hardware h) throws SQLException {
		String sql = "insert into hardware (descricao, preco) values ('"+h.getDescricao()+"','"+h.getPreco()+"')";
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		prepareStatement.executeUpdate();
		prepareStatement.close();
	}
	
	public void deletar(int id) throws SQLException {
		String sql = "delete from hardware where idhardware = '"+id+"'";
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		prepareStatement.executeUpdate();
		prepareStatement.close();
	}
	
	public void alterar(Hardware h, int id) {
		String sql = "update hardware set descricao='"+h.getDescricao()+"',preco='"+h.getPreco()+"' where idhardware = '"+id+"';";
		PreparedStatement prepareStatement;
		try {
			prepareStatement = this.conn.prepareStatement(sql);
			prepareStatement.executeUpdate();
            prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public List<Hardware> listar() {
		String sql = "select * from hardware";
        List<Hardware> hardware = new ArrayList<Hardware>();
		try {
			PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next()) {
				Hardware h = new Hardware();
				h.setId(rs.getInt("idhardware"));
				h.setDescricao(rs.getString("descricao"));
				h.setPreco(rs.getFloat("preco"));
				hardware.add(h);
			}
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hardware;
	}
}
