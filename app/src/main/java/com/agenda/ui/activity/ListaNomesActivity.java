package com.agenda.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.agenda.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaNomesActivity extends AppCompatActivity {
// quando colocamos o AppCompatActivity, ele vai manter a App bar com o título do nosso projeto, que no caso é "Agenda".
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Agenda");
        setContentView(R.layout.activity_lista_nomes);

        List<String> alunos=new ArrayList<>(Arrays.asList("alex", "joao", "simba"));
        ListView listaNomes = findViewById(R.id.lista_nomes);
        listaNomes.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,alunos));//implementando adapter na listview


    }
}