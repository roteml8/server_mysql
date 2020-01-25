package main.data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "PRODUCTS")
public class Product {

	private String productName;
	@Id
	private String productId;
	private double productPrice;
	private int quantity;
	@ManyToOne
	private Supplier supplier;
	@ManyToOne
	private ProductCategory category;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public ProductCategory getCategory() {
		return category;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	public Product(String productName, String productId, double productPrice, int quantity, Supplier supplier,
			ProductCategory category) {
		super();
		this.productName = productName;
		this.productId = productId;
		this.productPrice = productPrice;
		this.quantity = quantity;
		this.supplier = supplier;
		this.category = category;
	}
	public Product() {
		super();
	}
	


	
}
