# Java 2-1
## 조건문(Conditional Statements)
### 조건문의 구성
 * 조건식 (Conditional Expression), 실행문 (Execution Statement)
### 다양한 조건문
#### if 계열 조건문
 * if문
 ````
  if(조건식)
    // 실행문
  
  if(조건식) {
    // 실행문
  }
 ````
 * if~else 문
 ````
 if(조건식) {
   // 조건식이 true일때 실행할 실행문
 } else {
   // 조건식이 false일때 실행할 실행문
 }
 ````
 * if~else if~else문
 ````
 if(조건식1) {
   // 조건식1이 true일때 실행할 실행문
 } else if(조건식2) {
   // 조건식1이 false이고, 조건식2가 true일때 실행할 실행문
 } else {
   // 모든 조건식이 false일 때 실행할 실행문
 }
 ````
 * Nested(둥지) if문 = 중첩 if문
 ````
if (score >=90 ){
   System.out.println("A");
}else if(score >=80){
   System.out.println("B");
}else if(score >=70){
   System.out.println("C");
}else if(score >=60){
   System.out.println("D");
}else{
   System.out.println("F");
}
 ````
#### switch ~ case
 * switch ~ case는 좀 다름 조건문은 '값'이 들어오게된다. boolean에 한정되 않습니다.
 * case범위가 될 수 없고, case도 값이어야 합니다.
 * break가없으면 다른 케이스로 계속 넘어감, fall-through가 발생함.
 * fall-through를 의도하지 않았다면 주석을 달아줘야한다.
 ````
  grade = 'F';
  switch (grade){
      case 'A':
          System.out.println("훌륭");
          break;
 
      case 'B':
           System.out.println("멋집니다");
           break;
      case 'C':
           System.out.println("노력하세요");
           break;
  //  case 'C':
  //  case 'D':
  //       System.out.println("많이 노력하세요");
  //       break; //이렇게도 사용가능.
      case 'D':
           System.out.println("많이 노력하세요");
           break;
      default: //***무조건 마지막에 실행된다.***
           System.out.println("다음엔 잘하세요");
  }
 ```` 


