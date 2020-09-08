# 자바 컬렉션 프레임워크(Java Collections Framework)
## 컬렉션 프레임워크란
* java.utils에 속한 일련의 클래스로, 자료구로를 담당
* 잘 짜여진 인터페이스를 기반으로 다양한 자료구조를 구현한다.
* 제네릭 클래스로 되어 있어, 다양한 객체를 요소로 담을 수 있다.

* 자료구조를 담당하는 API라고할 수 있음
* 인터페이스가 상속이되서 클래스에서 구현이된다. 비슷한 구조가 자료구조쪽에도 있는데 추상자료형이라는게 있다 영어로는 abstract data type
* 이걸 실제로 구현을 하면 자료구조(DS, data structure)라고 부른다.
* 추상자로형은 어떤 자료구조 같은 특정 자료형이 어떤 기능들을 가지고 잇는지 어떤 메소드들을 가지고 있는지 메소도들의 행동이 어떤지 정의하는 것이다.
* 이 것들을 실제로 코드 같은 것으로 구현을 해서 특정한 요소들까지 구현을 하게 되면 자료 구조이다.


## JCF 인터페이스
* List 인터페이스
   * Collection 인터페이스를 상속
   * 순서가 있는 데이터의 집합으로, 데이터의 중복을 허용
   * 순서가 있기때문에 순서로 데이터가 정의되어서 동일한 데어티가 여러 개 있더라도
       순서가 다르면 다른 데이터로 인식 데이터 그래서 데이터 중복을 허용한다.
   * list는 ADT에 해당하고, Collection 인터페이스를 기본으로하고 있다.
   * 구현체: ArrayList, LinkedList(Vector,Stack 잘 사용x, 1.5버전 이전에 사용)
 
   * Stack 대신에 ArrayList를 많이 씀
   Vector는 제네릭이 아니고 오브젝트를 받게 되어있다. ArrayList와 동기화 부분만 다르다.
   구현 자체는 둘다 Array로 되어있다. 제네릭이 없기 때문에 오브젝트로 되어 있다.
   내부 구현은 완전히 같은지는 모른다. 비슷한 구조이긴 하다
   
* Set 인터페이스
   * Collection 인터페이스를 상속
   * 순서가 없는 데이터의 집합으로,말그대로 집합이다. 데이터의 중복을 허용하지 않는다.
   * 순서가 없기 때문에 데이터 중복 x 데이터가 있는 상태에서 동일한 값이 들어오면
     오류가 나는게 아니고 무시를 한다. 중복되는 개체를 없애고 유니크한 개체만 남긴다. 
   * Collection 인터페이스를 기본으로하고 있다.
   * 구현체: HashSet, TreeSet
   * 구현체는 hashset 일반적인집합 treeset은 이진트리를 기반으로 하는 재밌는 특성을가진 자료구조

* List 인터페이스와 Set 인터페이스는 제네릭 클래스로되어있다. 
* 자료들을 효율적으로 다루기 위한 것이다.이 하나하나가 정수일것이냐 아니면 클래스의 인스턴스인 객체일 것이냐
* 이런 것 들을 제네릭으로 되어있으면 우리가 선택을 할 수 있다. 정수면 Integer을 사용할 수 있는 등 선택을 할 수 있다.
  
* Map 인터페이스 
   * Key-Value 쌍으로 이루어진 데이터의 집합
   * 구현체 : HashMap, TreeMap, HashTable, Properties

### Collection 인터페이스
* Collection 인터페이스의 주요 메소드

| 메소드 | 설명 |
| ------ | ---- |
| boolean add(E e) | 요소 e를 컬렉션에 추가한다.
| boolean addAll(Collection < ? extends E > c) | 다른 컬렉션 c의 모든 요소를 컬렉션에 추가한다.
| void clear() | 컬렉션의 모든 요소를 제거한다. |
| boolean contains(Object o) | 컬렉션에 요소 o가 존재하는지 확인한다. |
| boolean containsAll(Collection<?> c) | 컬렉션 c의 모든 요소가 컬렉션에 포함되는지 확인한다. |
| boolean equals(Object o) | 컬렉션 o와 같은 내용을 포함하는지 비교한다. |
| boolean isEmpty() | 컬렉션이 비어있는지 확인한다. |
| Iterator<E> iterator() | 컬렉션의 Iterator를 반환한다. |
| boolean remove(Object o) | 컬렉션에서 요소 o를 제거한다. |
| boolean removeAll(Collection<?> c) | 컬렉션 c에 속한 모든 요소를 컬렉션에서 제거한다. |
| boolean retainAll(Collection<?> c) | 컬렉션 c에 포함된 객체만 남기고 나머지 요소를 제거한다. |
| int size() | 컬렉션에 속한 요소의 개수를 반환한다. |
| T[] toArray(T[] a) | 컬렉션의 요소들을 T[] 배열로 반환한다. |

* Iterator
   * `iterator()` 로 반환되는 객체로, Collection에 저장된 요소에 접근하기 위해 사용
     
    | 메소드 | 설명 |
    | ------ | ----- |
    | boolean hasNext() | 다음 요소가 있는지 확인 |
    | E next() | 다음 요소가 있을 경우 반환 |
    | void remove() | 현재 위치의 요소를 삭제 |
    
   * Iterator의 활용
   
     ````groovy
      Iterator<String> iter = collection.iterator();
      while (iter.hasNext()) {
          String string = iter.next();
          System.out.println(string);
      }
     ````
     ```groovy
       for (String string: collection) {
           System.out.println(string);
       }
     ```
