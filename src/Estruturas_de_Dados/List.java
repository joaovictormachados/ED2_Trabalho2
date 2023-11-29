package Estruturas_de_Dados;

public class List<T> {
        Node<T> head;
        private int length = 0;

        public List() {
            this.head = null;
        }

        public int getLength() {
            return length;
        }

        public T get(int index) {
            if (index < 0 || index >= length) {
                throw new IndexOutOfBoundsException("Index is out of bounds.");
            }

            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }

            return current.data;
        }


        public void add(T data) {
            Node<T> newnode = new Node<>(data);

            if (this.head == null) {
                head = newnode;
            } else {
                Node<T> temp = head;

                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = newnode;
            }
            length++;
        }

        public Node<T> remove(T key) {
            Node<T> current = this.head;
            Node<T> prev = null;

            while (current != null) {
                if (current.data.equals(key)) {
                    if (prev == null) {
                        // O elemento a ser removido é a cabeça da lista
                        this.head = current.next;
                    } else {
                        prev.next = current.next;
                    }
                    length--;
                    return current;
                }
                prev = current;
                current = current.next;
            }
            return null; // Elemento não encontrado na lista.
        }

        public void print() {
            Node<T> current = this.head;
            while (current != null) {
                System.out.print(current.data + " ");
                current = current.next;
            }
            System.out.println();
        }
}
