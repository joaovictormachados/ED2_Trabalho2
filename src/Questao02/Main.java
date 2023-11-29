package Questao02;

import java.util.Scanner;

import Estruturas_de_Dados.*;
import Questao01.* ;
import Questao02.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        boolean menu = true;

        while (menu) {
            System.out.println();
            System.out.println("|----------MENU-----------|");
            System.out.println("1 - Questão 1");
            System.out.println("2 - Questão 2 com Tabela Hash");
            System.out.println("3 - Questão 2 com Árvore AVL");
            System.out.println("4 - Sair");
            System.out.println("Opção: ");
            int opcao = scanner.nextInt();
            System.out.println();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o tamanho do multimapa que deseja criar: ");
                    int tamanho = scanner.nextInt();
                    questao1(tamanho);
                    break;
                case 2:
                    System.out.println("Digite o valor de m: ");
                    int m1 = scanner.nextInt();
                    questao2_A(m1);
                    break;
                case 3:
                    System.out.println("Digite o valor de m: ");
                    int m2 = scanner.nextInt();
                    questao2_B(m2);
                    break;
                case 4:
                    System.out.println("Até mais!");
                    menu = false;
                default:
                    System.out.println();
            }
        }
    }

    public static void questao1(int tamanho) {

        MultiMapa<Integer, String> multimap = new MultiMapa<>(tamanho);
        multimap.put(0, "Vito");
        multimap.put(1, "Marcelo");
        multimap.put(2, "Gabriel");
        multimap.put(3, "Marcelino");
        multimap.put(4, "Patricia");
        multimap.put(5, "Andre");
        multimap.put(6, "Marcelo");
        multimap.put(7, "Dallyson");
        multimap.put(8, "Flamengo");
        multimap.put(9, "Computacao");

        //mostrando todas as chaves e seus indices no multimap
        multimap.print();

        //Mostrando todos os valores em uma determinada posição

        int x = multimap.generateHash(3);
        System.out.print("\nValores Associados a posição " +x + ": ");
        List<String> valores = multimap.findAll(3);

        if (valores != null) {
            for (int i = 0; i < valores.getLength(); i++) {
                System.out.printf(valores.get(i)+ ", ");
            }
        }
        System.out.println("\nTamanho do MultiMap: " + multimap.getTamanho());
        System.out.println("Total de Elementos no MultiMap: " + multimap.getTotalItens());
    }

    public static void questao2_A (int m) {
        String pastaDocumentos = "C:\\Users\\andre\\IdeaProjects\\Trabalho2_ED2\\src\\Questao02\\DocumentosBase";
        String pastaVerificar = "C:\\Users\\andre\\IdeaProjects\\Trabalho2_ED2\\src\\Questao02\\TestarPlagio";


        PlagioTabela questao = new PlagioTabela(m);
        questao.uploadDirectory(pastaDocumentos);

        String plagio = questao.verificaPlagioNaTabela(pastaVerificar);
        System.out.println();
        System.out.println(plagio);
    }
    public static void questao2_B (int m) {
        ArvoreAVL arvore = new ArvoreAVL();

        String pastaDocumentos = "C:\\Users\\andre\\IdeaProjects\\Trabalho2_ED2\\src\\Questao02\\DocumentosBase";
        String pastaVerificar = "C:\\Users\\andre\\IdeaProjects\\Trabalho2_ED2\\src\\Questao02\\TestarPlagio";

        PlagioArvore plagioA = new PlagioArvore(m);
        plagioA.uploadArquivo(pastaDocumentos);
        String plagio = plagioA.verificaPlagio(pastaVerificar);
        System.out.println();
        System.out.println(plagio);

    }
}
