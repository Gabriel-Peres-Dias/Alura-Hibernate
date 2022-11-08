package br.com.alura.loja.dao;

import br.com.alura.loja.model.Produto;

import javax.persistence.EntityManager;

public class ProdutoDAO {

    private EntityManager entityManager;

    public ProdutoDAO (EntityManager entityManager) {
    this.entityManager = entityManager;
    }

    public void salvar(Produto produto) {
        this.entityManager.persist(produto);
    }


}
