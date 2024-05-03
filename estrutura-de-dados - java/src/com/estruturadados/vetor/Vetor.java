package com.estruturadados.vetor;

import com.estruturadados.vetor.model.Pessoa;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Vetor {

    Scanner entrada = new Scanner(System.in);
    private Pessoa[] elementos;

    private int tamanho;
    public Vetor(int capacidade)
    {
        this.elementos = new Pessoa[capacidade];
        this.tamanho = 0;
    }

    @Override
    public String toString(){

        StringBuilder s = new StringBuilder();
        s.append("[");

        for(int i = 0; i < tamanho-1; i++){
            s.append(elementos[i]);
            s.append(", ");
        }

        if(tamanho > 0){
            s.append(elementos[tamanho-1]);
        }
        s.append("]");

        return s.toString();
    }

    public int getTamanho(){
        return this.tamanho;
    }

    public void adicionar()
    {
        System.out.println("--------------------------------------------------Adicionando uma Pessoa-----------------------------------------------");

        System.out.print("Nome: ");
        String nome = entrada.nextLine();
        System.out.print("Email: ");
        String email = entrada.nextLine();

        //Cria uma nova pessoa
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setEmail(email);

        //Insere a pessoa na próxima posição vazia do vetor
        if (tamanho < elementos.length)
        {
            elementos[tamanho] = pessoa;
            tamanho++;
            System.out.println("\n" + nome + " adicionado(a) com sucesso!");
        } else
        {
            System.out.println("Ocorreu um erro!");
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
    }

    public void alterarPessoa(){
        String nome;
        String email;

        System.out.println("--------------------------------------------------Alterando uma Pessoa-----------------------------------------------");

        System.out.println("Insira o id da pessoa que deseja alterar: ");
        int id = entrada.nextInt();

        for (int i = 0; i < tamanho; i++)
        {
            if(elementos[i].getId() == id)
            {
                System.out.println("Nome atual é: " + elementos[i].getNome() +". Deseja alterar?(s/n) ");
                String opcao = entrada.next();

                if(Objects.equals(opcao, "s")){
                    System.out.println("Alterar nome para: ");
                    nome = entrada.next();
                    elementos[i].setNome(nome);
                }

                System.out.println("Email atual é: " + elementos[i].getEmail() +". Deseja alterar?(s/n): ");
                opcao = entrada.next();

                if(Objects.equals(opcao, "s")) {
                    System.out.println("Alterar email para: ");
                    email = entrada.next();
                    elementos[i].setEmail(email);
                }

                System.out.println("\nAlterações feitas com sucesso\n");
            }
        }

        System.out.println("---------------------------------------------------------------------------------------------------------------------");


    }

    public void buscarPessoa()
    {
        System.out.println("--------------------------------------------------Buscando Pessoa--------------------------------------------------");

        System.out.println("Insira o email da pessoa que deseja buscar: ");
        String email = entrada.next();

        for (int i = 0; i < tamanho; i++)
        {
            if (Objects.equals(elementos[i].getEmail(), email))
            {
                System.out.println("Nome: " + elementos[i].getNome() + "\nEmail: " + elementos[i].getEmail());
            }
        }

        System.out.println("-------------------------------------------------------------------------------------------------------------------");

    }

    //opcao 4
    public void ordenarPessoas(){
        Arrays.sort(elementos, 0, tamanho, Comparator.comparing(Pessoa::getNome));

    }

    //opcao 5
    public void exluirPessoa()
    {
        System.out.println("--------------------------------------------------Excluindo Pessoa--------------------------------------------------");

        System.out.println("Insira o id da pessoa que deseja excluir: ");
        int id = entrada.nextInt();

        int posicaoParaexclusao = 0;

        for (int i = 0; i < tamanho; i++)
        {
            if (elementos[i].getId() == id)
            {
                posicaoParaexclusao = i;
            }
        }

        for(int i = posicaoParaexclusao; i < tamanho; i++){
            elementos[i] = elementos[i+1];
        }
        System.out.println("\nExclusão realizada com sucesso!");
        tamanho--;

        System.out.println("-------------------------------------------------------------------------------------------------------------------");

    }

    public void persistirDados(){
        int opcao = 1;
        System.out.println("--------------------------------------------------Persistindo os dados--------------------------------------------------\n" +
                "1 - Criar arquivo Json\n" + //ok
                "2 - Criar arquivo xml\n" +
                "3 - Criar arquivo binário\n" +
                "4 - Ler arquivo json\n" +
                "5 - Ler arquivo xml\n" +
                "6 - Ler arquivo binário\n" +
                "0 - Sair\n");

        opcao = entrada.nextInt();

        switch (opcao){
            case(0):
                break;
            case(1):
                persistirEmJson();
                break;
            case(2):
                //persistirEmXml();
                break;
            case(3):
                //persistirEmBinario();
                break;
            case(4):
                lerJson();
                break;
            case(5):
                //lerXml();
                break;
            case(6):
                //lerBinario();
                break;
            default:
                System.out.println("Opção inválida");
        }

    }
    public void persistirEmJson(){
        //Cria um Objeto JSON1
        JSONObject[] jsonObject = new JSONObject[tamanho];
        JSONArray jsonArrays = new JSONArray();

        FileWriter writeFile = null;

        for(int i = 0; i < tamanho; i++) {
            //Armazena dados em um Objeto JSON
            JSONObject object = new JSONObject();

            object.put("id", elementos[i].getId());
            object.put("nome", elementos[i].getNome());
            object.put("email", elementos[i].getEmail());

            jsonArrays.add(object);
        }

        try{
            writeFile = new FileWriter("arquivosJson/pessoas.json");

            //Escreve no arquivo conteudo do Objeto JSON

            writeFile.write(jsonArrays.toString());

            writeFile.close();

            System.out.println("Arquivo json gerado com sucesso!");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void lerJson(){

        JSONArray jsonArray;

        //Cria o parse de tratamento
        JSONParser parser = new JSONParser();

        //Variaveis que irao armazenar os dados do arquivo JSON
        String nome;
        String email;

        try {
            //Salva no objeto JSONObject o que o parse tratou do arquivo
            jsonArray = (JSONArray) parser.parse(new FileReader("arquivosJson/pessoas.json"));

            //Salva nas variaveis os dados retirados do arquivo
            nome = "Luiza";
            email = "(String) jsonArray.get()";

            System.out.printf("Nome: %s\nEmail: %s\n", nome, email);
        }
        //Trata as exceptions que podem ser lançadas no decorrer do processo
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    //opcao 7
    public void listarPessoas()
    {
        System.out.println("--------------------------------------------------Listagem de Pessoas--------------------------------------------------");

        for(int i = 0; i < tamanho; i++)
        {
            System.out.println(i+1 + " - " + elementos[i].getNome() + ", seu email é "+ elementos[i].getEmail() + " e seu id é " + elementos[i].getId());
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------");

    }
}



