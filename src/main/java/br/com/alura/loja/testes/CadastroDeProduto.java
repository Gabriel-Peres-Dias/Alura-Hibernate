package br.com.alura.loja.testes;

import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.model.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CadastroDeProduto {
    public static void main(String[] args) {
        Produto celular = new Produto();
        celular.setNome("Xiamo RedMI Note");
        celular.setDescricao("Topzera");
        celular.setPreco(new BigDecimal(1200));


        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        produtoDAO.salvar(celular);
        em.getTransaction().commit();
        em.close();
    }
}
