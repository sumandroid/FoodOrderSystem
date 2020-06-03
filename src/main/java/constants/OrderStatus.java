package constants;

public enum OrderStatus {

    PLACED("placed"),
    PROCESSING("processing"),
    PREPARED("prepared");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public static OrderStatus getFromString(String text){
        for(OrderStatus orderStatus : OrderStatus.values()){
            if(orderStatus.value.equalsIgnoreCase(text)){
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("Invalid value for order status");
    }
}
