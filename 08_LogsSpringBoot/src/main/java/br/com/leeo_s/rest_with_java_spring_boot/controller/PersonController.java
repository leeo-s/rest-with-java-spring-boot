package br.com.leeo_s.rest_with_java_spring_boot.controller;

import br.com.leeo_s.rest_with_java_spring_boot.model.Person;
import br.com.leeo_s.rest_with_java_spring_boot.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService)
    {
        this.personService = personService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll() {
        return personService.findAll();
    }

    //@RequestMapping(value = "/findById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    //também poderia ser feito assim, sem utilizar o verbo direto como anotação
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person update(@RequestBody Person person) {
        return personService.update(person);
    }

    @DeleteMapping( value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
