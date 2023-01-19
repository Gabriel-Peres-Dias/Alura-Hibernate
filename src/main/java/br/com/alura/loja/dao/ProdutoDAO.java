package br.com.alura.loja.dao;

import br.com.alura.loja.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProdutoDAO {

    private EntityManager entityManager;

    public ProdutoDAO (EntityManager entityManager) {
    this.entityManager = entityManager;
    }

    public void salvar(Produto produto) {
        this.entityManager.persist(produto);
    }

    public Produto buscarPorId( Long id) {
        return this.entityManager.find(Produto.class, id);
    }

    public List<Produto> buscarTodos() {
        String jpql = "SELECT p FROM Produto p";
        return entityManager.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String nome) {
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
        return entityManager.createQuery(jpql, Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nome) {
        return entityManager.createNamedQuery("Produto.produtosPorCategoria", Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
        return entityManager.createQuery(jpql, BigDecimal.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }

    public List<Produto> buscarPorParametrosComCriteria (String nome, BigDecimal preco, LocalDate dataCadastro) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> query =  criteriaBuilder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);

        Predicate filtros = criteriaBuilder.and();
        if (nome != null && !nome.trim().isEmpty()) {
            filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("nome"), nome));
        }
        if (preco != null) {
            filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("preco"), preco));
        }
        if (dataCadastro != null) {
            filtros = criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("dataCadastro"), dataCadastro));
        }
        query.where(filtros);
        return entityManager.createQuery(query).getResultList();
    }

}
