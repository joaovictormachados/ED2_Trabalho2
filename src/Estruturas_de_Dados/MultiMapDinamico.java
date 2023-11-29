package Estruturas_de_Dados;

public class MultiMapDinamico<K, V> {

    private int tamanho;
    private int totalItens;
    private final double loadFactor = 0.7;

    private Entry<K, V >[] tabela;

    public MultiMapDinamico(int tamanho) {
        this.tamanho = tamanho;
        tabela = new Entry[tamanho];
        this.totalItens = 0;
    }
    public int getTamanho() {
        return tamanho;
    }
    public Entry<K, V>[] getTabela() {
        return tabela;
    }
    public int getTotalItens() {
        return totalItens;
    }

    public V get(K key) {
        int indice = generateHash1(key);
        int increment = generateHash2(key);
        int contador = 1;
        int aux = indice;
        while (tabela[indice] != null) {
            if (tabela[indice].getKey().equals(key)) {
                return tabela[indice].getValue();
            }
            indice = (indice + (contador * increment)) % tabela.length;
            contador++;
            if(indice == aux){
                break;
            }
        }

        return null;
    }


    public void put(K key, V value) {
        if (key != null && value != null) {

            boolean inseriu = true;
            if (totalItens > tamanho * loadFactor) {
                resizeTable();
            }

            int indice = generateHash1(key);
            int increment = generateHash2(key);

            //É preciso verificar se onde vamos inserir tem algum elemento ou se ele não é igual
            while (tabela[indice] != null && inseriu) {
                if (tabela[indice].getValue().equals(value)){
                    inseriu = false;
                }
                indice = (indice + increment) % tamanho;
            }
            if (inseriu) {
                tabela[indice] = new Entry<>(key,value);
                totalItens++;
            }
        }
    }

    public int generateHash1(K key) {
        return Math.abs(key.hashCode() % tamanho);
    }
    public int generateHash2(K key) {
        int hash = Math.abs(key.hashCode() % tamanho);
        int increment = hash % (tamanho/2);

        if (increment==0) {
            increment = 1;
        }
        return increment;
    }

    public void resizeTable() {
        this.tamanho = (int) (tamanho *1.5);      //cuidado
        Entry<K,V>[] novaTabela = new Entry[tamanho];

        for (Entry<K,V> entry: tabela) {
            // É necessário recalcular as posições dos valores na nova tabela hash
            if (entry!= null) {
                int indice = generateHash1(entry.getKey());
                int increment = generateHash2(entry.getKey());

                //Colisão Ocorreu
                while (novaTabela[indice]!=null) {
                    indice = (indice + increment) % tamanho;
                }
                novaTabela[indice] = entry;
            }
        }
        tabela = novaTabela;
    }
    public void print() {
        for (int i = 0; i < tamanho; i++) {
            Entry<K, V> entry = tabela[i];
            if (entry != null) {
                System.out.println("Index " + i + ": Key: " + entry.getKey() + ", Value: " + entry.getValue());
            } else {
                System.out.println("Index " + i + ": null");
            }
        }
    }
    public static void main(String[] args) {
        MultiMapDinamico<Integer, String> mapa = new MultiMapDinamico<>(3);

        // Insere alguns valores na tabela
        System.out.println("Tamanho da tabela: " + mapa.getTamanho());
        mapa.put(1, "A");
        mapa.put(2, "B");
        mapa.put(3, "C");
        mapa.put(4, "D");
        mapa.put(5, "E");

        System.out.println("Total de itens: " + mapa.getTotalItens());

        // Insere mais valores para acionar o redimensionamento
        mapa.put(5, "F");
        mapa.put(5, "G");
        mapa.put(5, "H");

        System.out.println("Tamanho da tabela após redimensionamento: " + mapa.getTamanho());
        System.out.println("Total de itens após redimensionamento: " + mapa.getTotalItens());
        mapa.print();
    }
}
