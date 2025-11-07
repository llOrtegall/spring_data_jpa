package llortegall.pizzeria.services;

import llortegall.pizzeria.persistence.entity.PizzaEntity;
import llortegall.pizzeria.persistence.repository.PizzaPagSortRepository;
import llortegall.pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepo;
    private final PizzaPagSortRepository pizzaBySort;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepo, PizzaPagSortRepository pizzaBySort) {
        this.pizzaRepo = pizzaRepo;
        this.pizzaBySort = pizzaBySort;
    }

    public Page<PizzaEntity> getAllPizzas(int page, int elem){
        Pageable pageRequest = PageRequest.of(page, elem);
        return this.pizzaBySort.findAll(pageRequest);
    }

    public List<PizzaEntity> getAllPizzasAvailable(){
        System.out.println(this.pizzaRepo.countAllByVeganTrue());
        return this.pizzaRepo.findAllByAvailableTrueOrderByPrice();
    }

    public PizzaEntity getPizzaByName(String name){
        return this.pizzaRepo.findFirstByAvailableTrueAndNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("La pizza no existe"));
    }

    public List<PizzaEntity> getCheapestPizzas(double price){
        return this.pizzaRepo.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
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
