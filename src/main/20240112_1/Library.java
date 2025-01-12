// Library 클래스 - CRC 카드 기반 설계 적용
class Library {
    private List<Book> books = new ArrayList<>();

    // Responsibility: 책 추가
    public void addBook(Book book) {
        books.add(book);
    }

    // Responsibility: 책 검색
    public Book findBook(String title) {
        return books.stream()
                .filter(book -> book.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    // Responsibility: 책 대출
    public boolean checkoutBook(String title) {
        Book book = findBook(title);
        if (book != null) {
            return book.checkout();
        }
        return false;
    }

    // Responsibility: 책 반납
    public boolean returnBook(String title) {
        Book book = findBook(title);
        if (book != null && book.isCheckedOut()) {
            book.returnBook();
            return true;
        }
        return false;
    }
}