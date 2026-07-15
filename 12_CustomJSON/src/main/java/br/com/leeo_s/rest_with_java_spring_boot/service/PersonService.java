package br.com.leeo_s.rest_with_java_spring_boot.service;

import br.com.leeo_s.rest_with_java_spring_boot.data.dto.PersonDTO;
import br.com.leeo_s.rest_with_java_spring_boot.exception.ResourceNotFoundException;
import static br.com.leeo_s.rest_with_java_spring_boot.mapper.ObjectMapper.parseListObjects;
import static br.com.leeo_s.rest_with_java_spring_boot.mapper.ObjectMapper.parseObject;

import br.com.leeo_s.rest_with_java_spring_boot.model.Person;
import br.com.leeo_s.rest_with_java_spring_boot.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    PersonRepository personRepository;

    private final Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one Person!");

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        return parseObject(entity, PersonDTO.class);
    }

    public List<PersonDTO> findAll()
    {
        logger.info("Finding all Persons!");

        //List<Person> listPerson = new ArrayList<>();

        return parseListObjects(personRepository.findAll(), PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Creating new Person!");

        var entity = parseObject(person, Person.class);

        return parseObject(personRepository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("Updating Person!");

        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(personRepository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting Person!");

        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        personRepository.delete(entity);
    }
}
