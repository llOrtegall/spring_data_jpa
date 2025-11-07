package llortegall.pizzeria.services;

import llortegall.pizzeria.persistence.entity.PizzaEntity;
import llortegall.pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<PizzaEntity> getAllPizzasAvailable(){
        System.out.println(this.pizzaRepo.countAllByVeganTrue());
        return this.pizzaRepo.findAllByAvailableTrueOrderByPrice();
    }

    public PizzaEntity getPizzaByName(String name){
        return this.pizzaRepo.findAllByAvailableTrueAndNameIgnoreCase(name);
    }

    public List<PizzaEntity> getPizzaByDescription(String desc){
        return this.pizzaRepo.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(desc);
    }

    public List<PizzaEntity> getPizzaByWithOutDescription(String desc){
        return this.pizzaRepo.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(desc);
    }

    public PizzaEntity getPizzaById(int idPizza){
        return this.pizzaRepo.findById(idPizza).orElse(null);
    }

    public PizzaEntity createNewPizza(PizzaEntity newPizza) {
        return this.pizzaRepo.save(newPizza);
    }

    public void deletePizza(int idPizza){
        this.pizzaRepo.deleteById(idPizza);
    }

    public boolean exists(int idPizza){
        return this.pizzaRepo.existsById(idPizza);
    }
}
