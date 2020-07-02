package main.layout;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import main.data.BuyerOrder;
import main.infra.OrderService;

@Controller 
@RequestMapping(path="/database/orders") 
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	  @GetMapping(path="/all")
	  public @ResponseBody Iterable<BuyerOrder> getAllOrders() {
	  
	    ArrayList<BuyerOrder> allOrders = (ArrayList<BuyerOrder>) orderService.getAll();
	    for (BuyerOrder order:allOrders)
	    	order.setDate(order.getDate().plusDays(1));
	    return allOrders;
	    
	  }
	  
	@PostMapping(path="/add") 
		 public @ResponseBody Long addNewOrder (@RequestParam Long productId,@RequestParam int quantity,
				 @RequestParam int month, @RequestParam int year, @RequestParam int day, @RequestParam double
				 buyerAge) {

				return this.orderService.addNewOrder(productId, quantity, month, year, day, buyerAge);
		  }
	

}
