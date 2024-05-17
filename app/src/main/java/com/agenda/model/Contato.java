package com.agenda.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Contato implements Serializable {
    private int id=0;
    private  String nome;
    private  String telefone;
    private  String email;

    public Contato(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Contato() {

    }

    public String getNome() {return nome;}

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    @NonNull
    @Override
    public String toString() {
        return nome + " - "+ telefone;
    }//MOSTRA o nome e do telefone do contato na lista

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido() {
        return id > 0;
    }
}
