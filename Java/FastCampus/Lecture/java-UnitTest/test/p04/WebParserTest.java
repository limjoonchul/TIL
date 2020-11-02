package com.company.p04;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class WebParserTest {
    // 이렇게 테스트하면 문제점
    // 1. Fast에 맞지 않는다 느리다. 구글에 요청을 보내서 받아오는데 http 반응 속도가 느려서 그런 것이다. 인터넷 연결이 순간 안될 수도 있다.
    // 2. 웹페이지 내용이 변할 수 있다. (기대값이 변할 수 있다.)
//    @Test
//    public void countImageFromGoogleDotCom() throws IOException {
//        WebParser parser = new WebParser();
//        int actualResult =parser.countImageFromWebPage("http://google.com");
//
//        assertThat(actualResult, equalTo(5));
//    }

    private WebParser parser;
    @Before
    public void setUpUsingPageWithThreeImages(){
        // DI를 이용해 Http 객체의 stub을 구현하여 넣어준다. 실제로 웹파서를 이용할 때 httpimpl을 만들어서 넣어주면 된다.
        parser = new WebParser((targetUrl) -> {
            return "<html><meta content=a.png> <meta content=b.png> <meta content=c.png> </html>"; // 테스트하고자하는 상황을 더 넣으면 더의미가 있어짐
        }); // 이미지가 3개 나온다는걸 설정을 해줬다. 테스트가 많아질수록 의미가 있어진다.
    }

    @Test
    public void countImageFromThreeImagePageStub() throws IOException {
        int actualResult = parser.countImageFromWebPage("http://google.com");
        assertThat(actualResult, is(equalTo(3)));
    }
}