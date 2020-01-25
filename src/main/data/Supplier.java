package main.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SUPPLIERS")
public class Supplier {
	
	private String supplierName;
	@Id
	private String supplierId;
	private String supplierPassword;
	@OneToMany(mappedBy="supplier")
	private List<Product> products = new ArrayList<>();
	
	
	public Supplier(String supplierName, String supplierId, String supplierPassword, List<Product> products) {
		super();
		this.supplierName = supplierName;
		this.supplierId = supplierId;
		this.supplierPassword = supplierPassword;
		this.products = products;
	}
	public Supplier() {
		super();
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierPassword() {
		return supplierPassword;
	}
	public void setSupplierPassword(String supplierPassword) {
		this.supplierPassword = supplierPassword;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
		
	

}
