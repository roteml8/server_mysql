package main.dao.rdb;

import org.springframework.data.repository.CrudRepository;

import main.data.Supplier;


public interface SupplierRepository extends CrudRepository<Supplier,String> {

}
