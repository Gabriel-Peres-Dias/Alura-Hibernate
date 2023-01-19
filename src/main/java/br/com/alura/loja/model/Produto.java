package br.com.alura.loja.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "produtos")
//consulta que ao invés de ficar na DAO, fica na entidade
@NamedQuery(name = "Produto.produtosPorCategoria",
        query = "SELECT p FROM Produto p WHERE p.categoria.id.nome = :nome")
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro = LocalDate.now();

    /*
    temos duas principais estratégias com  mapeamento de heranças:
    1: SingleTable -> Criar uma tabela com todos os atributos juntos. Vantagem em performace  pq fica
    tudo junto. Usamos o @Inheritance e o Inheritance.SINGLE_TABLE na Entidade mãe
    2: Joined -> Uma tabela por classe. Desvantagem é que se um select na entidade filha vai ter
    um join com a entidade mae. Usamos o @Inheritance e o Inheritance.JOINED na Entidade mãe
     */

    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

    public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Produto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
