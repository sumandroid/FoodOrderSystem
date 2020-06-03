package services;

import constants.OrderStatus;
import domain.models.Item;
import domain.models.Order;
import domain.models.Restaurant;
import domain.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {

    private RestaurantService restaurantService;

    public OrderServiceImpl(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }

    @Override
    public Order placeOrder(List<String> itemNames, User user) {
        Restaurant deliveringRestaurant = restaurantService.findServingRestaurant(itemNames);
        Float orderAmount = calculateOrderAmount(deliveringRestaurant, itemNames);
        List<Item> items = new ArrayList<>();
        for(String itemName : itemNames){
            items.add(deliveringRestaurant.getItemByName(itemName));
        }
        Order order = new Order(user, deliveringRestaurant, orderAmount, OrderStatus.PLACED, items);
        user.addOrder(order);
        return order;
    }

    @Override
    public Order completeOrder(UUID orderId, User user) {
        Order order = user.getOrderById(orderId);
        Restaurant restaurant = order.getRestaurant();
        order.setOrderStatus(OrderStatus.PREPARED);
        restaurant.recoverProcessingPowerBy(order.getItems().size());
        return order;
    }


    private Float calculateOrderAmount(Restaurant restaurant, List<String> items){
        Float orderAmount = 0f;
        for (String itemName : items){
            Item item = restaurant.getItemByName(itemName);
            orderAmount += item.getPrice();
        }
        return orderAmount;
    }
}

