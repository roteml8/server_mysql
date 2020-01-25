package main.layout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import main.dao.rdb.ProductRepository;
import main.dao.rdb.SupplierRepository;

@Controller 
@RequestMapping(path="/database/products") 
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private SupplierRepository supplierRepository;
	

}
