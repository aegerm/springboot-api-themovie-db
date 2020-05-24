package br.com.api.movies.resources;

import br.com.api.movies.dto.PersonDTO;
import br.com.api.movies.entities.Person;
import br.com.api.movies.services.PersonService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping
    public ResponseEntity<List<PersonDTO>> findPersons() {
        List<Person> personList = this.personService.findAll();
        List<PersonDTO> personDTOList = personList.stream().map(value -> new PersonDTO(value)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(personDTOList);
    }

    /**
     * findPersonById
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> findPersonById(@PathVariable Long id) {
        Person person = this.personService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    /**
     * savePerson
     *
     * @param dto
     * @return
     */
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> savePerson(@RequestBody PersonDTO dto) {
        Person person = this.personService.fromDTO(dto);
        this.personService.savePerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}