package com.agenda.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.agenda.database.dao.RoomContatoDao;
import com.agenda.model.Contato;

@Database(entities = {Contato.class}, version = 1, exportSchema = false)
//precisa definir a classe ENTIDADE
 public abstract class AgendaDatabase extends RoomDatabase {
    //definir a classe ABSTRACT nao é necessario implementar os argumentos
    private static String NOME_BANCO_DE_DADOS;

    public abstract RoomContatoDao getRoomContatoDAO();

    public static AgendaDatabase getInstance(Context context) {
        NOME_BANCO_DE_DADOS = "agenda.db";
        return Room.databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DE_DADOS).allowMainThreadQueries().build();

    }


}
