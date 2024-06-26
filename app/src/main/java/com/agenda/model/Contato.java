package com.agenda.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Entity
public class Contato implements Serializable {
    //toda entidade precisa de uma primary key
    //TODA VEZ QUE MEDIFICAR UMA ENTIDADE, É NECESSARIO MUDAR A VERSAO
    @PrimaryKey(autoGenerate = true)
    private int id=0;
    private  String nome;
    private  String email;
    private String sobrenome;
    private Calendar momentoDeCadastro = Calendar.getInstance(); //
    private List<Telefone> telefones;

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public String getNome() {return nome;}


    public String getEmail() {
        return email;
    }
    @NonNull
    @Override
    public String toString() {
        return nome + " - ";
    }//MOSTRA o nome e do telefone do contato na lista

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    public String getSobrenome() {
        return sobrenome;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido() {
        return id > 0;
    }

    public String getNomeCompleto() {
        return nome + " " +sobrenome;
    }

    public Calendar getMomentoDeCadastro() {
        return momentoDeCadastro;
    }

    public void setMomentoDeCadastro(Calendar momentoDeCadastro) {
        this.momentoDeCadastro = momentoDeCadastro;
    }
    public String dataFormatada(){
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        return formatador.format(momentoDeCadastro.getTime());
    }
}
