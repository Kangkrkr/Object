public class ShoppingApp {
    public static void main(String[] args) {
        Cart cart = new Cart();
        cart.addProduct(new Product("노트북", 1500000));
        cart.addProduct(new Product("마우스", 25000));

        Order order = new Order(cart);
        order.placeOrder();
    }
}