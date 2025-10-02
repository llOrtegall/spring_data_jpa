package llortegall.pizzeria.services;

import llortegall.pizzeria.persistence.entity.PizzaEntity;
import llortegall.pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepo;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepo) {
        this.pizzaRepo = pizzaRepo;
    }

    public List<PizzaEntity> getAllPizzas(){
        return this.pizzaRepo.findAll();
    }

    public PizzaEntity getPizzaById(int idPizza){
        return this.pizzaRepo.findById(idPizza).orElse(null);
    }

    public PizzaEntity createNewPizza(PizzaEntity newPizza) {
        return this.pizzaRepo.save(newPizza);
    }

    public boolean exists(int idPizza){
        return this.pizzaRepo.existsById(idPizza);
    }
}
