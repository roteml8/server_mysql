package main.dao.rdb;

import org.springframework.data.repository.CrudRepository;

import main.data.ProductCategory;

public interface CategoryRepository extends CrudRepository<ProductCategory,String> {

}
