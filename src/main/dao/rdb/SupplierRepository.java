package main.dao.rdb;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import main.data.Supplier;


public interface SupplierRepository extends CrudRepository<Supplier,Long> {
	
	public Optional<Supplier> findBySupplierId (Long id);
	
	public ArrayList<Supplier> findBySupplierName (String name);



}
