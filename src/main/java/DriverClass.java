import domain.models.Item;
import domain.models.Order;
import domain.models.Restaurant;
import domain.models.User;
import services.OrderService;
import services.OrderServiceImpl;
import services.RestaurantService;
import services.RestaurantServiceImpl;
import strategies.FilterBasedOnProcessingPowerStrategy;
import strategies.RestaurantFilterStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DriverClass {

    public static void main(String []args){
        System.out.println("Welcome to food order system");

        //init items
        Restaurant restaurantOne = new Restaurant("RestaurantOne");
        Restaurant restaurantTwo = new Restaurant("RestaurantTwo");

        //init Items
        Item burger = new Item("burger", 30f);
        Item pizza = new Item("pizza", 150f);
        Item noodles = new Item("noodle", 100f);

        //adding items to restaurantOne
        restaurantOne.addItem(burger);
        restaurantOne.addItem(pizza);
        restaurantOne.addItem(noodles);

        //adding items to restaurantTwo
        burger.setPrice(40f);
        pizza.setPrice(140f);
        noodles.setPrice(90f);

        restaurantTwo.addItem(burger);
        restaurantTwo.addItem(pizza);
        restaurantTwo.addItem(noodles);

        User user = new User("suman", "suman@gmail.com", "8505947133");

        Scanner scanner = new Scanner(System.in);
        List<Restaurant> restaurants = new ArrayList<>(Arrays.asList(restaurantOne, restaurantTwo));
        RestaurantFilterStrategy restaurantFilterStrategy = new FilterBasedOnProcessingPowerStrategy();
        RestaurantService restaurantService = new RestaurantServiceImpl(restaurants, restaurantFilterStrategy);
        OrderService orderService = new OrderServiceImpl(restaurantService);
        while(true){
            try{
                System.out.println("Place an order, enter item name space seperated");
                String orderString = scanner.nextLine();
                if(orderString.equalsIgnoreCase("exit")) break;
                Order order = orderService.placeOrder(Arrays.asList(orderString.split(" ")), user);
                System.out.println("order placed: " + order.toString());
                System.out.println("Order preparing");
                Thread.sleep(1000);
                System.out.println("Order has been prepared");
                orderService.completeOrder(order.getOrderId(), user);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("System exiting.....");
    }
}
