package com.github.nicolasvpagliari.ms_pedido.repository;

import com.github.nicolasvpagliari.ms_pedido.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
