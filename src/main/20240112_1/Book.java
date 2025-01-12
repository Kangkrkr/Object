// Book 클래스 - 협력 대상
class Book {
    private String title;
    private boolean isCheckedOut;

    public Book(String title) {
        this.title = title;
        this.isCheckedOut = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public boolean checkout() {
        if (!isCheckedOut) {
            isCheckedOut = true;
            return true;
        }
        return false;
    }

    public void returnBook() {
        isCheckedOut = false;
    }
}