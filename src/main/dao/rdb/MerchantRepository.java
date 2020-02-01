package main.dao.rdb;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.data.Merchant;
import main.data.Product;

@Repository
public interface MerchantRepository extends CrudRepository<Merchant,Long> {
	
	public ArrayList<Merchant> findByMerchantName (String name);


}
