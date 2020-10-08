package com.company.s11;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * I/O 와 스트림
 * - I/O -> Input/Output( 입출력을 의미한다.)
 * - Java의 I/O방식은 Node -Stream
 *    - Node : 데이터의 소스 또는 데이터의 목적지를 의미
 *    - 노드는 키보드(입력), 모니터(출력,터치스크린은 입력가능), 파일(입출력), 메모리에 적혀있는걸 읽는것(입출력둘다 해당), 데이터베이스(입출력)
 *    - Stream: 노드로부터 데이터를 주고 받는 통로(Stream API와는 연관이 없다)
 *      - 입력으로 사용되는 스트림과 출력으로 사용되는 스트림은 별개이다.
 *      - 입출력을 함께 하는 것은 채널(Channel)이라고 한다 - NIO(IO를 더 발전시킨 형태 NEW Input/Output의 약자)
 */
public class Main {

    static void copyStream(InputStream input, OutputStream output) throws IOException{
        byte[] buff = new byte[1024];
        int read = 0;
        while ((read = input.read(buff)) > 0){
            output.write(buff,0,read);
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        // 입력 스트림/리더

        // stream/reader 등을 사용하는 이유는
        // 노드(입출력 장치/파일/메모리 등)으로부터 데이터를 읽고 쓰는 기본적인 방식
        // 보통은 컴퓨터를 사용하지만, Embadded machine의 경우 stream을 사용하는 경우가 많다.
        // 그래서 Scanner가 편리하긴 하지만, Stream/Reader 동작을 이해할 필요가 있다.

        // Stream - byte 단위로 읽고 쓰는 특징 스트림에서 바이트단위로 읽고쓴다는게 뭔 의미야
        // byte 단위로 읽어서 int로 출력한다.
        // 영어 알파벳은 byte 단위로 끊어도 되지만 한글 글자는 byte단위로 끊으면 읽고 쓸 수 없다.
        // 한글로 입력하면 깨진다 한글은 2~3바이트로 표현되기 때문에 억지로 잘라서 표현할려고해서 잘려서 나온다.
//        try( InputStream input = System.in){ //autocloseable 자동으로 close 호출
//            int read = -1; //-1이나오면 더이상 읽을게 없다라고 의미하는 것이다.
//            while((read = input.read()) != -1) { //read는 1바이트를 읽어온다.
//                System.out.printf("Int: %d Char: %c\n", read, read);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 입력하면 byte 로 출력이 됨
//        try( InputStream input = System.in){
//            int read = 0;
//            byte[] bytes = new byte[512]; // 일정한 크기를 잡아놓고 크기를 통째로 사용하는 것을 버퍼라고 표현한다.
             // input에 System.in을 통해서 입력을 받으면 그걸 input.read를통해서 ()안에 bytes배열에 넣고,
             // read는 입력된 개수를 나타내는 것 같다.input.read(bytes)) 반환이 int 출력한다.
//            while((read = input.read(bytes)) != 1) {  // 읽은 개수가 0이면 읽을게 없다라는 의미이다.
//                if((char)bytes[0] == 'q'){
//                    break;
//                }
//                System.out.println(Arrays.toString(bytes));
//                System.out.println(read);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //3:32

        // stream과 유사하나, Reader는 char 단위로 읽어서 int로 반환
        // stream을 이용해서 Reader를 초기화할 수 있다.
        // char 단위로 동작하기때문에 한글도 깨지지 않고 잘 동작한다.
        // byte단위로 하면 한글은 읽어서 조합을 해줘야 한다.
        //3:46
//        try( InputStreamReader reader = new InputStreamReader(System.in)){
//            int read = -1;
//            while((read = reader.read()) != 1) {  // 읽은 개수가 0이면 읽을게 없다라는 의미이다.
//                System.out.printf("Int: %d Char: %c\n", read, read);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 스캐너가 더 진보된 나중에 나온 것 Reader는 char 기반으로 동작한다.
        // 입력하면 char 그대로 출력이 된다.
//        try( InputStreamReader reader = new InputStreamReader(System.in)){
//            int read = -1;
//            char[] charbuffer = new char[100];
//            while((read = reader.read(charbuffer)) != 1) {  // 읽은 개수가 0이면 읽을게 없다라는 의미이다.
//                System.out.println(Arrays.toString(charbuffer));
//                System.out.println(read);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // Stream의 mark/reset 기능
        // mark - 스트림이 있고 내용이 차있을 때 하나씩가져오는게 스트림의 read인데
        // 마크를 호출하게 되면 해당 인덱스에 마킹이 된다 그다음 쭉쭉 진행이되다가 reset을하면 마크를 해놓은 부분을
        // 기억해놨다가 마킹되어있는 부분으로 돌아가게 되는 기능이다.
        System.out.println(System.in.markSupported()); // mark/reset 가능?

//        try( InputStream input = System.in){ //autocloseable 자동으로 close 호출
//            int read = -1;
//            while((read = input.read()) != 'q') {
//                System.out.printf("Int: %d Char: %c\n", read, read);
//                if((char)read == 'm'){ // m은 포함되지 않는다. 다음부터 마크가 된다.
//                    input.mark(32); //몇개까지 기억을 할것이냐 32개를 기억함.
//                }
//                if ((char)read == 'r'){ // r이 입력이 되면 reset이 호출된다.
//                    input.reset();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 출력 스트림/Writer
        // 메모리를 Node로 하는 입출력
        // 입력 Node : char array,
        // 출력 Node : char array
        //4:12
//        char [] memory = "메모리 입출력 테스트 입력".toCharArray();
//        char [] cbuf = new char[4]; //버퍼는 단순해야 단순하지않으면 오히려 속도가 떨어진다.
//        int read = 0;
//        // 리소스를 두개 사용하는 방법 ;을 사용하여 나누면됨
//        try (CharArrayReader reader = new CharArrayReader(memory);
//             CharArrayWriter writer = new CharArrayWriter()){
//            while ((read = reader.read(cbuf)) > 0){
//                //writer.write(cbuf, 0,read);
//                // 입력된걸 스킵할 수 있따? 0은 스킵없이한다 , read는 길이를 나타냄// 4:18
//                // 개수를 정해줘야 한다.
//                writer.write(cbuf); //개수를 지정해주지 않으면 메모리에 남아있는게 마지막 문자뒤에 전에 문자가 붙어서 출력됨
//            }
//            System.out.println(Arrays.toString(writer.toCharArray()));
//        } catch (IOException e){
//            e.printStackTrace();
//        }

        //System.out.println(File.separator); // \ 이것과 같은 것이다.

        String filePath = "D:\\Temp\\MyTemp";// 두개씩 써줘야 \\   \ 로 인식한다 ""안이라서
        // 자바에선 이렇게 사용한다.
        String filePath1 = "D:"+ File.separator + "Temp" + File.separator + "MyTemp";
        System.out.println(filePath1);

        // path가 파일 객체로되어있는게 맘에 안들어!!
        File fileOne = new File(filePath1); // path만 표현하고 있다 파일을 가리키고 있는게 아니라.
        System.out.println(fileOne.mkdir()); // 가장 하위 폴더만 생성 상위폴더가 없으면 실패
        System.out.println(fileOne.mkdirs()); // 경로에 있는 모든 폴더를 생성한다.
        // 4:37

        File fileTwo = new File(filePath1,"file2.txt");
        fileTwo.createNewFile(); //성공 True 실패 false

        File fileThree = new File(fileOne,"file3.txt"); // fileOne이 path이기 때문에 이것도 사용가능하다.
        fileThree.createNewFile();

        File fileFour = new File(new URI("file:///d:Temp/MyTemp/file4.txt"));
        fileFour.createNewFile();
        fileFour.deleteOnExit(); // Temp 파일을 사용할 때 유용하다, 4번파일을 생성했다가 프로그램이 종료될때 바로 삭제를 한다.

        /**
         * 메인에서 예외처리를 하는것은 메인을 호출하는 곳에서 예외처리를 하는데
         * 실질적으로 예외가 처리가 안되는 상태가 된다. 실제로 처리해줘야하는데 처리를 하지 않는다.
         */

        System.out.println(fileTwo.getName()); // 파일이름출력
        System.out.println(fileTwo.getParent()); // parent에 해당하는 path 출력
        System.out.println(fileTwo.isAbsolute()); // 절대 경로를 사용 하는지?
        // 상대 경로는 파일을 실행하는 위치로부터 찾아가는 것
        // 절대 경로 어디에서 찾아가도 똑같은 경로

        System.out.println(fileTwo.getAbsolutePath()); // 절대 경로이지 표준형은 아닐 수 있다
        System.out.println(fileTwo.getCanonicalPath()); // 깔끔한 경로만 사용한다 , .. 등을 모두 배제한 표준 표현 법 사용
        // 두개의 큰 차이는 없다.앱솔루트가 지저분해 질 수 있다 5:07
        System.out.println(fileOne.isDirectory()); // 디렉토리를 가리키는지 윈도우즈 이전 도스에서 사용하던 표현 디렉토리
        System.out.println(fileTwo.isFile()); // 파일을 가리키는지

        System.out.println(Arrays.toString(fileOne.list())); // 리스트를 이용해서 그안에 잇는 파일이름들을 다 출력함 String Array로 출력
        System.out.println(Arrays.toString(fileOne.listFiles())); // 파일의 경로까지 출력됨 File 객체로 출력이 나옴

        File srcFile = new File(fileOne, "src.txt");
        // 시작하기 전에 src 파일을 하나 만들고 시작을 했다 그래서 밑에 dst파일은 여기서 새롭게 만든다.
        File dstFile = new File(fileOne, "dst.txt");

        dstFile.createNewFile();

        // InputStream, OutputStream 으로 해도 됨 다형성에 의해서
        // Stream 을 이용한 파일의 복사(byte 단위)
//        try (FileInputStream src = new FileInputStream(srcFile);
//             FileOutputStream dst = new FileOutputStream(dstFile)){
//            int read = -1;
//            while ((read = src.read()) != -1){
//                dst.write(read);
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }

//        try (FileInputStream src = new FileInputStream(srcFile);
//             FileOutputStream dst = new FileOutputStream(dstFile)){
//            int read = 0;
//            byte[] buff = new byte[4];
//            while ((read = src.read(buff)) > 0){
//                dst.write(buff,0,read);
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }

