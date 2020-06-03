package services;

import domain.models.Item;
import domain.models.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant findServingRestaurant(List<String> itemNames);
}
