package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.model.*;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;


public class CriteriaAPITest {
    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO dao = new ProdutoDAO(em);
        dao.buscarPorParametrosComCriteria("PS5",null,null);

    }

    private static void popularBancoDeDados() {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("Xiami RedMI Note","Celular mid range",new BigDecimal(1200), celulares);
        Produto videogame = new Produto("Playstation 4","PS4 de cria",new BigDecimal(3500), videogames);
        Produto placaDeVideo = new Produto("RTX 3060 Colorful igame","Placa de vídeo para fullHD",new BigDecimal(2200), informatica);

        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        categoriaDAO.salvar(celulares);
        categoriaDAO.salvar(videogames);
        categoriaDAO.salvar(informatica);

        produtoDAO.salvar(celular);
        produtoDAO.salvar(videogame);
        produtoDAO.salvar(placaDeVideo);

        em.getTransaction().commit();
        em.close();
    }
}
