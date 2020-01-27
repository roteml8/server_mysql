package main.dao.rdb;

import org.springframework.data.repository.CrudRepository;

import main.data.StoreProduct;

public interface StoreProductRepository extends CrudRepository<StoreProduct, Long> {

}
