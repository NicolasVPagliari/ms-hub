package com.github.nicolasvpagliari.ms_pagamento.tests;


import com.github.nicolasvpagliari.ms_pagamento.dto.PagamentoDTO;
import com.github.nicolasvpagliari.ms_pagamento.entity.Pagamento;
import com.github.nicolasvpagliari.ms_pagamento.entity.Status;

import java.math.BigDecimal;

public class Factory {

    public static Pagamento createPagamento() {
        Pagamento pagamento = new Pagamento(1L, BigDecimal.valueOf(32.25), "Jon Snow", "226483204837283",
                "07/32", "585", Status.CRIADO, 1L, 2L);

        return pagamento;
    }

    public static PagamentoDTO createPagamentoDTO() {
        Pagamento pagamento = createPagamento();
        return new PagamentoDTO(pagamento);
    }

    public static PagamentoDTO createNewPagamentoDTO() {
        Pagamento pagamento = createPagamento();
        pagamento.setId(null);
        return new PagamentoDTO(pagamento);
    }
}
