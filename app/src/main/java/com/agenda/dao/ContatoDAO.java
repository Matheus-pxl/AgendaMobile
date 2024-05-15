package com.agenda.dao;

import com.agenda.model.Contato;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    private final static List<Contato> contatos = new ArrayList<>();
    private int contadorDeIds = 1;

    public List<Contato> todos() {
        return new ArrayList<>(contatos);
    }

    public void salva(Contato contato) {
        contato.setId(contadorDeIds);
        contatos.add(contato);
        contadorDeIds++;
    }

    public void edita(Contato contato) { //update
        Contato contatoEncontrado = null;
        contatoEncontrado = buscaContatoPeloId(contato);
        if (contatoEncontrado != null) {
            int posicaoAluno = contatos.indexOf(contatoEncontrado);     //depois é verificado se ele realmente existe
            contatos.set(posicaoAluno, contato);                        //e aplicado a edição
        }
    }

    private Contato buscaContatoPeloId(Contato contato) {
        for (Contato a :
                contatos) {                         //primeiro
            if (a.getId() == contato.getId()) {     //é encontrado
                return a;              //o nome
            }
        }
        return null;
    }

    public void remove(Contato contato) {//remove
        Contato contatoDevolvido = buscaContatoPeloId(contato);
        if(contatoDevolvido!=null){
        contatos.remove(contatoDevolvido);

        }
    }


}
