package llortegall.pizzeria.web.controller;

import llortegall.pizzeria.persistence.entity.PizzaEntity;
import llortegall.pizzeria.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll(){
        return ResponseEntity.ok(this.pizzaService.getAllPizzas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable int id){
        return ResponseEntity.ok(this.pizzaService.getPizzaById(id));
    }
}
