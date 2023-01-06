package vttp2022.paf.assessment.eshop.respositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderStatus;

import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

@Repository
public class OrderRepository {
	// TODO: Task 3

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Integer createLineItems(String orderId, LineItem li) {
		return jdbcTemplate.update(SQL_INSERT_LINE_ITEM, 
					orderId, 
					li.getItem(), 
					li.getQuantity());
	}

	public Integer createOrder(Order order) {
		List<LineItem> liList = order.getLineItems();
		for(LineItem li: liList)
			createLineItems(order.getOrderId(), li);

		return jdbcTemplate.update(SQL_INSERT_ORDER, 
					order.getOrderId(),
					order.getName(),
					order.getOrderDate());
	}

	public List<Order> getOrdersByName(String name) {
		final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_RETRIEVE_ORDER_BY_NAME, name);
		final List<Order> orderList = new LinkedList<>();

		while(rs.next()) {
			Order order = new Order();
			order.setName(rs.getString("name"));
			order.setOrderId(rs.getString("order_id"));
			order.setStatus(getOrderStatus(order.getOrderId()));
			orderList.add(order);
		}

		if(orderList.size()==0)
			return null;

		return orderList;
	}

	public String getOrderStatus(String orderId) {
		final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_RETRIEVE_ORDER_STATUS, orderId);
		String status = null;

		while(rs.next())
			status = rs.getString("status");

		return status;
	}
}
