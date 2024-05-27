package com.agenda.database;

import static com.agenda.database.AgendaMigrations.TODAS_MIGRATIONS;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.agenda.database.converter.ConversorCalendar;
import com.agenda.database.converter.ConversorTipoTelefone;
import com.agenda.database.dao.RoomContatoDao;
import com.agenda.model.Contato;
import com.agenda.model.Telefone;

@Database(entities = {Contato.class, Telefone.class}, version = 6, exportSchema = false)
//precisa definir a classe ENTIDADE
@TypeConverters({ConversorCalendar.class, ConversorTipoTelefone.class})
public abstract class AgendaDatabase extends RoomDatabase {

    //definir a classe ABSTRACT nao é necessario implementar os argumentos
    //toda vez que for feita uma alteração é necessario redefinir a VERSAO do banco de dados
    private static String NOME_BANCO_DE_DADOS = "agenda.db";

    public abstract RoomContatoDao getRoomContatoDAO();

    public static AgendaDatabase getInstance(Context context) {
        //PRECISA INFORMAR A MIGRATION (ANTERIOR, NOVA)
        return Room.databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DE_DADOS).allowMainThreadQueries().addMigrations(TODAS_MIGRATIONS).build();

    }


}
