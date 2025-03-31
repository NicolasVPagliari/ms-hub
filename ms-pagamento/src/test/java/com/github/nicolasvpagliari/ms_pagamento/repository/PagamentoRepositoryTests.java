package com.github.nicolasvpagliari.ms_pagamento.repository;

import com.github.nicolasvpagliari.ms_pagamento.entity.Pagamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class PagamentoRepositoryTests {
    @Autowired
    private PagamentoRepository repository;

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {

        Long existingId = 1L;

        repository.deleteById(existingId);

        Optional<Pagamento> result = repository.findById(existingId);

        Assertions.assertFalse(result.isPresent());
    }
}
