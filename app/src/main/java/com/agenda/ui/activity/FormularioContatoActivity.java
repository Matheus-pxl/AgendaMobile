package com.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.agenda.R;
import com.agenda.database.AgendaDatabase;
import com.agenda.database.dao.RoomContatoDao;
import com.agenda.model.Contato;

public class FormularioContatoActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoSobrenome;
    private EditText campoTelefone;
    private EditText campoCelular;
    private EditText campoEmail;
    private RoomContatoDao dao;
    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nome);

        AgendaDatabase database = AgendaDatabase.getInstance(this);
        dao = database.getRoomContatoDAO();
        inicializacaoCampos();
        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // inflando botao salvar no formulario
        getMenuInflater().inflate(R.menu.activity_formulario_contato_menu, menu); // APENAS inflando com o activity_formulario_contato_menu

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { // buscando o id e verificando qual botao apertou para finalizar o formulario
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
        campoEmail.setText(contato.getEmail());
        campoSobrenome.setText(contato.getSobrenome());
//        campoTelefone.setText(contato.getTelefone());
//        campoCelular.setText(contato.getCelular());
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
        campoSobrenome= findViewById(R.id.lista_campo_sobrenome);
        campoEmail = findViewById(R.id.lista_campo_email);
//        campoTelefone = findViewById(R.id.lista_campo_telefone);
//        campoCelular =findViewById(R.id.lista_campo_celular);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String sobrenome = campoSobrenome.getText().toString();
        String email = campoEmail.getText().toString();
//        String telefone = campoTelefone.getText().toString();
//        String celular = campoCelular.getText().toString();

        this.contato.setNome(nome);
        this.contato.setSobrenome(sobrenome);
        this.contato.setEmail(email);
//        this.contato.setTelefone(telefone);
//        this.contato.setCelular(celular);
    }
}