public class ArrayListy {
    private int size;
    private int[] array;
    private int head;

    public ArrayListy() {
        size = 1;
        array = new int[size];
        head = 0;
    }

    public boolean isEmpty() {
        return head == 0;
    }

    public boolean isFull() {
        return size == head;
    }

    public int size() {
        return head;
    }

    public void insert(int value) {
        if (isFull()) {
            resize();
        }
        array[head] = value;
        head++;
    }

    private void resize() {
        size <<= 1;
        int[] temp = new int[size];

        for (int i = 0; i < head; i++) {
            temp[i] = array[i];
        }

        array = temp;
    }

    public void insert(int value, int index) {
        if (index < 0 || index > head) {
            return;
        }

        if (isFull()) {
            resize();
        }

        int i = head;
        while (i > index) {
            array[i] = array[i - 1];
            i--;
        }

        array[index] = value;
        head++;
    }

    public void remove(int index) {
        if (isEmpty() || index < 0 || index >= head) {
            return;
        }

        for (int i = index; i < head - 1; i++) {
            array[i] = array[i + 1];
        }

        head--;
        array[head] = 0;
    }

    @Override
    public String toString() {
        if (head == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        int i = 0;
        for (; i < head - 1; i++) {
            sb.append(array[i]).append(",");
        }

        sb.append(array[i]);
        sb.append("]");

        return sb.toString();
    }
}