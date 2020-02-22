package main.data;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ORDERS")
public class BuyerOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", updatable = false, nullable = false)
	private Long orderId;
	@ManyToOne
	private StoreProduct product;
	private int quantity;
	@Basic
	@Temporal(TemporalType.DATE)
	private java.util.Date date;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public BuyerOrder(Long orderId, StoreProduct product, int quantity, Date date) {
		super();
		this.orderId = orderId;
		this.product = product;
		this.quantity = quantity;
		this.date = date;
	}
	
	

	

}
