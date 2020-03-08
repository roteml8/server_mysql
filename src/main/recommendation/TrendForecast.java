package main.recommendation;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import main.dao.rdb.BuyerOrderRepository;
import main.data.BuyerOrder;
import main.data.StoreProduct;

public class TrendForecast {
	
	@Autowired
	private BuyerOrderRepository orderRepository;
	private final int RAISE = 40; 
	
	
	public ArrayList<StoreProduct> getTrendingProducts()
	{
		ArrayList<StoreProduct> trending = new ArrayList<>();
		Iterable<BuyerOrder> orders = orderRepository.findAll();
		for (BuyerOrder o: orders)
		{
			StoreProduct theProduct = o.getProduct();
			if (this.isTrending(theProduct))
				trending.add(theProduct);
		}
		
		
		return trending;
	}
	
	public boolean isTrending (StoreProduct p)
	{
		ArrayList<BuyerOrder> ordersOfProduct = orderRepository.findByProduct(p);
		ordersOfProduct.sort(null);
		int week1counter = 0;
		int week2counter = 0;
		int week3counter = 0;

		LocalDate today = LocalDate.now();
		LocalDate week3 = today.minusDays(7);
		LocalDate week2 = today.minusDays(14);
		LocalDate week1 = today.minusDays(21);


		for (BuyerOrder o: ordersOfProduct)
		{
			LocalDate orderDate = o.getDate();
			int orderAmount = o.getQuantity();

			if (orderDate.isAfter(week1) && orderDate.isBefore(week2) || orderDate.isEqual(week1))
			{
				week1counter += orderAmount;
			}
			if (orderDate.isAfter(week2) && orderDate.isBefore(week3) || orderDate.isEqual(week2))
			{
				week2counter += orderAmount;
			}
			if (orderDate.isAfter(week3) && orderDate.isBefore(today) || orderDate.isEqual(week3))
			{
				week3counter += orderAmount;
			}
			
			if (week2counter-week1counter/week1counter*100 < RAISE)
				return false;
			
			if (week3counter-week2counter/week2counter*100 < RAISE)
				return false;			
			
		}
		
		return true;
	}

}
