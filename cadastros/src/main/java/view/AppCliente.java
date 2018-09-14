package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.ClienteJdbcDAO;
import controller.JdbUtil;
import model.Cliente;

public class AppCliente extends JFrame{
	JLabel lblNome = new JLabel("Nome: ");
	JTextField txtNome = new JTextField();
	
	JLabel lblSexo = new JLabel("Sexo: ");
	JRadioButton[] rdbsexo = new JRadioButton[2];
    ButtonGroup sexo = new ButtonGroup();{
    rdbsexo[0] = new JRadioButton("Feminino");
    rdbsexo[1] = new JRadioButton("Masculino");}
    
    JLabel lblEndereco = new JLabel("Endereço: ");
    JTextField txtEndereco = new JTextField();
    
    JLabel lblTelefone = new JLabel("Telefone: ");
    JTextField txtTelefone = new JTextField();
    
    JLabel lblEmail = new JLabel("E-mail: ");
    JTextField txtEmail = new JTextField();
    
    JLabel lblId = new JLabel("Id: ");
    JComboBox cboId = new JComboBox();
    
    JButton btnSalvar = new JButton("Salvar");
    JButton btnDeletar = new JButton("Deletar");
    JButton btnAlterar = new JButton("Alterar");
    JButton btnListar = new JButton("Listar");
    
    public AppCliente() {
    	super("Cliente");
    		
    	Container paine = this.getContentPane();
    	
    	paine.add(lblNome);
    	lblNome.setBounds(20, 20, 70, 25);
    	paine.add(txtNome);	
    	txtNome.setBounds(90, 20, 225, 25);
    		
    	paine.add(lblSexo);
    	lblSexo.setBounds(20, 55, 70, 25);
    	sexo.add(rdbsexo[0]);
    	sexo.add(rdbsexo[1]);
		paine.add(rdbsexo[0]);
		rdbsexo[0].setBounds(90, 55, 110, 25);
		paine.add(rdbsexo[1]);
		rdbsexo[1].setBounds(180, 55, 110, 25);
    	
    	paine.add(lblEndereco);
    	lblEndereco.setBounds(20, 90, 70, 25);
    	paine.add(txtEndereco);	
    	txtEndereco.setBounds(90, 90, 225, 25);
    		
    	paine.add(lblTelefone);
    	lblTelefone.setBounds(20, 125, 70, 25);
    	paine.add(txtTelefone);	
    	txtTelefone.setBounds(90, 125, 225, 25);
    	
    	paine.add(lblEmail);
    	lblEmail.setBounds(20, 160, 70, 25);
    	paine.add(txtEmail);	
    	txtEmail.setBounds(90, 160, 225, 25);
    	
   		paine.add(btnSalvar);
   		btnSalvar.setBounds(20, 195, 295, 30);
   		btnSalvar.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			try {
    			    Cliente c = new Cliente();
    			    c.setNome(txtNome.getText());
    			    if (rdbsexo[0].isSelected()) {
    			    	c.setSexo("F");
    			    }
    			    else {
    			    	c.setSexo("M");
    			    }
   				    c.setEndereco(txtEndereco.getText());
   				    c.setTelefone(txtTelefone.getText());
   				    c.setEmail(txtEmail.getText());
   				
   				    Connection connection = JdbUtil.getConnection();
   				    ClienteJdbcDAO clienteJdbcDao = new ClienteJdbcDAO(connection);
    				
   				    clienteJdbcDao.salvar(c);
    				
    			}catch(Exception ex) {
    				ex.printStackTrace();
   				}
   			}
   		});
   		
   		paine.add(lblId);
    	lblId.setBounds(20, 245, 70, 25);
    	paine.add(cboId);
    	cboId.setBounds(90, 245, 225, 25);
		cboId.addItem("");
		try {
			Connection conn = JdbUtil.getConnection();
			ClienteJdbcDAO clienteJdbcDAO = new ClienteJdbcDAO(conn);

			List<Cliente> cliente = clienteJdbcDAO.listar();

			for (int i = 0; i < cliente.size(); i++) {
				cboId.addItem(cliente.get(0).getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
   		
   		paine.add(btnDeletar);
   		btnDeletar.setBounds(20, 280, 140, 30);
   		btnDeletar.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			try {
    				int id = Integer.parseInt(cboId.getSelectedItem().toString());
    				
   				Connection connection = JdbUtil.getConnection();
   				ClienteJdbcDAO clienteJdbcDao = new ClienteJdbcDAO(connection);
    				
   				clienteJdbcDao.deletar(id);
    				
    			}catch(Exception ex) {
    				ex.printStackTrace();
   				}	
   			}
   		});
   		
   		paine.add(btnAlterar);
   		btnAlterar.setBounds(175, 280, 140, 30);
   		btnAlterar.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			try {
    				int id = Integer.parseInt(cboId.getSelectedItem().toString());
    			
    			    Cliente c = new Cliente();
    			    c.setNome(txtNome.getText());
    			    if (rdbsexo[0].isSelected()) {
			    	    c.setSexo("F");
			        }
			        else {
			    	    c.setSexo("M");
			        }
   				    c.setEndereco(txtEndereco.getText());
   				    c.setTelefone(txtTelefone.getText());
   				    c.setEmail(txtEmail.getText());
    				
   				    Connection connection = JdbUtil.getConnection();
   				    ClienteJdbcDAO clienteJdbcDao = new ClienteJdbcDAO(connection);
    				
   				    clienteJdbcDao.alterar(c, id);
    				
    			}catch(Exception ex) {
    				ex.printStackTrace();
   				}	
   			}
   		});
    		
    	this.setLayout(null);
    	this.setVisible(true);
    	this.setResizable(false);
   		this.setSize(340, 360);
   		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
