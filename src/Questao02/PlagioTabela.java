package Questao02;

import Estruturas_de_Dados.*;
import java.io.*;
import java.io.File;
import java.util.ArrayList;

public class PlagioTabela {

    public MultiMapDinamico<String,String> tabela;
    private int m;

    public PlagioTabela(int m) {
        this.tabela = new MultiMapDinamico<>(100);
        this.m = m;
    }

    public Entry<String,String>[] getTabela() {
        return tabela.getTabela();
    }
    public int getM() {
        return m;
    }

    public void uploadDirectory (String filePath) {
        File directory = new File(filePath);
        if (directoryExists(directory)) {
            File[] files = directory.listFiles();

            if (files != null) {
                for( File f: files) {
                    if (f.isFile()) {
                        distribute(f);
                    }
                }
            }else {
                System.out.println("PASTA INVÁLIDA!");
            }
        }
    }

    public Boolean directoryExists(File directory) {
        if (directory.exists() && directory.isDirectory()) {
            return true;
        }
        return false;
    }

    public void distribute(File file) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(file))) {
            String l;
            System.out.println("Lendo o arquivo: " + file.getName());
            // Ler o arquivo
            while ((l = leitor.readLine()) != null) {
                String[] palavras = l.split(" "); // Correção aqui
                // Divide em m seções
                StringBuilder secaoBuilder = new StringBuilder();
                for (int i = 0; i < palavras.length - m + 1; i++) {
                    secaoBuilder = new StringBuilder("");
                    for (int j = 0; j < m; j++) {
                        secaoBuilder.append(palavras[i + j]);
                        secaoBuilder.append(" ");

                    }
                    String secao = secaoBuilder.toString();

                    // Insere na tabela hash
                    String secaoMinuscula = secao.toLowerCase();
                    tabela.put(secaoMinuscula, secaoMinuscula);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String verificaPlagioNaTabela(String filePath) {
        ArrayList<String[]> secaoPalavras = fileVerify(filePath);
        StringBuilder plagioBuilder = new StringBuilder();

        for (String[] secao : secaoPalavras) {
            String secaoLowerCase = secao[0].toLowerCase();
            String valor = tabela.get(secaoLowerCase);

            if (valor != null) {
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


    public ArrayList<String[]> fileVerify(String arquivoPath) {
        File directory = new File(arquivoPath);
        ArrayList<String[]> secaoPalavras = new ArrayList<>();

        if (directoryExists(directory)) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File f : files) {
                    if (f.isFile()) {
                        processFile(f, secaoPalavras);
                    }
                }
            }
        }
        return secaoPalavras;
    }

    private void processFile(File file, ArrayList<String[]> secaoPalavras) {
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

    public static void gerarRelatorioPlagio(String conteudo) {
        String nomeArquivo = "RelatorioPlagio.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write(conteudo);
            System.out.println("Relatório de plágio gerado com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
