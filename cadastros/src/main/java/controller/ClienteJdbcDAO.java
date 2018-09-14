package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteJdbcDAO {
    private Connection conn;
	
	public ClienteJdbcDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void salvar(Cliente c) throws SQLException {
		String sql = "insert into cliente (nome, sexo, endereco, telefone, email) values ('"+c.getNome()+"','"+c.getSexo()+"','"+c.getEndereco()+"','"+c.getTelefone()+"','"+c.getEmail()+"')";
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		prepareStatement.executeUpdate();
		prepareStatement.close();
	}
	
	public void deletar(int id) throws SQLException {
		String sql = "delete from cliente where idcliente = '"+id+"'";
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		prepareStatement.executeUpdate();
		prepareStatement.close();
	}
	
	public void alterar(Cliente c, int id) {
		String sql = "update cliente set nome='"+c.getNome()+"',sexo='"+c.getSexo()+"',endereco='"+c.getEndereco()+"',telefone='"+c.getTelefone()+"',email='"+c.getEmail()+"' where idcliente = '"+id+"';";
		PreparedStatement prepareStatement;
		try {
			prepareStatement = this.conn.prepareStatement(sql);
			prepareStatement.executeUpdate();
            prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public List<Cliente> listar() {
		String sql = "select * from cliente";		
        List<Cliente> cliente = new ArrayList<Cliente>();
		try {
			PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getInt("idcliente"));
				c.setNome(rs.getString("nome"));
				c.setSexo(rs.getString("sexo"));
				c.setEndereco(rs.getString("endereco"));
				c.setTelefone(rs.getString("telefone"));
				c.setEmail(rs.getString("email"));
				cliente.add(c);
			}
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}
}
