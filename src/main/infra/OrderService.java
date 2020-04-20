package main.infra;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.dao.rdb.BuyerOrderRepository;
import main.dao.rdb.StoreProductRepository;
import main.data.BuyerOrder;
import main.data.Merchant;
import main.data.Product;
import main.data.Store;
import main.data.StoreProduct;

@Service
public class OrderService {
	
	@Autowired
	private BuyerOrderRepository orderRepository;
	@Autowired
	private StoreProductRepository storeRepository;
	
	public Iterable<BuyerOrder> getAll()
	{
		return orderRepository.findAll();
	}
	
	public Iterable<BuyerOrder> getByProduct(StoreProduct p)
	{
		return orderRepository.findByProduct(p);
	}
	
	public String addNewOrder (Long productId, int quantity, int month, int year, int day, double buyerAge)
	{
	   BuyerOrder newOrder = new BuyerOrder();
	   Optional<StoreProduct> optional = storeRepository.findById(productId);
	   if (!optional.isPresent())
		   return "Product of order does not exist!";
	   else
	   {
		   StoreProduct theProduct = optional.get();
		   Store theStore = theProduct.getStore();
		   newOrder.setStore(theStore);
		   newOrder.setProduct(theProduct);
		   newOrder.setQuantity(quantity);
		   newOrder.setBuyerAge(buyerAge);
		   LocalDate date = LocalDate.of(year, month, day);
		   newOrder.setDate(date);
		   BuyerOrder orderRV = orderRepository.save(newOrder);
		   return "Order added successufly to DB. Order ID is: "+orderRV.getOrderId();
	   }
	   
	}
	

}
