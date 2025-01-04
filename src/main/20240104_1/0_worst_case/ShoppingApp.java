import java.util.ArrayList;
import java.util.List;

class ShoppingApp {
    private List<String> productNames = new ArrayList<>();
    private List<Double> productPrices = new ArrayList<>();
    private double total = 0;

    // 모든 데이터를 직접 저장하고 관리
    public void addProduct(String name, double price) {
        productNames.add(name);
        productPrices.add(price);
    }

    // 주문 처리: 금액 계산부터 출력까지 모두 한 메서드에서 처리
    public void processOrder() {
        // 총 금액 계산
        total = 0;
        for (double price : productPrices) {
            total += price;
        }

        // 결제 처리
        System.out.println("결제가 완료되었습니다. 총 결제 금액: " + total + "원");

        // 주문 내역 출력
        System.out.println("주문 내역:");
        for (String product : productNames) {
            System.out.println("- " + product);
        }

        // 장바구니 초기화 (이후 새 주문을 위해 모든 데이터 초기화)
        clearCart();
    }

    // 장바구니 비우기
    private void clearCart() {
        productNames.clear();
        productPrices.clear();
        total = 0;
    }

    public static void main(String[] args) {
        ShoppingApp app = new ShoppingApp();

        // 상품 추가
        app.addProduct("노트북", 1500000);
        app.addProduct("마우스", 25000);

        // 주문/결제 처리
        app.processOrder();
    }
}