package com.estruturadados.vetor.contoller;

import com.estruturadados.vetor.Vetor;

import java.util.Scanner;

public class CrudVetor {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        Vetor vetorDePessoas = new Vetor(100);

        int opcao = 1;

        while (opcao != 0)
        {
            System.out.print("--------------------------------------------------Menu de opções: Vetor-------------------------------------------------------\n" +
                    "1 - Adiciona uma pessoa \n" + //ok
                    "2 - Altera uma pessoa \n" + //ok
                    "3 - Busca uma pessoa \n" + //ok
                    "4 - Ordena pessoas \n" + //ok
                    "5 - Exclui uma pessoa \n" + //ok
                    "6 - Persistir dados \n" + // só cria o elemento json :(
                    "7 - Listar pessoas \n" + //ok
                    "0 - sair \n");

            opcao = entrada.nextInt();

            switch (opcao){
                case(0):
                    break;
                case(1):
                    vetorDePessoas.adicionar();
                    break;
                case(2):
                    vetorDePessoas.alterarPessoa();
                    break;
                case(3):
                    vetorDePessoas.buscarPessoa();
                    break;
                case(4):
                    vetorDePessoas.ordenarPessoas();
                    break;
                case(5):
                    vetorDePessoas.exluirPessoa();
                    break;
                case(6):
                    vetorDePessoas.persistirDados();
                    break;
                case(7):
                    vetorDePessoas.listarPessoas();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
