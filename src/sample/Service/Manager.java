package sample.Service;

public interface Manager<T> {
    void add(T t);
    void print();
    void deleteItem();
    void editItem();
}
