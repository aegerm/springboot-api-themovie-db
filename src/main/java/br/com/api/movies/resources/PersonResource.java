package br.com.api.movies.resources;

import br.com.api.movies.entities.Person;
import br.com.api.movies.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/persons")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonResource {

    private final PersonService personService;

    /**
     * findPersons
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Person>> findPersons() {
        List<Person> personList = this.personService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(personList);
    }

    /**
     * findPersonById
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> findPersonById(@PathVariable Long id) {
        Person person = this.personService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    /**
     * savePerson
     *
     * @param person
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> savePerson(@RequestBody Person person) {
        this.personService.savePerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}