        // Reader을 이용한 파일의 복사 (char 단위)
//        try (FileReader src = new FileReader(srcFile);
//             FileWriter dst = new FileWriter(dstFile)){
//            int read = -1;
//            while ((read = src.read()) != -1){
//                dst.write(read);
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }

        // append - true로 FileWriter 를 생성하면 이어서 작성(txt, ini(설정값을 담는 파일), properties,...문자를 담고 있는 파일들만 사용)
        // binary 파일에는 잘 사용하지 않음
        // binary 파일 - 문자열로 작성된 것이 아닌, decoding이 된 상태의 파일
        // 그림파일, 동영상파일, exe 파일 ..
//        try (FileReader src = new FileReader(srcFile);
//             FileWriter dst = new FileWriter(dstFile, true)){ //
//            int read = 0;
//            char[] cuff = new char[4];
//            while ((read = src.read(cuff)) > 0){
//                dst.write(cuff,0,read);
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }

        // 보조 스트림
        // Node에 직접 연결되지 않고, 스트림에 부가적으로 사용되는 스트림
        // 보조 스트림을 연쇄적으로 생성 가능 -> Stream Chaining
        // 보조스트림을 연결해서 생성

        // InputStreamReader  ┬ byte 스트림 -> char 스트림으로 변환
        // OutputStreamWriter ┘
        // char스트림을 보조하는 스트림

        // 5:36  반응성이 중요하지 않은 경우(파일입출력, 네트워크 일부 경우(다운로드, 업로드...), 등등)
        // BufferedReader         ┬ 스트림에 버퍼링을 적용하여 스트림 throughput 형성
        // BuuferedWriter         ┤ throughput : 평균 전송 속도
        // BufferedInputStream    ┤ delay : 버퍼링을 쓸 경우 오히려 안좋아짐 반응성
        // BuuferedOutputStream   ┘  서로 트레이드오프 관계에있다.
        // 파일에 입출력을 하게 될 경우 파일에 넣어서 작성을 할 때 매번 하드디스크에 접속해서하는 것보다
        // 메모리에 입력을 해놓은다음에 사용하는게 효율적이다.??


        File src = new File("C:/Windows/explorer.exe");
        File dst = new File("D:/Temp/MyTemp/explorer.exe");

        try (FileInputStream in = new FileInputStream(src);
             FileOutputStream out = new FileOutputStream(dst);
             BufferedInputStream buffIn = new BufferedInputStream(in); //보조스트림이여서 메인스트림을 넣어서 만들어줘야한다
             BufferedOutputStream buffOut = new BufferedOutputStream(out)){
            long start = System.nanoTime();
            copyStream(in, out);
            System.out.println(System.nanoTime() - start);


            start = System.nanoTime();
            copyStream(buffIn, buffOut);
            System.out.println(System.nanoTime() - start);
            // 버퍼를 사용하면 throughput이 향상된다.
        }catch (IOException e){
            e.printStackTrace();
        }

        // DataInputStream   기본형 데이터을 전송하기 위한 스트림
        // DataOutputStream  Stream, Reader(Writer)는 byte/char로 고정되어있다.
                            // readBoolean, readByte,  readShort.. readUTF(String)
                            // writeBoolean, writeByte, writeShort.. writeUTF(String)

        // 윈도우는 /로 그냥 사용해도 상관없음
