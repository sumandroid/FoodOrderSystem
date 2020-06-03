package domain.models;

import constants.OrderStatus;

import java.util.List;
import java.util.UUID;

public class Order {

    private User user;
    private Restaurant restaurant;
    private Float orderAmount;
    private OrderStatus orderStatus;
    private List<Item> items;
    private UUID orderId;

    public UUID getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Float getOrderAmount() {
        return orderAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item){
        items.add(item);
        updateTotal();
    }

    public Order(User user, Restaurant restaurant, Float orderAmount, OrderStatus orderStatus, List<Item> items) {
        this.user = user;
        this.restaurant = restaurant;
        this.orderAmount = orderAmount;
        this.orderStatus = orderStatus;
        this.items = items;
        this.orderId = UUID.randomUUID();
    }

    private void updateTotal(){
        this.orderAmount = items.stream().map(Item::getPrice).reduce(0f, Float::sum);
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", restaurant=" + restaurant +
                ", orderAmount=" + orderAmount +
                ", orderStatus=" + orderStatus +
                ", items=" + items +
                ", orderId=" + orderId +
                '}';
    }
}
