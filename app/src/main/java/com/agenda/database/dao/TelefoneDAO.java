package com.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.agenda.model.Telefone;

@Dao
public interface TelefoneDAO {
    @Query("SELECT t.* FROM  Telefone t JOIN contato c ON t.contato_Id = c.id WHERE t.contato_id= :contatoId LIMIT 1")
    Telefone buscaPrimeiroTelefoneDoContato(int contatoId);
}
