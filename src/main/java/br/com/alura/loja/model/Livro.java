package br.com.alura.loja.model;

import javax.persistence.Entity;

@Entity
public class Livro extends Produto {

    private String autor;
    private Integer numeroDepaginas;

    public Livro() {
    }

    public Livro(String autor, Integer numeroDepaginas) {
        this.autor = autor;
        this.numeroDepaginas = numeroDepaginas;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getNumeroDepaginas() {
        return numeroDepaginas;
    }

    public void setNumeroDepaginas(Integer numeroDepaginas) {
        this.numeroDepaginas = numeroDepaginas;
    }
}
