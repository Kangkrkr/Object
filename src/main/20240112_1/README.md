# CRC 카드란 ?
> CRC 카드(Class-Responsibility-Collaboration)는 객체지향 설계에서   
> 클래스에 할당된 책임(Responsibility)과 해당 책임을 수행하기 위해   
> 협력해야 하는 다른 클래스와의 관계(Collaboration)를 표현합니다.   
> 이는 클래스를 설계할 때 역할과 책임을 명확히 정의하는 데 도움을 줍니다.

# 시각적인 CRC 카드의 예

+-------------------------------+   
 **Class**: Library               
+-------------------------------+   

 **Responsibilities:**            
 - 책을 추가한다 (addBook).         
 - 책을 검색한다 (findBook).        
 - 책을 대출/반납 (checkoutBook, returnBook)

+-------------------------------+   

 **Collaborations:**              
 - 협력 클래스: Book               
   - 책의 상태를 확인 및 변경.     
   - 책 데이터를 저장.             
   
+-------------------------------+   

이를 기반으로 구현한 클래스는 프로젝트 내 Book, Library 클래스와 같음.

``` java
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
```

```java
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
```

### CRC 카드 기반 설계 주요 정리
1. **책임 분리**:
    - `Library`: 책 전체를 관리하는 역할 (책 목록 관리, 검색, 대출/반납).
    - `Book`: 책의 엔티티 모델 (개별 상태 관리).

2. **협력 관계**:
    - `Library`는 `Book` 객체에 접근해 책의 상태를 확인하거나 빌리고 반납하는 작업을 처리하며, 해당 작업은 `Book`의 책임에 위임합니다.

3. **유연성**:
    - 설계가 명확하기 때문에 새로운 기능 추가(예: 책의 대출 기록 관리, 연체료 계산 등)가 쉽게 구현 가능합니다.
---

### 결론
CRC 카드를 통해 `Library` 클래스의 역할과 협력을 정의한 후, 이를 코드에 반영함으로써 객체 간 **책임을 명료화**하고 **협력 구조를 효율적으로 설계**할 수 있었습니다.
이 방법은 객체지향 설계의 기본 원칙을 잘 따르며, 유지보수성과 확장성 높은 코드를 작성하는 데 큰 도움이 됩니다

---

# 책임 할당

- 책임을 수행하는 데 필요한 정보를 가장 잘 알고 있는 "전문가"에게 그 책임을 할당   
  => 이를 "전문가 패턴"이라고 한다.
- 객체가 책임을 수행하게 하는 유일한 방법은 "메시지를 전송"라는 것이므로,   
  책임을 할당한다는 것은 "메시지"의 이름을 결정하는 것과 같다.
- 즉, "메시지"를 수행하기 위해서는, "수행"을 위한 "정보"를 가장 잘 알고 있는 녀석에게   
  "책임 할당"을 하여야 한다.
- 이는, "정보 정문가"에게 책임을 할당 하는 것만으로도 "상태"와 "행동"을 함께 가지는   
  자율적인 객체를 만들 가능성이 높아지기 때문이다.
- 이 방식이 바로 협력을 설계하는 "책임 주도 설계"의 방법이다.

# 책임 주도 설계

> 1. 시스템이 사용자에게 제공해야 하는 기능인 "시스템 책임"을 파악한다.
> 2. 시스템 책임을 더 작은 책임으로 분할한다.
> 3. 분할된 책임을 수행할 수 있는 적절한 객체 또는 역할을 찾아 책임을 할당한다.
> 4. 객체가 책임을 수행하는 도중, 다른 객체의 도움이 필요한 경우 이를 책임질 적절한 객체 또는 역할을 찾는다.
> 5. 해당 객체 또는 역할에게 책임을 할당함으로써 두 객체가 협력하게 한다.

- "책임 주도 설계"는 자연스럽게 객체의 구현이 아닌 "책임"에 집중할 수 있게 한다.

### 책임을 할당할 때 고려해야 하는 두가지 요소

> 1. 메시지가 객체를 결정한다.
- 객체에게 책임을 할당하려면 ? -> 필요한 메시지 식별 -> 메시지를 처리할 객체를 선택
  - ### 왜 이렇게 해야되는데 ?
  - 객체가 협력을 위한 "최소한의" 인터페이스를 가질 수 있게 된다.
  - 객체는 충분히 "추상적인" 인터페이스를 가질 수 있게 된다.   
    즉, "무엇"을 하는지는 표현해야 하지만, "어떻게" 수행하는지를 노출해서는 안된다.

> 2. 행동이 상태를 결정한다.
- 객체는 협력에 필요한 행동을 제공해야 한다. (인터페이스)
- "협력"이 객체의 행동을 결정하고, "행동"이 객체의 "상태"를 결정한다.
- 그리고 그 "행동"이 객체의 "책임"이 된다는 것이다.

---

# 역할

> 1. 추상화의 개념일 수 있다.
> 2. 다양한 객체 또는 책임에 대한 "슬롯"의 역할을 한다.
> 3. 역할은 다른 것으로 교체할 수 있는 책임의 "집합" 이다.
> 4. 역할을 사용하면, 유연하고 재사용 가능한 협력이 구성된다.