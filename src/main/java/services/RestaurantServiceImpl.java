package services;

import domain.models.Item;
import domain.models.Restaurant;
import strategies.RestaurantFilterStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantServiceImpl implements RestaurantService {

    private List<Restaurant> restaurants;
    private RestaurantFilterStrategy restaurantFilterStrategy;

    public RestaurantServiceImpl(List<Restaurant> restaurants, RestaurantFilterStrategy restaurantFilterStrategy){
        this.restaurants = restaurants;
        this.restaurantFilterStrategy = restaurantFilterStrategy;
    }


    @Override
    public Restaurant findServingRestaurant(List<String> itemNames) {
        List<Restaurant> servingRestaurants = findItemsServingRestaurants(itemNames);
        List<Restaurant> restaurantsWithSufficientProcessingPower = restaurantFilterStrategy
                .filterRestaurantsBasedOnProcessingPower(servingRestaurants, itemNames.size());
        if(restaurantsWithSufficientProcessingPower.size() == 0) throw new IllegalStateException("No restaurants are free");
        Restaurant eligibleRestaurant = restaurantFilterStrategy
                .getRestaurantWithLowestItemPrices(itemNames, restaurantsWithSufficientProcessingPower);
        eligibleRestaurant.reduceProcessingPowerBy(itemNames.size());
        return eligibleRestaurant;
    }


    private List<Restaurant> findItemsServingRestaurants(List<String> itemNames){
        List<Restaurant> servingRestaurants = new ArrayList<>();
        synchronized (Restaurant.class){
            for(Restaurant restaurant : restaurants){
                List<String> itemNamesList = restaurant.getItems().stream().map(Item::getName).collect(Collectors.toList());
                if(itemNamesList.containsAll(itemNames)){
                    servingRestaurants.add(restaurant);
                }
            }
        }
        return servingRestaurants;
    }
}
