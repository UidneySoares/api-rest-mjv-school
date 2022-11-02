package com.ecommerceapi.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

@Entity
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //Muitos pedidos para uma pessoa
    @ManyToOne
    @JoinColumn(name= "cliente_id")
    private Cliente cliente;

    private LocalDate dataPedido;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    @Column(name= "total", precision = 20, scale = 2)
    private BigDecimal total;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public com.ecommerceapi.models.Cliente getCliente() {
        return cliente;
    }

    public void setCliente(com.ecommerceapi.models.Cliente cliente) {
        cliente = cliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
