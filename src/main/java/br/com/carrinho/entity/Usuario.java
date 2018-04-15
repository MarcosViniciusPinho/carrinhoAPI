package br.com.carrinho.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Size(max = 30, min = 3)
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;

    @NotBlank
    @Size(max = 50, min = 5)
    @Column(name = "sobrenome", length = 50, nullable = false)
    private String sobrenome;

    @NotBlank
    @Size(max = 50, min = 4)
    @Column(name = "login", length = 50, nullable = false, unique = true)
    private String login;

    @NotBlank
    @Email(regexp = ".+@.+\\.[a-z]{3}+")
    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(max = 255, min = 8)
    @Column(name = "senha", length = 255, nullable = false)
    private String senha;

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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Usuario other = (Usuario) o;
        return Objects.equals(login, other.login) || Objects.equals(email, other.email);
    }

    @Override
    public int hashCode() {
        return this.id != null ? id.hashCode() : 0;
    }
}