package main.dao.rdb;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import main.data.Product;
import main.data.ProductCategory;
import main.data.Supplier;


@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
	
	public ArrayList<Product> findByCategory (ProductCategory c);
	public ArrayList<Product> findBySupplier (Supplier s);
	public ArrayList<Product> findByProductNameIgnoreCaseContaining (String name);
	public ArrayList<Product> findByProductPriceLessThan (double price);
	public ArrayList<Product> findByQuantityGreaterThan (int quantity);





}
