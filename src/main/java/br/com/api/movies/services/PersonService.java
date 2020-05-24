package br.com.api.movies.services;

import br.com.api.movies.entities.Person;
import br.com.api.movies.repositories.PersonRepository;
import br.com.api.movies.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;

    /**
     * savePerson
     *
     * @param person
     */
    public void savePerson(Person person) {
        log.info("Salvando nome do ator.");
        this.personRepository.save(person);
    }

    /**
     * findAll
     *
     * @return
     */
    public List<Person> findAll() {
        log.info("Buscando todos os atores.");
        List<Person> personList = this.personRepository.findAll();
        return personList;
    }

    /**
     * findById
     *
     * @param id
     * @return
     */
    public Person findById(Long id) {
        log.info("Buscando ator pelo id.");
        Optional<Person> person = this.personRepository.findById(id);
        return person.orElseThrow(() -> new ObjectNotFoundException("Não existe pessoa com o Id: " + id));
    }
}