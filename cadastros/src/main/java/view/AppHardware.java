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

import controller.HardwareJdbcDAO;
import controller.JdbUtil;
import model.Hardware;


public class AppHardware extends JFrame{
	JLabel lblDescricao = new JLabel("Descrição: ");
	JTextField txtDescricao = new JTextField();
    
    JLabel lblPreco = new JLabel("Preço: ");
    JTextField txtPreco = new JTextField();
    
    JLabel lblId = new JLabel("Id: ");
    JComboBox cboId = new JComboBox();
    
    JButton btnSalvar = new JButton("Salvar");
    JButton btnDeletar = new JButton("Deletar");
    JButton btnAlterar = new JButton("Alterar");
    JButton btnListar = new JButton("Listar");
    
    public AppHardware() {
    	super("Hardware");
    		
    	Container paine = this.getContentPane();
    		
    	paine.add(lblDescricao);
    	lblDescricao.setBounds(20, 20, 70, 25);
    	paine.add(txtDescricao);	
    	txtDescricao.setBounds(90, 20, 225, 25);
    	
    	paine.add(lblPreco);
    	lblPreco.setBounds(20, 55, 70, 25);
    	paine.add(txtPreco);	
    	txtPreco.setBounds(90, 55, 225, 25);
    		   	
    	paine.add(btnSalvar);
   		btnSalvar.setBounds(20, 90, 295, 30);
   		btnSalvar.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			try {
    			Hardware h = new Hardware();
    			h.setDescricao(txtDescricao.getText());
   				h.setPreco(Float.parseFloat(txtPreco.getText()));
    				
   				Connection connection = JdbUtil.getConnection();
   				HardwareJdbcDAO hardwareJdbcDao = new HardwareJdbcDAO(connection);
    				
   				hardwareJdbcDao.salvar(h);
    				
    			}catch(Exception ex) {
    				ex.printStackTrace();
   				}
   			}
   		});
    	
   		paine.add(lblId);
   		lblId.setBounds(20, 140, 70, 25);
   		paine.add(cboId);
    	cboId.setBounds(90, 140, 225, 25);
		cboId.addItem("");
		try {
			Connection conn = JdbUtil.getConnection();
			HardwareJdbcDAO hardwareJdbcDao = new HardwareJdbcDAO(conn);

			List<Hardware> hardware = hardwareJdbcDao.listar();

			for (int i = 0; i < hardware.size(); i++) {
				cboId.addItem(hardware.get(0).getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
   		
   		paine.add(btnDeletar);
   		btnDeletar.setBounds(20, 175, 140, 30);
   		btnDeletar.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			try {
    				int id = Integer.parseInt(cboId.getSelectedItem().toString());
    				
   				Connection connection = JdbUtil.getConnection();
   				HardwareJdbcDAO hardwareJdbcDao = new HardwareJdbcDAO(connection);
    				
   				hardwareJdbcDao.deletar(id);
    				
    			}catch(Exception ex) {
    				ex.printStackTrace();
   				}	
   			}
   		});
   		
   		paine.add(btnAlterar);
   		btnAlterar.setBounds(175, 175, 140, 30);
   		btnAlterar.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			try {
    				int id = Integer.parseInt(cboId.getSelectedItem().toString());
    			
    			Hardware h = new Hardware();
    			h.setDescricao(txtDescricao.getText());
   				h.setPreco(Float.parseFloat(txtPreco.getText()));
    				
   				Connection connection = JdbUtil.getConnection();
   				HardwareJdbcDAO hardwareJdbcDao = new HardwareJdbcDAO(connection);
    				
   				hardwareJdbcDao.alterar(h, id);
    				
    			}catch(Exception ex) {
    				ex.printStackTrace();
   				}	
   			}
   		});
    		
    	this.setLayout(null);
    	this.setVisible(true);
    	this.setResizable(false);
   		this.setSize(340, 255);
   		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
