package com.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.agenda.dao.ContatoDAO;
import com.agenda.database.AgendaDatabase;
import com.agenda.database.dao.RoomContatoDao;
import com.agenda.model.Contato;
import com.agenda.ui.adapter.ListaContatosAdapter;

public class ListaContatosView {

    private final RoomContatoDao dao;
    private final ListaContatosAdapter adapter;
    private final Context context;

    public ListaContatosView(Context context) {
        this.context = context;
        this.adapter = new ListaContatosAdapter(this.context);
        dao = AgendaDatabase.getInstance(context).getRoomContatoDAO();
    }

    public void confirmaRemocao(@NonNull final MenuItem item) {
        //implementação do clique  "sim" do dialog REMOVER
        new AlertDialog.Builder(context).// precisa inicializar o contexto para nao ter nullpointer exception, usando o construtor
                setTitle("Removendo contato!").
                setMessage("Tem certeza que deseja remover este contato?").
                setPositiveButton("Sim", (dialog, which) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();//convertendo menu info para menuinfo do adapter view
                    Contato contatoEscolhido = adapter.getItem(menuInfo.position);    //buscando o contato pela posição
                    remove(contatoEscolhido);//removendo contato
                }).
                setNegativeButton("Não",null).//
                show();  //DIALOG DE ALERTA PARA QUANDO CLICAR NO BOTAO REMOVER
    }

    public void atualizaContatos() {
        adapter.atualiza(dao.todos());
    }


    private void remove(Contato contato) {
        dao.remove(contato);//remove contato da view
        adapter.remove(contato);//remove contato do dao
    }
    public void configuraAdapter(ListView listaNomes) {      // o adapter nao aceita um layout com mais de um textview, é preciso criar um adapter PERSONALIZADO

        listaNomes.setAdapter(adapter);
    }


}