//        File src1 = new File("D:/Temp/MyTemp/data.dat");
//        // 5 :52
//        DataOutputStream out = new DataOutputStream(new FileOutputStream(src)); // 보조스트림이어서 메인스트림을 만들어서 사용해야한다.
//        out.writeUTF("자바왕");
//        out.writeInt(128);
//        out.writeFloat(523.411f);
//
//        DataInputStream in = new DataInputStream(new FileInputStream(src));
//        String  str = in.readUTF(); // 읽어올 때는 위에 쓴 순서대로 읽어와야한다 그렇지않으면 디코딩이 제대로 이뤄지지 않음
//        int integer = in.readInt();
//        float floatVal = in.readFloat();
//
//        System.out.println(str +" "+ integer + " "+floatVal);

        // 객체 직렬화를 위한 인터페이스 - Serializable
        // 객체 직렬화란 객체는 결국 힙영역에 내용이 써져있는 것이다. (객체의실체)
        // 다만 이게 어떤 클래스인지 알고 있기 때문에 여기에 적혀 있는 내용이 어떤내용인가
        // 즉 어떤 변수이고 이런것들을 알 수 있다.
        // 객체 내용중에서 관심있는 내용들 출력을 이용해서 노드에 입력을 해줄 것인데
        // 시리얼라이즈는 전체 내용을 입력하는게 아니라 관심있는 내용을 일자로 쭉 펴주는 것이다 --------- 이렇게
        // 객체를 직렬로 만들어 저장하는것을 시리얼라이제이션,
        // 반대로 힙에다가 풀어서 써줘야 한다. 풀어서쓰는것을 객체로 만들어주는 것을 디시리얼라이제이션
        // 시리얼라이즈 할 수 있는 객체를 만든다.
        // has-a 관걔에 있는 모든 객체도 시리얼라이즈해야만 가능하다.

       class Foo implements Serializable{
            static final long serialVersionUID = 1L; // 클래스 버전 관리
           // FOO라고 만든 객체가 있을 때 겍체를 저장할 때 FOO의 내용이 바뀌어서
           // 유저 아이디가 추가될 때 버전이 2L로 올라가는 자체적으로 버전관리를 할 수 있는 변수이다.
           // 객체를 저장하라 때와 불러올 대 같은지 체크하여 serialVersionUID가 일치하지 않으면 실패
           // 예를들어 변수의 순서가 바껴도 문제가 될 수있다 유저아이디와 유저네임의 순서가 바뀔 대도
           // 코드의 변경이 있으면 UID를 변경시켜서 관리해줘야한다.

           String userName;
           int userID;
           transient  String password; //패스워드는 저장되는걸 막아서 null 로출력
           // Serialize에 포함하지 않음. (저장/불러오기 대상에서 제외되는 방식)

           public Foo() {}
           public Foo(String userName, int userID, String password) {
               this.userName = userName;
               this.userID = userID;
               this.password = password;
           }

           @Override
           public String toString(){
               return userName + " " + userID + " " + password;
           }

        }

        Foo foo = new Foo("Hansol-The-OutSider",
                1423,"negazeilalnaga");
        System.out.println(foo);
        File dst1 = new File("C:/Temp/MyTemp/obj.data.txt");
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dst1));
           ObjectInputStream in = new ObjectInputStream(new FileInputStream(dst1))){
           out.writeObject(foo); //
           Object read = in.readObject(); //return 타입이 object
            if(read != null && read instanceof  Foo){ //Foo객체가 맞는지 안전하게 확인한다.
                Foo readFoo = (Foo)read;
                System.out.println(readFoo);
            }
        }catch (ClassNotFoundException e){

        }
        //3:43

        // 부모클래스는 Serializable하지 않을 때,
        // 자식클래스를 Serializable하게 구현하기
        class ParentFoo{
            int memVarOne;
            double memVarTwo;
        }// 부모는 serialzie되어이짔지 않기 대문에 outputstream에서 알아서 제외된다
        // 그래서 직접 해줘야함
        class ChildFoo extends ParentFoo implements Serializable{
            int childMember;


            private void writeObject(ObjectOutputStream out) throws  IOException{
                out.writeInt(memVarOne);//부모 클래스를 시리얼라이즈하기 위해 써주는 것
//                out.writeDouble(memVarTwo);
                //  transient 대신 그냥 원하는 것만 쓰면 안쓴건 제외시킨다
                out.defaultWriteObject(); //시리얼라이즈되어있는 자식클래스를
                //3:52

            }

            private void readObject(ObjectInputStream in) throws  IOException,ClassNotFoundException{
                memVarOne = in.readInt();
//                memVarTwo = in.readDouble();
                in.defaultReadObject();
                // writer와 read순서 지켜줘야한다.
            }
        }


    }
}
