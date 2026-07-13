package br.com.leeo_s.rest_with_java_spring_boot.controller;

import br.com.leeo_s.rest_with_java_spring_boot.model.Person;
import br.com.leeo_s.rest_with_java_spring_boot.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService)
    {
        this.personService = personService;
    }

    //@RequestMapping(value = "/findById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    //também poderia ser feito assim, sem utilizar o verbo direto como anotação
    @GetMapping("/findById/{id}")
    public Person findById(@PathVariable("id") String id) {
        return personService.findById(id);
    }

    @GetMapping("/findAll")
    public List<Person> findAll() {
        return personService.findAll();
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }

    @RequestMapping( value = "/findById/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person update(@RequestBody Person person, @PathVariable("id") String id) {
        return personService.update(person, id);
    }

    @RequestMapping( value = "/delete/{id}",
            method = RequestMethod.DELETE
    )
    public void create(@PathVariable("id") String id) {
        personService.delete(id);
    }
}
