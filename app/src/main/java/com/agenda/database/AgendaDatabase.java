package com.agenda.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.agenda.database.converter.ConversorCalendar;
import com.agenda.database.dao.RoomContatoDao;
import com.agenda.model.Contato;

@Database(entities = {Contato.class}, version = 4, exportSchema = false)
//precisa definir a classe ENTIDADE
@TypeConverters({ConversorCalendar.class})
 public abstract class AgendaDatabase extends RoomDatabase {
    //definir a classe ABSTRACT nao é necessario implementar os argumentos
    private static String NOME_BANCO_DE_DADOS = "agenda.db";

    public abstract RoomContatoDao getRoomContatoDAO();

    public static AgendaDatabase getInstance(Context context) {
                                                                                                                        //PRECISA INFORMAR A MIGRATION (ANTERIOR, NOVA)
        return Room.databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DE_DADOS).allowMainThreadQueries().addMigrations(new Migration(1, 2) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("ALTER TABLE contato ADD COLUMN sobrenome TEXT");
            }
        }, new Migration(2, 3) {//FAZENDO MIGRATION CASO SEJA NECESSARIO REMOVER O CAMPO SOBRENOME
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                // criar NOVA tabela com as informações desejadas
                database.execSQL("CREATE TABLE IF NOT EXISTS `Contato_novo` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `telefone` TEXT, `email` TEXT, `sobrenome` TEXT)");
                //copiar dados da tabela antiga para a nova
                database.execSQL("INSERT INTO Contato_novo(id, nome, telefone, email) SELECT id, nome, telefone, email FROM contato");
                //remover tabela antiga
                database.execSQL("DROP TABLE contato");

                // renomear tabela nova com o nome da tabela antiga
                database.execSQL("ALTER TABLE Contato_novo RENAME TO contato");
            }
        }, new Migration(3,4) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("ALTER TABLE contato ADD COLUMN momentoDeCadastro INTEGER");
            }
        }).build();

    }


}
