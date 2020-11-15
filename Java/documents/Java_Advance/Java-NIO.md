# NIO(New Input Output)
## NIO 의 특징
 * 채널을 이용해서 입/출력을 모두 처리한다.(동시에 처리하는 것은 안된다)
 * IO와 달리 기본적으로 버퍼를 사용한다.
 * NIO는 Blocking 및 Nonblocking을 모두 지원한다.
    * Non-blocking은 비동기식으로 읽기/쓰기를 시켜놓고 다른 작업을 진행 가능하다(멀티 쓰레드)
## 경로와 파일
* java.nio.file.path
  * Path::get() 메소드
     * public static Path get(String first, Stirng ... more)
     * public static Path get(URI uri)
     ```groovy
       Path path = Paths.get("1.txt");
       Path path1 = Paths.get("USERS","jeyoung","1.txt");
       Path path2 = Paths.get(new URI("file:///users/jeyoung/1.txt")); 
       // file도 프로토콜이다? 로컬에 접근하는 프로토콜이다 그래서 네트워크는 아니다. 네트워크에서 사용불가 ftp를 사용한다       
     ```
  * Path의 주요 메소드
  
  | 메소드 |	설명 |
  | ---- | ---- |
  | getFileName() |	파일 또는 마지막 디렉토리의 이름을 갖는 Path를 반환 |
  | getNameCount()|	루트 경로 이후의 경로의 개수를 반환 | 
  | getName()| 특정 인덱스의 경로의 이름을 반환 |
  | subpath() |	beginIndex부터 endIndex로 구성된 Path를 반환 |
  | getParent() | 상위 Path 객체를 반환 |
  | getRoot() |	최상위 Path 객체를 반환 |
  | toAbsolutePath() | 상대경로의 Path를 절대경로의 Path로 반환 |
  | normalize()	| 표준화된 경로 표현으로 변환하여 Path로 반환 |
  | toFile() |	Path를 File 객체로 반환 |
  | toUri()	| URI 객체로 반환 | 
  
* java.nio.files.Files
  * Files의 주요 정적 메소드
  
  | 메소드| 설명 |
  | ---- | ---- |
  | newInputStream() | InputStream을 반환 |
  | newOutputStream() | OutputStream을 반환 |
  | newBufferedReader() | BufferedReader를 반환 |
  | newBufferedWriter() | Buffered Writer를 반환 |
  | newDirectoryStream() | DirectoryStream을 반환 |
  | newByteChannel() | SeekableByteChannel을 반환 |
  | createFile() | 빈 파일 생성 |
  | createDirectory() |	빈 디렉토리 생성 |
  | delete() |	디렉토리 또는 파일을 삭제 | 
  | copy() | source를 target으로 복사 |
  | move() | source를 target으로 이동 |
  | readAllLines() | 모든 행을 읽어서 List<String>으로 반환 |
  
## Buffers
* Direct Buffer vs Non-Direct Buffer

| Direct Buffer | Non-Direct Buffer |
| ------------- | ------------------ |
| OS 메모리를 직접 사용 | JVM 메모리(힙 메모리) |
| OS와의 통신에 비해 버퍼 생성이 느림 | 빠름 |
| 사용 가능 크기가 큼 | JVM에 의해서 제한된 메모리만 사용 가능 |
| 입출력 성능이 좋음 | 입출력 성능이 떨어짐 |
  * 자주쓰는건 DirectBuffer를 사용하는게 좋다.
  
* 버퍼의 생성
  * 정적 메소드 allocate()를 이용한 버퍼의 생성
    *  모든 타입의 non-direct buffer 생성
  * 정적 메소드 wrap()을 이용한 버퍼의 생성
    * 이미 생성된 배열을 이용하여 non-direct buffer 버퍼 객체 생성
  * 정적 메소드 allocateDirect()를 이용한 direct buffer 생성
    * ByteBuffer 정적 메소드를 이용하여 ByteBuffer 생성
    * 생성된 Direct buffer를 asShortBuffer(), asIntBuffer() ... 등을 이용해 버퍼 변환
* 버퍼의 사용

  | 속성 | 설명 |
  | ---- | ---- |
  | capacity | 버퍼의 크기 |
  | limit | 버퍼에 있는 데이터의 한계 (flip으로 설정) |
  | position | 버퍼에서 현재 커서의 위치 |
  | mark | reset()으로 돌아오기 위해 표시한 위치 |

  * 0 <= mark <= position <= limit <= capacity
  
  | 메소드 | 설명 |
  | ----- | ---- |
  | flip() | limit=position, position=0 |
  | mark() | mark=position |
  | reset()	| position=mark |
  | rewind() |	position=0, mark 삭제 |
  | clear() |	position=0, 모든 mark 삭제, limit=capacity |
  | get() |상대/절대 위치에서 버퍼 읽기 |
  | getP() |	P자료형으로 버퍼 읽기 |
  | put() |	상대/절대 위치에서 버퍼 쓰기 |
  | putP() |	P자료형으로 버퍼 쓰기 |

