package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.ClienteJdbcDAO;
import controller.JdbUtil;
import controller.VendaJdbcDAO;
import model.Cliente;
import model.Venda;

public class AppVenda extends JFrame{
	JLabel lblIdCliente = new JLabel("Id Cliente:");
	JComboBox cboIdCliente = new JComboBox();
    
    JLabel lblData = new JLabel("Data: ");
    JTextField txtData = new JTextField();
    
    JLabel lblVlrtotal = new JLabel("Valor Total: ");
    JTextField txtVlrtotal = new JTextField();
    
    JLabel lblDesconto = new JLabel("Desconto: ");
    JTextField txtDesconto = new JTextField();
    
    JLabel lblId = new JLabel("Id: ");
    JComboBox cboId = new JComboBox();
    
    JButton btnSalvar = new JButton("Salvar");
    JButton btnDeletar = new JButton("Deletar");
    JButton btnAlterar = new JButton("Alterar");
    JButton btnListar = new JButton("Listar");
    
    public AppVenda() {
    	super("Venda");
    		
    	Container paine = this.getContentPane();
    		
    	paine.add(lblIdCliente);
    	lblIdCliente.setBounds(20, 20, 80, 25);
    	paine.add(cboIdCliente);
    	cboIdCliente.setBounds(90, 20, 225, 25);
    	cboIdCliente.addItem("");
		try {
			Connection conn = JdbUtil.getConnection();
			ClienteJdbcDAO clienteJdbcDAO = new ClienteJdbcDAO(conn);

			List<Cliente> cliente = clienteJdbcDAO.listar();

			for (int i = 0; i < cliente.size(); i++) {
				cboIdCliente.addItem(cliente.get(0).getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	paine.add(lblData);
    	lblData.setBounds(20, 55, 70, 25);
    	paine.add(txtData);	
    	txtData.setBounds(90, 55, 225, 25);
    		
    	paine.add(lblVlrtotal);
    	lblVlrtotal.setBounds(20, 90, 70, 25);
    	paine.add(txtVlrtotal);	
    	txtVlrtotal.setBounds(90, 90, 225, 25);
    	
    	paine.add(lblDesconto);
    	lblDesconto.setBounds(20, 125, 70, 25);
    	paine.add(txtDesconto);	
    	txtDesconto.setBounds(90, 125, 225, 25);
    	
   		paine.add(btnSalvar);
   		btnSalvar.setBounds(20, 160, 295, 30);
   		btnSalvar.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			try {
    				
    			Venda v = new Venda();
    			v.setIdCliente(Integer.parseInt(cboIdCliente.getSelectedItem().toString()));
   				v.setData(txtData.getText());
   				v.setVlrtotal(Float.parseFloat(txtVlrtotal.getText()));
   				v.setDesconto(Float.parseFloat(txtDesconto.getText()));
    				
   				Connection connection = JdbUtil.getConnection();
   				VendaJdbcDAO vendaJdbcDao = new VendaJdbcDAO(connection);
    				
   				vendaJdbcDao.salvar(v);
    				
    			}catch(Exception ex) {
    				ex.printStackTrace();
   				}
   			}
   		});
   		
   		paine.add(lblId);
    	lblId.setBounds(20, 210, 70, 25);
    	paine.add(cboId);
    	cboId.setBounds(90, 210, 225, 25);
		cboId.addItem("");
		try {
			Connection conn = JdbUtil.getConnection();
			VendaJdbcDAO vendaJdbcDao = new VendaJdbcDAO(conn);

			List<Venda> venda = vendaJdbcDao.listar();

			for (int i = 0; i < venda.size(); i++) {
				cboId.addItem(venda.get(0).getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
   		
   		paine.add(btnDeletar);
   		btnDeletar.setBounds(20, 245, 140, 30);
   		btnDeletar.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			try {
    				int id = Integer.parseInt(cboId.getSelectedItem().toString());
    				
   				Connection connection = JdbUtil.getConnection();
   				VendaJdbcDAO vendaJdbcDao = new VendaJdbcDAO(connection);
    				
   				vendaJdbcDao.deletar(id);
    				
    			}catch(Exception ex) {
    				ex.printStackTrace();
   				}	
   			}
   		});
   		
   		paine.add(btnAlterar);
   		btnAlterar.setBounds(175, 245, 140, 30);
   		btnAlterar.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			try {
    				int id = Integer.parseInt(cboId.getSelectedItem().toString());
    			
    			Venda v = new Venda();
    			v.setIdCliente(Integer.parseInt(cboIdCliente.getSelectedItem().toString()));
   				v.setData(txtData.getText());
   				v.setVlrtotal(Float.parseFloat(txtVlrtotal.getText()));
   				v.setDesconto(Float.parseFloat(txtDesconto.getText()));
    				
   				Connection connection = JdbUtil.getConnection();
   				VendaJdbcDAO vendaJdbcDao = new VendaJdbcDAO(connection);
    				
   				vendaJdbcDao.alterar(v, id);
    				
    			}catch(Exception ex) {
    				ex.printStackTrace();
   				}	
   			}
   		});
    		
    	this.setLayout(null);
    	this.setVisible(true);
    	this.setResizable(false);
   		this.setSize(340, 325);
   		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
