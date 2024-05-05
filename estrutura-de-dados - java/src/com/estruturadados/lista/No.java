package com.estruturadados.lista;

import com.estruturadados.vetor.model.Pessoa;

import java.io.Serializable;

public class No<T> {
    private Pessoa pessoa;
    private No<Pessoa> proximo;

    public No(Pessoa pessoa){
        this.pessoa = pessoa;
        this.proximo = null;
    }

    public No(Pessoa pessoa, No<Pessoa> proximo) {
        this.pessoa = pessoa;
        this.proximo = proximo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public No<Pessoa> getProximo() {
        return proximo;
    }

    public void setProximo(No<Pessoa> proximo) {
        this.proximo = proximo;
    }

    public void adicionarNo(Pessoa pessoa) {
        No<Pessoa> novoNo = new No<>(pessoa);
        if (this.proximo == null) {
            this.proximo = novoNo;
        } else {
            No<Pessoa> noTempo = this.proximo;
            while (noTempo.proximo != null) {
                noTempo = noTempo.proximo;
            }
            noTempo.proximo = novoNo;
        }
    }
    @Override
    public String toString() {
        return " " + pessoa + proximo;
    }
}
