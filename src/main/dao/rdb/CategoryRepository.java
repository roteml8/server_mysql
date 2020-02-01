package main.dao.rdb;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import main.data.ProductCategory;

@Repository
public interface CategoryRepository extends CrudRepository<ProductCategory,String> {

}
