package com.cicloi.eletronica.models.embeddable;

import javax.persistence.Column;

public class Contexto {

    @Column(name = "I_DATABASE")
    private Long database;

    @Column(name = "I_ENTIDADE")
    private Long entidade;

    public Long getDatabase() {
        return database;
    }

    public Contexto setDatabase(Long database) {
        this.database = database;
        return this;
    }

    public Long getEntidade() {
        return entidade;
    }

    public Contexto setEntidade(Long entidade) {
        this.entidade = entidade;
        return this;
    }
}
