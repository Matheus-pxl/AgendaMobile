package com.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.agenda.model.Contato;

import java.util.List;

@Dao
public interface RoomContatoDao {

    @Insert
    void salva(Contato contato);


    @Query("SELECT * FROM contato")
    List<Contato> todos();

    @Delete
    void remove(Contato contato);

    @Update
    void edita(Contato contato);
}
