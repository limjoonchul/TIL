# Java 2-4
# 다차원 배열(N-D Arrays)
## 다차원 배열의 정의
 * 배열을 담고 있는 배열을 다차원 배열이라고 한다.
 * 수학의 선 -> 면 -> 공간 등과 동일한 개념
## 다차원 배열의 생성
 * 다차원 배열의 선언 및 초기화
 ````groovy
 int [][] ints;
 int [] halfCStyle[]; //hybrid 안좋음
 int oldCStlye[][]; // lod... 안좋음
  
 int [][] ints1 = new int[10][10]; // 앞에 있는 숫자가 더 큰 차원의 수 길이가 5인 인테저어레이를 담고 있는 10인 인테저어레이
  
 int [][] ints2 = new int[10][];
 for (int i =0; i< ints2.length; i++){
      ints2[i] = new int[5];
 }
  
 int [][] ints4 = {{1,2,3},{4,5,6}}; // [2][3]
 int [][] ints5 = {{1,3,2},{1,2},{4,5,3}}; //[3][] 두개짜리는 실제 길이가 더 짧은 것 비어있는게 아니다.
 ````
##  배열의 반복문
 ````groovy
 for (int i=0; i<ints5.length; i++){ //과제에 이용하면 역순 가능.
     System.out.printf("[%d]", ints5[i].length);
     for (int j=0; j<ints5[i].length; j++){
         System.out.printf("%d ",ints5[i][j]);
     }
     System.out.println("");
 }
  
 for (int [] array : ints5){ //for each 문 사용
      for (int val: array){
           System.out.printf("%d ", val);
      }
      System.out.println("");
 }
 ````
## ArrayList 클래스
 * 자바에서 제공되는 객체 배열이 구현된 클래스
 * 객체 배열을 사용하는데 필요한 여러 메소드들이 구현되어 있음
 
 * 배열의 길이와 ArrayList 사이즈의 차이
 ````groovy
    ArrayList<String> list = new ArrayList<String>();
    list.add("aaa");
    list.add("bbb");
    list.add("ccc");
 
    // 총갯수는 size로 꺼낼수 있는데 배열은 length로 꺼낼 수 있는데
    // size와 length의 차이점
    // 배열은 길이가 10개고 요소가 3개만 들어있어도 length는 10이지만,
    // size는 요소가 들어가 있는 개수만큼이다.
    for (int i =0; i<list.size(); i++){
         System.out.println(list.get(i));
    }
 
    for (String s : list){
         System.out.println(s);
    }
 ````

## 얕은 복사 / 깊은 복사

### 얕은 복사
* 얕은 복사 인스턴스가 새로 생성되서 복사하는게 아니라 주소만 복사하는 것
* 두개의 객체배열이 똑같은 주소를 가지고 있기 때문에 하나만 변경되도 같이 변경된다.
* 가르키고 있는 주소의 값들을 변경하는 것이기 때문에.
````groovy
        Book[] libarary = new Book[5];
        Book[] copyLibrary = new Book[5];

        libarary[0] = new Book("태백산맥1","조정래");
        libarary[1] = new Book("태백산맥2","조정래");
        libarary[2] = new Book("태백산맥3","조정래");
        libarary[3] = new Book("태백산맥4","조정래");
        libarary[4] = new Book("태백산맥5","조정래");

        System.arraycopy(libarary,0,copyLibrary,0,libarary.length);
  
        libarary[0].setTitle("나목");
        libarary[0].setAuthor("박완서");
        for (Book book : libarary){
            book.showBookInfo();
        }

        System.out.println("=================");

        for (Book book : copyLibrary){
            book.showBookInfo();
        }
````

### 깊은 복사
* 깊은 복사 새로운 객체를 생성해줘서 값을 넣어주는 것.
````groovy
   copyLibrary[0] = new Book();
   copyLibrary[1] = new Book();
   copyLibrary[2] = new Book();
   copyLibrary[3] = new Book();
   copyLibrary[4] = new Book();
   for (int i =0; i<libarary.length; i++){
       copyLibrary[i].setAuthor(libarary[i].getAuthor());
       copyLibrary[i].setTitle(libarary[i].getTitle());
   }
   System.out.println("++++++++++++++++++");
   libarary[0].setTitle("나목");
   libarary[0].setAuthor("박완서");
   for (Book book : libarary){
       book.showBookInfo();
   }

   System.out.println("=================");

   for (Book book : copyLibrary){
      book.showBookInfo();
   }
````