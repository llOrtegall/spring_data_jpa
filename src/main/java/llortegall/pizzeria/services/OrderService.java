package llortegall.pizzeria.services;

import llortegall.pizzeria.persistence.entity.OrderEntity;
import llortegall.pizzeria.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SITE = "S";

    @Autowired
    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<OrderEntity> getAllOrders(){
        return this.orderRepo.findAll();
    }

    public List<OrderEntity> getTodayOrders(){
        LocalDateTime today = LocalDate.now().atTime(0, 0);
        return this.orderRepo.findAllByDateAfter(today);
    }

    public List<OrderEntity> getOutSideOrders(){
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return this.orderRepo.findAllByMethodIn(methods);
    }

    public List<OrderEntity> getCustomerOrdersById(String idCustomer){
        return this.orderRepo.findCustomerOrders(idCustomer);
    }
}
