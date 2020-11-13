package com.company.p01;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * nio (new input/output)
 * nonblocking -io (x)
 *
 * io stream : 입력  노드/ 출력 노드가 구분 별도의 클래스로 구분되어있어서 적절하게 사용햇어야했다
 * nio channel : 입/출력 모두 처리 가능 이건 통합 인터페이스이다,
 *
 * - 채널을 이용해서 입/출력을 모두 처리(동시에는 안됨)
 * - 기본적으로 버퍼를 사용
 * - nio는 non-blocking을 지원 (비동기식)
 *   - 읽기/쓰기를 시켜놓고 다른 작업을 진행 가능하다. (말하자면 멀티쓰레드)
 *
 *
 */
public class Main {
    public static void main(String[] args) throws URISyntaxException {
        // path 표현 방식
//        Path path = Paths.get("1.txt");
//        Path path1 = Paths.get("USERS","jeyoung","1.txt");
//        Path path2 = Paths.get(new URI("file:///users/jeyoung/1.txt")); // file도 프로토콜이다? 로컬에 접근하는 프로토콜이다 그래서 네트워크는 아니다. 네트워크에서 사용불가 ftp를 사용한다

//        path.getName(1); // 인덱스는 차례로  user jeyong 을가져온다
//        path.getParent(); // 디렉토리 , 나머지주소를 가져옴 1.txt빼고
//        path.toAbsolutePath(); // 절대경로로 만들 수 있다.
        // get. to 시리즈를 이용해서 사용하면 된다.

        // Paths -> get 정적 메소드를 사용가능
        // Files -> createFile, createDirectory, delte, copy, move

        /**
         * Buffer : Direct buffer, non-direct buffer
         * Direct buffer : OS에 직접 요청
         *                   -> 사용가능한 크기가 큼(jvm에 의해서 제한되는 부분이 없어서 큰 메모리를 쓸 수 있다.),
         *                   생성 속도는 느리다.(os와 통신을 해야 하기 때문에),
         *                   입출력 성능은 좋다.
         *                   ByteBuffer만 생성 가능하다. 버퍼는 어레이처럼있는 메모리 구획(덩어리)을 의미한다.  nio는 bytebuffer를 사용
         *
         * non-direct buffer : jvm에 있는 힙 메모리를 그대로 사용. os까지 가지 않는다.
         *                 -> 사용 가능한 메모리는 작은편 jvm에 의해 제한되니깐,
         *                 생성 속도가 빠름 jvm 상에서 곧바로 사용하니깐,
         *                 입출력 성능은 direct buffer에 비해 떨어진다 longbuffer intbuffer
         *
         *  힙메모리에 접근하는것보다 os의 스택메모리를 가져오는게 성능이 좋다
         *  자주쓰는건 directbuffer를 사용하는게 좋다.
         */

//        ByteBuffer buff = ByteBuffer.allocateDirect(1024); // direct buffer크기를 지정해놓고 받아야하고 변하지 않는다.
//        ByteBuffer buff1 = ByteBuffer.allocate(1024); // non-direct buffer
//
//        CharBuffer cBuff = CharBuffer.allocate(1024);
//        IntBuffer iBuff = IntBuffer.allocate(1024);
//        // ByteBuffer 외 다른 버퍼는 allocateDirect 없음
//
//        DoubleBuffer dBuff = buff.asDoubleBuffer();// 다른 버퍼로 바꿀 수 있다. os처리는 바이트 기준으로 된다.

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

            /**
             * buffer 사용법
             * capacity, limit, postition, mark
             * 0 <= mark <= position <= limit <= capacity
             * 한번 읽을 때마다 포지션이 증가함
             * flip 현재 포지션을 limit으로 세팅하고 포지션을 0으로 돌리는 역할을 함. 읽은 포지션을 limit으로 기억함.(어느 부분까지 적혀있는지, 사이즈를 말하는 듯)
             * channel2.write(readBuffer) 그래서 포지션 0부터 limit까지 쓰게 된다.
             * read()하면 포지션은 0 로되고 limit은 capacity랑 같은 사이즈가 되서 원래 사용하지 않은것처럼 돌아간다.
             */

        } catch (IOException e) {
            e.printStackTrace();
        }


        // 버퍼가 작은 경우
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


    }
}
