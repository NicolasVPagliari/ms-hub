package com.github.nicolasvpagliari.ms_pagamento.service;

import com.github.nicolasvpagliari.ms_pagamento.dto.PagamentoDTO;
import com.github.nicolasvpagliari.ms_pagamento.entity.Pagamento;
import com.github.nicolasvpagliari.ms_pagamento.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Transactional(readOnly = true)
    public List<PagamentoDTO> getAll() {
        List<Pagamento> pagamentos = repository.findAll();
        return pagamentos.stream().map(Pagamento::new).collect(Collectors.toList());
    }
}
