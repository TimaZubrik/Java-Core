package by.timaz.javacore.CustomLinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomLinkedListTest {
    private CustomLinkedList<Integer> list;
@BeforeEach
void setUp() {
    list = new CustomLinkedList<>();
    list.addFirst(1);
    list.addLast(2);
    list.addLast(3);
}
    @Test
    void size() {
      assertEquals(3, list.size());
    }

    @Test
    void add() {
        list.add(0,7);
        assertEquals(4, list.size());
        assertEquals(7, list.get(0));
    }

    @Test
    void remove() {
        int n = list.remove(1);
        assertEquals(2, n);
        assertEquals(2, list.size());
    }

    @Test
    void addFirst() {
        list.addFirst(5);
        assertEquals(5, list.getFirst());
    }

    @Test
    void addLast() {
    list.addLast(5);
    assertEquals(5, list.getLast());
    }

    @Test
    void get() {
    assertEquals(1, list.get(0));
    }

    @Test
    void getFirst() {
        assertEquals(1, list.getFirst());
    }

    @Test
    void getLast() {
    assertEquals(3, list.getLast());
    }

    @Test
    void removeFirst() {
    assertEquals(1, list.removeFirst());
    assertEquals(2, list.size());
    }

    @Test
    void removeLast() {
    assertEquals(3, list.removeLast());
    assertEquals(2, list.size());
    }

}