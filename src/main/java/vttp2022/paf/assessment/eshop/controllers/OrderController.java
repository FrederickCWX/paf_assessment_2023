package vttp2022.paf.assessment.eshop.controllers;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.xdevapi.JsonArray;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonString;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

	@Autowired
	private CustomerRepository customerRepo;

	@PostMapping(path = "order")
	public ResponseEntity<String> getOrder(@RequestBody Map<String, Object> payload) {

		//Task 3b UUID
		String orderId = UUID.randomUUID().toString().substring(0,8);
		//Task 3b Date
		Date date = new Date();


		String name = (String) payload.get("name");
		List<Map<String, Object>> liMap = (List<Map<String, Object>>) payload.get("lineItems");

		List<Object> lineItems = (List<Object>) payload.get("lineItems");
		List<LineItem> itemList = new LinkedList<>();

		for(Map<String,Object> itemMap: liMap) {
			LineItem li = new LineItem();
			String item = (String) itemMap.get("item");
			li.setItem(item);
			Integer quantity = (Integer) itemMap.get("quantity");
			li.setQuantity(quantity);
			itemList.add(li);
		}

		Optional<Customer> customer = customerRepo.findCustomerByName(name);

		JsonObject error = Json.createObjectBuilder()
														.add("error", "Customer "+name+" not found")
														.build();

		if(customer.equals(Optional.empty()))
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.contentType(MediaType.APPLICATION_JSON)
					.body(error.toString());

		
		
		return ResponseEntity
					.status(HttpStatus.OK)
					.contentType(MediaType.APPLICATION_JSON)
					.body("found");
	}

	//TODO: Task 3

}
