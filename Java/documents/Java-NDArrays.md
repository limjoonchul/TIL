# Java 2-4
## 다차원 배열(N-D Arrays)
### 다차원 배열의 정의
 * 배열을 담고 있는 배열을 다차원 배열이라고 한다.
 * 수학의 선 -> 면 -> 공간 등과 동일한 개념
### 다차원 배열의 생성
 * 다차원 배열의 선언 및 초기화
 ````
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
###  배열의 반복문
 ````
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
 