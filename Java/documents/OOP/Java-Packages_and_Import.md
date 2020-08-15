# Java 2-3
## 패키지(package)
* Java에서 소스 코드를 관리하는 방식
* 물리적으로 디렉토리로 구분된 파일을 .으로 계층적으로 구분
* 패키지 이름을 짓는 규칙: package 소속.프로젝트.용도 
ex) package com.google.dev.utils ex) package com.fastcampus.catcare.service
## 임포트(imports)
* 다른 패키지에 선언된 클래스를 사용하기 위한 키워드
* 자동으로 임포트되는 경우
  1. java. lang. * - import를하지않아도 자동으로 되어있음 예를들어 string
  2. auto import 
* 패키지에 속한 모든 클래스를 import (하위 패키지는 포함하지 않음)
````groovy
import com.example.project.utils.*;
````
* 패키지에 속한 특정 클래스를 import(권장 방법)
````groovy
import com.fastcampus.dogcare.service.WebAPI;
import java.io.InputStream;
````
* 클래스의 이름이 겹치는 경우, 패키지명을 포함하여 사용(권장 방법)
````java
import java.util.List;

public class Foo {
    public static void main(String args[]) {
        List list = new List();
        java.awt.List list2 = new java.awt.List();
    }
}
````
* static 멤버는 static import하여 클래스를 생략하고 사용 가능(권장 x)
````java
import static java.lang.Math.random;
import static java.lang.System.out;

public class StaticImport {
    public static void main(String args[]) {
        out.println(random());
    }
}
````