* 버퍼의 변환
  * ByteBuffer로의 수동 변환
  ```groovy
    int [] src = {423, 525, 236, 523};
    IntBuffer iBuff = IntBuffer.wrap(src);
    ByteBuffer bBuff = ByteBuffer.allocate(iBuff.capacity() * 4));
    for (int i = 0; i < iBuff.capacity(); i++) {
      bBuff.putInt(iBuff.get(i));
    }
    
    // Use bBuff ...
    
    bBuffer.rewind();
    IntBuffer iBuffer2 = bBuffer.asIntBuffer();
    int [] target = new int[iBUffer2.capacity()];
    iBuffer2.get(target);
    System.out.println(Arrays.toString(target));
   ```

  * java.nio.charset.Charset

   | 메소드 | 설명 |
   | ----- | ---- |
   | forName() | charsetName을 입력받아 Charset 객체 생성 |
   | encode() | String 또는 CharBuffer를 ByteBuffer로 변환하여 반환 |
   | decode() | ByteBuffer를 CharBuffer로 변환하여 반환 |
   ```groovy
    String str = "문자열 데이터";
    Charset charset = Charset.forName("utf-8");
    ByteBuffer bBuff = charset.encode(str);
    CharBuffer cBuff = charset.decode(bBuff);
    System.out.println(new String(cBuff.array()));
   ``` 
   
    
* Channels
  * java.nio.channel.FileChannel

  | 메소드 |	설명 |
  | ----- | ---- |
  | open() | FileChannel 객체 생성 |
  | close() | FileChannel의 리소스를 반환 |
  | read() | ByteBuffer에 FileChannel에서 읽은 내용을 저장 |
  | write() | ByteBuffer의 내용을 FileChannel을 이용해 출력 |
  
* StandardOpenOption

| 열거형 상수 | 설명 |
| --------- | ---- |
| READ | 읽기용으로 파일 오픈 |
| WRITE	| 쓰기용으로 파일 오픈 |
| CREATE | 파일이 없으면 새로운 파일 생성, 있으면 기존 파일 사용 |
| CREATE_NEW | 파일이 없으면 새로운 파일 생성, 있으면 FileAlreadyExistsException 예외 발생 |
| APPEND | 파일 뒤에 내용 추가 |
| DELETE_ON_CLOSE | 채널을 닫을 때 파일 삭제 |

* flip() - 현재 포지션을 limit으로 세팅하고 포지션을 0으로 돌리는 역할을 함. 읽은 포지션을 limit으로 기억함.(어느 부분까지 적혀있는지, 사이즈를 말하는 듯)
* channel2.write(readBuffer) 그래서 포지션 0부터 limit까지 쓰게 된다.
* read()하면 포지션은 0 로되고 limit은 capacity랑 같은 사이즈가 되서 원래 사용하지 않은것처럼 돌아간다.
  
```groovy
Path src = Paths.get("1.txt");
Path dst = Paths.get("2.txt");

try (FileChannel channel1 = FileChannel.open(src, StandardOpenOption.READ);
    FileChannel channel2 = FileChannel.open(dst, StandardOpenOption.WRITE, StandardOpenOption.CREATE)){ //with resourece로 하는게 좋음 create 하면 새로만듬 create_new는 없으니 새로만든다는 의미 파일이 기존에 있으면 에러남
    
    int read = -1;
    ByteBuffer readBuffer = ByteBuffer.allocate((int) channel1.size());
    // allocate -> allocateDirect(보통사용함)
    // 버퍼 크기를 변경하여 최적화 가능. 버퍼크기가 너무 크면 느려진다. 적절한게 좋음.
    read = channel1.read(readBuffer); // 버퍼를 읽어줌
    if(read == -1){ // 읽은게 하나도 없다면
        throw new IOException();
    }
    
    readBuffer.flip();
    channel2.write(readBuffer); //
    readBuffer.clear();
    

} catch (IOException e) {
      e.printStackTrace();
}
```

* 버퍼가 작은 경우
```groovy
src = Paths.get("1.txt");
dst = Paths.get("3.txt");

try (FileChannel channel1 = FileChannel.open(src, StandardOpenOption.READ);
     FileChannel channel2 = FileChannel.open(dst, StandardOpenOption.WRITE, StandardOpenOption.CREATE)){ //with resourece로 하는게 좋음 create 하면 새로만듬 create_new는 없으니 새로만든다는 의미 파일이 기존에 있으면 에러남

    int read = -1;
    ByteBuffer readBuffer = ByteBuffer.allocate(8);

    // 채널에서 읽어올때  순서대로 읽어올수 있게 기억하고 있음 readbuffer크기만큼 가져오고 나머지는 대기하고있음
    while ((read = channel1.read(readBuffer)) != -1){

        readBuffer.flip();
        channel2.write(readBuffer);
        readBuffer.clear(); // 버퍼를 다시 쓸 수 있게 초기화
    }
    
} catch (IOException e) {
    e.printStackTrace();
}
```