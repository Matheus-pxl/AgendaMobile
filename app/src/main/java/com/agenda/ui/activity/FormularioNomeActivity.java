package com.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
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
        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // inflando bptao salvar no formulario
        getMenuInflater().inflate(R.menu.activity_formulario_contato_menu, menu); // APENAS inflando com o activity_formulario_contato_menu

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { // procurando o id e verificando qual botao apertou para finalizar o formulario
        int itemId = item.getItemId();
        if(itemId==R.id.activity_formulario_contato_salvar){
            finalizarFormulario();
        }
        return super.onOptionsItemSelected(item);
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