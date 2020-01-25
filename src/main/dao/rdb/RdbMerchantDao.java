package main.dao.rdb;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import main.dao.MerchantDao;
import main.data.Merchant;


@Repository
public class RdbMerchantDao implements MerchantDao<String> {
	
	private MerchantRepository crud; 
	
	@Autowired
	public RdbMerchantDao (MerchantRepository crud)
	{
		this.crud = crud; 
	}

	@Override
	@Transactional
	public Merchant create(Merchant m) {
		return this.crud.save(m);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Merchant> readById(String merchantKey) {
		return this.crud.findById(merchantKey);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Merchant> readAll() {
		List<Merchant> rv = new ArrayList<>();
		 this.crud.findAll().forEach(rv::add);
		 return rv;
	}

	@Override
	@Transactional
	public void update(String merchantKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void deleteByKey(String merchantKey) {
		this.crud.deleteById(merchantKey);
		
	}


	@Override
	@Transactional
	public void deleteAll() {
		this.crud.deleteAll();
		
	}

}
