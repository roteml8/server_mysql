package main.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIES")
public class ProductCategory {
	
	@Id
	private String categoryName;

	public ProductCategory(String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public ProductCategory() {
		super();
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	
	//COSMETICS, ELECTRONICS, FASHION, JEWELRY, PETS, 
	//CHILDREN, HOME, LEISURE 

}
