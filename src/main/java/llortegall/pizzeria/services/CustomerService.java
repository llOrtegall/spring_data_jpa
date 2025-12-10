package llortegall.pizzeria.services;

import llortegall.pizzeria.persistence.entity.CustomerEntity;
import llortegall.pizzeria.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity findByPhone(String phoneNumber){
        return this.customerRepository.findByPhoneNumber(phoneNumber);
    }

}
