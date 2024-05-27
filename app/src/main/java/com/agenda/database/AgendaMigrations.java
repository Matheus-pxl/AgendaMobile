package com.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

class AgendaMigrations {
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE contato ADD COLUMN sobrenome TEXT");
        }
    };
    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {//passos para atualizar um campo que foi alterado
            // criar NOVA tabela com as informações desejadas
            database.execSQL("CREATE TABLE IF NOT EXISTS `Contato_novo` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `telefone` TEXT, `email` TEXT, `sobrenome` TEXT)");
            //copiar dados da tabela antiga para a nova
            database.execSQL("INSERT INTO Contato_novo(id, nome, telefone, email) SELECT id, nome, telefone, email FROM contato");
            //remover tabela antiga
            database.execSQL("DROP TABLE contato");
            // renomear tabela nova com o nome da tabela antiga
            database.execSQL("ALTER TABLE Contato_novo RENAME TO contato");
        }
    };
    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {//inserir momento de cadastro
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE contato ADD COLUMN momentoDeCadastro INTEGER");
        }
    };
    private static final Migration MIGRATION_4_5 = new Migration(4,5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {//inserir novo campo na tabela
            database.execSQL("ALTER TABLE contato ADD COLUMN celular TEXT");
        }
    };
    public static final Migration[] TODAS_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4,MIGRATION_4_5};
}
