package com.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.agenda.R;
import com.agenda.dao.ContatoDAO;
import com.agenda.model.Contato;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaNomesActivity extends AppCompatActivity {
        private final ContatoDAO dao = new ContatoDAO();
    private ArrayAdapter<Contato> adapter;

    // quando colocamos o AppCompatActivity, ele vai manter a App bar com o título do nosso projeto, que no caso é "Agenda".
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Agenda");
        setContentView(R.layout.activity_lista_nomes);
        configuraFabNovoAluno();
        configuraLista();
//        dao.salva(new Nome("Alex", "1122223333", "alex@alura.com.br"));

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Remover");//adicionando um menu de contexto para remover um contato
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();//convertando menu info para menuinfo do adapter view
        Contato alunoEscolhido = adapter.getItem(menuInfo.position);                            //buscando o contato pela posição
        remove(alunoEscolhido);                                                                 //removendo contato da posiçao selecionada
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
    protected void onResume(){
        super.onResume();
        atualizaContatos();
    }

    private void atualizaContatos() {
        adapter.clear();
        adapter.addAll(dao.todos());
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

    private void configuraAdapter(ListView listaNomes) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);//tornando o adapter atributo da classe
        listaNomes.setAdapter(adapter); //implementando adapter na listview
    }
}
