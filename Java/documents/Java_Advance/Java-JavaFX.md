# JavaFX

## 자바 UI 변천사

### AWT(Abstract Window Toolkit)
* 운영체제가 제공하는 네이티브 UI컴포넌트를 이용
* 운영체제에 따라 UI의 모양이 서로 달랐고, 종류도 제한적이다.
### Swing
* 모든 운영체제상에서 동일한 UI를 갖도록 자바 자신만의 UI를 가짐
* 사용자는 애니메이션이 추가된 더 이뻐진 운영 체제의 네이티브 UI를 더 선호하게되었다.
   * 네이티브 UI로 보여지도록 자신의 UI를 다시 페이팅
   * 실행 성능이 느려지고 메모리를 더 많이 사용
### JavaFx
* 데스크탑 뿐만 아니라 임베디드까지 적용할 수 있도록 가볍고 풍부한 UI제공
* 레이아웃, 스타일, 애플리케이션 로직을 분리 개발
* 자바7 업데이트6버전부터 JavaFX2.2를 JDK와 JRE에 포함
* 리아를 만들기 위한(Rich Internet Application) API로 개발이 되었다.

* 리치 인터넷 애플리케이션(Rich Internet Application; RIA)은 웹 애플리케이션의 장점은 
유지하면서 기존 웹 브라우저 기반 인터페이스의 단점인 늦은 응답 속도, 
데스크톱 애플리케이션에 비해 떨어지는 조작성 등을 개선하기 위한 기술의 통칭이다. 
  * 즉, 별도의 설치가 필요 없는 웹 브라우저 기반의 애플리케이션 배포 장점과 서버 측 웹 서비스와의
  연동, 마크업 언어 기반의 선언적 애플리케이션 구성 등은 유지하면 서 데스크톱 애플리케이션과
  대등한 사용자 경험을 주는 것을 목표로 하는 기술이다.

### JavaFX 애플리케이션 구성 요소
* 레이아웃 - 자바 코드 파일 또는 FXML 파일
* 외관 및 스타일 - CSS 파일
* 비즈니스 로직 - 자바 코드 파일
* 리소스 - 그림 파일, 동영상

* 메인 클래스는 Application 클래스를 반드시 상속해서 작성을 해야한다.
그런다음 start() 메소드를 재정의해야한다 
start()는 ui를 생성하고 윈도우창을 실행시켜주는 역할을 한다.
코드에서 직접 호출할 수 없고 메인메소드에서 Application이 갖는 정적메소드인 launch를 호출해서
start를 호출하게 해야 한다,
  * 이유는 javafx Application은 ui를 실행하는 별도의 스레드가 있다. 그래서 메인을 실행시켜주는
  메인 스레드 이외의 ui생성용 스레드를 가지고 있다  이게 javafx Application 스레드
  이 스레드를 launch안에서 만든다 만들어서  javafx Application 스레드가 start를 호출할 수 있게 되어있다.

* start()는 매개변수로 Stage가 있는데 이건 윈도우창을 의미한다. 무대와 동일한 개념으로 사용했다

## javaFX  라이프 사이클

* launch()가 호출이 되면 메인클래스의 기본생성자가 호출이되면서 메인객체가 생성이된다.
메인객체가 생성이된다음에 자동으로 init()을 호출시킨다. init()은 Application 클래스가 갖고 있는 메소드이다
그럼 다음 start()메소드가 호출이 된다. 그러면 우리가 윈도우를 사용할 수 있다.
사용도중 또는 이벤트가 발생해서 윈도우를 종료해야 할 때  Platform.exit() 호출 또는 그냥 윈도우 창닫기를 하면됨
닫히면 자동으로 stop()이 호출이 된다.

* launch()가 호출이되면 자동적으로 두개의 스레드(Javafx Application Thread ,  JavaFX Launcher)가 실행이된다.
  * Javafx Application Thread - UI를 생성하고 변경을 하는 스레드/ 기본 생성자를 만들어주고 , start() 호출하고, stop()을 호출하는 역할을 한다
    이 스레드 이외에 다른 스레드가 ui를 생성하고 변경하게 되면 javafx 애플리케이션은 예외가 발생한다.
    반드시 이 안에서 ui 생성과 변경이 이루어져야한다.
   스레드가 다르기 때문에 init()에서  ui 생성과 변경을 하면 안된다!!!
  * JavaFX Launcher - init()메소드를 실행시켜주는 스레드이다. init() 만 실행!

```markdown
스레드   동작 사이클

 main /   Application.launch()
          ↓
 Javafx
 Application /   기본 생성자
 Thread
          ↓
 
 JavaFX 
 Launcher    / init()
          ↓

 JavaFX
 Application / start()
 Thread 
          ↓
 
         사용

          ↓
 Javafx      /   Platform.exit() 호출
 Application /           또는
 Thread      /   그냥 윈도우 창닫기
          ↓
 Javafx         
 Application /        stop()  
 Thread
          
          ↓
 
        종료
``` 

## 메인 클래스 실행 매개값 얻기
* 프롬포트 창에서 자바프로그램을 실행할 때 아래와같이 실행시켜야한다,
`java AppMain --ip=192.168.0.5 --port=50001`
* 이런 정보를 javafx 어플래케이션이 받아서 실행을 해야 하는데
어떻게 정보를 얻냐하면 init()를 통해서 두가지 방법으로 매개값(ip=192.168.0.5 --port=50001)을 얻는다
* Parameters params = getParameters(); // 파라미터라는 객체를 얻어서
* List<String> list = params.getRaw() // 1번 getRow는 --ip=192.168.0.5,--port=50001 이거 하나하나를 Row로 본다.
* Map<String,String> map = params.getNamed(); // 2번 getNamed는 ip를 key로 보고 192.168.0.5는 값으로 봐서 Entry를 만들어서 저장한다.
   * 2번이 더 사용하기 편함

* 결국 init()은 클래스를 실행할 때 준 실행 매개값을 받아서 javafx application이 이용할 수 있게 해주는 것이다.

* 클래스를 시작하면서 처음 매개값을 줄 때 main의 args에 값이 들어가서 launch(args)를 실행할 때 들어간다.
들어간 args는 Application 클래스의 Parameters라는 객체에 저장된다.
그런후에 init()가 Application클래스 안에 있는 Parameters라는 객체를 얻어서 ip와 port 번호를 얻는다,


## 무대와 장면
* 무대는 하나의 장면을 가질 수 있다.(윈도우 창)
*  장면은 javafx.scene.Scene으로 표현한다. (윈도우창안에 나오는 내용)
* 윈도우에 내용을 넣고 싶으면 Scene 객체를 만들어서 Stage에 추가하면 된다.

```markdown
Scene scene = new Scene(Parent root);
primaryStage.setScene(scene);
primaryStage.show();
```
* 장면에 실제 내용을 가지고 있는 부분이 Parent이다 parent는 나중에 컨테이커 클래스로 만든다
내용의 젤 위에있는 객체라고해서 루트 컨테이너라고 부른다.  장면을 만들 때 매개값으로 전달된다.

* 다른 장면을 보여주고싶다고 하면  primaryStage.setScene(otherScene); 하고 다른 씬을 넣어주면 된다.
그런다음 show()를하면 윈도우창을보주게 된다.

