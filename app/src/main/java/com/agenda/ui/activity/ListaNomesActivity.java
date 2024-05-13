package com.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.agenda.R;
import com.agenda.dao.AlunoDAO;
import com.agenda.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaNomesActivity extends AppCompatActivity {
        private final AlunoDAO dao = new AlunoDAO();
// quando colocamos o AppCompatActivity, ele vai manter a App bar com o título do nosso projeto, que no caso é "Agenda".
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Agenda");
        setContentView(R.layout.activity_lista_nomes);
        configuraFabNovoAluno();
        dao.salva(new Aluno("Alex", "1122223333", "alex@alura.com.br"));

    }

    private void configuraFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.lista_fab_novo_aluno);//primeiro é feito o bind do FLOATING BUTTON
        botaoNovoAluno.setOnClickListener(v -> { //depois o click para escutar o floating butao para ir ao formulario
            startActivity(new Intent(ListaNomesActivity.this, FormularioNomeActivity.class));//referencia da activity que voce esta, activity que voce quer ir(no caso lista de nomes, indo para formulario
            finish();
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        ListView listaNomes = findViewById(R.id.lista_nomes); //bind da list view
        List<Aluno> alunos = dao.todos();
        listaNomes.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos)); //implementando adapter na listview

        listaNomes.setOnItemClickListener((parent, view, posicao, id) -> {
            Aluno alunoEscolhido = alunos.get(posicao); //pega o aluno de acordo com sua posicao
            Intent vaiParaFormularioActivity = new Intent(ListaNomesActivity.this, FormularioNomeActivity.class);
            vaiParaFormularioActivity.putExtra("nome",alunoEscolhido); // para mandar DADOS VIA EXTRA ele precisa ser "serializable"
            startActivity(vaiParaFormularioActivity);
        });
    }
}
