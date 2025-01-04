class Order {
    private Cart cart;

    public Order(Cart cart) {
        this.cart = cart;
    }

    public void placeOrder() {
        double total = cart.calculateTotal(); // 협력 객체에 요청
        System.out.println("주문 완료. 총 금액: " + total + "원");
    }
}