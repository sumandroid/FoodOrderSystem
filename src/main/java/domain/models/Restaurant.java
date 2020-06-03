package domain.models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private String name;
    private List<Item> items;
    private Integer processingPower;
    private final Object lock = new Object();

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }

    public Integer getProcessingPower() {
        return processingPower;
    }

    public void addItem(Item item) {
        items.add(item);
        setProcessingPower();
    }

    public Item getItemByName(String name) {
        synchronized (lock) {
            for (Item item : items) {
                if (item.getName().equalsIgnoreCase(name)) {
                    return item;
                }
            }
        }
        throw new IllegalArgumentException("no item found by name");
    }

    private void setProcessingPower() {
        synchronized (lock) {
            processingPower = this.items.size();
        }
    }

    public Restaurant(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    public void reduceProcessingPowerBy(int value) {
        synchronized (lock){
            this.processingPower -= value;
        }
    }

    public void recoverProcessingPowerBy(int value) {
        synchronized (lock){
            this.processingPower += value;
        }
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                '}';
    }
}
