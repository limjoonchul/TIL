# Java 2-2 
## 반복문(Loops)
### 반복문의 일반적인 구성
 * 초기화 (Initialization)
 * 조건식 (Conditional Expression)
 * 실행문 (Execution Statement)
 * 증감식 (Increment and Decrement Statement)
### 다양한 반복문
#### for 문
````groovy
for(초기화; 조건식; 증감식) {
  // 실행문
}
````
#### while 문
* while 문의 경우 실행문이 한번도 실행되지 않을 수도 있다.
````groovy
// 초기화
while(조건식) {
  // 실행문
  // 증감식
}
````
#### do ~ while 문
* do ~ while 문의 경우 실행문은 무조건 한 번 이상 실행된다.
````groovy
//초기화
do {
  //실행문
  //증감식
} while(조건식)
````
### 반복문 제어
#### break 문
 * 반복문을 곧바로 종료할 때 사용.
 ````groovy
while (조건식) {
  if (종료조건) {
    break;
  }
  //실행문
  //증감식
}
 ````
#### continue 문
 * 반복문을 곧바로 다음 반복으로 건너 뛴다.
 * while 문의 경우 증감식이 실행되지 않을 수 있다.
 ````groovy
while (조건식) {
  if (제어조건) {
    //증감식
    continue;
  }
  //실행문
  //증감식
}
 ````
#### label
* 중첩 반복문에서 어떤 반복문을 제어할지 결정
````groovy
 outer: //레이블 가장 가까운걸 건너 뛰는게 아니라 레이블명이 젹혀있는 반복문 쪽으로 감 실행(break도 가능)
 for (int i =0; i<5; i++){
    for (int j=0; j<5; j++){
       if(i == 3){
          System.out.println("continue called");
          continue;
          //여기서 continue만 사용하면 j는 4번동안 반복됨 레이블을 사용하면 i쪽으로가서 제어함.
       }
       System.out.printf("%d * %d = %d\n", i, j, i*j);
    }
 }
````
