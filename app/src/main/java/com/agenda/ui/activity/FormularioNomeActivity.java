package com.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.agenda.R;
import com.agenda.dao.AlunoDAO;
import com.agenda.model.Aluno;

import java.io.Serializable;

public class FormularioNomeActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nome);
        setTitle("Novo contato");
        inicializacaoCampos();
        configuraBotaoSalvar();

        Intent dados = getIntent();
        Aluno aluno = (Aluno) dados.getSerializableExtra("nome");
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.lista_button_salvar);//bind da view do botao salvar
        botaoSalvar.setOnClickListener(v -> {
            startActivity(new Intent(FormularioNomeActivity.this, ListaNomesActivity.class));
            Aluno alunoCriado = criaAluno();
            salva(alunoCriado);
        });
    }

    private void inicializacaoCampos() {
        campoNome = findViewById(R.id.lista_campo_nome);
        campoTelefone = findViewById(R.id.lista_campo_telefone);
        campoEmail = findViewById(R.id.lista_campo_email);
    }

    private void salva(Aluno aluno) {
        dao.salva(aluno);
        finish();
    }

    @NonNull
    private Aluno criaAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        return new Aluno(nome, telefone, email);
    }
}