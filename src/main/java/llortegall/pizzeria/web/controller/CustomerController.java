package llortegall.pizzeria.web.controller;

import llortegall.pizzeria.persistence.entity.CustomerEntity;
import llortegall.pizzeria.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/phone/{phoneNumer}")
    public ResponseEntity<CustomerEntity> getByPhone(@PathVariable String phoneNumer){
        CustomerEntity customer = this.customerService.findByPhone(phoneNumer);
        return ResponseEntity.ok(customer);
    }
}
