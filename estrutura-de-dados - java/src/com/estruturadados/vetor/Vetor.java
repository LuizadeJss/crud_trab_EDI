package com.estruturadados.vetor;

import com.estruturadados.lista.No;
import com.estruturadados.vetor.model.Pessoa;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class Vetor {

    Scanner entrada = new Scanner(System.in);
    private Pessoa[] elementos;

    private int tamanho;

    public Vetor(int capacidade) {
        this.elementos = new Pessoa[capacidade];
        this.tamanho = 0;
    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();
        s.append("[");

        for (int i = 0; i < tamanho - 1; i++) {
            s.append(elementos[i]);
            s.append(", ");
        }

        if (tamanho > 0) {
            s.append(elementos[tamanho - 1]);
        }
        s.append("]");

        return s.toString();
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public void adicionar() {
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
        if (tamanho < elementos.length) {
            elementos[tamanho] = pessoa;
            tamanho++;
            System.out.println("\n" + nome + " adicionado(a) com sucesso!");
        } else {
            System.out.println("Ocorreu um erro!");
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
    }

    public void alterarPessoa() {
        String nome;
        String email;

        System.out.println("--------------------------------------------------Alterando uma Pessoa-----------------------------------------------");

        System.out.println("Insira o id da pessoa que deseja alterar: ");
        int id = entrada.nextInt();

        for (int i = 0; i < tamanho; i++) {
            if (elementos[i].getId() == id) {
                System.out.println("Nome atual é: " + elementos[i].getNome() + ". Deseja alterar?(s/n) ");
                String opcao = entrada.next();

                if (Objects.equals(opcao, "s")) {
                    System.out.println("Alterar nome para: ");
                    nome = entrada.next();
                    elementos[i].setNome(nome);
                }

                System.out.println("Email atual é: " + elementos[i].getEmail() + ". Deseja alterar?(s/n): ");
                opcao = entrada.next();

                if (Objects.equals(opcao, "s")) {
                    System.out.println("Alterar email para: ");
                    email = entrada.next();
                    elementos[i].setEmail(email);
                }

                System.out.println("\nAlterações feitas com sucesso\n");
            }
        }

        System.out.println("---------------------------------------------------------------------------------------------------------------------");


    }

    public void buscarPessoa() {
        System.out.println("--------------------------------------------------Buscando Pessoa--------------------------------------------------");

        System.out.println("Insira o email da pessoa que deseja buscar: ");
        String email = entrada.next();

        for (int i = 0; i < tamanho; i++) {
            if (Objects.equals(elementos[i].getEmail(), email)) {
                System.out.println("Pessoa: " + elementos[i].getId() + "| Nome: " + elementos[i].getNome() + " | Email: " + elementos[i].getEmail());
            }
        }

        System.out.println("-------------------------------------------------------------------------------------------------------------------");

    }

    //opcao 4
    public void ordenarPessoas() {
        Arrays.sort(elementos, 0, tamanho, Comparator.comparing(Pessoa::getNome));
        listarPessoas();
    }

    //opcao 5
    public void exluirPessoa() {
        System.out.println("--------------------------------------------------Excluindo Pessoa--------------------------------------------------");

        System.out.println("Insira o id da pessoa que deseja excluir: ");
        int id = entrada.nextInt();

        int posicaoParaexclusao = 0;

        for (int i = 0; i < tamanho; i++) {
            if (elementos[i].getId() == id) {
                posicaoParaexclusao = i;
            }
        }

        for (int i = posicaoParaexclusao; i < tamanho; i++) {
            elementos[i] = elementos[i + 1];
        }
        System.out.println("\nExclusão realizada com sucesso!");
        tamanho--;

        System.out.println("-------------------------------------------------------------------------------------------------------------------");

    }

    public void persistirDados() {
        int opcao = 1;
        System.out.println("--------------------------------------------------Persistindo os dados--------------------------------------------------\n" +
                "1 - Criar arquivo Json\n" + //ok
                "2 - Criar arquivo xml\n" +
                "3 - Criar arquivo binário\n" +
                "4 - Ler arquivo json\n" + //ok
                "5 - Ler arquivo xml\n" +
                "6 - Ler arquivo binário\n" +
                "0 - Sair\n");

        opcao = entrada.nextInt();

        switch (opcao) {
            case (0):
                break;
            case (1):
                persistirEmJson();
                break;
            case (2):
                persistirEmXml();
                break;
            case (3):
                persistirEmBinario();
                break;
            case (4):
                lerJson();
                break;
            case (5):
                lerXml();
                break;
            case (6):
                lerBinario();
                break;
            default:
                System.out.println("Opção inválida");
        }

    }

    public void persistirEmJson() {

        FileWriter writeFile = null;

        try {
            writeFile = new FileWriter("arquivosJson/vetor-pessoas.json");

            writeFile.write("\"Pessoas\":[\n");

            for (int i = 0; i < tamanho; i++){
                writeFile.write(String.format("  {\"id\": %d, \"nome\": \"%s\", \"email\": \"%s\"}\n",
                        elementos[i].getId(),
                        elementos[i].getNome(),
                        elementos[i].getEmail()));
            }
            writeFile.write("]\n");
            writeFile.close();

            System.out.println("Arquivo json gerado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void persistirEmXml(){

        try (FileWriter writer = new FileWriter("arquivosXML/vetor-pessoas.xml")) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<pessoas>\n");

            for (int i = 0; i < tamanho; i++)
            {
                writer.write(String.format("   <pessoa id=\"%d\">\n", elementos[i].getId()));
                writer.write(String.format("      <nome>%s</nome>\n", elementos[i].getNome()));
                writer.write(String.format("      <email>%s</email>\n", elementos[i].getEmail()));
                writer.write("   </pessoa>\n");
            }

            writer.write("</pessoas>\n");

            System.out.println("Arquivo xml salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo xml.");
        }
    }
    public void lerJson() {

        Pessoa[] pessoas = null;
        int indexPessoa = 0; // Índice atual para adicionar pessoas ao vetor

        try (BufferedReader reader = new BufferedReader(new FileReader("arquivosJson/vetor-pessoas.json"))) {
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

                if (pessoas == null) {
                    pessoas = new Pessoa[1]; // Inicializa o vetor com tamanho 1
                    pessoas[indexPessoa++] = pessoa; // Adiciona a primeira pessoa ao vetor
                } else {
                    Pessoa[] novoArray = new Pessoa[pessoas.length + 1]; // Cria um novo vetor com tamanho maior
                    System.arraycopy(pessoas, 0, novoArray, 0, pessoas.length); // Copia os elementos do vetor antigo para o novo
                    novoArray[indexPessoa++] = pessoa; // Adiciona a nova pessoa ao final do novo vetor
                    pessoas = novoArray; // Atualiza o vetor de pessoas para o novo vetor
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < indexPessoa; i++){
            System.out.println("Pessoa: " + pessoas[i].getId() + "| Nome: " + pessoas[i].getNome() + " | Email: " + pessoas[i].getEmail());
        }
    }
    public void lerXml(){

        Pessoa[] pessoas = null;
        int indexPessoa = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("arquivosXML/vetor-pessoas.xml"))) {
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

                if (pessoas == null) {
                    pessoas = new Pessoa[1]; // Inicializa o vetor com tamanho 1
                    pessoas[indexPessoa++] = pessoa; // Adiciona a primeira pessoa ao vetor
                } else {
                    Pessoa[] novoArray = new Pessoa[pessoas.length + 1]; // Cria um novo vetor com tamanho maior
                    System.arraycopy(pessoas, 0, novoArray, 0, pessoas.length); // Copia os elementos do vetor antigo para o novo
                    novoArray[indexPessoa++] = pessoa; // Adiciona a nova pessoa ao final do novo vetor
                    pessoas = novoArray; // Atualiza o vetor de pessoas para o novo vetor
                }

                reader.readLine(); // próxima <pessoa>
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < indexPessoa; i++){
            System.out.println("Pessoa: " + pessoas[i].getId() + "| Nome: " + pessoas[i].getNome() + " | Email: " + pessoas[i].getEmail());
        }
    }

    public void persistirEmBinario(){

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("arquivosBinarios/vetor-pessoas.bin"))) {

            for(int i = 0 ; i< tamanho; i++) {
                outputStream.writeObject(elementos[i]);
            }

            System.out.println("Arquivo binário salvo!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo binário.");
        }
    }
    public void lerBinario(){
        Pessoa[] pessoas = null;
        Pessoa pessoa = new Pessoa();
        int indexPessoa = 0;

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("arquivosBinarios/vetor-pessoas.bin"))) {

                while ((pessoa = (Pessoa) objectInputStream.readObject()) != null){

                    if (pessoas == null) {
                        pessoas = new Pessoa[1]; // Inicializa o vetor com tamanho 1
                        pessoas[indexPessoa++] = pessoa; // Adiciona a primeira pessoa ao vetor
                    } else {
                        Pessoa[] novoArray = new Pessoa[pessoas.length + 1]; // Cria um novo vetor com tamanho maior
                        System.arraycopy(pessoas, 0, novoArray, 0, pessoas.length); // Copia os elementos do vetor antigo para o novo
                        novoArray[indexPessoa++] = pessoa; // Adiciona a nova pessoa ao final do novo vetor
                        pessoas = novoArray; // Atualiza o vetor de pessoas para o novo vetor
                    }

                }

        } catch (IOException | ClassNotFoundException e) {

        }
        for(int i = 0; i < indexPessoa; i++){
            System.out.println("Pessoa: " + pessoas[i].getId() + "| Nome: " + pessoas[i].getNome() + " | Email: " + pessoas[i].getEmail());
        }
    }
    //opcao 7
    public void listarPessoas()
    {
        System.out.println("--------------------------------------------------Listagem de Pessoas--------------------------------------------------");

        if (this.tamanho == 0)
        {
            System.out.println("Lista vazia");
        }
        else {
            for (int i = 0; i < tamanho; i++) {
                System.out.println("Pessoa: " + elementos[i].getId() + "| Nome: " + elementos[i].getNome() + " | Email: " + elementos[i].getEmail());
            }
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");

    }
}



