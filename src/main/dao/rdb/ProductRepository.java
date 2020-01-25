package main.dao.rdb;

import org.springframework.data.repository.CrudRepository;

import main.data.Product;

public interface ProductRepository extends CrudRepository<Product,String> {

}
