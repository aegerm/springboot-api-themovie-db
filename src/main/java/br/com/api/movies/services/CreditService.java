package br.com.api.movies.services;

import br.com.api.movies.entities.Credit;
import br.com.api.movies.repositories.CreditRepository;
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
public class CreditService {

    private final CreditRepository creditRepository;

    /**
     * findAll
     *
     * @return
     */
    public List<Credit> findAll() {
        log.info("Buscando todos os créditos disponíveis.");
        List<Credit> credits = this.creditRepository.findAll();
        return credits;
    }

    /**
     * findById
     *
     * @param id
     * @return
     */
    public Credit findById(Long id) {
        log.info("Buscando crédito pelo id.");
        Optional<Credit> credit = this.creditRepository.findById(id);
        return credit.orElseThrow(() -> new ObjectNotFoundException("Credit is null " + Credit.class.getName()));
    }
}