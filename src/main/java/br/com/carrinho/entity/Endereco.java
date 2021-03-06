package br.com.carrinho.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @Size(max = 8)
    @Column(name = "cep")
    private String cep;

    @NotBlank
    @Size(max = 100)
    @Column(name = "logradouro", nullable = false, length = 100)
    private String logradouro;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @Size(max = 20)
    @Column(name = "complemento", length = 20)
    private String complemento;

    @NotBlank
    @Size(max = 40)
    @Column(name = "bairro", length = 40, nullable = false)
    private String bairro;

    @NotBlank
    @Size(max = 50)
    @Column(name = "municipio", length = 50, nullable = false)
    private String municipio;

    @NotBlank
    @Size(max = 2)
    @Column(name = "estado", length = 2, nullable = false)
    private String estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Endereco other = (Endereco) o;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return this.id != null ? id.hashCode() : 0;
    }
}