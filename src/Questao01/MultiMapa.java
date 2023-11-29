package Questao01;
import Estruturas_de_Dados.*;
public class MultiMapa<K, V> {



    private int tamanho;
    private int totalItens;
    private Entry<K, List<V> >[] tabela;

    public MultiMapa(int tamanho) {
        this.tamanho = tamanho;
        tabela = new Entry[tamanho];
        this.totalItens = 0;
    }
    public int getTamanho() {
        return tamanho;
    }
    public Entry<K, List<V> >[] getTabela() {
        return tabela;
    }
    public int getTotalItens() {
        return totalItens;
    }


    public void put(K key, V value) {
        int indice = generateHash(key);
        if (tabela[indice] ==null) {
            tabela[indice] = new Entry<>(key,new List<V>());
        }
        tabela[indice].setKey(key);
        tabela[indice].getValue().add(value);
        this.totalItens +=1;
    }

    public List<V> findAll(K key){
        int indice = generateHash(key);
        if (tabela[indice] != null) {
            return tabela[indice].getValue();
        }
        return null;
    }


    public void print() {
        for (int i = 0; i < tamanho; i++) {
            Entry<K, List<V>> bucket = tabela[i];

            if (bucket != null) {
                System.out.print("Index " + i + ": ");
                List<V> values = bucket.getValue();

                for (int j = 0; j < values.getLength(); j++) {
                    System.out.print("Value: " + values.get(j) + " -> ");
                }
                System.out.println("null");
            } else {
                System.out.println("Index " + i + ": null");
            }
        }
    }


    public int generateHash(K key) {
        int index = 0;
        if (key instanceof Integer) {
            index = Math.abs( (Integer) key) % tamanho;
        }
        else if(key instanceof Double) {
            int aux = ( (Double) key).intValue();
            index = Math.abs(aux) % tamanho;
        }
        else if (key instanceof Character) {
            index = ( (int) key ) % tamanho;
        }
        else if(key instanceof String) {
            int soma = 0;
            for (char c: ((String) key).toCharArray()) {
                soma += c;
            }
            index = soma % tamanho;
        }

        return index;
    }



}
