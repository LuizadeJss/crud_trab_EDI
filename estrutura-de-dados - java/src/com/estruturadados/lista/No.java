package com.estruturadados.lista;

import com.estruturadados.vetor.model.Pessoa;

public class No<T>{
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

    @Override
    public String toString() {
        return "No{" +
                "pessoa=" + pessoa +
                ", proximo=" + proximo +
                '}';
    }
}
