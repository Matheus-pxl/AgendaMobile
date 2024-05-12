package com.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.agenda.R;
import com.agenda.dao.AlunoDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaNomesActivity extends AppCompatActivity {
// quando colocamos o AppCompatActivity, ele vai manter a App bar com o título do nosso projeto, que no caso é "Agenda".
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Agenda");
        setContentView(R.layout.activity_lista_nomes);


        FloatingActionButton botaoNovoAluno = findViewById(R.id.lista_fab_novo_aluno);//primeiro é feito o bind do FLOATING BUTTON
        botaoNovoAluno.setOnClickListener(v -> { //depois o click para escutar o floating butao para ir ao formulario
            startActivity(new Intent(ListaNomesActivity.this, FormularioNomeActivity.class));//referencia da activity que voce esta, activity que voce quer ir(no caso lista de nomes, indo para formulario
            finish(); // finaliza a activity de formulario e volta para a anterior
        });
    }
    @Override
    protected void onResume(){
        super.onResume();

        AlunoDAO dao = new AlunoDAO();
        ListView listaNomes = findViewById(R.id.lista_nomes); //bind da list view
        listaNomes.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.todos())); //implementando adapter na listview

    }
}
