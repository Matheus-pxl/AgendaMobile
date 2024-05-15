package com.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.agenda.R;
import com.agenda.dao.ContatoDAO;
import com.agenda.model.Contato;

public class FormularioNomeActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final ContatoDAO dao = new ContatoDAO();
    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nome);
        setTitle("Novo contato");
        inicializacaoCampos();
        configuraBotaoSalvar();
        carregaAluno();
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra("nome")) { //checando se os campos nao possuem valores nulos
            setTitle("Editar contato");
            contato = (Contato) dados.getSerializableExtra("nome");
            preencheCampos();
        } else {
            setTitle("Novo contato");
            contato = new Contato();
        }
    }

    private void preencheCampos() {
        campoNome.setText(contato.getNome());
        campoTelefone.setText(contato.getTelefone());
        campoEmail.setText(contato.getEmail());
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.lista_button_salvar);//bind da view do botao salvar
        botaoSalvar.setOnClickListener(v -> {

            finalizarFormulario();
        });
    }

    private void finalizarFormulario() {
        preencheAluno();
        if (contato.temIdValido()) {
            dao.edita(contato);
        } else {
            dao.salva(contato);
        }
        finish();
    }

    private void inicializacaoCampos() {
        campoNome = findViewById(R.id.lista_campo_nome);
        campoTelefone = findViewById(R.id.lista_campo_telefone);
        campoEmail = findViewById(R.id.lista_campo_email);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        this.contato.setNome(nome);
        this.contato.setTelefone(telefone);
        this.contato.setEmail(email);
    }
}