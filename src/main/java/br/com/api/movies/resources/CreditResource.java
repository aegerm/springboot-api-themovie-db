package br.com.api.movies.resources;

import br.com.api.movies.dto.CreditDTO;
import br.com.api.movies.entities.Credit;
import br.com.api.movies.services.CreditService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping
    public ResponseEntity<List<CreditDTO>> findCredits() {
        List<Credit> credits = this.creditService.findAll();
        List<CreditDTO> creditDTOS = credits.stream().map(value -> new CreditDTO(value)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(creditDTOS);
    }

    /**
     * findCreditById
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Credit> findCreditById(@PathVariable Long id) {
        Credit credit = this.creditService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(credit);
    }
}