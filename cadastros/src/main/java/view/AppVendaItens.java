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
import controller.VendaItensJdbcDAO;
import controller.VendaJdbcDAO;
import model.Hardware;
import model.Venda;
import model.VendaItens;

public class AppVendaItens extends JFrame{
	JLabel lblIdVenda = new JLabel("Id Venda: ");
	JComboBox cboIdVenda = new JComboBox();
    
    JLabel lblIdHardware = new JLabel("Id Hardware: ");
    JTextField txtIdHardware = new JTextField();
    JComboBox cboIdHardware = new JComboBox();
    
    JLabel lblQtde = new JLabel("Quantidade: ");
    JTextField txtQtde = new JTextField();
    
    JLabel lblTotalItem = new JLabel("Total Item: ");
    JTextField txtTotalItem = new JTextField();
    
    JLabel lblId = new JLabel("Id: ");
    JComboBox cboId = new JComboBox();
    
    JButton btnSalvar = new JButton("Salvar");
    JButton btnDeletar = new JButton("Deletar");
    JButton btnAlterar = new JButton("Alterar");
    JButton btnListar = new JButton("Listar");
    
    public AppVendaItens() {
    	super("Itens da Venda");
    		
    	Container paine = this.getContentPane();
    		
    	paine.add(lblIdVenda);
    	lblIdVenda.setBounds(20, 20, 80, 25);
    	paine.add(cboIdVenda);
    	cboIdVenda.setBounds(95, 20, 220, 25);
    	cboIdVenda.addItem("");
		try {
			Connection conn = JdbUtil.getConnection();
			VendaJdbcDAO vendaJdbcDao = new VendaJdbcDAO(conn);

			List<Venda> venda = vendaJdbcDao.listar();

			for (int i = 0; i < venda.size(); i++) {
				cboIdVenda.addItem(venda.get(0).getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	paine.add(lblIdHardware);
    	lblIdHardware.setBounds(20, 55, 80, 25);
    	paine.add(cboIdHardware);
    	cboIdHardware.setBounds(95, 55, 220, 25);
    	cboIdHardware.addItem("");
		try {
			Connection conn = JdbUtil.getConnection();
			HardwareJdbcDAO hardwareJdbcDao = new HardwareJdbcDAO(conn);

			List<Hardware> hardware = hardwareJdbcDao.listar();

			for (int i = 0; i < hardware.size(); i++) {
				cboIdHardware.addItem(hardware.get(0).getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    		
    	paine.add(lblQtde);
    	lblQtde.setBounds(20, 90, 80, 25);
    	paine.add(txtQtde);	
    	txtQtde.setBounds(95, 90, 220, 25);
    	
    	paine.add(lblTotalItem);
    	lblTotalItem.setBounds(20, 125, 70, 25);
    	paine.add(txtTotalItem);	
    	txtTotalItem.setBounds(95, 125, 220, 25);
    	
   		paine.add(btnSalvar);
   		btnSalvar.setBounds(20, 160, 295, 30);
   		btnSalvar.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			try {
    				VendaItens vi = new VendaItens();
    				vi.setIdVenda(Integer.parseInt(cboIdVenda.getSelectedItem().toString()));
    				vi.setIdHardware(Integer.parseInt(cboIdHardware.getSelectedItem().toString()));
    				vi.setQtde(Integer.parseInt(txtQtde.getText()));
    				vi.setTotalItem(Float.parseFloat(txtTotalItem.getText()));
    				
   				    Connection connection = JdbUtil.getConnection();
   				    VendaItensJdbcDAO vendaitensJdbcDao = new VendaItensJdbcDAO(connection);
    				
   				    vendaitensJdbcDao.salvar(vi);
    			}
    			catch(Exception ex) {
    				ex.printStackTrace();
   				}
   			}
   		});
   		
   		paine.add(lblId);
    	lblId.setBounds(20, 210, 70, 25);
    	paine.add(cboId);
    	cboId.setBounds(95, 210, 220, 25);
		cboId.addItem("");
		try {
			Connection conn = JdbUtil.getConnection();
			VendaItensJdbcDAO vendaitensJdbcDao = new VendaItensJdbcDAO(conn);

			List<VendaItens> vendaitens = vendaitensJdbcDao.listar();

			for (int i = 0; i < vendaitens.size(); i++) {
				cboId.addItem(vendaitens.get(0).getId());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
   		
   		paine.add(btnDeletar);
   		btnDeletar.setBounds(20, 245, 140, 30);
   		btnDeletar.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			try {
    				int id = Integer.parseInt(cboId.getSelectedItem().toString());
    				
   				Connection connection = JdbUtil.getConnection();
   				VendaItensJdbcDAO vendaitensJdbcDao = new VendaItensJdbcDAO(connection);
    				
   				vendaitensJdbcDao.deletar(id);
    				
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
    			
    			VendaItens vi = new VendaItens();
    			vi.setIdVenda(Integer.parseInt(cboIdVenda.getSelectedItem().toString()));
				vi.setIdHardware(Integer.parseInt(cboIdHardware.getSelectedItem().toString()));
				vi.setQtde(Integer.parseInt(txtQtde.getText()));
				vi.setTotalItem(Float.parseFloat(txtTotalItem.getText()));
    				
   				Connection connection = JdbUtil.getConnection();
   				VendaItensJdbcDAO vendaitensJdbcDao = new VendaItensJdbcDAO(connection);
    				
   				vendaitensJdbcDao.alterar(vi, id);
    				
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
