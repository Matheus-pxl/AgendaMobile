package com.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.agenda.R;
import com.agenda.dao.AlunoDAO;
import com.agenda.model.Aluno;

public class FormularioNomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nome);

        final AlunoDAO dao = new AlunoDAO();


        final EditText campoNome = findViewById(R.id.lista_campo_nome);
        final EditText campoTelefone = findViewById(R.id.lista_campo_telefone);
        final EditText campoEmail = findViewById(R.id.lista_campo_email);


        Button botaoSalvar = findViewById(R.id.lista_button_salvar);//bind da view do botao salvar
        botaoSalvar.setOnClickListener(new View.OnClickListener() {//adicionando interface do click listener no botao salvar
            @Override
            public void onClick(View v) {
                String nome = campoNome.getText().toString();
                String telefone = campoTelefone.getText().toString();
                String email = campoEmail.getText().toString();

                Aluno alunoCriado = new Aluno(nome, telefone, email);
                dao.salva(alunoCriado);

            }
        });



    }
}