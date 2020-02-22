package main.infra;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.dao.rdb.CategoryRepository;
import main.dao.rdb.ProductRepository;
import main.dao.rdb.SupplierRepository;
import main.data.Product;
import main.data.ProductCategory;
import main.data.Supplier;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private SupplierRepository supplierRepository;
	
	public Iterable<Product> getAll()
	{
		return productRepository.findAll();
	}
	
	public Iterable<Product> getByCategory(String category)
	{
		Optional<ProductCategory> c = this.categoryRepository.findById(category);
		if (!c.isPresent()) {
			return new ArrayList<Product>();
		}
		else {
			ProductCategory theCategory = c.get();
		    return productRepository.findByCategory(theCategory);
		}
	}
		
	public Iterable<Product> getBySupplier(Long supplierId) throws Exception
	{
		Optional<Supplier> s = this.supplierRepository.findById(supplierId);
		if (!s.isPresent()) {
			throw new Exception("Supplier does not exist in the system");
		}
		else {
			Supplier theSupplier = s.get();
			return this.productRepository.findBySupplier(theSupplier);
		}
	}
	
	public Iterable<Product> getByName(String name)
	{
		  return this.productRepository.findByProductNameIgnoreCaseContaining(name);
	}
	
	public Iterable<Product> getByPrice(double price)
	{
		 return this.productRepository.findByProductPriceLessThan(price);	  
	}
	
	public Iterable<Product> getByQuantity(int quantity)
	{
		  return this.productRepository.findByQuantityGreaterThan(quantity); 

	}


}
