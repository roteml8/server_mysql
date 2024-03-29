package main.data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "PRODUCTS")
public class Product {

	private String productName;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", updatable = false, nullable = false)
	private Long productId;
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
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
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
//	public Product(String productName, String productId, double productPrice, int quantity, Supplier supplier,
//			ProductCategory category) {
//		super();
//		this.productName = productName;
//		this.productId = productId;
//		this.productPrice = productPrice;
//		this.quantity = quantity;
//		this.supplier = supplier;
//		this.category = category;
//	}
	public Product() {
		super();
	}
	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productId=" + productId + ", productPrice=" + productPrice
				+ ", quantity=" + quantity + ", supplier=" + supplier + ", category=" + category + "]";
	}
	
	
	


	
}
