package llortegall.pizzeria.persistence.projection;

import java.time.LocalDateTime;

public interface OrderSummary {
    Integer getIdOrder();
    String getCustomerName();
    LocalDateTime getOrderDay();
    Double getOrderTotal();
    String getPizzaNames();
}
