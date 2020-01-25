package main.dao;

import java.util.List;
import java.util.Optional;

import main.data.Merchant;

public interface MerchantDao<String> {
	
	public Merchant create(Merchant m);
	public Optional<Merchant> readById(String merchantKey);
	public List<Merchant> readAll();
	public void update (String merchantKey);
	public void deleteByKey(String merchantKey);
	public void deleteAll();

}
