package main.data;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRENDS")
public class Trend {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", updatable = false, nullable = false)
	private Long trendId;
	private String productName;
	@ManyToOne
	private Merchant merchant;
	@ManyToOne
	private Platform platform;
	private LocalDate forecastDate;
	public Long getTrendId() {
		return trendId;
	}
	public void setTrendId(Long trendId) {
		this.trendId = trendId;
	}

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	public LocalDate getForecastDate() {
		return forecastDate;
	}
	public void setForecastDate(LocalDate forecastDate) {
		this.forecastDate = forecastDate;
	}
	public Trend() {
		super();
	}
	public Platform getPlatform() {
		return platform;
	}
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	@Override
	public String toString() {
		return "Trend [trendId=" + trendId + ", productName=" + productName + ", selling merchant=" + merchant + ", platform="
				+ platform + ", forecastDate=" + forecastDate + "]";
	}
	
	
	

}
