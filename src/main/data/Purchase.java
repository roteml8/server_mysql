package main.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PURCHASES")
public class Purchase {
	
	@Id
	private String purchaseId;
	@ManyToOne
	private Merchant merchant;
	@ManyToOne
	private Supplier supplier;
	@ManyToOne
	private Product product;
	private int amount; 

	public Purchase() {
		super();
	}
	
	public Purchase(String purchaseId,Merchant merchant, Supplier supplier, Product product, int amount) {
		super();
		this.purchaseId = purchaseId;
		this.merchant = merchant;
		this.supplier = supplier;
		this.product = product;
		this.amount = amount;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	
	

}
