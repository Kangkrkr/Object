class Cart {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    // 총 금액 계산 및 메시징
    public double checkout(PaymentProcessor paymentProcessor) {
        double total = products.stream().mapToDouble(Product::getPrice).sum();
        paymentProcessor.processPayment(total); // PaymentProcessor와 직접 협력
        return total;
    }
}