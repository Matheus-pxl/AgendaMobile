package com.agenda.ui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.agenda.R;
import com.agenda.dao.ContatoDAO;
import com.agenda.model.Contato;
import com.agenda.ui.adapter.ListaContatosAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListaNomesActivity extends AppCompatActivity {
    private final ContatoDAO dao = new ContatoDAO();

    private ListaContatosAdapter adapter;

    // quando colocamos o AppCompatActivity, ele vai manter a App bar com o título do nosso projeto, que no caso é "Agenda".
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Agenda");
        setContentView(R.layout.activity_lista_nomes);
        configuraFabNovoAluno();
        configuraLista();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //menu.add("Remover");//adicionando um menu de contexto para remover um contato
        getMenuInflater().inflate(R.menu.activity_lista_contato_menu, menu);//para usar o MENU activity_lista_contato_menu precisa usar o menu inflater
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {//SELECIONANDO MENU CONTEXTO DE REMOVER CONTATO (ativo quando segurar o contato)
        int itemId = item.getItemId();//buscando o ID

        if (itemId == R.id.activity_lista_nome_menu_remover) {//verificando se esta clicando no botao de remoção correto -> "Remover"
            confirmaRemocao(item);

        }
        return super.onContextItemSelected(item);

    }

    private void confirmaRemocao(@NonNull final MenuItem item) {
        new AlertDialog.Builder(this).
                setTitle("Removendo contato!").
                setMessage("Tem certeza que deseja remover este contato?").
                setPositiveButton("Sim", new DialogInterface.OnClickListener() {//implementação do clique  "sim" do dialog REMOVER
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();//convertando menu info para menuinfo do adapter view
                        Contato contatoEscolhido = adapter.getItem(menuInfo.position);    //buscando o contato pela posição
                        remove(contatoEscolhido);//removendo contato
                    }
                }).
                setNegativeButton("Não",null).//
                show();  //DIALOG DE ALERTA PARA QUANDO CLICAR NO BOTAO REMOVER
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.lista_fab_novo_aluno);//primeiro é feito o bind do FLOATING BUTTON
        botaoNovoAluno.setOnClickListener(v -> { //depois o click para escutar o floating butao para ir ao formulario
            abreFormularioModoInsereContato();
        });
    }

    private void abreFormularioModoInsereContato() {
        startActivity(new Intent(ListaNomesActivity.this, FormularioNomeActivity.class));//referencia da activity que voce esta, activity que voce quer ir(no caso lista de nomes, indo para formulario
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaContatos();
    }

    private void atualizaContatos() {
        adapter.atualiza(dao.todos());
    }

    private void configuraLista() {
        ListView listaNomes = findViewById(R.id.lista_nomes); //bind da list view
        configuraAdapter(listaNomes);
        configuraListenerDeClickPorItem(listaNomes);
        registerForContextMenu(listaNomes);//registando o botao de contexto para remover contato

    }

    private void remove(Contato contato) {
        dao.remove(contato);//remove contato da view
        adapter.remove(contato);//remove contato do dao
    }

    private void configuraListenerDeClickPorItem(ListView listaNomes) {
        listaNomes.setOnItemClickListener((adapterView, view, posicao, id) -> {
            Contato contatoEscolhido = (Contato) adapterView.getItemAtPosition(posicao); //pega o aluno de acordo com sua posicao
            abreFormularioModoEditaContato(contatoEscolhido);
        });
    }

    private void abreFormularioModoEditaContato(Contato contato) {
        Intent vaiParaFormularioActivity = new Intent(ListaNomesActivity.this, FormularioNomeActivity.class);
        vaiParaFormularioActivity.putExtra("nome", contato); // para mandar DADOS VIA EXTRA ele precisa ser "serializable"
        startActivity(vaiParaFormularioActivity);
    }

    private void configuraAdapter(ListView listaNomes) {      // o adapter nao aceita um layout com mais de um textview, é preciso criar um adapter PERSONALIZADO
        adapter = new ListaContatosAdapter(this);

        listaNomes.setAdapter(adapter);
    }
}
