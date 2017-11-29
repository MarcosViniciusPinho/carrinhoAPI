package br.com.carrinho.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "carrinho")
public class Carrinho implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @NotNull
    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.PERSIST)
    private List<ProdutoCarrinho> produtoCarrinhoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ProdutoCarrinho> getProdutoCarrinhoList() {
        return produtoCarrinhoList;
    }

    public void setProdutoCarrinhoList(List<ProdutoCarrinho> produtoCarrinhoList) {
        produtoCarrinhoList.forEach(produtoCarrinho -> produtoCarrinho.setCarrinho(this));
        this.produtoCarrinhoList = produtoCarrinhoList;
    }

    @Transient
    public BigDecimal getValorDaCompra(){
        BigDecimal valorDaCompra = BigDecimal.ZERO;
        for(ProdutoCarrinho produtoCarrinho : this.produtoCarrinhoList){
            valorDaCompra = valorDaCompra.add(produtoCarrinho.getProduto().getValor().multiply(
                    BigDecimal.valueOf(produtoCarrinho.getQuantidade())));
        }
        return valorDaCompra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Carrinho other = (Carrinho) o;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return this.id != null ? id.hashCode() : 0;
    }
}
