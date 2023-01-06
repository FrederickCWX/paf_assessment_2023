package vttp2022.paf.assessment.eshop.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatus;

@Service
public class WarehouseService {

	private static String URL = "http://paf.chuklee.com/dispatch/";

	// You cannot change the method's signature
	// You may add one or more checked exceptions
	public OrderStatus dispatch(Order order) {

		RestTemplate restTemplate = new RestTemplate();

		// TODO: Task 4
		return null;
	}
}
