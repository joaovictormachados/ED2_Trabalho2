package Estruturas_de_Dados;

public class ArvoreAVL {
    private No raiz = null;

    public ArvoreAVL() {
        raiz = null;
    }

    //retorna a raiz da árvore
    public No getRaiz (){
        return raiz;
    }

    //apaga a árvore
    public void limpar() {
        raiz = null;
    }

    //verifica se a árvore está vazia
    public boolean estaVazio() {
        return raiz == null;
    }


    // Retorna a altura da árvore
    private static int getAltura(No t) {
        if (t == null) {
            return -1;
        } else {
            return t.altura;
        }
    }


    //Retorna a maior chave da árvore.
    private static int getMaior( int esq, int dir ) {
        if (esq > dir) {
            return esq;
        } else {
            return dir;
        }
    }

    // Retorna o fator de balanceamento da árvore com raiz t
    private int calculaBalanceamento (No t) {
        return getAltura( t.esquerda ) - getAltura( t.direita );
    }

    public boolean insere (String palavra) {
        raiz = insereNaArvore(palavra, raiz);
        return true;
    }

    public No insereNaArvore (String palavra, No selecionado) {
        palavra = palavra.toLowerCase();
        if(selecionado == null)
            selecionado = new No( palavra, null, null );
        else if(converte(palavra) < selecionado.chave) selecionado.esquerda = insereNaArvore(palavra, selecionado.esquerda);
        else if(converte(palavra) > selecionado.chave) selecionado.direita = insereNaArvore(palavra, selecionado.direita);
        selecionado = consertaArvore (selecionado);
        return selecionado;
    }

    // Verifica se a árvore precisa de rotação
    public No consertaArvore (No selecionado) {
        if (calculaBalanceamento(selecionado) == 2 ) {
            if (calculaBalanceamento (selecionado.esquerda) > 0) selecionado = rotacaoADireita(selecionado);
            else selecionado = rotacaoDuplaADireita(selecionado);
        }
        else if (calculaBalanceamento(selecionado) == -2 ) {
            if (calculaBalanceamento(selecionado.direita) < 0) selecionado = rotacaoAEsquerda(selecionado);
            else selecionado = rotacaoDuplaAEsquerda(selecionado);
        }
        selecionado.altura = getMaior(getAltura(selecionado.esquerda), getAltura(selecionado.direita) ) + 1;
        return selecionado;
    }

    // Rotação Simples à Direita
    private static No rotacaoADireita(No selecionado) {
        No esq = selecionado.esquerda;
        selecionado.esquerda = esq.direita;
        esq.direita = selecionado;
        selecionado.altura = getMaior(getAltura(selecionado.esquerda), getAltura(selecionado.direita)) + 1;
        esq.altura = getMaior(getAltura(esq.esquerda), selecionado.altura) + 1;
        return esq;
    }

    // Rotação Simples à Esquerda
    private static No rotacaoAEsquerda(No selecionado) {
        No dir = selecionado.direita;
        selecionado.direita = dir.esquerda;
        dir.esquerda = selecionado;
        selecionado.altura = getMaior(getAltura(selecionado.esquerda), getAltura(selecionado.direita)) + 1;
        dir.altura = getMaior(getAltura(dir.direita), selecionado.altura) + 1;
        return dir;
    }

    // Rotação Dupla à Direita
    private static No rotacaoDuplaADireita(No selecionado) {
        selecionado.esquerda = rotacaoAEsquerda(selecionado.esquerda);
        return rotacaoADireita(selecionado);
    }

    // Rotação Dupla à Esquerda
    private static No rotacaoDuplaAEsquerda(No selecionado) {
        selecionado.direita = rotacaoADireita(selecionado.direita);
        return rotacaoAEsquerda(selecionado);
    }

    // Faz a busca na árvore
    public boolean busca(String palavra) {
        return buscaNaArvore(raiz,palavra);
    }
    protected Boolean buscaNaArvore(No atual, String palavra) {
        palavra = palavra.toLowerCase();
        while (atual != null) {
            if (palavra.equals(atual.palavra)){
                return true;
            }
            else if (converte(palavra)<atual.chave){
                atual = atual.esquerda;
            }
            else{
                atual = atual.direita;
            }
        }
        return false;
    }

    public static int converte(String palavra){
        int cod = 0;
        for(int i = 0;i<palavra.length();i++){
            cod += palavra.codePointAt(i);
        }
        return cod;
    }


}
