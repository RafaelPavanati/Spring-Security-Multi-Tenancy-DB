package com.cicloi.eletronica.models;

import com.cicloi.eletronica.models.embeddable.Contexto;
import com.cicloi.eletronica.models.embeddable.Endereco;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "clientes")
@SequenceGenerator(name = "clientes_seq", sequenceName = "clientes_seq", allocationSize = 1)
public class Cliente extends Contexto {

    @Id
    @Column(name = "ID_USUARIO")
    @GeneratedValue(generator = "usuarios_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Column(name = "NOME")
    private String nome;

    @Column(name = "CPFCNPJ")
    private String cpfCnpj;

    @Column(name = "IE")
    private String ie;

    @Column(name = "NASC")
    private LocalDate nasc;

    @Embedded
    private Endereco endereco;

    public String getEmail() {
        return email;
    }

    public Cliente setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Cliente setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public Cliente setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
        return this;
    }

    public String getIe() {
        return ie;
    }

    public Cliente setIe(String ie) {
        this.ie = ie;
        return this;
    }

    public LocalDate getNasc() {
        return nasc;
    }

    public Cliente setNasc(LocalDate nasc) {
        this.nasc = nasc;
        return this;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Cliente setEndereco(Endereco endereco) {
        this.endereco = endereco;
        return this;
    }
}
