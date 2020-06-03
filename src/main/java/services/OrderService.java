package services;

import domain.models.Item;
import domain.models.Order;
import domain.models.User;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    Order placeOrder(List<String> items, User user);

    Order completeOrder(UUID orderId, User user);
}
