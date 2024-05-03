package com.estruturadados.lista;

import com.estruturadados.vetor.model.Pessoa;

import java.util.Objects;
import java.util.Scanner;

public class Lista<T>
{
    Scanner entrada = new Scanner(System.in);
    private No<Pessoa> inicio;
    private No<Pessoa> ultimo;
    private int tamanho = 0;

    public int getTamanho() {
        return tamanho;
    }

    @Override
    public String toString() {

        if(this.tamanho == 0){
            return "[Lista vazia]";
        }

        StringBuilder builder = new StringBuilder();

        No<Pessoa> atual = this.inicio;

        //insere a primeira pessoa na lista
        builder.append(atual.getPessoa()).append("\n");

        while (atual.getProximo() != null)
        {
            atual = atual.getProximo();
            //continua iterando as pessoas na lista, até que chegue ao ultimo elemento
            builder.append(atual.getPessoa()).append("\n");
        }

        return builder.toString();
    }
    public void adiciona(){


        System.out.println("--------------------------------------------------Adicionando uma Pessoa-----------------------------------------------");

        System.out.print("Nome: ");
        String nome = entrada.nextLine();
        System.out.print("Email: ");
        String email = entrada.nextLine();

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setEmail(email);

        No<Pessoa> celula = new No<Pessoa>(pessoa);

        System.out.println(pessoa.getNome() + " com id: " + pessoa.getId() + " adicionado(a) com sucesso!");

        if(this.tamanho == 0)
        {
            this.inicio = celula;
        }
        else
        {
            this.ultimo.setProximo(celula);
        }
        this.ultimo = celula;
        this.tamanho++;
    }

    public void alteraPessoa(){
        String nome;
        String email;

        System.out.println("--------------------------------------------------Alterando uma Pessoa-----------------------------------------------");

        System.out.println("Insira o id da pessoa que deseja alterar: ");
        int id = entrada.nextInt();

        No<Pessoa> noAtual = this.inicio;

        for(int i = 0; i < this.tamanho; i++)
        {
            if(noAtual.getPessoa().getId() == id)
            {
                System.out.println("Nome atual é: " +noAtual.getPessoa().getNome() +". Deseja alterar?(s/n) ");
                String opcao = entrada.next();

                if(Objects.equals(opcao, "s")){
                    System.out.println("Alterar nome para: ");
                    nome = entrada.next();
                    noAtual.getPessoa().setNome(nome);
                }

                System.out.println("Email atual é: " + noAtual.getPessoa().getEmail() +". Deseja alterar?(s/n): ");
                opcao = entrada.next();

                if(Objects.equals(opcao, "s")) {
                    System.out.println("Alterar email para: ");
                    email = entrada.next();
                    noAtual.getPessoa().setEmail(email);
                }
                System.out.println("\nAlterações feitas com sucesso!\n");
                break;
            }
            noAtual = noAtual.getProximo();
        }
    }
    public void buscarPessoa(){

        System.out.println("--------------------------------------------------Buscando uma Pessoa-----------------------------------------------");


        System.out.println("Insira o email da pessoa que deseja buscar: ");
        String email = entrada.next();

        No<Pessoa> noAtual = this.inicio;

        for(int i = 0; i < this.tamanho; i++)
        {
            if(noAtual.getPessoa().getEmail().equals(email))
            {
                System.out.println("Nome: " + noAtual.getPessoa().getNome());
                break;
            }
            noAtual = noAtual.getProximo();
        }
    }

