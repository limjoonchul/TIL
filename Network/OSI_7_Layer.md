# OSI  7 Layer
## OSI란
* Open System Interconnectiond의 약자이고 번역하면 개방형 시스템 상호연결을 말한다.
## OSI 7 계층이란
* 개방형 시스템의 상호 연결모델의 표준을 말한다.
* 서로 다른 컴퓨터 간의 정보 교환을 원활히 하기 위해 국제 표준화 기구인 ISO에서 제정했다.
* 네트워크에서 통신이 일어나는 과정을 7단계로 나눈 것이고, 네트워크로 연결된 기기들이 올바른 정보를 주고받기 위한 규격이다 라고 생각하면 될 것 같다.
* 각 계층마다 헤더를 붙여서 캡슐화를 진행하게 되고, 수신측에서는 물리계층부터 역으로 올라가며 헤더를 떼어내게 한다.(디캡슐레이션)
## 그렇다면 OSI를 7 계층으로 나눈 이유는 무엇일까?
1. 통신이 일어나는 과정을 계층별로 나누었기 때문에 계층별로 파악할 수 있다.
2. 네트워크 통신 중에 발생하는 문제에 대해서 어떤 계층의 문제인지 파악하기 쉽고 
해당 계층만 해결하면 되니 다른 계층을 건드리지 않아도 된다.(그래서 각 계층은 독립적 이다.)

![OSI 7 Layer](../Java/documents/images/OSI%207%20Layer.jpg)

## 각 계층별 기능 및 특징
### 1계층 - 물리 계층(Physical Layer)
* 주로 전기적, 기계적, 기능적인 특성을 이용해 통신 케이블로 비트 스트림 형태(끊임없이 연결되는 비트열)의 데이터를 전송하는 계층이다.
* 단지 전기적 신호로 변환(0과 1)해서 데이터를 전달할 뿐 전송하는 데이터가 어떤 것인지, 어떤 오류가 있는지에 대한 것을 모른다.
* 전송 단위는 bit이다.
* 프로토콜은 이더넷, RS-232C, 모뎀 등이 있다.
* 장비는 통신 케이블, 리피터, 허브 등이 있다.

### 2계층 - 데이터링크 계층(DataLink Layer)
* 물리적인 네트워크 사이에 Data 전송을 담당하는 계층이다.
* 기능 - 에러 검출, 재전송 흐름제어
* 물리적인 연결을 통하여 인접한 장치간에 신뢰성 있는 정보전송을 담당한다.
* 전송 단위는 frame이다.
* 프로토콜은 mac 주소, PPP, 무선랜이 있다.
* 장비는 브릿지, 스위치 등이 있다.

### 3계층 - 네트워크 계층(Network Layer)
* 전송 데이터를 목적지까지 경로를 찾아 가장 안전하고 빠르게 전송하는 계층이다.
* 패킷이 최종 목적지까지 가는 경로를 선택하고 주소를 정하고 통신량을 제어한다.
  * 목적지로 가는 가장 빠른 길을 계산하여 목적지에 빠르게 갈 수 있게 돕는다.
* 연결 방식에는 동적 연결, 정적 연결이 존재한다.
  * 동적 연결은 빠른 길을 탐색하는 것이고, 정적 연결은 지정된 정적경로를 통하여 네트워크 계층에서 전송되는 데이터의 단위 패킷을 전송한다.
* 기능 - 주소부여, 경로 설정
* 전송 단위는 packet이다.
* 프로토콜은 IP, ICMP, IGMP, RIP, IPX, DDP가 있다.
* 장비는 라우터, 2계층의 스위치에 라우팅 기능을 장착한 Layer 3 스위치 IP주소를 사용한다.

### 4계층 - 전송 계층(Transport Layer)
* 데이터를 전송하고 속도를 조절하며 오류가 발생된 부분을 다시 맞춰준다.
* 서비스를 구분하고, 전송 방식을 결정하며 제어 기능을 통해 신뢰성을 보장하는 계층
* 수신자가 네트워크를 통하여 전송하고자 하는 전체 메세지를 최종 목적지까지 안전하고 신뢰성 있게 전달한다.
* 보통 TCP 프로토콜을 주로 사용하게 되고, 데이터를 전송 받은 경우, 전송 계층에서 데이터를 합산하여 세션 계층으로 보내주게 된다. 
* 전송 단위 segmnet이다.
* 프로토콜은 TCP, UDP, ARP, RTP, SCTP, SPX 등이 있다.

### 5계층 - 세션 계층(Session Layer)
* 네트워크상 양쪽 연결을 관리하고 연결을 지속시켜준다.
* 데이터가 통신하기 위한 논리적인 연결을 하는 계층이다.
* 통신장치간 상호작용 및 동기화를 제공한다(연결을 관리한다.). 
* 연결세션에서 데이터교환과 에러발생시 복구를 관리한다.
* 프로토콜은 SSH, TLS, ISO8327 APPLE TALK NETBIOS등이 있다.

### 6계층 - 표현계층(Presentation Layer)
* 응용 계층으로 전달받거나 전송하는 데이터의 인코딩 및 디코딩, 암호화가 이루어진다.
* 코드 간의 번역을 담당하여 사용자 시스템에서 데이터의 형식상 차이를 다루는 부담을 응용 계층으로부터 덜어 준다. 
  MIME 인코딩이나 암호화 등의 동작이 이 계층에서 이루어진다.
* JPEG, MPEG, TIFF, GIF 등 다양한 포멧을 구분하게 된다.

### 7계층 - 응용계층(Application Layer)
* 사용자 또는 어플리케이션이 네트워크에 접근할 수 있게 한다.
* 사용자와 가장 인접한 계층으로 인터페이스 역할을 한다.
* 응용프로세스간의 정보교환을 담당한다 예로는 전자메일 인터넷 접속 동영상플레이등의 어플리케이션이 있다.
* 프로토콜은 HTTP, FTP, DNS, DHCP, SMTP, NFS, RTSP 등이 있다.

참고사이트 : [http://blog.naver.com/PostView.nhn?blogId=pst8627&logNo=221670903384](http://blog.naver.com/PostView.nhn?blogId=pst8627&logNo=221670903384)
참고사이트 : [https://shlee0882.tistory.com/110](https://shlee0882.tistory.com/110)