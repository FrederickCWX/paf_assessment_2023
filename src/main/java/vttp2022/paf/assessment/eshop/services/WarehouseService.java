package vttp2022.paf.assessment.eshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatus;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;

@Service
public class WarehouseService {

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private OrderRepository orderRepo; 

	// You cannot change the method's signature
	// You may add one or more checked exceptions
	public OrderStatus dispatch(Order order) {

		String orderId = order.getOrderId();
		String url = "http://paf.chuklee.com/dispatch/"+orderId;
		List<LineItem> liList = order.getLineItems();

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> resp = null;

		JsonArrayBuilder jArrayBuilder = Json.createArrayBuilder();
			for(LineItem li: liList)
				jArrayBuilder.add(
							Json.createObjectBuilder()
							.add("item", li.getItem())
							.add("quantity", li.getQuantity())
							.build()
				);
			JsonArray ja = jArrayBuilder.build();

			JsonObject response = Json.createObjectBuilder()
																.add("orderId", orderId)
																.add("name", order.getName())
																.add("address", order.getAddress())
																.add("email", order.getEmail())
																.add("lineItems", ja)
																.add("createdBy", "Chan Weixun Frederick")
																.build();

		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity request = new HttpEntity(httpHeaders);
            resp = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    String.class,
                    1);


			
		} catch (Exception e) {
			// TODO: handle exception
		}

		// TODO: Task 4
		return null;
	}
}
