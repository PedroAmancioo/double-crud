package com.example.ListaUsuarios.model;

public class User {
    private Long id;
    private String name;
    private String arroba;
    private String cpf;

    public User(Long id, String name, String arroba, String cpf){
        this.id = id;
        this.arroba = arroba;
        this.name = name;
        this.cpf = cpf;

    }

    public String getCpf() {
        return cpf;
    }

    public Long getId() {
        return id;
    }

    public String getArroba() {
        return arroba;
    }

    public String getName() {
        return name;
    }

    public void setArroba(String arroba) {
        this.arroba = arroba;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
