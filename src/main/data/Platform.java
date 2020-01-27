package main.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PLATFORMS")
public class Platform{
	
	@Id
	private String platformName;

	public Platform() {
		super();
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	} 
	
	

}
