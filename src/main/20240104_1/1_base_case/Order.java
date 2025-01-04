class Order {
    private Cart cart;

    public Order(Cart cart) {
        this.cart = cart;
    }

    public void placeOrder() {
        double total = 0;
        for (Product product : cart.getProducts()) {
            total += product.getPrice();
        }
        System.out.println("총 주문 금액: " + total + "원");
    }
}