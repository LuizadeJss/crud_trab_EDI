package com.estruturadados.vetor.model;

import java.util.Random;

public class Pessoa {

    Random random = new Random();
    int idRandom = random.nextInt(10000);
    private int id;
    private String nome;
    private String email;

    private void setId(int idRandom) {
        this.id = idRandom;
    }

    public int getId(){
        return idRandom;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    //apresenta os dados de cada objeto de maneira personalizada
    @Override
    public String toString(){
        return "Nome: " + this.nome + " | Email: " + this.email;
    }

}
