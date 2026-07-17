package br.com.leeo_s.rest_with_java_spring_boot.service;

import br.com.leeo_s.rest_with_java_spring_boot.controller.PersonController;
import br.com.leeo_s.rest_with_java_spring_boot.data.dto.PersonDTO;
import br.com.leeo_s.rest_with_java_spring_boot.exception.RequiredObjectIsNullException;
import br.com.leeo_s.rest_with_java_spring_boot.exception.ResourceNotFoundException;
import static br.com.leeo_s.rest_with_java_spring_boot.mapper.ObjectMapper.parseListObjects;
import static br.com.leeo_s.rest_with_java_spring_boot.mapper.ObjectMapper.parseObject;

import br.com.leeo_s.rest_with_java_spring_boot.model.Person;
import br.com.leeo_s.rest_with_java_spring_boot.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    public PersonRepository personRepository;

    private final Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one Person!");

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        var dto = parseObject(entity, PersonDTO.class);

        addLinksHateoas(dto);

        return dto;
    }

    public List<PersonDTO> findAll()
    {
        logger.info("Finding all Persons!");

        //List<Person> listPerson = new ArrayList<>();

        var dtos = parseListObjects(personRepository.findAll(), PersonDTO.class);

        dtos.forEach(this::addLinksHateoas);

        return dtos;
    }

    public PersonDTO create(PersonDTO person) {

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Creating new Person!");

        var entity = parseObject(person, Person.class);

        var dto = parseObject(personRepository.save(entity), PersonDTO.class);

        addLinksHateoas(dto);

        return dto;
    }

    public PersonDTO update(PersonDTO person) {

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Updating Person!");

        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = parseObject(personRepository.save(entity), PersonDTO.class);

        addLinksHateoas(dto);

        return dto;
    }

    public void delete(Long id) {
        logger.info("Deleting Person!");

        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        personRepository.delete(entity);
    }

    private void addLinksHateoas(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
