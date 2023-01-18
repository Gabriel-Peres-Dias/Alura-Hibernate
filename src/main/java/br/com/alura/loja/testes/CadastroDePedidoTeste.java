package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.model.*;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedidoTeste {
    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();

        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        Produto produto = produtoDAO.buscarPorId(1L);
        Produto produto2 = produtoDAO.buscarPorId(2L);
        Produto produto3 = produtoDAO.buscarPorId(3L);

        Cliente cliente = clienteDAO.buscarPorId(1L);

        em.getTransaction().begin();

        List<Categoria> categorias = categoriaDAO.buscarCategorias();
        categorias.forEach(cat -> System.out.println(cat.getNome()));

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10,pedido, produto));
        pedido.adicionarItem(new ItemPedido(40,pedido, produto2));

        Pedido pedido2 = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(5, pedido, produto3));

        PedidoDAO pedidoDao = new PedidoDAO(em);

        pedidoDao.salvarPedido(pedido);
        pedidoDao.salvarPedido(pedido2);

        em.getTransaction().commit();

        BigDecimal valorTotal = pedidoDao.valorTotalVendido();
        System.out.println("Valor Total: " + valorTotal);

        List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
        relatorio.forEach(System.out::println);

        em.close();
    }

    private static void popularBancoDeDados() {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);

        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("Xiami RedMI Note","Celular mid range",new BigDecimal(1200), celulares);
        Produto videogame = new Produto("Playstation 4","PS4 de cria",new BigDecimal(3500), videogames);
        Produto placaDeVideo = new Produto("RTX 3060 Colorful igame","Placa de vídeo para fullHD",new BigDecimal(2200), informatica);

        Cliente cliente = new Cliente("Gabriel Peres", "12345678911");

        categoriaDAO.salvar(celulares);
        categoriaDAO.salvar(videogames);
        categoriaDAO.salvar(informatica);

        produtoDAO.salvar(celular);
        produtoDAO.salvar(videogame);
        produtoDAO.salvar(placaDeVideo);

        clienteDAO.salvarCliente(cliente);

        em.getTransaction().commit();
        em.close();
    }
}
