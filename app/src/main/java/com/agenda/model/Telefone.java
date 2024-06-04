package com.agenda.model;

import static androidx.room.ForeignKey.CASCADE;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
@Entity
public class Telefone {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String numero;
    private TipoTelefone tipo;

//    @ForeignKey(entity = Contato.class,parentColumns = "id", childColumns = "contatoId", onUpdate = CASCADE, onDelete = CASCADE)
    @ColumnInfo(name = "contato_id")
    private int contatoId;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefone getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }

    public int getContatoId() {
        return contatoId;
    }

    public void setContatoId(int contatoId) {
        this.contatoId = contatoId;
    }
}
