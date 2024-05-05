package com.estruturadados.vetor.model;

import java.io.Serializable;
import java.util.Random;

public class Pessoa implements Serializable {

    Random random = new Random();
    int idRandom = random.nextInt(10000);
    private int id = idRandom;
    private String nome;
    private String email;

    public void setId(int id) {

        this.id = id;
    }

    public int getId(){

        return this.id;
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
        return "Pessoa: " + this.getId() + "| Nome: " + this.nome + " | Email: " + this.email + "\n";
    }

}
