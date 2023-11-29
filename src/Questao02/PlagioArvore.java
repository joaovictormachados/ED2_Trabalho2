package Questao02;

import Estruturas_de_Dados.ArvoreAVL;

import java.io.*;
import java.util.ArrayList;

public class PlagioArvore {
    private ArvoreAVL arvore;
    int m;

    public PlagioArvore(int m){
        this.arvore = new ArvoreAVL();
        this.m = m;
    }

    public int getM(){
        return m;
    }

    public void setM(int m){
        this.m = m;
    }

    public void uploadArquivo(String diretorio){
        File pasta = new File(diretorio);

        if(pasta.exists() && pasta.isDirectory()){
            File[] arquivos = pasta.listFiles();

            if(arquivos != null){
                for(File a : arquivos){
                    if(a.isFile()){
                        dividirTexto(a);
                    }
                }
            }
            else{
                System.out.println("Pasta vazia!");
            }
        }
        else{
            System.out.println("Pasta inexistente ou invalida!");
        }
    }

    public void dividirTexto(File arquivo){
        try(BufferedReader l = new BufferedReader(new FileReader(arquivo))){
            String linha;
            System.out.println("Lendo o arquivo: " + arquivo.getName());

            while((linha = l.readLine()) != null){
                String[] palavras = linha.split(" ");
                StringBuilder secaoBuilder = new StringBuilder();

                for(int i = 0; i < palavras.length - m + 1; i++){
                    secaoBuilder = new StringBuilder("");

                    for(int j = 0; j < m; j++){
                        secaoBuilder.append(palavras[i+j]);
                        secaoBuilder.append(" ");
                    }
                    String secao = secaoBuilder.toString();
                    arvore.insere(secao.toLowerCase());
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public String verificaPlagio(String diretorio){
        ArrayList<String[]> secaoPalavras = verificaArquivo(diretorio);
        StringBuilder plagioBuilder = new StringBuilder();

        for (String[] secao : secaoPalavras) {
            String secaoLowerCase = secao[0].toLowerCase();
            boolean valor = arvore.busca(secaoLowerCase);

            if (valor == true) {
                plagioBuilder.append("Plágio no documento ")
                        .append(secao[2])
                        .append(", parágrafo ")
                        .append(secao[1])
                        .append(", no seguinte trecho: ")
                        .append(secao[0])
                        .append("\n");
            }
        }

        if (plagioBuilder.length() == 0) {
            plagioBuilder.append("Sem plágio :)");
        }

        String plagio = plagioBuilder.toString();

        return plagio;
    }


    public ArrayList<String[]> verificaArquivo(String diretorio) {
        File d = new File(diretorio);
        ArrayList<String[]> secaoPalavras = new ArrayList<>();

        if (d.exists() && d.isDirectory()) {
            File[] files = d.listFiles();

            if (files != null) {
                for (File f : files) {
                    if (f.isFile()) {
                        processaArquivo(f, secaoPalavras);
                    }
                }
            }
        }
        return secaoPalavras;
    }

    private void processaArquivo(File file, ArrayList<String[]> secaoPalavras) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(file))) {
            String linha;
            int paragrafo = 0;

            while ((linha = leitor.readLine()) != null) {
                String[] palavras = linha.split(" ");
                if (palavras.length > 1) {
                    paragrafo++;
                }

                for (int i = 0; i < palavras.length - m + 1; i++) {
                    String[] secao = new String[3];
                    secao[0] = "";
                    secao[1] = Integer.toString(paragrafo);
                    secao[2] = file.getName();

                    for (int j = 0; j < m; j++) {
                        secao[0] += palavras[i + j] + " ";
                    }
                    secaoPalavras.add(secao);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
