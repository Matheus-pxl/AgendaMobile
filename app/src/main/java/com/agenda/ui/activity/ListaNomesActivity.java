package com.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.agenda.R;
import com.agenda.model.Contato;
import com.agenda.ui.ListaContatosView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaNomesActivity extends AppCompatActivity {

    private final ListaContatosView listaContatosView = new ListaContatosView(this);

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
            listaContatosView.confirmaRemocao(item);

        }
        return super.onContextItemSelected(item);

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
        listaContatosView.atualizaContatos();
    }


    private void configuraLista() {
        ListView listaNomes = findViewById(R.id.lista_nomes); //bind da list view
        listaContatosView.configuraAdapter(listaNomes);
        configuraListenerDeClickPorItem(listaNomes);
        registerForContextMenu(listaNomes);//registando o botao de contexto para remover contato

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


}
