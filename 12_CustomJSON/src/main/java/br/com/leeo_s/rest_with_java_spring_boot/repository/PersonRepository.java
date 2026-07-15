package br.com.leeo_s.rest_with_java_spring_boot.repository;

import br.com.leeo_s.rest_with_java_spring_boot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