### List 인터페이스
* 순서가 있는 데이터의 집합을 다루는 인터페이스
* List는 인덱스를 이용해 요소를 구분할 수 있어, 데이터의 중복을 허용
* Collection 인터페이스를 상속받았으며, 추가 메소드가 구현되어 있다.

| 메소드 | 설명 |
| ---- | ---- |
| void add(int index, E element) | index 위치에 요소 element를 삽입한다. |
| boolean addAll(int index, Collection<? extends E> c) | index위치부터 컬렉션 c의 모든 요소를 추가 한다. |
| E get(int index) | index 위치에 저장된 요소를 반환한다. |
| int indexOf(Object o) | 객체 o가 저장된 첫번째 인덱스를 반환한다. 없을 경우 -1을 반환한다. |
| int lastIndexOf(Object o) | 객체 o가 저장된 마지막 인덱스를 반환한다. 없을 경우 -1을 반환한다. |
| ListIterator<E> listIterator() | ListIterator 객체를 반환한다. |
| E remove(int index) |	index에 위치한 객체를 제거하고 반환한다. |
| E set(int index, E element) |	index에 위치한 요소를 element로 대체한다. |
| List<E> subList(int fromIndex, int toIndex) |	fromIndex에 위치한 요소부터 toIndex의 직전에 위치한 요소까지 포함한 List를 반환한다. |

* List 인터페이스의 구현체
  ```groovy
   List<String> list = new ArrayList<>();
  list = new LinkedList<>(); // 동일 인터페이스로 다른 구현체를 사용 가능
  ```
    * `ArrayList<E>`
       * 제네릭 클래스로 구현된 자료구조
       * 배열을 기반으로 구현된 클래스로, 가장 자주 활용되며 활요도가 높다.
       * ArrayList의 생성자는 세 종류가 주어진다.
         ```groovy
          public ArrayList()
          public Arraylist(int initialCapacity)
          public ArrayList(Collection<? extends E>)
         ```
    * LinkedList<E>
      * 제네릭 클래스로 구현된 자료구조
      * 연결리스트를 기반으로 구현된 클래스로, 배열의 단점을 극복하기 위한 구현체
      * ArrayList vs. LinkedList
      
        | 구현체 | 순차 추가/수정/삭제 |	비순차 추가/수정/삭제 | 조회 |
        | ---- | ---------------- | ----------------- | ------ |
        | ArrayList | 빠름 | 느림 | 빠름 |
        | LinkedList | 느림 | 빠름 | 느림 |
    * ArrayList, LinkedList
      * Object를 요소로 가지는 List 구현체 (Java5 이전)

* List의 정렬
  * Collections 클래스의 sort() 메소드 사용
     * public static <T extends Comparable<? super T> void sort(List<T> list)
        * 객체에 선언된 public int compareTo(T t) 메소드로 비교하여 정렬
     * public static <T> void sort(List<T> list, Comparator<? super T> c)
        * Comparator 객체 c에 정의된 public int compare(T o1, T o2) 메소드로 비교하여 정렬

* ListIterator
  * listIterator()로 반환되는 객체로, 양방향 사용이 가능
  * Iterator를 상속받은 클래스
  
  | 메소드 |	설명 |
  | ----- | ---- |
  | boolean hasPrevious() |	이전 요소가 있는지 확인 |
  | E previous() | 이전 요소가 있을 경우 반환 |

### Set 인터페이스
* 순서가 없는 데이터의 집합을 다루는 인터페이스
* 중복되는 데이터를 효율적으로 제거하는 데에 쓸 수 있음
* Collection 인터페이스를 상속받아 메소드를 구현하고 있음
* Set의 구현체
  * HashSet<E>
    * Set의 대표적인 구현체로, 기본적인 기능이 구현되어 있다.
  * TreeSet<E>
    * NavigableSet<E> 인터페이스를 구현하며, 이진 탐색 트리 자료구조이다.
    * 객체를 삽입하는 동시에 정렬되는 특성상 비교가 가능해야 한다.
     ```groovy
       public TreeSet() // Comparable 구현체의 경우
       public TreeSet(Comparator<? super E> comparator) // 별도로 비교 객체 삽입
     ```
  * TreeSet의 메소드
  
  | 메소드 |	설명 |
  | ----- | ---- |
  | public E first() | 정렬된 첫 요소를 반환 |
  | public E last() | 정렬된 마지막 요소를 반환 |
  | public E lower(E e) |	e 객체의 바로 이전 객체를 반환
  | public E higher(E e) |	e 객체의 바로 다음 객체를 반환
  | public E floor(E e) |	e 또는 e 보다 이전 객체를 반환
  | public E ceiling(E e) |	e 또는 e 보다 이후 객체를 반환
  | public E pollFirst() |	정렬된 첫 요소를 제거하고 반환
  | public E pollLast() |	정렬된 마지막 요소를 제거하고 반환
  | public NavigableSet<E> descendingSet() |	내림차순으로 정렬하는 객체를 반환
  | public NavigableSet<E> headSet(E toElement, boolean inclusive) |	toElement 이전 요소로 구성된 객체 반환 |
  | public NavigableSet<E> tailSet(E fromElement, boolean inclusive) |	fromElement 이후 요소로 구성된 객체 반환 |
  | public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) |	fromElement 이후, toElement 이전 요소로 구성된 객체 반환 |

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
