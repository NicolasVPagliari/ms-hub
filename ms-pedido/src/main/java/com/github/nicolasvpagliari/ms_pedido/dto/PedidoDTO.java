package com.github.nicolasvpagliari.ms_pedido.dto;

import com.github.nicolasvpagliari.ms_pedido.entities.ItemDoPedido;
import com.github.nicolasvpagliari.ms_pedido.entities.Pedido;
import com.github.nicolasvpagliari.ms_pedido.entities.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoDTO {

    private Long id;

    @NotEmpty(message = "Nome requerido")
    @Size(min = 3, max = 100, message = "O nome deve conter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "CPF Requerido")
    @Size(min = 11, max = 11, message = "CPF deve ter 11 caracteres")
    private String cpf;
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private Status status;

    private List<@Valid ItemDoPedidoDTO> itens = new ArrayList<>();

    public PedidoDTO(Pedido entity) {
        id = entity.getId();
        nome = entity.getNome();
        cpf = entity.getCpf();
        data = entity.getData();
        status = entity.getStatus();

        for (ItemDoPedido item : entity.getItens()) {
              ItemDoPedidoDTO itemDTO = new ItemDoPedidoDTO(item);
              itens.add(itemDTO);
        }
    }
}
