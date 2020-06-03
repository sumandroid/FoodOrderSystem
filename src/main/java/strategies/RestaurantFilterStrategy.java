package strategies;

import domain.models.Item;
import domain.models.Restaurant;

import java.util.List;

public interface RestaurantFilterStrategy {

    List<Restaurant> filterRestaurantsBasedOnProcessingPower(List<Restaurant> restaurants, Integer processingPower);

    Restaurant getRestaurantWithLowestItemPrices(List<String> itemNames, List<Restaurant> restaurants);
}
