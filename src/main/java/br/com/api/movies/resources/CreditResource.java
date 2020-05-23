package br.com.api.movies.resources;

import br.com.api.movies.entities.Credit;
import br.com.api.movies.services.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/credits")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreditResource {

    private final CreditService creditService;

    /**
     * findCredits
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Credit>> findCredits() {
        List<Credit> credits = this.creditService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(credits);
    }

    /**
     * findCreditById
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Credit> findCreditById(@PathVariable Long id) {
        Credit credit = this.creditService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(credit);
    }
}