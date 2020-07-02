package main.infra;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import main.dao.rdb.CategoryRepository;
import main.dao.rdb.ProductRepository;
import main.dao.rdb.SupplierRepository;
import main.data.Product;
import main.data.ProductCategory;
import main.data.Supplier;

@Service
public class SupplierService {
	
	@Autowired
	private SupplierRepository supplierRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Long addNewSupplier (String supplierName)
	{
	    Supplier s = new Supplier();
	    s.setSupplierName(supplierName);
	    Supplier rv = supplierRepository.save(s);
	    return rv.getSupplierId();
	}
	
	public Long addToCatalog( Long supplierId, String name, String category, double price, int quantity)
	{

		Optional<Supplier> s = this.supplierRepository.findById(supplierId);
		if (s.isPresent()) {
			Product p = new Product();
			Optional<ProductCategory> c = this.categoryRepository.findById(category);
			if (!c.isPresent()) {
				ProductCategory newCategory = new ProductCategory(category);
				this.categoryRepository.save(newCategory);
				p.setCategory(newCategory);
			}
			else
			{
				p.setCategory(c.get());
			}
			Supplier theSupplier = s.get();
			p.setSupplier(theSupplier);
			p.setProductName(name);
			p.setProductPrice(price);
			p.setQuantity(quantity);
		    Product rv = productRepository.save(p);
		    return rv.getProductId();

		}
		else
			return (long) 0;
	}
	
	public Iterable<Supplier> getAll()
	{
		return this.supplierRepository.findAll();
	}

}
