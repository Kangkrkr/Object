class Order {
    private Cart cart;

    public Order(Cart cart) {
        this.cart = cart;
    }

    public void placeOrder(PaymentProcessor paymentProcessor) {
        double total = cart.checkout(paymentProcessor); // Cart에 메시지를 보내 현재 작업 수행
        System.out.println("주문이 완료되었습니다. 총 금액: " + total + "원");
    }
}