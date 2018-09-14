package model;

public class VendaItens {
	private int idVenda;
	private int idHardware;
	private int qtde;
	private float totalItem;
	private int id;
	
	public int getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}
	
	public int getIdHardware() {
		return idHardware;
	}
	public void setIdHardware(int idHardware) {
		this.idHardware = idHardware;
	}
	
	public int getQtde() {
		return qtde;
	}
	public void setQtde(int qtde) {
		this.qtde = qtde;
	}
	
	public float getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(float totalItem) {
		this.totalItem = totalItem;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
