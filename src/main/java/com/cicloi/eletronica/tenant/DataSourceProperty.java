package com.cicloi.eletronica.tenant;

import lombok.Data;

@Data
public class DataSourceProperty {

    private String name;
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    public String getName() {
        return name;
    }


    public DataSourceProperty setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public DataSourceProperty setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public DataSourceProperty setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DataSourceProperty setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public DataSourceProperty setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
        return this;
    }
}
