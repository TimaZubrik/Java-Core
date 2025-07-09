package by.timaz.javacore.CustomLinkedList;

public class CustomLinkedList<E> {
    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;

    public int size() {
        return size;
    }

    public void add(int index, E element) {
        Node<E> newNode = new Node<E>(element, node(index));
        Node<E> current = first;
        if(index == 0) {
            addFirst(element);
        }else{
            for (int i = 0; i < index-1; i++) {
                current = current.next;
            }
            current.next = newNode;
            size++;
        }

    }

    public E remove(int index) {
        Node<E> current = first;
        for (int i = 0; i < index-1; i++) {
            current = current.next;
        }
        Node<E> removed = current.next;
        current.next = current.next.next;
        size--;
        return removed.element;
    }

    public void addFirst(E e) {
        Node<E> f = first;
        Node<E> newNode = new Node<E>(e, f);
        first = newNode;
        if(f == null) {
            last = newNode;
        }
        size++;
    }

    public void addLast(E e) {
        Node<E> newNode = new Node<>(e, null);
        if (first == null) {
            first = newNode;
        }else{
            Node<E> current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        last = newNode;
        size++;
    }
    public E get(int index) {
        return node(index).element;
    }
    public E getFirst() {
        return first.element;
    }
    public E getLast() {
        return last.element;
    }

    public E removeFirst() {
        Node<E> f = first;
        first = f.next;
        size--;
        return f.element;
    }

    public E removeLast() {
        Node<E> l = last;
        last = node(size-2);
        size--;
        return l.element;
    }

    private Node<E> node(int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private static class Node<E> {
        public E element;
        public Node<E> next;
        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

}
