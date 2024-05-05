package com.estruturadados.lista;

import com.estruturadados.vetor.model.Pessoa;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
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
                    System.out.print("Alterar nome para: ");
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
                System.out.println("Pessoa: " + noAtual.getPessoa().getId() + "| Nome: " + noAtual.getPessoa().getNome() + " | Email: " + noAtual.getPessoa().getEmail());
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

        System.out.println("Ordenação realizada com sucesso!");
        this.listarPessoas();
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
    public void persistirDados(){
        System.out.println("--------------------------------------------------Persistindo os dados--------------------------------------------------\n" +
                "1 - Criar arquivo Json\n" +
                "2 - Criar arquivo xml\n" +
                "3 - Criar arquivo binário\n" +
                "4 - Ler arquivo json\n" +
                "5 - Ler arquivo xml\n" +
                "6 - Ler arquivo binário\n" +
                "0 - Sair\n");

        int opcao = 1;
        opcao = entrada.nextInt();

        switch (opcao){
            case(0):
                break;
            case(1):
                persistirEmJson();
                break;
            case(2):
                persistirEmXml();
                break;
            case(3):
                persistirEmBinario();
                break;
            case(4):
                lerJson();
                break;
            case(5):
                lerXml();
                break;
            case(6):
                lerBinario();
                break;
            default:
                System.out.println("Opção inválida");
        }
    }

    public void persistirEmJson(){

        try (FileWriter writer = new FileWriter("arquivosJson/lista-encadeada-pessoas.json")) {
            writer.write("\"Pessoas\":[\n");

            No<Pessoa> noAtual = inicio;

            while (noAtual != null) {
                writer.write(String.format("  {\"id\": %d, \"nome\": \"%s\", \"email\": \"%s\"}\n",
                        noAtual.getPessoa().getId(),
                        noAtual.getPessoa().getNome(),
                        noAtual.getPessoa().getEmail()));

                noAtual = noAtual.getProximo();
            }

            writer.write("]\n");
            writer.close();
            System.out.println("Arquivo json criado com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro na criação do arquivo Json");
        }

    }

    public void lerJson(){
        No<Pessoa> noAtual = null;

        try (BufferedReader reader = new BufferedReader(new FileReader("arquivosJson/lista-encadeada-pessoas.json"))) {
            String linha;

            reader.readLine(); // Ignora a primeira linha

            while ((linha = reader.readLine()) != null && linha.charAt(0) != ']') {

                Pessoa pessoa = new Pessoa(); // Inicializa com valores padrão

                int indexInicial = linha.indexOf("\"id\":") + "\"id\":".length();
                int indexFinal = linha.indexOf(",", indexInicial);
                int id = Integer.parseInt(linha.substring(indexInicial, indexFinal).trim());;

                pessoa.setId(id);

                indexInicial = linha.indexOf("\"nome\": ") + "\"nome\": ".length() + 1; // +1 para ignorar aspas
                indexFinal = linha.indexOf("\"", indexInicial);
                String nome = linha.substring(indexInicial, indexFinal);

                pessoa.setNome(nome);

                indexInicial = linha.indexOf("\"email\": ") + "\"email\": ".length() + 1; // +1 para ignorar aspas
                indexFinal = linha.indexOf("\"", indexInicial);
                String email = linha.substring(indexInicial, indexFinal);

                pessoa.setEmail(email);

                if(noAtual == null)
                {
                    noAtual = new No<>(pessoa);
                }
                else {
                    noAtual.adicionarNo(pessoa);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(noAtual);
    }
    public void persistirEmXml(){
        try (FileWriter writer = new FileWriter("arquivosXML/lista-encadeada-pessoas.xml")) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<pessoas>\n");

            No<Pessoa> noAtual = inicio;

            while (noAtual != null) {
                writer.write(String.format("   <pessoa id=\"%d\">\n", noAtual.getPessoa().getId()));
                writer.write(String.format("      <nome>%s</nome>\n", noAtual.getPessoa().getNome()));
                writer.write(String.format("      <email>%s</email>\n", noAtual.getPessoa().getEmail()));
                writer.write("   </pessoa>\n");
                noAtual = noAtual.getProximo();
            }

            writer.write("</pessoas>\n");

            System.out.println("Arquivo xml salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo xml.");
        }
    }
    public void lerXml(){
        No<Pessoa> noAtual = null;

        try (BufferedReader reader = new BufferedReader(new FileReader("arquivosXML/lista-encadeada-pessoas.xml"))) {
            String linha;

            reader.readLine(); // <?xml version="1.0" encoding="UTF-8"?>
            reader.readLine(); // <pessoa>

            while (!(linha = reader.readLine()).equals("</pessoas>")) {
                Pessoa pessoa = new Pessoa(); // Inicializa com valores padrão

                int indexIdInicio = linha.indexOf("id=\"") + 4; // 4 é o número fixo de caractres que o id pode conter
                int indexIdFim = linha.indexOf("\">", indexIdInicio);
                int id = Integer.parseInt(linha.substring(indexIdInicio, indexIdFim));

                pessoa.setId(id);

                String nome = reader.readLine().trim(); // nome
                pessoa.setNome(nome.substring(6, nome.length() - 7)); // remove a tag <nome> e </nome>

                //reader.readLine(); // <email>
                String email = reader.readLine().trim(); // lê a próxima linha e remove espaços em branco
                pessoa.setEmail(email.substring(7, email.length() - 8)); // remove a tag <email> e </email>

                if(noAtual == null){
                    noAtual = new No<>(pessoa);
                }
                else
                    noAtual.adicionarNo(pessoa);

                reader.readLine(); // próxima <pessoa>
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(noAtual);

}
    public void persistirEmBinario(){
        No<Pessoa> noAtual = inicio;

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("arquivosBinarios/lista-encadeada-pessoas.bin"))) {

            while (noAtual != null) {
                outputStream.writeObject(noAtual.getPessoa());
                noAtual = noAtual.getProximo();
            }

            System.out.println("Arquivo binário salvo!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo binário.");
        }
    }

    public void lerBinario(){

        No<Pessoa> noAtual = null;
        Pessoa pessoa;

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("arquivosBinarios/lista-encadeada-pessoas.bin"))) {

            while ((pessoa = (Pessoa) objectInputStream.readObject()) != null){

                if(noAtual == null){
                    noAtual = new No<>(pessoa);
                }
                else
                {
                    noAtual.adicionarNo(pessoa);
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(noAtual);

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
                if(atual.getProximo()!=null)
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
