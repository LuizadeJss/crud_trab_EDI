package com.estruturadados.lista.controller;

import com.estruturadados.lista.Lista;
import com.estruturadados.vetor.model.Pessoa;

import java.util.Scanner;

public class ListaEncadeadaCrud {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        Lista<Pessoa> listaDePessoas = new Lista<>();

        int opcao = 1;

        while (opcao != 0)
        {

            System.out.print("--------------------------------------------------Menu de opções: Lista Encadeada-------------------------------------------------------\n" +
                    "1 - Adiciona uma pessoa \n" +
                    "2 - Altera uma pessoa \n" +
                    "3 - Busca uma pessoa \n" +
                    "4 - Ordena pessoas \n" +
                    "5 - Exclui uma pessoa \n" +
                    "6 - Persistir dados \n" +
                    "7 - Listar pessoas \n" +
                    "8 - Limpa a lista\n" +
                    "0 - sair \n");

            opcao = entrada.nextInt();

            switch (opcao)
            {
                case(0):
                    System.out.println("Saindo...");
                    break;
                case(1):
                    listaDePessoas.adiciona();
                    break;
                case(2):
                    listaDePessoas.alteraPessoa();
                    break;
                case(3):
                    listaDePessoas.buscarPessoa();
                    break;
                case(4):
                    listaDePessoas.ordenacao(listaDePessoas);
                    break;
                case(5):
                    listaDePessoas.excluirPessoa();
                    break;
                case(6):
                    listaDePessoas.persistirDados();
                    break;
                case(7):
                    listaDePessoas.listarPessoas();
                    //System.out.println(listaDePessoas);
                    break;
                case(8):
                    listaDePessoas.limparLista();
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
