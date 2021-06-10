package sample.Service;

public interface Find<T> {
    int findIndex(String itemName);
    void findByName();
    void findByCost();
    void findByTime();
}
