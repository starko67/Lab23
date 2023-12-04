package lab23;
import java.util.NoSuchElementException;

// Шаг 2 Класс реализующий очередь на основе связанного списка
class LinkedQueue<E> implements Queue<E> {
    private Node<E> front; // Указатель на начало очереди
    private Node<E> rear; // Указатель на конец очереди
    private int size; // Размер очереди

    private static class Node<E> {
        E data; // Данные узла
        Node<E> next; // Ссылка на следующий узел

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    public void enqueue(E element) {
        Node<E> newNode = new Node<>(element); // Создание нового узла
        if (isEmpty()) {
            front = rear = newNode; // Очередь пуста, устанавливаем новый элемент как начало и конец
        } else {
            rear.next = newNode; // Добавляем новый элемент в конец очереди
            rear = newNode; // Переназначаем указатель на конец очереди
        }
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty"); // Если очередь пуста, выбрасываем исключение
        }
        E removed = front.data; // Получаем данные из начала очереди
        front = front.next; // Сдвигаем указатель на начало на следующий элемент
        if (front == null) {
            rear = null; // Если удаляем последний элемент, устанавливаем указатель на конец в null
        }
        size--;
        return removed; // Возвращаем удаленный элемент
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty"); // Если очередь пуста, выбрасываем исключение
        }
        return front.data; // Возвращаем данные из начала очереди без удаления
    }

    public boolean isEmpty() {
        return size == 0; // Проверка, пуста ли очередь
    }

    public int size() {
        return size; // Получение размера очереди
    }

    public void clear() {
        while (!isEmpty()) {
            dequeue(); // Пока очередь не пуста, удаляем элементы
        }
    }
}

//Шаг 3 Абстрактный класс для общих частей классов очередей
abstract class AbstractQueue<E> implements Queue<E> {
    protected int size; // Размер очереди

    public boolean isEmpty() {
        return size == 0; // Проверка, пуста ли очередь
    }

    public int size() {
        return size; // Получение размера очереди
    }

    public void clear() {
        while (!isEmpty()) {
            dequeue(); // Пока очередь не пуста, удаляем элементы
        }
    }
}

// Главный класс программы
public class Main {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedQueue<>(); // Создание экземпляра очереди типа Integer
        queue.enqueue(1); // Добавление элементов в очередь
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println("Queue size: " + queue.size()); // Вывод размера очереди
        System.out.println("Front element: " + queue.peek()); // Вывод первого элемента очереди

        System.out.println("Dequeue: " + queue.dequeue()); // Удаление и вывод первого элемента очереди
        System.out.println("Queue size after dequeue: " + queue.size()); // Вывод размера очереди после удаления элемента

        queue.clear(); // Очистка очереди
        System.out.println("Queue cleared. Is empty: " + queue.isEmpty()); // Проверка, пуста ли очер
    }
}