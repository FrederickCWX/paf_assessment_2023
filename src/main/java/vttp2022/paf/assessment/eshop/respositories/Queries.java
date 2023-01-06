package vttp2022.paf.assessment.eshop.respositories;

public class Queries {

  public static final String SQL_RETRIEVE_CUSTOMER_BY_NAME = "select * from customers where name = ?";

  public static final String SQL_RETRIEVE_ORDER_BY_NAME = "select * from orders where name = ?";

  public static final String SQL_RETRIEVE_ORDER_STATUS = "select * from order_status where order_id = ";

  public static final String SQL_INSERT_LINE_ITEM = "insert into line_items(order_id, item, quantity) values (?, ?, ?)";
  public static final String SQL_INSERT_ORDER = "insert into orders(order_id, name, order_date) values (?, ?, ?)";
  
}
