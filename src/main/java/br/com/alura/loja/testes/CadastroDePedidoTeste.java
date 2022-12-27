package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.model.*;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDePedidoTeste {
    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);
        Produto produto = produtoDAO.buscarPorId(1L);
        Cliente cliente = clienteDAO.buscarPorId(1L);


        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10,pedido, produto));

        PedidoDAO pedidoDao = new PedidoDAO(em);
        pedidoDao.salvarPedido(pedido);


        em.getTransaction().commit();
        em.close();
    }

    private static void popularBancoDeDados() {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);

        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiami RedMI Note","Celular mid range",new BigDecimal(1200), celulares);
        Cliente cliente = new Cliente("Gabriel Peres", "12345678911");

        categoriaDAO.salvar(celulares);
        produtoDAO.salvar(celular);
        clienteDAO.salvarCliente(cliente);

        em.getTransaction().commit();
        em.close();
    }
}
