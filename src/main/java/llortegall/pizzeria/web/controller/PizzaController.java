package llortegall.pizzeria.web.controller;

import llortegall.pizzeria.persistence.entity.PizzaEntity;
import llortegall.pizzeria.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>> getAllAvailable(){
        return ResponseEntity.ok(this.pizzaService.getAllPizzasAvailable());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable String name){
        return ResponseEntity.ok(this.pizzaService.getPizzaByName(name));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapest(@PathVariable double price){
        return ResponseEntity.ok(this.pizzaService.getCheapestPizzas(price));
    }

    @GetMapping("/with/{desc}")
    public ResponseEntity<List<PizzaEntity>> getByDescription(@PathVariable String desc){
        return ResponseEntity.ok(this.pizzaService.getPizzaByDescription(desc));
    }

    @GetMapping("/without/{desc}")
    public ResponseEntity<List<PizzaEntity>> getByWithOutDescription(@PathVariable String desc){
        return ResponseEntity.ok(this.pizzaService.getPizzaByWithOutDescription(desc));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable int id){
        return ResponseEntity.ok(this.pizzaService.getPizzaById(id));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity newPizza){
        if(newPizza.getIdPizza() == null || !this.pizzaService.exists(newPizza.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.createNewPizza(newPizza));
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity newPizza){
        if(newPizza.getIdPizza() != null && this.pizzaService.exists(newPizza.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.createNewPizza(newPizza));
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable int idPizza){
        if(this.pizzaService.exists(idPizza)){
            this.pizzaService.deletePizza(idPizza);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
