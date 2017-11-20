package br.com.carrinho.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "produto_carrinho")
public class ProdutoCarrinho implements Serializable{

    @Column(name = "quantidade", nullable = false)
    private Long quantidade;

    @JsonIgnore
    @Id
    @ManyToOne
    @JoinColumn(name = "id_carrinho", nullable = false)
    private Carrinho carrinho;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        ProdutoCarrinho other = (ProdutoCarrinho) o;
        return Objects.equals(produto, other.produto) && Objects.equals(carrinho, other.carrinho);
    }

    @Override
    public int hashCode() {
        return this.produto != null ? produto.hashCode() : carrinho != null ? carrinho.hashCode() : 0;
    }
}
