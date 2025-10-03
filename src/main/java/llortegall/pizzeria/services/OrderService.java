package llortegall.pizzeria.services;

import llortegall.pizzeria.persistence.entity.OrderEntity;
import llortegall.pizzeria.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepo;

    @Autowired
    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<OrderEntity> getAllOrders(){
        return this.orderRepo.findAll();
    }
}
