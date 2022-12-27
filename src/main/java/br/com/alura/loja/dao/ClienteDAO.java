package br.com.alura.loja.dao;

import br.com.alura.loja.model.Cliente;
import br.com.alura.loja.model.Pedido;

import javax.persistence.EntityManager;

public class ClienteDAO {

    private EntityManager entityManager;

    public ClienteDAO (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void salvarCliente(Cliente cliente) {
        this.entityManager.persist(cliente);
    }

    public Cliente buscarPorId(long id) {
        return this.entityManager.find(Cliente.class, id);
    }
}
