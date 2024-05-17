package com.agenda;

import android.app.Application;

import com.agenda.dao.ContatoDAO;
import com.agenda.model.Contato;

public class AgendaApplication extends Application { // PARA NAO CRIAR NOVOS CONTATOS AO ROTACIONAR, É PRECIOSO CRIAR UMA APPLICATION
    @Override
    public void onCreate() {
        super.onCreate();
        criaContatosDeTeste();
    }

    private static void criaContatosDeTeste() {
        ContatoDAO dao = new ContatoDAO();
        dao.salva(new Contato("Alex", "1122223333", "alex@alura.com.br"));
        dao.salva(new Contato("Aasodkaodlex", "1122223333", "alex@alura.com.br"));
    }
}
