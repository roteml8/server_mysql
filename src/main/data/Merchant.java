package main.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "MERCHANTS")
public class Merchant {
	
	private String merchantName;
	@Id
	private String merchantId;
	private String merchantPassword;
	@OneToMany(mappedBy="merchant")
	private List<Store> stores = new ArrayList<>();
	
	
	public Merchant(String name, String ID, String password, List<Store> stores) {
		setMerchantName(name);
		setMerchantId(ID);
		setMerchantPassword(password);
		this.stores = stores;

	}
	

	public Merchant() {
	}


	public List<Store> getStores() {
		return stores;
	}



	public void setStores(List<Store> stores) {
		this.stores = stores;
	}



	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	
	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantPassword() {
		return merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}
	

}
