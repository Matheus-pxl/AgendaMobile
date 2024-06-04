package com.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.agenda.R;
import com.agenda.database.AgendaDatabase;
import com.agenda.database.dao.TelefoneDAO;
import com.agenda.model.Contato;
import com.agenda.model.Telefone;

import java.util.ArrayList;
import java.util.List;

public class ListaContatosAdapter extends BaseAdapter {
    private final List<Contato> contatos = new ArrayList<>();
    private final Context context;

    public ListaContatosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Contato getItem(int position) {
        return contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contatos.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);// indicando que é false, ele nao se responsabiliza em criar view e adicionar diretamente na viewgroup
        Contato contatoDevolvido = contatos.get(position);

        vinculandoInformacoes(viewCriada, contatoDevolvido);

        return viewCriada;
    }

    private void vinculandoInformacoes(View view, Contato contato) {
        TextView nomeContato = view.findViewById(R.id.item_contato_nome);
        nomeContato.setText(contato.getNomeCompleto() + " " + contato.dataFormatada());//bindd do nome completo + data formatada da criaçao do contato

        TextView telefone = view.findViewById(R.id.item_contato_telefone);
        TelefoneDAO dao = AgendaDatabase.getInstance(context).getTelefoneDAO();
        Telefone primeiroTelefone = dao.buscaPrimeiroTelefoneDoContato(contato.getId());
        telefone.setText(primeiroTelefone.getNumero());

    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_contato, viewGroup, false);
    }

    public void atualiza(List<Contato> contatos) {
        this.contatos.clear();
        this.contatos.addAll(contatos);

        notifyDataSetChanged(); //notificando que o dataset da listview foi atualizado
    }

    public void remove(Contato contato) {
        contatos.remove(contato);//removendo contato da listview
        notifyDataSetChanged(); //notificando que o dataset da listview mudou
    }
};
