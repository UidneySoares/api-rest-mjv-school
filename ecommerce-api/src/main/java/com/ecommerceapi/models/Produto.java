package com.ecommerceapi.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private String desenvolvedora;

    @Column(name= "preco_unitario")
    private BigDecimal preco;



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

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}