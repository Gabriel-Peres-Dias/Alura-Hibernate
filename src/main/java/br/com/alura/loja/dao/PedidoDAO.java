package br.com.alura.loja.dao;

import br.com.alura.loja.model.Categoria;
import br.com.alura.loja.model.Pedido;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDAO {

    private EntityManager entityManager;

    public PedidoDAO (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void salvarPedido(Pedido pedido) {
        this.entityManager.persist(pedido);
    }

    public BigDecimal valorTotalVendido() {
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return entityManager.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public List<Object[]> relatorioDeVendas() {
        String jpql = "SELECT produto.nome, " +
                "SUM(item.quantidade), " +
                "MAX(pedido.data) " +
                "FROM Pedido pedido " +
                "JOIN pedido.itens item " +
                "JOIN item.produto produto " +
                "GROUP BY produto.nome ";

        return entityManager.createQuery(jpql, Object[].class).getResultList();
    }

}
