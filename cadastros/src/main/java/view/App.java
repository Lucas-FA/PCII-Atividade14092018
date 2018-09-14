package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class App extends JFrame {
    JButton btnCliente = new JButton("Cliente");
    JButton btnHardware = new JButton("Hardware");
    JButton btnVenda = new JButton("Venda");
    JButton btnVendaItens = new JButton("Itens da Venda");

    public App() {
    	super("Cadastro");
    		
    	Container paine = this.getContentPane();
   		
   		paine.add(btnCliente);
   		btnCliente.setBounds(20, 20, 130, 30);
   		btnCliente.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			AppCliente cliente = new AppCliente();
    			cliente.setVisible(true);
   			}
   		});
   		
   		paine.add(btnHardware);
   		btnHardware.setBounds(160, 20, 130, 30);
   		btnHardware.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			AppHardware hardware = new AppHardware();
    			hardware.setVisible(true);
   			}
   		});
   		
   		paine.add(btnVenda);
   		btnVenda.setBounds(20, 60, 130, 30);
   		btnVenda.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			AppVenda venda = new AppVenda();
    			venda.setVisible(true);	
   			}
   		});
   		
   		paine.add(btnVendaItens);
   		btnVendaItens.setBounds(160, 60, 130, 30);
   		btnVendaItens.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			AppVendaItens itens = new AppVendaItens();
    			itens.setVisible(true);
   			}
   		});
    		
    	this.setLayout(null);
    	this.setVisible(true);
    	this.setResizable(false);
   		this.setSize(320, 140);
   		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
   	public static void main(String[] args){
       	App app = new App();
    }
}
