package com.github.nicolasvpagliari.ms_pagamento.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nicolasvpagliari.ms_pagamento.dto.PagamentoDTO;
import com.github.nicolasvpagliari.ms_pagamento.service.PagamentoService;
import com.github.nicolasvpagliari.ms_pagamento.service.exceptions.ResourceNotFoundException;
import com.github.nicolasvpagliari.ms_pagamento.tests.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@WebMvcTest
public class PagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PagamentoService service;

    private PagamentoDTO dto;
    private Long existingId;
    private Long nonExistingId;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 100L;

        dto = Factory.createPagamentoDTO();

        List<PagamentoDTO> list = List.of(dto);

        //simulando o comportamento do getAll
        Mockito.when(service.getAll()).thenReturn(list);

        //simulando o comportamento do getById - quando Id existe
        Mockito.when(service.getById(existingId)).thenReturn(dto);

        //simulando o comportamento do getById - quando Id existe
        Mockito.when(service.getById(nonExistingId)).thenThrow(ResourceNotFoundException.class);


    }

   @Test
    public void getAllShouldReturnListPagamentoDto() throws Exception{
        //chamando a requisição com o método GET - endpoint/ pagamentos
       ResultActions result = mockMvc.perform(get("/pagamentos").accept(MediaType.APPLICATION_JSON));

       result.andExpect(status().isOk());
   }

   @Test
    public void getByIdShouldReturnDtoWhenIdExists() throws Exception{
        ResultActions result = mockMvc.perform(get("/pagamentos/{id}", existingId).accept(MediaType.APPLICATION_JSON));

        //assertion
       result.andExpect(status().isOk());

       result.andExpect(jsonPath("$.id").exists());
       result.andExpect(jsonPath("$.valor").exists());
       result.andExpect(jsonPath("$.status").exists());
   }

    @Test
    public void getByIdShouldThrowResourceNotFoundEceptionWhenIdDoesNotExist() throws Exception{
        ResultActions result = mockMvc.perform(get("/pagamentos/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON));

        //assertion
        result.andExpect(status().isNotFound());
    }
}
