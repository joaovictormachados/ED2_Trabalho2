package Estruturas_de_Dados;

public class No {
    protected int altura, chave;
    protected String palavra;
    protected No esquerda, direita;

    public No ( String palavra ) {
        this( palavra, null, null );
    }

    public No ( String palavra, No esquerda, No direita ) {
        this.palavra = palavra;
        altura   = 0;
        chave = converte(palavra);
        this.esquerda = esquerda;
        this.direita = direita;
    }
    public static int converte(String palavra){
        int cod = 0;
        for(int i =0;i<palavra.length();i++){
            cod += palavra.codePointAt(i);
        }
        return cod;
    }
}
