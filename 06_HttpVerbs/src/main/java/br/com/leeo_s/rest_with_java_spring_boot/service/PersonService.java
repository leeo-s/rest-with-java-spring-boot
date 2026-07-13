package br.com.leeo_s.rest_with_java_spring_boot.service;

import br.com.leeo_s.rest_with_java_spring_boot.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(String id) {
        logger.info("Finding one Person!");

        Person personMock = new Person();
        personMock.setId(counter.incrementAndGet());
        personMock.setFirstName("Leonardo");
        personMock.setLastName("Silva");
        personMock.setAddress("Rua João Delgado Hidalgo, 164");
        personMock.setGender("Masculino");

        return personMock;
    }

    public List<Person> findAll()
    {
        logger.info("Finding all Persons!");

        List<Person> listPerson = new ArrayList<Person>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            listPerson.add(person);
        }

        return listPerson;
    }

    public Person create(Person person) {
        logger.info("Creating new Person!");

        return person;
    }

    public Person update(Person person, String id) {
        logger.info("Updating Person!");

        return person;
    }

    public void delete(String id) {
        logger.info("Deleting Person!");
    }

    private Person mockPerson(int i) {

        Person personMock = new Person();
        personMock.setId(counter.incrementAndGet());
        personMock.setFirstName("Firstname " + i);
        personMock.setLastName("Lastname " + i);
        personMock.setAddress("Address " + i);
        personMock.setGender("Gender " + i);

        return personMock;
    }
}
