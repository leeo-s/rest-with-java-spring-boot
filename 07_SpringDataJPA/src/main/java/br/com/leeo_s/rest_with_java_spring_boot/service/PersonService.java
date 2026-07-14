package br.com.leeo_s.rest_with_java_spring_boot.service;

import br.com.leeo_s.rest_with_java_spring_boot.exception.ResourceNotFoundException;
import br.com.leeo_s.rest_with_java_spring_boot.model.Person;
import br.com.leeo_s.rest_with_java_spring_boot.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    PersonRepository personRepository;

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findById(Long id) {
        logger.info("Finding one Person!");

        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
    }

    public List<Person> findAll()
    {
        logger.info("Finding all Persons!");

        //List<Person> listPerson = new ArrayList<>();

        return personRepository.findAll();
    }

    public Person create(Person person) {
        logger.info("Creating new Person!");

        return personRepository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating Person!");

        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return personRepository.save(entity);
    }

    public void delete(Long id) {
        logger.info("Deleting Person!");

        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        personRepository.delete(entity);
    }
}
