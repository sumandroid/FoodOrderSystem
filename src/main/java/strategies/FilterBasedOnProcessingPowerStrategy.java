package strategies;

import domain.models.Item;
import domain.models.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterBasedOnProcessingPowerStrategy implements RestaurantFilterStrategy {

    @Override
    public List<Restaurant> filterRestaurantsBasedOnProcessingPower(List<Restaurant> restaurants, Integer processingPower) {
        synchronized (Restaurant.class){
            return restaurants
                    .stream().filter(restaurant -> restaurant.getProcessingPower() >= processingPower)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Restaurant getRestaurantWithLowestItemPrices(List<String> itemNames, List<Restaurant> restaurants) {
        Float minPrice = Float.MAX_VALUE;
        Restaurant restaurantWithMinCost = null;
        for(Restaurant restaurant : restaurants){
            Float minPriceTemp = 0f;
            for(String itemName : itemNames){
                Item item = restaurant.getItemByName(itemName);
                minPriceTemp += item.getPrice();
            }
            if(minPriceTemp < minPrice){
                restaurantWithMinCost = restaurant;
            }
        }
        return restaurantWithMinCost;
    }
}
