package main.dao.rdb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.data.Merchant;

@Repository
public interface MerchantRepository extends CrudRepository<Merchant,Long> {
	
	public ArrayList<Merchant> findByMerchantName (String name);
	public List<Merchant> findAll();



}
