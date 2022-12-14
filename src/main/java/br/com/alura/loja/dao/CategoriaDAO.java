package br.com.alura.loja.dao;

import br.com.alura.loja.model.Categoria;
import br.com.alura.loja.model.Produto;

import javax.persistence.EntityManager;

public class CategoriaDAO {

    private EntityManager entityManager;

    public CategoriaDAO (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void salvar(Categoria categoria) {
        this.entityManager.persist(categoria);
    }

    public void atualizar(Categoria categoria) {
        this.entityManager.merge(categoria);
    }

    public void deletar(Categoria categoria) {
        // Forçando a entidade para o estado de managed
        categoria = entityManager.merge(categoria);
        this.entityManager.remove(categoria);
    }
}
