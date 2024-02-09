import java.util.Objects;

public class Deque {
    static final Integer MAX = 1000;
    Character arr[];
    Integer front;
    Integer rear;
    Integer size;

    public Deque(Integer size) {
        arr = new Character[MAX];
        front = -1;
        rear = 0;
        this.size = size;
    }

    Character get(Integer index) {
        Objects.checkIndex(index, size);
        return arr[index];
    }

    public Integer size() {return size;}

    boolean isFull() {return ((front == 0 && rear == size - 1) || front == rear + 1);}

    boolean isEmpty() {return (front == -1);}

    void insertFront(Character key) {
        if (isFull()) {
            System.out.println("Overflow. Choose a different row number.");
            return;
        }
        else {
            if (front == -1) {
                front = size-1;
                rear = size-1;
            }
            else if (front == 0)
                front = size - 1;

            else
                front = front - 1;
        }
        arr[front] = key;
    }

    void insertRear(Character key) {
        if (isFull()) {
            System.out.println("Overflow. Choose a different row number.");
            return;
        }
        else {
            if (front == -1) {
                front = 0;
                rear = 0;
            }
            else if (rear == size - 1)
                rear = 0;
            else
                rear = rear + 1;
        }
        arr[rear] = key;
    }

    void deleteFront() {
        if (isEmpty()) {
            System.out.println("Underflow\n");
            return;
        }
        else {
            if (front == rear) {
                front = -1;
                rear = -1;
            }
            else
            if (front == size - 1)
                front = 0;
            else
                front = front + 1;
        }
    }

    void deleteRear() {
        if (isEmpty()) {
            System.out.println("Underflow");
            return;
        }
        else {
            if (front == rear) {
                front = -1;
                rear = -1;
            }
            else if (rear == 0)
                rear = size - 1;
            else
                rear = rear - 1;
        }
    }

    public char getFront() {
        if (isEmpty()) {
            System.out.println("Underflow");
            return (char) -1;
        }
        return arr[front];
    }

    public char getRear() {
        if (isEmpty() || rear < 0) {
            System.out.println("Underflow\n");
            return (char) -1;
        }
        return arr[rear];
    }

    public boolean contain (Character key){
        for (int i = 0; i < arr.length; i++){
            if(key == arr[i]){
                return true;
            }
        }return false;
    }
}


