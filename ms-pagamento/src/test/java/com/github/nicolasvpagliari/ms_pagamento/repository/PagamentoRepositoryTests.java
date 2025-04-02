package com.github.nicolasvpagliari.ms_pagamento.repository;

import com.github.nicolasvpagliari.ms_pagamento.entity.Pagamento;
import com.github.nicolasvpagliari.ms_pagamento.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class PagamentoRepositoryTests {
    @Autowired
    private PagamentoRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalPagamento;

    @BeforeEach
    void setUp() throws Exception{
        existingId = 1L;
        nonExistingId = 100L;
        countTotalPagamento = 3L;
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {

        Long existingId = 1L;

        repository.deleteById(existingId);

        Optional<Pagamento> result = repository.findById(existingId);

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void givenValidParamsAndIdIsNull_whenCallCreatePagamento_thenInstantiateAPagamento() {

        Pagamento pagamento = Factory.createPagamento();
        pagamento.setId(null);
        pagamento = repository.save(pagamento);
        Assertions.assertNotNull(pagamento.getId());

        Assertions.assertEquals(countTotalPagamento + 1, pagamento.getId());
    }

    @Test
    @DisplayName("dado um id não existente ao chamar findById então deve retornar um Optional não vazio")
    public void givenAnExistingId_whenCallFindById_thenReturnNonEmptyOptional() {
        Optional<Pagamento> result = repository.findById(existingId);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("dado um id não existente ao chamar findById então deve retornar um Optional vazio")
    public void givenAnNonExistingId_whenCallFindById_thenReturnEmptyOptional() {
        Optional<Pagamento> result = repository.findById(nonExistingId);
        Assertions.assertTrue(result.isEmpty());
    }
}
