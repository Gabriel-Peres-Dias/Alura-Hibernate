package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.model.Categoria;
import br.com.alura.loja.model.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProdutoTeste {
    public static void main(String[] args) {
        cadastrarPorduto();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        Produto p = produtoDAO.buscarPorId(1L);
        System.out.println(p.getPreco());

        List<Produto> produtos = produtoDAO.buscarTodos();
        produtos.forEach(pdt -> System.out.println(pdt.getNome()));

        List<Produto> nomeProduto = produtoDAO.buscarPorNome("Xiamo RedMI Note");
        nomeProduto.forEach(pdt -> System.out.println(pdt.getNome()));

        List<Produto> nomeDaCategoria = produtoDAO.buscarPorNomeDaCategoria("CELULARES");
        nomeDaCategoria.forEach(pdt -> System.out.println(pdt.getNome()));

        BigDecimal precoDoProduto = produtoDAO.buscarPrecoDoProdutoComNome("Xiamo RedMI Note");
        System.out.println("Preço do produto: " + precoDoProduto);
    }

    private static void cadastrarPorduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiamo RedMI Note","Topzera",new BigDecimal(1200), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        categoriaDAO.salvar(celulares);
        produtoDAO.salvar(celular);
        em.getTransaction().commit();
        em.close();
    }
}