    public void ordenacao(Lista lista) {

        boolean swapped;
        do {
            swapped = false;
            No<Pessoa> noAtual = inicio;
            No<Pessoa> anterior = null;

            while (noAtual.getProximo() != null) {
                if (noAtual.getPessoa().getNome().compareTo(noAtual.getProximo().getPessoa().getNome()) > 0) {

                    // Troca os nós
                    No<Pessoa> noTroca = noAtual.getProximo();
                    noAtual.setProximo(noTroca.getProximo());
                    noTroca.setProximo(noAtual);

                    if (anterior != null) {
                        anterior.setProximo(noTroca);
                    } else {
                        lista.inicio = noTroca;
                    }

                    anterior = noTroca;
                    swapped = true;
                }
                else {
                    anterior = noAtual;
                    noAtual = noAtual.getProximo();
                }
            }
        } while (swapped);
    }
    public void excluirPessoaInicio()
    {
        Pessoa removido = this.inicio.getPessoa();

        this.inicio = this.inicio.getProximo();

        this.tamanho--;

        if(this.tamanho == 0)
        {
            this.ultimo = null;
        }
        System.out.println(removido.getNome()+ " removido com sucesso");
    }
    public void excluirPessoaFinal(int id)
    {
        No<Pessoa> penultimoNo = this.buscaNo(id);

        Pessoa removido = penultimoNo.getPessoa();

        penultimoNo.setProximo(null);

        this.ultimo = penultimoNo;

        this.tamanho--;

        System.out.println(removido.getNome()+ " removido com sucesso");
    }
    public void excluirPessoa(){

        System.out.println("--------------------------------------------------Excluindo Pessoa--------------------------------------------------");

        System.out.println("Insira o id da pessoa que deseja excluir: ");
        int id = entrada.nextInt();

        //verifica se a lista está vazia
        if(this.tamanho == 0)
            System.out.println("A lista está vazia");
        //verifica se o elemento é o primeiro da lista
        else if(inicio.getPessoa().getId() == id)
            excluirPessoaInicio();
        //verifica se o elemento é o último da lista
        else if(ultimo.getPessoa().getId() == id)
            excluirPessoaFinal(id);
        else {
            No<Pessoa> noAnterior = this.buscaNoAnte(id);
            No<Pessoa> noAtual = noAnterior.getProximo();
            No<Pessoa> noProximo = noAtual.getProximo();

            Pessoa removido = noAtual.getPessoa();

            noAnterior.setProximo(noProximo);

            noAtual.setProximo(null);

            this.tamanho--;
            System.out.println(removido.getNome() + " removido com sucesso");
        }
    }

    public void listarPessoas() {

        System.out.println("--------------------------------------------------Listagem de Pessoas--------------------------------------------------");

        if (this.tamanho == 0)
        {
            System.out.println("Lista vazia");
        }
        else
        {
            No<Pessoa> atual = this.inicio;

            for(int i = 0; i < this.tamanho; i++)
            {
                System.out.println(atual.getPessoa());
                atual = atual.getProximo();
            }

            System.out.println("-----------------------------------------------------------------------------------------------------------------------");

        }
    }

    public void limparLista(){
        System.out.println("--------------------------------------------------Limpando Lista--------------------------------------------------");

        for(No<Pessoa> atual = this.inicio; atual != null;)
        {
            No<Pessoa> proximo = atual.getProximo();
            atual.setPessoa(null);
            atual.setProximo(null);
            atual = proximo;
        }

        this.inicio = null;
        this.ultimo =  null;
        this.tamanho = 0;

        System.out.println("Lista limpa com sucesso!");
        System.out.println("------------------------------------------------------------------------------------------------------------------");

    }

    public No<Pessoa> buscaNo(int id){

        No<Pessoa> noAtual = this.inicio;

        for(int i = 0; i < this.tamanho; i++)
        {
            if(noAtual.getPessoa().getId() == id)
            {
                return noAtual;
            }
            noAtual = noAtual.getProximo();
        }
        return null;

    }

    public No<Pessoa> buscaNoAnte(int id){

        No<Pessoa> noAtual = this.inicio;
        No<Pessoa> noAnterior = noAtual;

        for(int i = 0; i < this.tamanho; i++)
        {
            if(noAtual.getPessoa().getId() == id)
            {
                return noAnterior;
            }
            noAnterior = noAtual;
            noAtual = noAtual.getProximo();
        }
        return null;

    }
}
