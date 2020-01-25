package main.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import main.data.Merchant;
import main.data.Platform;

@Entity
@Table(name = "STORES")
public class Store {
	
	
	private Platform platform;
	@Id
	private String storeId;
	private String username;
	private String password; 
	@ManyToOne
	private Merchant merchant;
	
	public Store(Platform platform, String storeId, String username, String password, Merchant merchant) {
		super();
		this.platform = platform;
		this.storeId = storeId;
		this.username = username;
		this.password = password;
		this.merchant = merchant;
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
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	
	
	
}
	
	