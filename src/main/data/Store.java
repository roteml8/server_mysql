package main.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import main.data.Merchant;
import main.data.Platform;

@Entity
@Table(name = "STORES")
public class Store {
	
	
	@ManyToOne
	private Platform platform;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", updatable = false, nullable = false)
	private Long storeId;
	@ManyToOne
	private Merchant merchant;

	private String storeName;
	
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Store() {
		super();
	}
	public Platform getPlatform() {
		return platform;
	}
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	@Override
	public String toString() {
		return "Store [platform=" + platform + ", storeId=" + storeId + ", merchant=" + merchant + ", storeName="
				+ storeName + "]";
	}
	
	
	
}
	
	