package domain.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class User {

    private String name;
    private String email;
    private String phone;
    private List<Order> orders;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.orders = new ArrayList<>();
    }

    public Order getOrderById(UUID orderId){
        Optional<Order> orderOptional = orders
                .stream().filter(o -> o.getOrderId().equals(orderId)).findFirst();
        if(orderOptional.isPresent()){
            return orderOptional.get();
        }
        throw new IllegalStateException("order not found");
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
