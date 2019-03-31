package com.cicloi.eletronica.multitenant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntityImpl {

    @Column(name = "tenant", insertable = false, updatable = false)
    private String tenant;

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }
}
