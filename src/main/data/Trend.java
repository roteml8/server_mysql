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
	@ManyToOne
	private StoreProduct product;
	private LocalDate forecastDate;
	public Long getTrendId() {
		return trendId;
	}
	public void setTrendId(Long trendId) {
		this.trendId = trendId;
	}
	public StoreProduct getProduct() {
		return product;
	}
	public void setProduct(StoreProduct product) {
		this.product = product;
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
	
	

}
