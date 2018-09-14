package model;

import java.util.Date;

public class Venda {
	private int idCliente;
	private String data;
	private float vlrtotal;
	private float desconto;
	private int id;
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public float getVlrtotal() {
		return vlrtotal;
	}
	public void setVlrtotal(float vlrtotal) {
		this.vlrtotal = vlrtotal;
	}
	
	public float getDesconto() {
		return desconto;
	}
	public void setDesconto(float desconto) {
		this.desconto = desconto;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
