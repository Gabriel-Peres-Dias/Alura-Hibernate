package br.com.alura.loja.dao;

import br.com.alura.loja.model.Categoria;
import br.com.alura.loja.model.Pedido;

import javax.persistence.EntityManager;

public class PedidoDAO {

    private EntityManager entityManager;

    public PedidoDAO (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void salvarPedido(Pedido pedido) {
        this.entityManager.persist(pedido);
    }

}
