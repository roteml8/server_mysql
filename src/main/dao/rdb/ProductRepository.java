package main.dao.rdb;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import main.data.Product;
import main.data.ProductCategory;

@Transactional
public interface ProductRepository extends CrudRepository<Product,String> {
	
	public ArrayList<Product> findByCategory (ProductCategory c);

}
