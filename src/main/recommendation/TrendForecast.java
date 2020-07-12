package main.recommendation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import main.data.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import main.dao.rdb.BuyerOrderRepository;
import main.dao.rdb.StoreProductRepository;
import main.dao.rdb.TrendRepository;
import main.data.BuyerOrder;
import main.data.StoreProduct;
import main.data.Trend;
import main.infra.OrderService;

@Service
public class TrendForecast {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private TrendRepository trendRepository;
	@Autowired
	private StoreProductRepository productRepository;
	private final int RAISE = 40; 
	
	@Autowired
	public TrendForecast(OrderService orderService)
	{
		this.orderService = orderService;
	}
	
	public Iterable<Trend> getAll(){
		return trendRepository.findAll();
	}

	// return trends of given products not on the given platform
	public List<Trend> findTrendsByProductsNotInPlatform(List<StoreProduct> products, Platform platform){
		
		List<String> productNames = products.stream().map(StoreProduct::getName).collect(Collectors.toList());
		return trendRepository.findAllByProductNameInAndPlatform_PlatformNameIsNotLikeOrderByForecastDateDesc(
				productNames, platform.getPlatformName());
	}

	// return true if a given product is trending on a given platform
	public Boolean isProductTrendingAtPlatform(String productName, Platform platform){
		return !trendRepository.findAllByProductNameAndPlatform_PlatformNameIsLike(productName,
				platform.getPlatformName()).isEmpty();
	}
	
	public void setTrendingProducts()
	{
		Iterable<StoreProduct> products = productRepository.findAll();
		for (StoreProduct p: products)
		{	if (isTrending(p))
			{
				Trend newTrend = new Trend();
				newTrend.setProductName(p.getName());
				newTrend.setMerchant(p.getStore().getMerchant());
				newTrend.setForecastDate(LocalDate.now());
				newTrend.setPlatform(p.getStore().getPlatform());
				trendRepository.save(newTrend);
			}
		}
		
		}
	
	public boolean isTrending (StoreProduct p)
	{
		ArrayList<BuyerOrder> ordersOfProduct = (ArrayList<BuyerOrder>) orderService.getByProduct(p);
		ordersOfProduct.sort(null);
		int week1counter = 0;
		int week2counter = 0;
		int week3counter = 0;

		LocalDate today = LocalDate.now();
		LocalDate week3 = today.minusDays(7);
		LocalDate week2 = today.minusDays(14);
		LocalDate week1 = today.minusDays(21);

		if (ordersOfProduct.isEmpty())
			return false;
		for (BuyerOrder o: ordersOfProduct)
		{
			LocalDate orderDate = o.getDate().plusDays(1);
			System.out.println(orderDate);
			int orderAmount = o.getQuantity();

			if ((orderDate.isAfter(week1) && orderDate.isBefore(week2)) || orderDate.equals(week1))
			{
				week1counter += orderAmount;
			}
			if ((orderDate.isAfter(week2) && orderDate.isBefore(week3)) || orderDate.equals(week2))
			{
				week2counter += orderAmount;
			}
			if ((orderDate.isAfter(week3) && orderDate.isBefore(today)) || orderDate.equals(week3))
			{
				week3counter += orderAmount;
			}
		}
			System.out.println(week1counter);
			System.out.println(week2counter);
			System.out.println(week3counter);

			if (((week2counter-week1counter)/(double)week1counter)*100 < RAISE)
				return false;
			
			if (((week3counter-week2counter)/(double)week2counter)*100 < RAISE)
				return false;			
			return true;
	}

}
