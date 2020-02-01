package main.dao.rdb;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import main.data.Supplier;

@Repository

public interface SupplierRepository extends CrudRepository<Supplier,Long> {
		
	public ArrayList<Supplier> findBySupplierName (String name);



}
