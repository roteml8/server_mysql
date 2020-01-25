package main.layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import main.dao.rdb.MerchantRepository;
import main.data.Merchant;


@Controller 
@RequestMapping(path="/database/merchants") 
public class MerchantController {
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@PostMapping(path="/add") // Map ONLY POST Requests
	  public @ResponseBody String addNewMerchant (@RequestParam String id, @RequestParam String name
	      , @RequestParam String password) {

	    Merchant m = new Merchant();
	    m.setMerchantId(id);
	    m.setMerchantName(name);
	    m.setMerchantPassword(password);
	    merchantRepository.save(m);
	    return "Saved merchant successfully.";
	  }

	  @GetMapping(path="/all")
	  public @ResponseBody Iterable<Merchant> getAllMerchants() {
	    // This returns a JSON or XML with the users
	    return merchantRepository.findAll();
	  }

}
