package HashTable;

public class TestCase {
    public static void main(String[] args) {
        int[] elements = {103, 29, 38, 4, 7, 55, 907, 32};
        MyHashTable myHashTable = new MyHashTable();
        for (int e : elements) {
            myHashTable.insert(e);
        }
    }
}
