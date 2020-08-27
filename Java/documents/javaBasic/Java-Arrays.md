# Java 2-3
# 배열(Arrays)
## 배열의 특성
* 자료구조의 일종으로 가장 기본적이면서도 단순한 구조고 많이 쓰는 구조이다.
* 자료들을 모아두는 방식이다. 동일한 자료형의 순차적 자료 구조이다.
* 배열은 크기가 고정되어 있음. 배열의 크기를 바꾸고 싶을 때는 새로운 배열을 만들어 복사해서 사용해야 한다.
* 장점 첨자연산이 빠르다.
* 배열은 물리적인 위치와 논리적인 위치가 순서가 동일하다.(순차적,연속적)
* 배열을 만들면 메모리를 보면 첫인덱스의 시작 주소부터 자료형의 크기만큼 딱 딱 그 주소가 떨어져 있음.
* 동일하지 않는 것도 있다 (LinkedList - 개수에 제한이 덜하다.)
* 배열에 속한 값은 메모리에 연속으로 위치한다.**

## 배열의 생성
 * 배열의 선언 (메모리를 잡고 있지 않은 상태)
 * 객체 배열 객체들이 연속된 자료형으로 있을 때 기본 자료형 배열과 참조 자료형 배열의 차이
 * 객체 배열은 선언만했을 때 안에 null값을 갖는데 나중에 생성할 객체의 주소를 담는 공간이다.
 ````groovy
 int[] intArray;// 자료형[] 변수명; recommended
 int integerArray[];// 자료형 변수명[]; old c-style
 ````
 * 배열의 생성/초기화(메모리를 잡고 있는 상태)
   * 생성 후 값 할당
  ````groovy
   int[] intArray = new int[10];
   intArray[0] = 4;
   intArray[1] = 10;
   
   int[] intArray;
   intArray = new int[]{4, 5, 1, 2, 5}; // 길이를 입력하지 않아도 된다.
   ````
   * 생성과 동시에 값 할당
   ````groovy
   int[] intArray = {3, 5, 1, 20, 65};
   int[] intArray2 = new int[]{4, 6, 2, 3, 4};
   ````

 * 배열의 각 요소들을 초기화 해주지 않으면 자료형의 기본값으로 초기화 된다
 ```groovy
   int[] arr = new int[10];
   for(int i : arr){
         System.out.println(i);
   }

   double[] dArr = new double[5];
   dArr[0] = 1.1;
   dArr[1] = 2.1;
   dArr[2] = 3.1;

   double total = 1;
   for (int i=0; i<dArr.length; i++){
        total *= dArr[i];
   }
   System.out.println(total); // 0이나온다
   //이유 인덱스 0,1,2까지만 값을 넣어주고 나머지 3,4는 넣어주지 않았을 때
   //자동으로 빈값은 0.0으로 초기화 됨. 그래서 곱해도 0이 나옴.
 ```
## 배열의 반복문
 * 인덱스를 이용한 배열 접근
 ````groovy
 float[] floatArray = new float[10];
 for (int i = 0; i < floatArray.length; i++) {
   floatArray[i] = i * 0.5;
 }
 ````
 * 향상된 for문을 이용한 배열 접근 (Enhancded for, for each문)
 ````groovy
 int[] intArray = {4, 5, 1, 2, 7, 5};
 for (int value: intArray) {// 배열의 길이만큼 알아서 포문을 돌면서 하나씩 값을 받아옴. 0~ 길이까지
   System.out.println(value);
 }
 ````

## 배열의 크기 변경
 * 배열의 크기는 변경할 수 없으므로 새로운 배열을 만들고 데이터를 옮겨야 한다.
 ````groovy
int[] src = {1, 2, 3, 4, 5};
int[] dst = new int[10];
for(int i = 0; i < source.length; i++) {
  dst[i] = src[i];
}
 ````
 ````groovy
int[] src = {1, 2, 3, 4, 5};
int[] dst = new int[10];
System.arraycopy(src, 0, dst, 0, src.length);
 ````
### arraycopy() 
* System의 static method 한 배열에 있던 값들을 다른 배열의 값에 넣을 때 사용하는 메소드.
* System.arraycopy(기존배열,기존배열에서 옮길 값의 시작 인덱스, 옮길배열, 옮길배열의 시작 인덱스, 몇개를 옮길 것인지);
````groovy
 int[] arr1 = {10,20,30,40,50};
 int[] arr2 = {1,2,3,4,5,};
 System.arraycopy(arr1,0,arr2,1,3);

 for (int i=0; i<arr2.length; i++){
     System.out.println(arr2[i]);
 }
````



