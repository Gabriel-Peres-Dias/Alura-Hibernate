package br.com.alura.loja.model;

import javax.persistence.Embeddable;

//A anotação quer dizer que essa classe é imbutível, conseguindo embuti-la em uma entidade.
@Embeddable
public class DadosPessoais {

    private String nome;
    private String cpf;

    public DadosPessoais () {}

    public DadosPessoais(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}