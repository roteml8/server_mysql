package main.recommendation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.data.Trend;

@Controller 
@RequestMapping(path="/database/trends") 
public class TrendController {
	
	@Autowired
	private TrendForecast trendForecast;
	
	  @GetMapping(path="/all")
	  public @ResponseBody Iterable<Trend> getAllTrends() {
	    return this.trendForecast.getAll();
	  }
	  
	  @GetMapping(path="/update")
	  public @ResponseBody Iterable<Trend> update() {
	     this.trendForecast.setTrendingProducts();
	     return this.trendForecast.getAll();
	  }
	

}
