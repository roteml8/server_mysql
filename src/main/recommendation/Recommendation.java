package main.recommendation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import main.data.Merchant;
import main.data.Store;

@Entity
@Table(name = "RECOMMENDATIONS")
public class Recommendation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", updatable = false, nullable = false)
	private Long recommendationId;
	@ManyToOne
	private Merchant toMerchant;
	private String productName;
	@ManyToOne
	private Store atStore;
	
	// toString: Merchant ID: _____ Should sell ______ (product name) on _______ (merchants store)
	
	// method: find recommendation by merchant


	public Recommendation() {
	}

	public Recommendation(Merchant toMerchant, String productName, Store atStore) {
	    setToMerchant(toMerchant);
	    setProductName(productName);
	    setAtStore(atStore);
	}

	public Long getRecommendationId() {
		return recommendationId;
	}

	public void setRecommendationId(Long recommendationId) {
		this.recommendationId = recommendationId;
	}

	public Merchant getToMerchant() {
		return toMerchant;
	}

	public void setToMerchant(Merchant toMerchant) {
		this.toMerchant = toMerchant;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Store getAtStore() {
		return atStore;
	}

	public void setAtStore(Store atStore) {
		this.atStore = atStore;
	}

	@Override
	public String toString() {
		return "Recommendation [recommendationId=" + recommendationId + ", toMerchant=" + toMerchant + ", recommendedProduct="
				+ productName + ", atStore=" + atStore + "]";
	}
	
	
}
