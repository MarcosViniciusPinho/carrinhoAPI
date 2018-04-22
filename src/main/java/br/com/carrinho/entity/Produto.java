package br.com.carrinho.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "produto")
public class Produto implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", length = 30, nullable = false)
    private String nome;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @Column(name = "descricao", length = 60)
    private String descricao;

    @Column(name = "valor", precision = 10, scale = 2, nullable = false)
    private BigDecimal valor;

    @OneToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @Column(name = "imagem", nullable = false)
    private String pathImagem;

    @Column(name = "quantidade", nullable = false)
    private String capacidade;

    @JsonIgnore
    @OneToMany(mappedBy = "produto")
    private List<ProdutoCarrinho> produtoCarrinhoList;

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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(String capacidade) {
        this.capacidade = capacidade;
    }

    public String getPathImagem() {
        return pathImagem;
    }

    public void setPathImagem(String pathImagem) {
        this.pathImagem = pathImagem;
    }

    public List<ProdutoCarrinho> getProdutoCarrinhoList() {
        return produtoCarrinhoList;
    }

    public void setProdutoCarrinhoList(List<ProdutoCarrinho> produtoCarrinhoList) {
        this.produtoCarrinhoList = produtoCarrinhoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Produto other = (Produto) o;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return this.id != null ? id.hashCode() : 0;
    }
}
