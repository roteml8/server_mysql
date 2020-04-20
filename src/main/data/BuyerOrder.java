package main.data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class BuyerOrder implements Comparable<BuyerOrder> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", updatable = false, nullable = false)
	private Long orderId;
	@ManyToOne
	private StoreProduct product;
	@ManyToOne
	private Store store;
	private int quantity;
	private java.time.LocalDate date;
	private double buyerAge; 
	

	public double getBuyerAge() {
		return buyerAge;
	}
	public void setBuyerAge(double buyerAge) {
		this.buyerAge = buyerAge;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}

	public StoreProduct getProduct() {
		return product;
	}
	public void setProduct(StoreProduct product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public BuyerOrder() {
		super();
	}
	public java.time.LocalDate getDate() {
		return date;
	}
	public void setDate(java.time.LocalDate date) {
		this.date = date;
	}
	@Override
	public int compareTo(BuyerOrder o) {
		return this.date.compareTo(o.getDate());

	}
	
	
	
	

	

}
