package main.dao.rdb;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.data.Store;
import main.data.StoreProduct;
@Repository

public interface StoreProductRepository extends CrudRepository<StoreProduct, Long> {
	
	public ArrayList<StoreProduct> findByName(String name);
	public StoreProduct findByNameAndStore(String name, Store store);

}
