package llortegall.pizzeria.web.controller;

import llortegall.pizzeria.persistence.entity.PizzaEntity;
import llortegall.pizzeria.services.PizzaService;
import llortegall.pizzeria.services.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "4") int elem){
        return ResponseEntity.ok(this.pizzaService.getAllPizzas(page, elem));
    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAllAvailable(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "4") int elem,
                                                             @RequestParam(defaultValue = "price") String sortBy,
                                                             @RequestParam(defaultValue = "ASC")String sortDirection){
        return ResponseEntity.ok(this.pizzaService.getAllPizzasAvailable(page, elem, sortBy, sortDirection));
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

    @PatchMapping("/update")
    public ResponseEntity<Void> update(@RequestBody UpdatePizzaPriceDto dto){
        if(this.pizzaService.exists(dto.getPizzaId())){
            this.pizzaService.updatePrice(dto);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
